package com.zitego.markup.html.tag;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.tag.CommentTag;
import com.zitego.markup.html.StyleDeclaration;
import com.zitego.markup.html.javascript.Statement;
import com.zitego.markup.html.javascript.JavascriptBody;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;

/**
 * This class represents an html comment tag. A comment tag must have
 * a parent tag. A comment tag has no attributes, just comment text. An
 * html comment tag is special in the sense that if the parent is a
 * Script tag or a Style tag, then the comment will be parsed for the
 * appropriate content. Ex:
 * <pre>
 * &lt;script language="Javascript"&gt;
 *  &lt;-- //Comment out for older browsers...
 *  function bla()
 *  {
 *     ...
 *  }
 *  --&gt;
 * &lt;/script&gt;
 * </pre>
 *
 * @author John Glorioso
 * @version $Id: HtmlCommentTag.java,v 1.2 2008/05/12 18:13:55 jglorioso Exp $
 * @see com.zitego.markup.tag.HtmlMarkupTag#addComment(String)
 */
public class HtmlCommentTag extends CommentTag
{
    /**
     * Creates a new html comment tag with no parent.
     */
    public HtmlCommentTag()
    {
        super();
    }

    /**
     * Creates a new html comment tag with a parent.
     *
     * @param parent The parent tag.
     */
    public HtmlCommentTag(HtmlMarkupTag parent)
    {
        super(parent);
    }

    /**
     * Creates a new html comment tag with a statement parent and text.
     *
     * @param parent The parent javascript statement tag.
     * @param comment The comment.
     */
    public HtmlCommentTag(Statement parent, String comment)
    {
        super(parent);
        setComment(comment);
    }

    /**
     * Creates a new html comment tag with a javascript statement parent.
     *
     * @param parent The parent tag.
     */
    public HtmlCommentTag(Statement parent)
    {
        super(parent);
    }

    /**
     * Creates a new html comment tag with a parent and text.
     *
     * @param parent The parent tag.
     * @param comment The comment.
     */
    public HtmlCommentTag(HtmlMarkupTag parent, String comment)
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
        StringBuffer tmp = new StringBuffer(comment);
        MarkupContent parent = getParent();
        if (parent instanceof Style)
        {
            Style.parseForStyleDeclarations(tmp, this);
        }
        else if (parent instanceof Statement)
        {
            try
            {
                JavascriptBody.parseJs(new StringBuffer(comment), this, FormatType.HTML);
            }
            catch (UnsupportedFormatException ufe)
            {
                super.setComment(comment);
            }
        }
        else
        {
            super.setComment(comment);
        }
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
     * Returns the comment based on the format type.
     *
     * @param type The format type.
     * @return String
     */
    public String getComment(FormatType type)
    {
        MarkupContent parent = getParent();
        if (!preserveWhiteSpace() && parent instanceof Style && getBodySize() > 0)
        {
            try
            {
                //Append the padding, so the end comment tag will be spaced properly.
                return getBody().format(type)+getPadding();
            }
            catch (UnsupportedFormatException ufe)
            {
                return null;
            }
        }
        else if (parent instanceof Statement && getBodySize() > 0)
        {
            try
            {
                return getBody().format(type);
            }
            catch (UnsupportedFormatException ufe)
            {
                return null;
            }
        }
        else
        {
            return super.getComment(type);
        }
    }
}