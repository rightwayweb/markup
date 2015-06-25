package com.zitego.markup.html.tag.block;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import java.util.Hashtable;

/**
 * This class represents an html div tag. A div tag has only one
 * attribute. This attribute is align. Align can be left (default), right, and center.
 * This class must have an HtmlMarkupTag as the parent. This class is up to date as
 * of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Div.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see BlockFormatFactory#getBlockFormat(BlockFormatType, HtmlMarkupTag)
 */
public class Div extends BlockFormat
{
    /** A hashtable of the allowable alignment options. */
    protected static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("right", "1");
        _allowableAligns.put("center", "1");
        _allowableAligns.put("top", "1");
        _allowableAligns.put("bottom", "1");
    }

    /**
     * Creates a new div tag with no parent.
     */
    public Div()
    {
        super("div");
    }

    /**
     * Creates a new div tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Div(HtmlMarkupTag parent)
    {
        super("div", parent);
    }

    /**
     * Sets the alignment of the div.
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
     * Returns the alignment of the div.
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
        return BlockFormatType.DIV;
    }
}