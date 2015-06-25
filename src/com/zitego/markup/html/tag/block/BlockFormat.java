package com.zitego.markup.html.tag.block;

import com.zitego.markup.html.HtmlTextContent;
import com.zitego.markup.html.tag.*;
import com.zitego.markup.Newline;

/**
 * This class represents an html block formatting tag such as p or div. This class is
 * abstract and must be extended to implement getType.
 *
 * @author John Glorioso
 * @version $Id: BlockFormat.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see BlockFormatFactory#getBlockFormat(BlockFormatType, HtmlMarkupTag)
 */
public abstract class BlockFormat extends EventDrivenTag
{
    /**
     * Creates a new block format tag with a tag name.
     *
     * @param tagName The tag name.
     */
    protected BlockFormat(String tagName)
    {
        super(tagName);
    }

    /**
     * Creates a new block format tag with an HtmlMarkupTag parent and a tag name.
     *
     * @param tagName The tag name.
     * @param parent The parent.
     */
    protected BlockFormat(String tagName, HtmlMarkupTag parent)
    {
        super(tagName, parent);
    }

    /**
     * Adds text to the MarkupBody and returns it as an HtmlTextContent object. If the
     * last MarkupBody element was text content, then it is appended to that. Otherwise,
     * it will create a new HtmlTextContent and add that.
     *
     * @param txt The text to add.
     * @return HtmlTextContent
     * @throws IllegalArgumentException if the content is null.
     */
    public HtmlTextContent addText(String txt) throws IllegalArgumentException
    {
        return (HtmlTextContent)addBodyContent(txt);
    }

    /**
     * Returns the block format type.
     *
     * @return BlockFormatType
     */
    public abstract BlockFormatType getType();
}