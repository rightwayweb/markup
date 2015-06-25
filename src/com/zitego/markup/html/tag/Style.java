package com.zitego.markup.html.tag;

import com.zitego.markup.*;
import com.zitego.markup.tag.CommentTag;
import com.zitego.markup.html.*;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;
import com.zitego.format.*;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class represents an html style tag. A style tag must have a parent tag and
 * that parent tag must be either an HtmlMarkupTag. A style tag has two
 * attributes. These attributes are type and media. Type is fixed at text/css. This
 * class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Style.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Style extends HtmlMarkupTag
{
    /** A hashtable of the allowable media options. */
    protected static Hashtable _allowableMedia = new Hashtable();
    static
    {
        _allowableMedia.put("screen", "1");
        _allowableMedia.put("tty", "1");
        _allowableMedia.put("tv", "1");
        _allowableMedia.put("projection", "1");
        _allowableMedia.put("handheld", "1");
        _allowableMedia.put("print", "1");
        _allowableMedia.put("braille", "1");
        _allowableMedia.put("aural", "1");
        _allowableMedia.put("all", "1");
    }
    /** The style declarations. */
    private Vector _styleDeclarations = new Vector();

    public static void main(String[] args) throws Exception
    {
        com.zitego.markup.html.tag.Html html = new com.zitego.markup.html.tag.Html();
        Style style = html.getHead().getStyleTag();

        StyleDeclaration tdHeader = style.createStyleDeclaration("td", "header");
        tdHeader.setProperty("background-color", "#D4D0C8");
        tdHeader.setProperty("border-style", "solid");
        tdHeader.setProperty("border-width", "1px");
        tdHeader.setProperty("border-color", "#808080");
        tdHeader.setProperty("border-left-color", "#FFFFFF");
        tdHeader.setProperty("border-top-color", "#FFFFFF");

        StyleDeclaration tdBody = style.createStyleDeclaration("td", "body");
        tdBody.setProperty("border-style", "solid");
        tdBody.setProperty("border-width", "1px");
        tdBody.setProperty("border-color", "#FFFFFF");

        System.out.println( html.format(com.zitego.format.FormatType.HTML) );
    }

    /**
     * Creates a new Style tag with no parent.
     */
    public Style()
    {
        super("style");
        setType("text/css");
    }

    /**
     * Creates a new Style tag with an HtmlMarkupTag parent. This is automatically
     * added to the parent's body.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Style(HtmlMarkupTag parent)
    {
        super("style", parent);
        setType("text/css");
    }

    /**
     * Sets the media of the style.
     *
     * @param media The media.
     * @throws IllegalArgumentException if the media is invalid and this is set to strict.
     */
    public void setMedia(String media) throws IllegalArgumentException
    {
        if (media != null)
        {
            media = media.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableMedia.get(media) == null)
                {
                    throw new IllegalArgumentException("media must be screen, tty, tv, projection, handheld, " +
                                                       "print, braille, aural, all. " + media + " is invalid.");
                }
            }
        }
        super.setAttribute("media", media);
    }

    /**
     * Returns the media of the style.
     *
     * @return String
     */
    public String getMedia()
    {
        return getAttributeValue("media");
    }

    /**
     * Sets the type of style.
     *
     * @param type The type.
     */
    protected void setType(String type)
    {
        setAttribute("type", type);
    }

    /**
     * Returns the type of style.
     *
     * @return String
     */
    public String getType()
    {
        return getAttributeValue("type");
    }

    /**
     * Overrides setAttribute to make sure that media is valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the media is not valid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "media".equals(name) )
        {
            setMedia(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    /**
     * This cannot create text content, so it throws a IllegalMarkupException if
     * this is set to strict.
     *
     * @param txt Not used.
     * @return TextContent
     * @throws IllegalMarkupException
     */
    public TextContent createTextContent(String txt) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text content.");
        else return super.createTextContent(txt);
    }

     /**
     * This cannot create a table, so it throws an IllegalMarkupException if this is set to strict.
     *
     * @return Table
     * @throws IllegalMarkupException
     */
    public Table createTable() throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create a tables.");
        else return super.createTable();
    }

    /**
     * This cannot create an image, so it throws an IllegalMarkupException if this is set to strict.
     *
     * @param src The image src.
     * @return Img
     * @throws IllegalMarkupException
     */
    public Img createImage(String src) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create images.");
        else return super.createImage(src);
    }

    /**
     * This cannot create a text effect, so it throws an IllegalMarkupException if this is set to strict.
     *
     * @param type The text effect type.
     * @throws IllegalMarkupException
     */
    public TextEffect createTextEffect(TextEffectType type) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text effects.");
        else return super.createTextEffect(type);
    }

    /**
     * Sets all the styles within the given stylesheet.
     *
     * @param sheet The style sheet.
     */
    public void setStyles(CascadingStyleSheet sheet)
    {
        if (sheet == null) return;

        Vector d = sheet.getStyleDeclarations();
        int size = d.size();
        for (int i=0; i<size; i++)
        {
            StyleDeclaration elem = (StyleDeclaration)d.get(i);
            StyleDeclaration dec = getStyleDeclaration( elem.getTag(), elem.getClassAttributeName() );
            if (dec == null) dec = createStyleDeclaration( elem.getTag(), elem.getClassAttributeName() );
            dec.copy(elem);
        }
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
    public StyleDeclaration getStyleDeclaration(String name, String classAttr)
    {
        return getStyleDeclaration( name + (classAttr != null ? "." + classAttr : "") );
    }

    /**
     * Returns the specified StyleDeclaration.
     *
     * @param name the name of the declaration.
     */
    public StyleDeclaration getStyleDeclaration(String name)
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
     * Returns the StyleDeclarations.
     *
     * @return Vector
     */
    public Vector getStyleDeclarations()
    {
        return _styleDeclarations;
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
            d = CascadingStyleSheet.createStyleDeclaration(this, tagName, classAttr);
            addNewline();
            setStyleDeclaration(d.getFullName(), d);
        }
        return d;
    }

    protected MarkupBody createMarkupBody()
    {
        return new StyleBody(this);
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);
        //Set the style declarations
        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            if (c instanceof StyleDeclaration)
            {
                _styleDeclarations.add(c);
            }
        }
    }

    /**
     * Parses the given text for style declarations. They typically appear in the format
     * of:<br>
     * <pre>
     * style_name
     * {
     *   declaration: value;
     *   ...
     * }
     * </pre>
     *
     * @param text The text to parse.
     * @param parent The parent markup content.
     * @return StyleDeclaration[]
     */
    public static StyleDeclaration[] parseForStyleDeclarations(StringBuffer text, MarkupContent parent)
    {
        StyleDeclaration[] decs = new StyleDeclaration[0];
        if (text == null) return decs;
        Vector tmp = new Vector();
        while (text.length() > 0 && text.charAt(0) != '<' && text.indexOf("{") > -1)
        {
            StyleDeclaration dec = null;
            if (parent != null)
            {
                if (parent instanceof Style) dec = new StyleDeclaration( (Style)parent );
                else if (parent instanceof CascadingStyleSheet) dec = new StyleDeclaration( (CascadingStyleSheet)parent );
                else if (parent instanceof CommentTag) dec = new StyleDeclaration( (CommentTag)parent );
                parent.addNewline();
            }
            else
            {
                dec = new StyleDeclaration();
            }
            try
            {
                dec.parse(text, FormatType.HTML);
            }
            catch (Exception ime)
            {
                throw new RuntimeException("An error occurred parsing the style declaration:", ime);
            }
            tmp.add(dec);
            removeLeadingSpaces(text);
        }
        decs = new StyleDeclaration[tmp.size()];
        tmp.copyInto(decs);
        return decs;
    }

    private class StyleBody extends HtmlMarkupBody
    {
        /**
         * Creates a new StyleBody.
         *
         * @param parent The parent.
         */
        public StyleBody(MarkupContent content)
        {
            super(content);
        }

        public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
        {
            if (text == null) return;

            removeLeadingSpaces(text);
            if (text.length() > 0)
            {
                if (text.charAt(0) == '<')
                {
                    super.parseText(text, type);
                }
                else
                {
                    parseForStyleDeclarations( text, getParent() );
                }
            }
        }
    }
}