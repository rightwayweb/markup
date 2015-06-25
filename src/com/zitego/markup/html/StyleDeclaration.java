package com.zitego.markup.html;

import com.zitego.markup.*;
import com.zitego.markup.tag.CommentTag;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.html.tag.Style;
import com.zitego.util.TextUtils;
import java.util.Vector;

/**
 * This represents a single style within a style tag or a cascading style sheet.
 * A style consists of a tag such as TD, P, etc., an optional class attribute
 * to refer to it by, and one or more style elements. A style element consists of
 * a name and a property such as "bgcolor: #ffffff".
 *
 * @author John Glorioso
 * @version $Id: StyleDeclaration.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Style
 * @see CascadingStyleSheet
 */
public class StyleDeclaration extends MarkupContent
{
    /** An internal representation of the tag.class_attribute name. */
    private String _fullName;
    /** The tag name. */
    private String _tagName;
    /** The class attribute. */
    private String _classAttribute;

    /**
     * Creates a style declaration with no parent. This is mainly for tags with a style
     * attribute. No padding is written out and only the declaration elements are written.
     */
    public StyleDeclaration()
    {
        super();
        setHasPadding(false);
        setHasNewline(false);
    }

    /**
     * Creates a new StyleDeclaration with an html style tag parent. This is automatically added
     * to the body content of the style tag.
     *
     * @param style The style tag.
     */
    public StyleDeclaration(Style style)
    {
        super(style);
    }

    /**
     * Creates a new StyleDeclaration with a cascading style sheet parent. This is automatically added
     * to the body content of the cascading style sheet.
     *
     * @param styleSheet The cascading style sheet parent.
     */
    public StyleDeclaration(CascadingStyleSheet styleSheet)
    {
        super(styleSheet);
    }

    /**
     * Creates a new StyleDeclaration with a comment tag parent. This is automatically added
     * to the body content of the comment.
     *
     * @param comment The parent.
     */
    public StyleDeclaration(CommentTag comment)
    {
        super(comment);
    }

    /**
     * Sets the name of the tag this is for.
     *
     * @param name The tag name.
     * @throws IllegalArgumentException if the name is null.
     */
    public void setTag(String name) throws IllegalArgumentException
    {
        if (name == null) throw new IllegalArgumentException("tag name cannot be null");
        _tagName = name.toLowerCase();
        updateFullName();
    }

    /**
     * Returns the name of the tag this is for.
     *
     * @return String
     */
    public String getTag()
    {
        return _tagName;
    }

    /**
     * Sets the class attribute name.
     *
     * @param name The class attribute name.
     */
    public void setClassAttributeName(String name)
    {
        if (name != null) _classAttribute = name.toLowerCase();
        else _classAttribute = name;
        updateFullName();
    }

    /**
     * Returns the class attribute name.
     *
     * @return String
     */
    public String getClassAttributeName()
    {
        return _classAttribute;
    }

    /**
     * Updates the full name.
     */
    private void updateFullName()
    {
        _fullName = _tagName;
        if (_classAttribute != null) _fullName += "." + _classAttribute;
        setChanged();
    }

    /**
     * Returns the full name.
     *
     * @return String
     */
    public String getFullName()
    {
        return _fullName;
    }

    /**
     * Adds or sets a style declaration element property. If the name matches one already there,
     * then it will change the property value. The name and value are both case insensitive
     * and will always be converted to lowercase. If the value is null, then the element
     * will be removed.
     *
     * @param property The property name.
     * @param value The value.
     */
    public void setProperty(String property, String value) throws IllegalArgumentException
    {
        if (property == null) throw new IllegalArgumentException("property cannot be null");

        //See if we have this already, if not then add it. If so, replace it.
        StyleDeclarationElement elem = getStyleDeclarationElement(property);
        setChanged();
        if (elem == null)
        {
            if (value != null)
            {
                elem = new StyleDeclarationElement(this, property, value);
                if (getParent() == null)
                {
                    elem.setHasPadding(false);
                    elem.setHasNewline(false);
                }
            }
        }
        else
        {
            //Set it by reference if not null
            if (value != null) elem.setValue(value);
            else removeBodyContent(elem);
        }
    }

    /**
     * Returns the specified property.
     *
     * @param prop The property name.
     * @return String
     */
    public String getProperty(String prop)
    {
        StyleDeclarationElement elem = getStyleDeclarationElement(prop);
        if (elem != null) return elem.value;
        else return null;
    }

    /**
     * Removes the specified property from the style declaration.
     *
     * @param property The property to remove.
     */
    public void removeProperty(String property)
    {
        StyleDeclarationElement elem = getStyleDeclarationElement(property);
        if (elem != null) removeBodyContent(elem);
    }

    /**
     * Returns the specified StyleDeclarationElement.
     *
     * @param property the name.
     */
    protected StyleDeclarationElement getStyleDeclarationElement(String property)
    {
        if (property == null) return null;

        Vector e = getStyleDeclarationElements();
        int size = e.size();
        for (int i=0; i<size; i++)
        {
            StyleDeclarationElement elem = (StyleDeclarationElement)e.get(i);
            if ( property.equalsIgnoreCase(elem.property) ) return elem;
        }
        return null;
    }

    /**
     * Returns the StyleDeclarationElements.
     *
     * @return Vector
     */
    public Vector getStyleDeclarationElements()
    {
        //Go through the MarkupBody returning the StyleDeclarationElement objects
        Vector ret = new Vector();
        MarkupBody body = getBody();
        if (body != null)
        {
            int size = body.size();
            for (int i=0; i<size; i++)
            {
                MarkupContent c = getBodyContent(i);
                if (c instanceof StyleDeclarationElement) ret.add(c);
            }
        }
        return ret;
    }

    /**
     * Copies the given StyleDeclarationElement to this one. Properties that already exist
     * are overridden.
     *
     * @param dec The style declaration.
     */
    public void copy(StyleDeclaration dec)
    {
        if (dec == null) return;
        Vector elements = dec.getStyleDeclarationElements();
        int size = elements.size();
        for (int i=0; i<size; i++)
        {
            StyleDeclarationElement elem = (StyleDeclarationElement)elements.get(i);
            setProperty(elem.property, elem.value);
        }
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer();
            boolean preserve = preserveWhiteSpace();
            String padding = (!preserve ? getPadding() : "");
            String newline = (!preserve ? Newline.CHARACTER : "");
            if (getParent() != null)
            {
                ret.append(padding).append(_tagName);
                if (_classAttribute != null) ret.append(".").append(_classAttribute);
                if (!preserve)
                {
                    ret.append(newline)
                       .append(padding).append("{").append(newline);
                }
            }
            MarkupBody body = getBody();
            if (body != null) ret.append( body.format(type) );
            if ( hasNewline() ) ret.append(padding).append("}").append(newline);
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

        MarkupContent parent = getParent();
        boolean preserve = preserveWhiteSpace();
        StringBuffer pre = removeLeadingSpaces(text);
        if (preserve && pre.length() > 0)
        {
            TextContent content = addWhiteSpace(pre.toString(), parent);
            if (parent != null) parent.moveBodyContentToBefore(this, content);
        }

        boolean commentParent = (parent instanceof CommentTag);
        if (parent != null)
        {
            //See if we have a comment
            if (commentParent)
            {
                MarkupContent[] comments = Comment.checkForComments(text, parent, type);
                if (comments.length > 0) parent.moveBodyContentToBefore(this, comments[0]);
                for (int i=1; i<comments.length; i++)
                {
                    parent.moveBodyContentToAfter(comments[i-1], comments[i]);
                }
            }
            String tag = TextUtils.getTextUpTo(text, '{');
            if (!preserve)
            {
                //Strip out any newlines
                tag = tag.replaceAll("\r", "");
                tag = tag.replaceAll("\n", "");
            }
            if (tag != null) setTag(tag);
            //See if we have a .
            if (text.length() > 0 && text.charAt(0) == '.')
            {
                text.deleteCharAt(0);
                setClassAttributeName( TextUtils.getTextUpTo(text, new char[] {' ', '\r', '\n', '{'}) );
            }
            pre = removeLeadingSpaces(text);
            if (preserve && pre.length() > 0) addWhiteSpace(pre.toString(), this);
        }
        while (text.length() > 0 && text.charAt(0) != '}')
        {
            //See if we have a comment
            if (commentParent) Comment.checkForComments(text, this, type);
            //Remove the first char cause it is '\r','\n',or '{'
            char c = (char)0;
            pre = new StringBuffer();
            while ( text.length() > 0 && ((c=text.charAt(0)) == '\r' || c == '\n' || c == '{') )
            {
                pre.append(c);
                text.deleteCharAt(0);
            }
            if (preserve && pre.length() > 0) addWhiteSpace(pre.toString(), this);
            pre = removeLeadingSpaces(text);
            if (preserve && pre.length() > 0) addWhiteSpace(pre.toString(), this);

            if (text.length() > 0 && text.charAt(0) == '{')
            {
                text.deleteCharAt(0);
                if (preserve) addWhiteSpace("{", this);
            }
            pre = removeLeadingSpaces(text);
            if (preserve && pre.length() > 0) addWhiteSpace(pre.toString(), this);
            if (text.length() > 0)
            {
                StyleDeclarationElement elem = new StyleDeclarationElement(this);
                if (getParent() == null)
                {
                    elem.setHasPadding(false);
                    elem.setHasNewline(false);
                }
                elem.parse(text, type);
            }
            pre = removeLeadingSpaces(text);
            if (preserve && pre.length() > 0) addWhiteSpace(pre.toString(), this);
        }
        //Chop off the }
        if (text.length() > 0) text.deleteCharAt(0);
    }
}