package com.zitego.markup.html.tag.block;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html span tag. A span tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Span.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see BlockFormatFactory#getBlockFormat(BlockFormatType, HtmlMarkupTag)
 */
public class Span extends BlockFormat
{
    /**
     * Creates a new span tag with no parent.
     */
    public Span()
    {
        super("span");
    }

    /**
     * Creates a new span tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Span(HtmlMarkupTag parent)
    {
        super("span", parent);
    }

    public BlockFormatType getType()
    {
        return BlockFormatType.SPAN;
    }
}