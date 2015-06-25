package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import java.util.Hashtable;

/**
 * This class represents an html legend tag. A legend tag must have a parent tag and
 * that parent tag must be an HtmlMarkupTag. An legend tag only one attribute. It is
 * align. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Legend.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Legend extends EventDrivenTag
{
    /** A hashtable of the allowable alignment options. */
    protected static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("center", "1");
        _allowableAligns.put("right", "1");
    }
    
    /**
     * Creates a new Legend tag with no parent.
     */
    public Legend()
    {
        super("legend");
    }

    /**
     * Creates a new Legend tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Legend(HtmlMarkupTag parent)
    {
        super("legend", parent);
    }

    /**
     * Sets the alignment of the image.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not left, right, middle, top, or bottom
     *                                  and this is strict.
     */
    public void setAlign(String align) throws IllegalArgumentException
    {
        if (align != null)
        {
            align = align.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableAligns.get(align) == null)
                {
                    throw new IllegalArgumentException("align must be left, center, or right. "+align+" is invalid.");
                }
            }
        }
        super.setAttribute("align", align);
    }

    /**
     * Returns the alignment of the tag.
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
     * @throws IllegalArgumentException if align is the attribute and it is not valid.
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
}