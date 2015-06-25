package com.zitego.markup.html.tag.block;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import java.util.Hashtable;

/**
 * This class represents an html paragraph tag. A paragraph tag has only one
 * attribute. This attribute is align. Align can be left (default), right, center,
 * and justify. This class must have an HtmlMarkupTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Paragraph.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see BlockFormatFactory#getBlockFormat(BlockFormatType, HtmlMarkupTag)
 */
public class Paragraph extends BlockFormat
{
    /** A hashtable of the allowable alignment options. */
    protected static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("right", "1");
        _allowableAligns.put("center", "1");
        _allowableAligns.put("justify", "1");
    }

    /**
     * Creates a new p tag with no parent.
     */
    public Paragraph()
    {
        super("p");
        setHasEndTag(false);
    }

    /**
     * Creates a new p tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Paragraph(HtmlMarkupTag parent)
    {
        super("p", parent);
        setHasEndTag(false);
    }

    /**
     * Sets the alignment of the paragraph.
     *
     * @param align The align option.
     * @throws IllegalArgumentException if the alignment is not left, right, or center and this is strict.
     */
    public void setAlign(String align) throws IllegalArgumentException
    {
        if (_allowableAligns.get(align) == null && isStrict() )
        {
            throw new IllegalArgumentException("align option: "+align+" is invalid.");
        }
        super.setAttribute("align", align);
    }

    /**
     * Returns the alignment of the paragraph.
     *
     * @return String
     */
    public String getAlign()
    {
        return getAttributeValue("align");
    }

    /**
     * Overrides setAttribute to make sure that align is valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the attribute is align and the value is not valid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "align".equals(name) )
        {
            setAlign(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    public BlockFormatType getType()
    {
        return BlockFormatType.PARAGRAPH;
    }
}