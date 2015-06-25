package com.zitego.markup.html.tag;

import java.util.Hashtable;

/**
 * This class represents an html image tag. An image tag must have a parent tag and
 * that parent tag must be an HtmlMarkupTag. An image tag has many attributes. These
 * attributes are align, border, height, ismap, longdesc, usemap, vspace, width, alt,
 * name, and src. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Img.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see HtmlMarkupTag#createImage(String)
 */
public class Img extends EventDrivenTag implements BasePath
{
    /** A hashtable of the allowable alignment options. */
    protected static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("middle", "1");
        _allowableAligns.put("right", "1");
        _allowableAligns.put("top", "1");
        _allowableAligns.put("bottom", "1");
    }
    /** This is set if the context path is set in the html parent is set and this img href has
      * a base domain that is different. **/
    protected String _basePath;

    /**
     * Creates a new Img tag with no parent.
     */
    public Img()
    {
        super("img");
        setHasEndTag(false);
        setAddClosingSlashInStart(true);
    }

    /**
     * Creates a new Img tag with no parent and a src.
     *
     * @param src The src.
     */
    public Img(String src)
    {
        this();
        setSrc(src);
    }

    /**
     * Creates a new Img tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Img(HtmlMarkupTag parent)
    {
        super("img", parent);
        setHasEndTag(false);
        setAddClosingSlashInStart(true);
    }

    /**
     * Creates a new Img tag with an HtmlMarkupTag parent and a src.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param src The src.
     */
    public Img(HtmlMarkupTag parent, String src)
    {
        this(parent);
        setSrc(src);
    }

    /**
     * Sets the alternate text of the image.
     *
     * @param alt The alternate text.
     */
    public void setAlt(String alt)
    {
        setAttribute("alt", alt);
    }

    /**
     * Returns the alternate text of the image.
     *
     * @return String
     */
    public String getAlt()
    {
        return getAttributeValue("alt");
    }

    /**
     * Sets the alignment of the image.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not left, right, middle, top, or bottom and we are strict.
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
                    throw new IllegalArgumentException("align must be left, right, middle, top, or bottom. "+align+" is invalid.");
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
     * Sets the border size (in pixels) of the image.
     *
     * @param border The border size.
     * @throws IllegalArgumentException if the size is negative and we are strict.
     */
    public void setBorder(int border) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (border < 0)
            {
                throw new IllegalArgumentException("border size must be positive.");
            }
        }
        super.setAttribute( "border", String.valueOf(border) );
    }

    /**
     * Returns the border size in pixels. If it is not set, then it returns 0.
     *
     * @return int
     */
    public int getBorder()
    {
        return getIntAttributeValue("border");
    }

    /**
     * Sets the hspace (in pixels) of the image.
     *
     * @param hspace The hspace.
     * @throws IllegalArgumentException if the hspace is negative and we are strict.
     */
    public void setHspace(int hspace) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (hspace < 0)
            {
                throw new IllegalArgumentException("hspace must be positive.");
            }
        }
        super.setAttribute( "hspace", String.valueOf(hspace) );
    }

    /**
     * Returns the hspace in pixels.
     *
     * @return int
     */
    public int getHspace()
    {
        return getIntAttributeValue("hspace");
    }

    /**
     * Sets the vspace (in pixels) of the image.
     *
     * @param vspace The vspace.
     * @throws IllegalArgumentException if the vspace is negative and we are strict.
     */
    public void setVspace(int vspace) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (vspace < 0)
            {
                throw new IllegalArgumentException("vspace must be positive.");
            }
        }
        super.setAttribute( "vspace", String.valueOf(vspace) );
    }

    /**
     * Returns the vspace in pixels.
     *
     * @return int
     */
    public int getVspace()
    {
        return getIntAttributeValue("vspace");
    }

    /**
     * Sets the height of the image. It can be an exact number of pixels or an integer percentage.
     *
     * @param height The height.
     * @throws IllegalArgumentException if the height is not valid.
     */
    public void setHeight(String height) throws IllegalArgumentException
    {
        super.setAttribute( "height", (height != null ? new VariableSize(height, false).toString() : null) );
    }

    /**
     * Removes the height attribute.
     */
    public void removeHeight()
    {
        removeAttribute("height");
    }

    /**
     * Sets the height of the image as an exact number of pixels.
     *
     * @param height The height.
     */
    public void setHeight(int height)
    {
        setHeight( String.valueOf(height) );
    }

    /**
     * Returns the height of the image.
     *
     * @return String
     */
    public String getHeight()
    {
        return getAttributeValue("height");
    }

    /**
     * Sets the width of the image. It can be an exact number of pixels or an integer percentage.
     *
     * @param width The width.
     * @throws IllegalArgumentException if the width is not valid.
     */
    public void setWidth(String width) throws IllegalArgumentException
    {
        super.setAttribute( "width", (width != null ? new VariableSize(width).toString() : null) );
    }

    /**
     * Removes the width attribute.
     */
    public void removeWidth()
    {
        removeAttribute("width");
    }

    /**
     * Sets the width of the image as an exact number of pixels.
     *
     * @param width The width.
     */
    public void setWidth(int width)
    {
        setWidth( String.valueOf(width) );
    }

    /**
     * Returns the width of the image.
     *
     * @return String
     */
    public String getWidth()
    {
        return getAttributeValue("width");
    }

    /**
     * Sets whether this is a map image or not.
     *
     * @param map Whether this is a map.
     */
    public void setIsMap(boolean map)
    {
        if (map) super.setAttribute("ismap");
        else removeAttribute("ismap");
    }

    /**
     * Returns whether or not this is a map.
     *
     * @return boolean
     */
    public boolean getIsMap()
    {
        return (getAttribute("ismap") != null);
    }

    /**
     * Sets the longdesc of the tag.
     *
     * @param longdesc The longdesc.
     */
    public void setLongdesc(String longdesc)
    {
        setAttribute("longdesc", longdesc);
    }

    /**
     * Returns the longdesc of the tag.
     *
     * @return String
     */
    public String getLongdesc()
    {
        return getAttributeValue("longdesc");
    }

    /**
     * Tells the image what client side map to use.
     *
     * @param map The map name.
     */
    public void setUseMap(String map)
    {
        setAttribute("usemap", map);
    }

    /**
     * Returns the client side map name for the image tag to use.
     *
     * @return String
     */
    public String getUseMap()
    {
        return getAttributeValue("usemap");
    }

    /**
     * Sets the name of the image.
     *
     * @param name The name.
     */
    public void setName(String name)
    {
        setAttribute("name", name);
    }

    /**
     * Returns the name of the image.
     *
     * @return String
     */
    public String getName()
    {
        return getAttributeValue("name");
    }

    /**
     * Sets the src url of the image.
     *
     * @param src The src url.
     */
    public void setSrc(String src)
    {
        if (_basePath != null && src.indexOf(_basePath) == 0) src = src.substring(_basePath.length()+1);
        setAttribute("src", src);
    }

    /**
     * Returns the src url of the image.
     *
     * @return String
     */
    public String getSrc()
    {
        return getAttributeValue("src");
    }

    // -------------------------------- Start BasePath Interface ------------------------------ //

    public void setBasePath(String base)
    {
        _basePath = base;
    }

    public String getBasePath()
    {
        return _basePath;
    }

    public String getFullPath()
    {
        String src = getSrc();
        if (src == null)
        {
            return null;
        }
        else if (_basePath == null)
        {
            return src;
        }
        else if (src.indexOf("http://") == -1 && src.charAt(0) == '/')
        {
            return _basePath+src;
        }
        else
        {
            return src;
        }
    }

    // -------------------------------- End BasePath Interface ------------------------------ //

    /**
     * Overrides setAttribute to make sure that border, hspace, and vspace
     * are numeric as well as making sure that ismap remains boolean. In addition,
     * it makes sure that height and width are legitimate VariableSize.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is border, hspace, or vspace
     *                               and the value is not numeric.
     * @throws IllegalArgumentException if the width or height are not valid VariableSize.
     */
    public void setAttribute(String name, String val) throws NumberFormatException, IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "border".equals(name) )
        {
            try
            {
                setBorder( Integer.parseInt(val) );
            }
            catch (NumberFormatException nfe)
            {
                if ( isStrict() ) throw nfe;
            }
        }
        else if ( "hspace".equals(name) )
        {
            try
            {
                setHspace( Integer.parseInt(val) );

            }
            catch (NumberFormatException nfe)
            {
                if ( isStrict() ) throw nfe;
            }
        }
        else if ( "vspace".equals(name) )
        {
            try
            {
                setVspace( Integer.parseInt(val) );

            }
            catch (NumberFormatException nfe)
            {
                if ( isStrict() ) throw nfe;
            }
        }
        else if ( "ismap".equals(name) )
        {
            super.setAttribute(name);
        }
        else if ( "height".equals(name) )
        {
            setHeight(val);
        }
        else if ( "width".equals(name) )
        {
            setWidth(val);
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