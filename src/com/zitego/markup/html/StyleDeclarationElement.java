package com.zitego.markup.html;

import com.zitego.markup.Comment;
import com.zitego.markup.TextContent;
import com.zitego.markup.MarkupContent;
import com.zitego.markup.Newline;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.TextUtils;

/**
 * A class used in conjunction with StyleDeclaration to build a style definition. The declaration
 * includes a property and value. Ex: color: #ffffff
 *
 * @author John Glorioso
 * @version $Id: StyleDeclarationElement.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
class StyleDeclarationElement extends MarkupContent
{
    String property;
    String value;
    private String _postColonWhiteSpace = "";
    private boolean _semiColon = false;
    private String _endText = "";

    /**
     * Creates a new style declaration element with a StyleDeclaration parent.
     * The element is automatically added to the parent's body content.
     *
     * @param dec The parent.
     */
    StyleDeclarationElement(StyleDeclaration dec)
    {
        super(dec);
    }

    /**
     * Creates a new style declaration element with a StyleDeclaration parent, a property,
     * and a value. The element is automatically added to the parent's body content.
     *
     * @param dec The parent.
     * @param prop The property.
     * @param value The value.
     */
    StyleDeclarationElement(StyleDeclaration dec, String prop, String value)
    {
        super(dec);
        setProperty(prop);
        setValue(value);
    }

    void setProperty(String prop)
    {
        property = prop;
        setChanged();
    }

    void setValue(String val)
    {
        value = val;
        setChanged();
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            boolean preserve = preserveWhiteSpace();
            StringBuffer ret = new StringBuffer( (!preserve ? getParent().getPadding() : "") );
            if ( !preserve && hasPadding() ) ret.append("  ");
            ret.append(property).append(":").append( (preserve ? _postColonWhiteSpace : "") );
            if (value != null) ret.append( (!preserve ? " " : "") ).append(value);
            if (_semiColon) ret.append(";");
            if ( !preserve && hasNewline() ) ret.append(Newline.CHARACTER);
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

        MarkupContent[] comments = Comment.checkForComments(text, parent, type);
        if (comments.length > 0) parent.moveBodyContentToBefore(this, comments[0]);
        for (int i=1; i<comments.length; i++)
        {
            parent.moveBodyContentToAfter(comments[i-1], comments[i]);
        }
        String prop = TextUtils.getTextUpTo(text, ':');
        setProperty( (preserve ? prop : prop.trim()) );
        if (text.length() > 0) text.deleteCharAt(0);
        pre = removeLeadingSpaces(text);
        if (preserve) _postColonWhiteSpace = pre.toString();
        char[] chars = new char[0];
        if (parent.getParent() == null) chars = new char[] {';', '\r', '\n', '"', '>'};
        else chars = new char[] {';', '\r', '\n', '}'};
        String val = TextUtils.getTextUpTo(text, chars);
        if (val != null)
        {
            if (!preserve) val = val.replaceAll("\"", "'").trim();
            setValue(val);
        }
        //See if there was a semi-colon
        if ( !preserve || (text.length() > 0 && text.charAt(0) == ';') ) _semiColon = true;
        pre = new StringBuffer();
        //Gotta remove that last char
        if (text.length() > 0 && text.charAt(0) != '>' && text.charAt(0) != '}')
        {
            pre.append( text.charAt(0) );
            text.deleteCharAt(0);
        }
        //See if the next char is a \r \n
        if ( text.length() > 0 && (text.charAt(0) == '\r' || text.charAt(0) == '\n') )
        {
            pre.append( text.charAt(0) );
            text.deleteCharAt(0);
        }
        //Check once more for safety
        if ( text.length() > 0 && (text.charAt(0) == '\r' || text.charAt(0) == '\n') )
        {
            pre.append( text.charAt(0) );
            text.deleteCharAt(0);
        }
        if (preserve && pre.length() > 0) _endText = pre.toString();
    }
}