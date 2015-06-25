package com.zitego.markup.html.tag;

import java.util.Hashtable;

/**
 * This class represents an html applet tag. An applet tag must have a parent tag and
 * that parent tag must be an HtmlMarkupTag. An applet tag has many attributes. These
 * attributes are align, alt, archive, code, codebase, hspace, name, object, title, and
 * width, height, mayscript, and vspace. This class is up to date as of W3C specification
 * version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Applet.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Applet extends EventDrivenTag
{
    /** A hashtable of the allowable alignment options. */
    protected static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("right", "1");
        _allowableAligns.put("top", "1");
        _allowableAligns.put("bottom", "1");
        _allowableAligns.put("middle", "1");
        _allowableAligns.put("baseline", "1");
        _allowableAligns.put("texttop", "1");
        _allowableAligns.put("absmiddle", "1");
        _allowableAligns.put("absbottom", "1");
    }

    /**
     * Creates a new Applet tag with no parent.
     */
    public Applet()
    {
        super("applet");
    }

    /**
     * Creates a new Applet tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Applet(HtmlMarkupTag parent)
    {
        super("applet", parent);
    }

    /**
     * Sets the alignment of the applet.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not valid and this is set to strict.
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
                    throw new IllegalArgumentException("align must be left, right, top, bottom, middle, baseline, " +
                                                       "texttop, absmiddle, or absbottom. "+align+" is invalid.");
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
     * Sets the alternate text of the applet.
     *
     * @param alt The alternate text.
     */
    public void setAlt(String alt)
    {
        setAttribute("alt", alt);
    }

    /**
     * Returns the alternate text of the applet.
     *
     * @return String
     */
    public String getAlt()
    {
        return getAttributeValue("alt");
    }

    /**
     * Sets the url of the applet's jar or zip file.
     *
     * @param archive The url.
     */
    public void setArchive(String archive)
    {
        setAttribute("archive", archive);
    }

    /**
     * Returns the archive url.
     *
     * @return String
     */
    public String getArchive()
    {
        return getAttributeValue("archive");
    }

    /**
     * Sets the url code class of the applet.
     *
     * @param code The classid.
     */
    public void setCode(String code)
    {
        setAttribute("code", code);
    }

    /**
     * Returns the url code class of the applet.
     *
     * @return String
     */
    public String getCode()
    {
        return getAttributeValue("code");
    }

    /**
     * Sets the codebase of the applet.
     *
     * @param codebase The code base.
     */
    public void setCodeBase(String codebase)
    {
        setAttribute("codebase", codebase);
    }

    /**
     * Returns the codebase of the applet.
     *
     * @return String
     */
    public String getCodeBase()
    {
        return getAttributeValue("codebase");
    }

    /**
     * Sets that this applet may call javascript functions.
     *
     * @param mayscript Whether this can call javascript.
     */
    public void setMayscript(boolean mayscript)
    {
        if (mayscript) super.setAttribute("mayscript");
        else removeAttribute("mayscript");
    }

    /**
     * Returns whether this applet may call javascript functions.
     *
     * @return boolean
     */
    public boolean getMayscript()
    {
        return (getAttribute("mayscript") != null);
    }

    /**
     * Sets the height of the applet in pixels.
     *
     * @param height The height.
     * @throws IllegalArgumentException if the height is negative and we are set to strict.
     */
    public void setHeight(int height) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (height < 0)
            {
                throw new IllegalArgumentException("height must be positive.");
            }
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
     * Returns the height of the applet.
     *
     * @return int
     */
    public int getHeight()
    {
        return getIntAttributeValue("height");
    }

    /**
     * Sets the hspace (in pixels) of the applet.
     *
     * @param hspace The hspace.
     * @throws IllegalArgumentException if the hspace is negative and we are set to strict.
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
     * Sets the object name of the applet.
     *
     * @param obj The text.
     */
    public void setObject(String obj)
    {
        setAttribute("object", obj);
    }

    /**
     * Returns the object name.
     *
     * @return String
     */
    public String getObject()
    {
        return getAttributeValue("object");
    }

    /**
     * Sets the title of the applet.
     *
     * @param title The title.
     */
    public void setTitle(String title)
    {
        setAttribute("title", title);
    }

    /**
     * Returns the title of the applet.
     *
     * @return String
     */
    public String getTitle()
    {
        return getAttributeValue("title");
    }

    /**
     * Sets the vspace (in pixels) of the applet.
     *
     * @param vspace The vspace.
     * @throws IllegalArgumentException if the vspace is negative and we are set to strict.
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
     * Sets the width of the applet in pixels.
     *
     * @param width The width.
     * @throws IllegalArgumentException if the width is negative and we are set to strict.
     */
    public void setWidth(int width) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (width < 0)
            {
                throw new IllegalArgumentException("width must be positive.");
            }
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
     * Returns the width of the applet.
     *
     * @return int
     */
    public int getWidth()
    {
        return getIntAttributeValue("width");
    }

    /**
     * Overrides setAttribute to make sure that height, hspace, vspace, and width
     * are numeric as well as making sure that mayscript remains boolean. In addition,
     * it makes sure that align is valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is height, hspace, vspace, or width
     *                               and the value is not numeric.
     * @throws IllegalArgumentException if the attribute is align and the value is not valid.
     */
    public void setAttribute(String name, String val) throws NumberFormatException, IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        try
        {
            if ( "height".equals(name) )
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
            else if ( "mayscript".equals(name) )
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