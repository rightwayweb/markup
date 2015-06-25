package com.zitego.markup.html.tag;

import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.IllegalMarkupException;

/**
 * This class is here so that the html markup factory does not fail every time someone creates
 * an html document with an unexpected tag.
 *
 * @author John Glorioso
 * @version $Id: UnknownHtmlTag.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class UnknownHtmlTag extends HtmlMarkupTag
{
    private String _originalText;

    /**
     * Creates a new unknown html tag with a name.
     *
     * @param tag The tag name.
     */
    public UnknownHtmlTag(String tag)
    {
        super(tag);
        setHasEndTag(false);
    }

    /**
     * Creates a new unknown html tag with a name and a parent.
     *
     * @param tag The tag name.
     * @param parent The parent tag.
     */
    public UnknownHtmlTag(String tag, HtmlMarkupTag parent)
    {
        super(tag, parent);
        setHasEndTag(false);
    }

    /**
     * Returns the original text that was passed in to parse
     *
     * @return String
     */
    public String getOriginalText()
    {
        return _originalText;
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        _originalText = text.toString();
        int index = _originalText.indexOf(">");
        if (index > -1) _originalText = _originalText.substring(0, index+1);
        super.parseText(text, type);
    }
}