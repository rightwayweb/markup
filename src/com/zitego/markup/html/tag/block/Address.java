package com.zitego.markup.html.tag.block;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html address tag. An address tag has no attributes.
 * This class must have an HtmlMarkupTag as the parent. This class is up to
 * date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Address.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see BlockFormatFactory#getBlockFormat(BlockFormatType, HtmlMarkupTag)
 */
public class Address extends BlockFormat
{
    /**
     * Creates a new address tag with no parent.
     */
    public Address()
    {
        super("address");
    }

    /**
     * Creates a new address tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Address(HtmlMarkupTag parent)
    {
        super("address", parent);
    }

    public BlockFormatType getType()
    {
        return BlockFormatType.ADDRESS;
    }
}