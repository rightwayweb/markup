package com.zitego.markup.html.tag;

import java.util.Hashtable;

/**
 * This class represents an html iframe tag. An iframe tag must have a parent tag and
 * that parent tag must be an HtmlMarkupTag. An iframe tag has many attributes. These
 * attributes are align, frameborder, height, longdesc, marginheight, marginwidth, name,
 * scrolling, src, and width. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Iframe.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Iframe extends Frame
{
    /** A hashtable of the allowable alignment options. */
    protected static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("right", "1");
        _allowableAligns.put("top", "1");
        _allowableAligns.put("middle", "1");
        _allowableAligns.put("bottom", "1");
    }
    
    /**
     * Creates a new Iframe tag with no parent.
     */
    public Iframe()
    {
        super("iframe");
    }

    /**
     * Creates a new Iframe tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent tag.
     */
    public Iframe(HtmlMarkupTag parent)
    {
        super("iframe", parent);
    }

    /**
     * Sets the alignment of the iframe.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not left, center, right, or justify and we are strict.
     */
    public void setAlign(String align) throws IllegalArgumentException
    {
        if (align != null)
        {
            align = align.toLowerCase();
            if ( isStrict() )
            {
                //Convert middle to center
                if ( align.equals("middle") ) align = "center";
                if (_allowableAligns.get(align) == null)
                {
                    throw new IllegalArgumentException("align must be left, center, right, or justify. "+align+" is invalid.");
                }
            }
        }
        super.setAttribute("align", align);
    }

    /**
     * Returns the alignment of the iframe.
     *
     * @return String
     */
    public String getAlign()
    {
        return getAttributeValue("align");
    }

    /**
     * Sets the height of the iframe. It can be an exact number of pixels or an integer percentage.
     *
     * @param height The height.
     * @throws IllegalArgumentException if the height is not valid.
     */
    public void setHeight(String height) throws IllegalArgumentException
    {
        super.setAttribute( "height", new VariableSize(height).toString() );
    }

    /**
     * Removes the height attribute.
     */
    public void removeHeight()
    {
        removeAttribute("height");
    }

    /**
     * Returns the height of the iframe.
     *
     * @return String
     */
    public String getHeight()
    {
        return getAttributeValue("height");
    }

    /**
     * Sets the width of the iframe. It can be an exact number of pixels or an integer percentage.
     *
     * @param width The width.
     * @throws IllegalArgumentException if the width is not valid.
     */
    public void setWidth(String width) throws IllegalArgumentException
    {
        super.setAttribute( "width", new VariableSize(width).toString() );
    }

    /**
     * Removes the width attribute.
     */
    public void removeWidth()
    {
        removeAttribute("width");
    }

    /**
     * Returns the width of the iframe.
     *
     * @return String
     */
    public String getWidth()
    {
        return getAttributeValue("width");
    }

    /**
     * Overrides setAttribute to make sure that width and height valid VariableSize. In
     * addition, it makes sure that align is valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the attribute is align, width, or height and
     *                                  the value is not valid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "width".equals(name) )
        {
            setWidth(val);
        }
        else if ( "height".equals(name) )
        {
            setHeight(val);
        }
        else if ( "align".equals(name) )
        {
            setAlign(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }
}