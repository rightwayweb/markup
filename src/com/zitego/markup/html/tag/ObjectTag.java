package com.zitego.markup.html.tag;

import java.util.Hashtable;

/**
 * This class represents an html object tag. An object tag must have a parent tag and
 * that parent tag must be an HtmlMarkupTag. An object tag has many attributes. These
 * attributes are align, archive, border, classid, codebase, codetype, data, declare,
 * height, hspace, name, standby, type, usemap, vspace, and width. This class is up to
 * date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: ObjectTag.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class ObjectTag extends EventDrivenTag
{
    /** A hashtable of the allowable alignment options. */
    protected static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("right", "1");
        _allowableAligns.put("top", "1");
        _allowableAligns.put("bottom", "1");
    }

    /**
     * Creates a new Object tag with no parent.
     */
    public ObjectTag()
    {
        super("object");
    }

    /**
     * Creates a new Object tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public ObjectTag(HtmlMarkupTag parent)
    {
        super("object", parent);
    }

    /**
     * Sets the alignment of the object.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not left, right, top, or bottom and this is strict.
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
                    throw new IllegalArgumentException("align must be left, right, top, or bottom. "+align+" is invalid.");
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
     * Sets the space delimited list of urls to archive's for the object.
     *
     * @param archive The list of urls.
     */
    public void setArchive(String archive)
    {
        setAttribute("archive", archive);
    }

    /**
     * Returns the delimited list of archives.
     *
     * @return String
     */
    public String getArchive()
    {
        return getAttributeValue("archive");
    }

    /**
     * Sets the border size (in pixels) of the object.
     *
     * @param border The border size.
     * @throws IllegalArgumentException if the size is negative and this is strict.
     */
    public void setBorder(int border) throws IllegalArgumentException
    {
        if ( isStrict() && border < 0)
        {
            throw new IllegalArgumentException("border size must be positive.");
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
     * Sets the classid of the object.
     *
     * @param classid The classid.
     */
    public void setClassId(String classid)
    {
        setAttribute("classid", classid);
    }

    /**
     * Returns the classid of the object.
     *
     * @return String
     */
    public String getClassId()
    {
        return getAttributeValue("classid");
    }

    /**
     * Sets the codebase of the object.
     *
     * @param codebase The code base.
     */
    public void setCodeBase(String codebase)
    {
        setAttribute("codebase", codebase);
    }

    /**
     * Returns the codebase of the object.
     *
     * @return String
     */
    public String getCodeBase()
    {
        return getAttributeValue("codebase");
    }

    /**
     * Sets the codetype of the object.
     *
     * @param codetype The code type.
     */
    public void setCodeType(String codetype)
    {
        setAttribute("codetype", codetype);
    }

    /**
     * Returns the codetype of the object.
     *
     * @return String
     */
    public String getCodeType()
    {
        return getAttributeValue("codetype");
    }

    /**
     * Sets the data of the object.
     *
     * @param data The data.
     */
    public void setData(String data)
    {
        setAttribute("data", data);
    }

    /**
     * Returns the data of the object.
     *
     * @return String
     */
    public String getData()
    {
        return getAttributeValue("data");
    }

    /**
     * Sets that this object should only be declared, not instantiated.
     *
     * @param declare Whether this should only be declared.
     */
    public void setDeclare(boolean declare)
    {
        if (declare) super.setAttribute("declare");
        else removeAttribute("declare");
    }

    /**
     * Returns whether this object should only be declared.
     *
     * @return boolean
     */
    public boolean getDeclare()
    {
        return (getAttribute("declare") != null);
    }

    /**
     * Sets the height of the object in pixels.
     *
     * @param height The height.
     * @throws IllegalArgumentException if the height is negative and this is strict.
     */
    public void setHeight(int height) throws IllegalArgumentException
    {
        if ( isStrict() && height < 0)
        {
            throw new IllegalArgumentException("height must be positive.");
        }
        super.setAttribute( "height", String.valueOf(height) );
    }

    /**
     * Removes the height attribute.
     */
    public void removeHeight()
    {
        removeAttribute("height");
    }

    /**
     * Returns the height of the object.
     *
     * @return int
     */
    public int getHeight()
    {
        return getIntAttributeValue("height");
    }

    /**
     * Sets the hspace (in pixels) of the object.
     *
     * @param hspace The hspace.
     * @throws IllegalArgumentException if the hspace is negative and this is strict.
     */
    public void setHspace(int hspace) throws IllegalArgumentException
    {
        if ( isStrict() && hspace < 0)
        {
            throw new IllegalArgumentException("hspace must be positive.");
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
     * Sets the name of the object.
     *
     * @param name The name.
     */
    public void setName(String name)
    {
        setAttribute("name", name);
    }

    /**
     * Returns the name of the object.
     *
     * @return String
     */
    public String getName()
    {
        return getAttributeValue("name");
    }

    /**
     * Sets the standby text to display while the object is loading.
     *
     * @param text The text.
     */
    public void setStandby(String text)
    {
        setAttribute("standby", text);
    }

    /**
     * Returns the standby text.
     *
     * @return String
     */
    public String getStandby()
    {
        return getAttributeValue("standby");
    }

    /**
     * Sets the mime type of the object.
     *
     * @param type The type.
     */
    public void setType(String type)
    {
        setAttribute("type", type);
    }

    /**
     * Returns the mime type of the object.
     *
     * @return String
     */
    public String getType()
    {
        return getAttributeValue("type");
    }

    /**
     * Tells the object what client side map to use.
     *
     * @param map The map name.
     */
    public void setUseMap(String map)
    {
        setAttribute("usemap", map);
    }

    /**
     * Returns the client side map name for the object tag to use.
     *
     * @return String
     */
    public String getUseMap()
    {
        return getAttributeValue("usemap");
    }

    /**
     * Sets the vspace (in pixels) of the object.
     *
     * @param vspace The vspace.
     * @throws IllegalArgumentException if the vspace is negative and this is strict.
     */
    public void setVspace(int vspace) throws IllegalArgumentException
    {
        if ( isStrict() && vspace < 0)
        {
            throw new IllegalArgumentException("vspace must be positive.");
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
     * Sets the width of the object in pixels.
     *
     * @param width The width.
     * @throws IllegalArgumentException if the width is negative and this is strict.
     */
    public void setWidth(int width) throws IllegalArgumentException
    {
        if ( isStrict() && width < 0)
        {
            throw new IllegalArgumentException("width must be positive.");
        }
        super.setAttribute( "width", String.valueOf(width) );
    }

    /**
     * Removes the width attribute.
     */
    public void removeWidth()
    {
        removeAttribute("width");
    }

    /**
     * Returns the width of the object.
     *
     * @return int
     */
    public int getWidth()
    {
        return getIntAttributeValue("width");
    }

    /**
     * Overrides setAttribute to make sure that border, height, hspace, vspace, and width
     * are numeric as well as making sure that declare remains boolean. In addition,
     * it makes sure that align is valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is border, height, hspace, vspace, or width
     *                               and the value is not numeric.
     * @throws IllegalArgumentException if the attribute is align and the value is not valid.
     */
    public void setAttribute(String name, String val) throws NumberFormatException, IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        try
        {
            if ( "border".equals(name) )
            {
                setBorder( Integer.parseInt(val) );
            }
            else if ( "height".equals(name) )
            {
                setHeight( Integer.parseInt(val) );
            }
            else if ( "hspace".equals(name) )
            {
                setHspace( Integer.parseInt(val) );
            }
            else if ( "vspace".equals(name) )
            {
                setVspace( Integer.parseInt(val) );
            }
            else if ( "declare".equals(name) )
            {
                super.setAttribute(name);
            }
            else if ( "width".equals(name) )
            {
                setWidth( Integer.parseInt(val) );
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
        catch (NumberFormatException nfe)
        {
            if ( isStrict() ) throw nfe;
        }
    }
}