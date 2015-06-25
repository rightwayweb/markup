package com.zitego.markup.html.tag.block;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html blockquote tag. A blockquote tag has only one
 * attribute. This attribute is cite. This class must have an HtmlMarkupTag as
 * the parent. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: BlockQuote.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see BlockFormatFactory#getBlockFormat(BlockFormatType, HtmlMarkupTag)
 */
public class BlockQuote extends BlockFormat
{
    /**
     * Creates a new blockquote tag with no parent.
     */
    public BlockQuote()
    {
        super("blockquote");
    }

    /**
     * Creates a new blockquote tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public BlockQuote(HtmlMarkupTag parent)
    {
        super("blockquote", parent);
    }

    /**
     * Sets the url reference of the quote.
     *
     * @param cite The cite.
     */
    public void setCite(String cite)
    {
        setAttribute("cite", cite);
    }

    /**
     * Returns the url reference of the quote.
     *
     * @return String
     */
    public String getCite()
    {
        return getAttributeValue("cite");
    }

    public BlockFormatType getType()
    {
        return BlockFormatType.BLOCK_QUOTE;
    }
}