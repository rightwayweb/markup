package com.zitego.markup.tag;

import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.MarkupContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.Newline;
import com.zitego.util.TextUtils;

/**
 * This class represents an markup content comment tag. A comment tag must have
 * a parent tag. A comment tag has no attributes, just comment text.
 *
 * @author John Glorioso
 * @version $Id: CommentTag.java,v 1.2 2008/05/12 18:13:01 jglorioso Exp $
 * @see com.zitego.markup.tag.MarkupTag#addComment(String)
 */
public class CommentTag extends MarkupTag
{
    /**
     * Creates a new comment tag with no parent.
     */
    public CommentTag()
    {
        super();
    }

    /**
     * Creates a new comment tag with a parent.
     *
     * @param parent The parent tag.
     */
    public CommentTag(MarkupTag parent)
    {
        super(parent);
    }

    /**
     * Creates a new comment tag with a parent and text.
     *
     * @param parent The parent content.
     * @param comment The comment.
     */
    public CommentTag(MarkupContent parent, String comment)
    {
        super(parent);
        setComment(comment);
    }

    /**
     * Creates a new comment tag with a parent.
     *
     * @param parent The parent content.
     */
    public CommentTag(MarkupContent parent)
    {
        super(parent);
    }

    /**
     * Creates a new comment tag with a parent and text.
     *
     * @param parent The parent tag.
     * @param comment The comment.
     */
    public CommentTag(MarkupTag parent, String comment)
    {
        super(parent);
        setComment(comment);
    }

    /**
     * Sets the comment.
     *
     * @param comment The comment.
     */
    public void setComment(String comment)
    {
        setAttribute("comment", comment);
    }

    /**
     * Returns the comment.
     *
     * @return String
     */
    public String getComment()
    {
        return getComment(FormatType.HTML);
    }

    /**
     * Returns the comment.
     *
     * @param type The format type to get.
     * @return String
     */
    public String getComment(FormatType comment)
    {
        return getAttributeValue("comment");
    }

    public String getTagName()
    {
        return "!--";
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            boolean preserve = preserveWhiteSpace();
            StringBuffer ret = new StringBuffer()
                .append( (!preserve ? getPadding() : "") ).append("<!--").append( (!preserve ? " " : "") )
                .append( getComment(type) ).append( (!preserve ? " " : "") ).append("-->");
            if (!isEmbeddedInLine() && !preserve) ret.append(Newline.CHARACTER);
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

        removeLeadingSpaces(text);
        //The next four characters should be "<!--". Error if not
        if ( text.length() < 4 || !text.substring(0, 4).equals("<!--") ) throw new IllegalMarkupException("Invalid comment: "+text);

        //Remove that
        text.delete(0, 4);
        boolean preserve = preserveWhiteSpace();
        if (!preserve) removeLeadingSpaces(text);

        //Set the comment
        StringBuffer comment = new StringBuffer();
        while (text.length() > 0)
        {
            char c = text.charAt(0);
            if (c == '-' && text.length() > 2 && text.charAt(1) == '-' && text.charAt(2) == '>')
            {
                //At the end here...
                setComment( (!preserve ? comment.toString().trim() : comment.toString()) );
                text.delete(0, 3);
                break;
            }
            else
            {
                comment.append(c);
                text.delete(0, 1);
            }
        }
    }

    protected void validateTagName(StringBuffer text)
    {
        //Now we should be at the tag name. Make sure it is right
        if (text == null || text.indexOf("!--") != 0) throw new IllegalMarkupException
        (
            "Expected open comment tag, but found <"+TextUtils.trunc(text.toString(), 25)
        );
    }
}