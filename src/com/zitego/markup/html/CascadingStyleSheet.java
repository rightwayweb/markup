package com.zitego.markup.html;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.MarkupBody;
import com.zitego.markup.Comment;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.html.tag.Style;
import com.zitego.util.TextUtils;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * This represents a cascading style sheet that contains one or more style declarations.
 * StyleDeclarations can be created by calling one of the two createStyleDeclaration()
 * methods. The style delcaration is automatically added to the style sheet when called
 * without a Style tag. The declaration can be retrieved through the MarkupMap or by
 * name for editing.
 *
 * @author John Glorioso
 * @version $Id: CascadingStyleSheet.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Style
 * @see CascadingStyleSheet
 */
public class CascadingStyleSheet extends MarkupContent
{
    /** The style declarations. */
    private Vector _styleDeclarations = new Vector();

    public static void main(String[] args) throws Exception
    {
        CascadingStyleSheet sheet = new CascadingStyleSheet( new URL(args[0]) );
        /*StyleDeclaration tdHeader = sheet.createStyleDeclaration("td", "header");
        tdHeader.setProperty("background-color", "#D4D0C8");
        tdHeader.setProperty("border-style", "solid");
        tdHeader.setProperty("border-width", "1px");
        tdHeader.setProperty("border-color", "#808080");
        tdHeader.setProperty("border-left-color", "#FFFFFF");
        tdHeader.setProperty("border-top-color", "#FFFFFF");

        StyleDeclaration tdBody = sheet.createStyleDeclaration("td", "body");
        tdBody.setProperty("border-style", "solid");
        tdBody.setProperty("border-width", "1px");
        tdBody.setProperty("border-color", "#FFFFFF");*/

        System.out.println( "out="+sheet.format(FormatType.CSS) );
    }

    /**
     * Creates a new CascadingStyleSheet.
     */
    public CascadingStyleSheet()
    {
        super();
    }

    /**
     * Creates a new CascadingStyleSheet based on a file path to the actual style sheet.
     *
     * @param f The file.
     * @throws IOException if an io error occurs.
     * @throws UnsupportedFormatException if an error occurs parsing the style sheet.
     */
    public CascadingStyleSheet(File f) throws IOException, UnsupportedFormatException
    {
        this( new FileInputStream(f) );
    }

    /**
     * Creates a new CascadingStyleSheet based on a url to the actual style sheet.
     *
     * @param url The url to the input stream.
     * @throws IOException if an io error occurs.
     * @throws UnsupportedFormatException if an error occurs parsing the style sheet.
     */
    public CascadingStyleSheet(URL url) throws IOException, UnsupportedFormatException
    {
        this( url.openStream() );
    }

    /**
     * Creates a new CascadingStyleSheet from an InputStream.
     *
     * @param in The input stream.
     * @throws IOException if an io error occurs.
     * @throws UnsupportedFormatException if an error occurs parsing the style sheet.
     */
    public CascadingStyleSheet(InputStream in) throws IOException, UnsupportedFormatException
    {
        super();
        BufferedReader reader = new BufferedReader( new InputStreamReader(in) );
        String line = null;
        StringBuffer ret = new StringBuffer();
        while ( (line=reader.readLine()) != null )
        {
            ret.append(line).append("\n");
        }
        parse(ret.toString(), FormatType.HTML);
    }

    /**
     * Returns the StyleDeclarations.
     *
     * @return Vector
     */
    public Vector getStyleDeclarations()
    {
        return _styleDeclarations;
    }

    /**
     * Adds or sets a style declaration property. If the name matches one already there,
     * then it will update the style. The name is case insensitive and will always be converted
     * to lowercase. The name is the full name of the style declaration including the class
     * attribute name if any. If the style declaration is null, then it will be removed.
     *
     * @param name The style declaration name.
     * @param declaration The StyleDeclaration.
     */
    public void setStyleDeclaration(String name, StyleDeclaration declaration) throws IllegalArgumentException
    {
        if (name == null) throw new IllegalArgumentException("StyleDeclarationName cannot be null");

        //See if we have this already, if not then add it. If so, replace it.
        StyleDeclaration dec = getStyleDeclaration(name);
        if (dec != null) removeBodyContent(dec);
        if (declaration != null)
        {
            declaration.setHasPadding(false);
            _styleDeclarations.add(declaration);
        }
    }

    /**
     * Removes the specified style declaration.
     *
     * @param name The style declaration to remove.
     */
    public void removeStyleDeclaration(String name)
    {
        StyleDeclaration dec = getStyleDeclaration(name);
        if (dec != null) removeBodyContent(dec);
    }

    /**
     * Returns the specified StyleDeclaration give then tag name and class attribute.
     *
     * @param name the tag name of the declaration.
     * @param classAttr The class attribute.
     * @return StyleDeclaration
     */
    protected StyleDeclaration getStyleDeclaration(String name, String classAttr)
    {
        return getStyleDeclaration( name + (classAttr != null ? "." + classAttr : "") );
    }

    /**
     * Returns the specified StyleDeclaration.
     *
     * @param name the name of the declaration.
     */
    protected StyleDeclaration getStyleDeclaration(String name)
    {
        if (name == null) return null;

        int size = _styleDeclarations.size();
        for (int i=0; i<size; i++)
        {
            StyleDeclaration d = (StyleDeclaration)_styleDeclarations.get(i);
            if ( name.equalsIgnoreCase(d.getFullName()) ) return d;
        }
        return null;
    }

    /**
     * Creates a new StyleDeclaration with the given tag name and no class attribute,
     *
     * @param tagName The style tag name.
     * @throws IllegalArgumentException if the tag name is null.
     */
    public StyleDeclaration createStyleDeclaration(String tagName) throws IllegalArgumentException
    {
        return createStyleDeclaration(tagName, null);
    }

    /**
     * Creates a new StyleDeclaration with the given tag name and the specified class
     * attribute. If the class attribute is null, then it is not set.
     *
     * @param tagName The style tag name.
     * @param classAttr The class attribute name.
     * @throws IllegalArgumentException if the tag name is null.
     */
    public StyleDeclaration createStyleDeclaration(String tagName, String classAttr) throws IllegalArgumentException
    {
        if (tagName == null) throw new IllegalArgumentException("Tag name cannot be null");

        //First see if we already have this
        StyleDeclaration d = getStyleDeclaration(tagName, classAttr);

        if (d == null)
        {
            //Add new lines if this is our second one...
            if (_styleDeclarations.size() > 0)
            {
                addNewline();
                addNewline();
            }
            d = new StyleDeclaration(this);
            d.setTag(tagName);
            if (classAttr != null) d.setClassAttributeName(classAttr);
            setStyleDeclaration(d.getFullName(), d);
        }
        return d;
    }

    /**
     * Creates a new StyleDeclaration with the given tag name and no class attribute for
     * the given Style tag. If the class attribute is null, then it is not set.
     *
     * @param style The style tag to create the attribute for.
     * @param tagName The style tag name.
     * @throws IllegalArgumentException if the Style tag or tag name is null.
     */
    public static StyleDeclaration createStyleDeclaration(Style style, String tagName) throws IllegalArgumentException
    {
        return createStyleDeclaration(style, tagName, null);
    }

    /**
     * Creates a new StyleDeclaration with the given tag name and the specified class
     * attribute for the given Style tag. If the class attribute is null, then it is not set.
     *
     * @param style The style tag to create the attribute for.
     * @param tagName The style tag name.
     * @param classAttr The class attribute name.
     * @throws IllegalArgumentException if the Style tag or tag name is null.
     */
    public static StyleDeclaration createStyleDeclaration(Style style, String tagName, String classAttr)
    throws IllegalArgumentException
    {
        if (style == null) throw new IllegalArgumentException("Style cannot be null");
        if (tagName == null) throw new IllegalArgumentException("Tag name cannot be null");

        StyleDeclaration d = new StyleDeclaration(style);
        d.setTag(tagName);
        if (classAttr != null) d.setClassAttributeName(classAttr);
        return d;
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer();

            MarkupBody body = getBody();
            if (body != null) ret.append( body.format(type) );
            return ret.toString();
        }
        else
        {
            return (String)getCachedContent(type);
        }
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;

        while (text.length() > 0)
        {
            removeLeadingSpaces(text);
            //TO DO - integrate this to print back out
            //See if we have a comment here or a style declaration. Comment will begin with either a //
            // or a /*
            if (text.length() == 0) continue;
            char c = text.charAt(0);
            if (c == '/')
            {
                Comment comment = new Comment(this);
                comment.parseText(text, type);
                removeLeadingSpaces(text);
                if (text.length() == 0) continue;
            }
            StyleDeclaration d = new StyleDeclaration(this);
            d.parseText(new StringBuffer( TextUtils.getTextUpTo(text, '}') ), type);
            _styleDeclarations.add(d);
            //Get rid of the }
            text.deleteCharAt(0);
            //TO DO - handle single line styles not in { } (low priority)
        }
    }

    public int getDeepness()
    {
        return -1;
    }
}