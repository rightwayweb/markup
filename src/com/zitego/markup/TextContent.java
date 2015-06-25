package com.zitego.markup;

import com.zitego.markup.tag.MarkupTag;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.TextUtils;
import java.util.Vector;

/**
 * This is a class to represent a plain text content component.
 *
 * @author John Glorioso
 * @version $Id: TextContent.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class TextContent extends MarkupContent
{
    /** The text. */
    protected StringBuffer _text;

    /**
     * Creates new text content with no parent.
     */
    public TextContent()
    {
        super();
        _text = new StringBuffer();
    }

    /**
     * Creates new text content with a parent.
     *
     * @param parent The parent content.
     */
    public TextContent(MarkupContent parent)
    {
        super(parent);
        _text = new StringBuffer();
    }

    /**
     * Creates new text content with some text.
     *
     * @param text The text.
     */
    public TextContent(String text)
    {
        super();
        _text = new StringBuffer();
        setText(text);
    }

    /**
     * Creates new text content with a a parent and some text.
     *
     * @param parent The parent content.
     * @param text The text.
     */
    public TextContent(MarkupContent parent, String text)
    {
        this(parent);
        setText(text);
    }

    /**
     * Sets the text.
     *
     * @param text The text.
     */
    public void setText(String text)
    {
        _text.setLength(0);
        appendText(text);
    }

    /**
     * Returns the text.
     *
     * @return String
     */
    public String getText()
    {
        return _text.toString();
    }

    /**
     * Appends text to the existing text.
     *
     * @param text The text to append.
     */
    public void appendText(String text)
    {
        if (text != null) _text.append(text);
        setChanged();
    }

    /**
     * Returns a String representation of this.
     *
     * @return String
     */
    public String toString()
    {
        boolean preserve = preserveWhiteSpace();
        String txt = _text.toString();
        MarkupContent parent = getParent();
        if ( !preserve && (parent == null || parent.trimChildText()) ) txt = txt.trim();
        StringBuffer ret = new StringBuffer( (!preserve ? getPadding() : "") ).append(txt);
        if (!preserve)
        {
            boolean noNewline = (parent instanceof MarkupTag && ((MarkupTag)parent).hasEndTagOnSameLine() && parent.indexOfInBody(this) == parent.getBodySize()-1);
            if (hasNewline() && !noNewline) ret.append(Newline.CHARACTER);
        }
        return ret.toString();
    }

    /**
     * Writes out the text. This ignores the FormatType.
     *
     * @param type Not used.
     * @return String
     * @throws UnsupportedFormatException
     */
    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer( toString() );
            if ( !preserveWhiteSpace() )
            {
                Vector breaks = getLineBreaks();
                int size = breaks.size();
                MarkupContent parent = getParent();
                boolean noNewline = (parent instanceof MarkupTag && ((MarkupTag)parent).hasEndTagOnSameLine() && parent.indexOfInBody(this) == parent.getBodySize()-1);
                for (int i=0; i<size; i++)
                {
                    MarkupContent br = (MarkupContent)breaks.get(i);
                    if ( !noNewline || !(br instanceof Newline) ) ret.append( br.format(type) );
                }
                if (size > 0 && !noNewline) ret.append(Newline.CHARACTER);
            }
            return ret.toString();
        }
        else
        {
            return (String)getCachedContent(type);
        }
    }

    /**
     * Parses the given string and sets the text until it reaches a < character.
     *
     * @param text The text.
     * @param type The format type.
     * @throws IllegalMarkupException if the text is invalid.
     * @throws UnsupportedFormatException if the format is not supported.
     */
    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        String content = TextUtils.getTextUpTo(text, '<');
        setText(content);
    }
}