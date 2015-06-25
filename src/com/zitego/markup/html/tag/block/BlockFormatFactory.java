package com.zitego.markup.html.tag.block;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class creates block formats depending on the type passed in.
 *
 * @author John Glorioso
 * @version $Id: BlockFormatFactory.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see BlockFormatType
 * @see com.zitego.markup.html.tag.HtmlMarkupTag#createBlockFormat(BlockFormatType)
 */
public class BlockFormatFactory
{
    /**
     * Returns a block format tag given the parent and the type.
     *
     * @param type The type.
     * @param parent The parent tag.
     * @return BlockFormat
     * @throws IllegalArgumentException if the block format type is not valid.
     */
    public static BlockFormat getBlockFormat(BlockFormatType type, HtmlMarkupTag parent) throws IllegalArgumentException
    {
        if (type == BlockFormatType.SPAN) return new Span(parent);
        else if (type == BlockFormatType.BLOCK_QUOTE) return new BlockQuote(parent);
        else if (type == BlockFormatType.DIV) return new Div(parent);
        else if (type == BlockFormatType.PARAGRAPH) return new Paragraph(parent);
        else if (type == BlockFormatType.ADDRESS) return new Address(parent);
        else throw new IllegalArgumentException("Invalid block format type: "+type);
    }
}