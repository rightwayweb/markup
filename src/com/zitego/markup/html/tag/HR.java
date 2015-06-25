package com.zitego.markup.html.tag;

/**
 * This class represents an html HR tag. An HR tag must have a parent tag and
 * that parent tag must be an HtmlMarkupTag. An HR tag has several attributes.
 * These attributes are align, noshade, size, and width. This class is up to date
 * as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: HR.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see HtmlMarkupTag#createHR()
 */
public class HR extends EventDrivenTag
{
    /**
     * Creates a new HR tag with no parent.
     */
    public HR()
    {
        super("hr");
        setHasEndTag(false);
    }
    
    /**
     * Creates a new HR tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent head tag.
     */
    public HR(HtmlMarkupTag parent)
    {
        super("hr", parent);
        setHasEndTag(false);
    }

    /**
     * Sets the alignment of the tag.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not left, right, or center and this is strict.
     */
    public void setAlign(String align) throws IllegalArgumentException
    {
        if (align != null)
        {
            align = align.toLowerCase();
            if ( isStrict() )
            {
                if ( !"left".equals(align) && !"center".equals(align) && !"right".equals(align) )
                {
                    throw new IllegalArgumentException("align must be left, right, or center. "+align+" is invalid.");
                }
            }
        }
        setAttribute("align", align);
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
     * Sets the whether noshade is on or off.
     *
     * @param noshade true is noshade is on.
     */
    public void setNoshade(boolean noshade)
    {
        if (noshade) super.setAttribute("noshade");
        else removeAttribute("noshade");
    }

    /**
     * Returns whether noshade is set or not.
     *
     * @return boolean
     */
    public boolean getNoshade()
    {
        return (getAttributeValue("noshade") != null);
    }

    /**
     * Sets the size of the HR. The size can either be an absolute pixel size or a percentage.
     *
     * @param size The size.
     * @throws IllegalArgumentException if the size is not valid.
     */
    public void setSize(String size) throws IllegalArgumentException
    {
        super.setAttribute( "size", (size != null ? new VariableSize(size, false).toString() : null) );
    }

    /**
     * Returns the size of the tag.
     *
     * @return String
     */
    public String getSize()
    {
        return getAttributeValue("size");
    }

    /**
     * Sets the width of the HR. The width can either be an absolute pixel size or a percentage.
     *
     * @param width The width.
     * @throws IllegalArgumentException if the width is not valid.
     */
    public void setWidth(String width) throws IllegalArgumentException
    {
        super.setAttribute( "width", (width != null ? new VariableSize(width, false).toString() : null) );
    }

    /**
     * Removes the width attribute.
     */
    public void removeWidth()
    {
        removeAttribute("width");
    }

    /**
     * Returns the width of the tag.
     *
     * @return String
     */
    public String getWidth()
    {
        return getAttributeValue("width");
    }

    /**
     * Overrides setAttribute to make sure that noshade remains boolean. In addition,
     * this makes sure that size and width are legitimate VariableSize attributes.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the attribute is size or width and the VariableSize
     *                                  is invalid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "size".equals(name) )
        {
            setSize(val);
        }
        else if ( "width".equals(name) )
        {
            setWidth(val);
        }
        else if ( "noshade".equals(name) )
        {
            super.setAttribute(name);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }
}