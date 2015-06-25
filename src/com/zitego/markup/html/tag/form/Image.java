package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.markup.html.tag.BasePath;
import java.util.Hashtable;

/**
 * This class represents an html form image element. An image element must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form. It has a few attributes.
 * They are src, alt, and align. This class is up to date as of W3C specification
 * version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Image.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Image extends Input implements BasePath
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
        _allowableAligns.put("texttop", "1");
        _allowableAligns.put("absmiddle", "1");
        _allowableAligns.put("baseline", "1");
        _allowableAligns.put("absbottom", "1");
    }
    /** This is set if the context path is set in the html parent is set and this img href has
      * a base domain that is different. **/
    protected String _basePath;

    /**
     * Creates a new image tag with no parent.
     */
    public Image()
    {
        super("image");
    }

    /**
     * Creates a new image tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Image(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new image element with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Image(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "image");
    }

    /**
     * Creates a new image element.
     *
     * @param form The parent form.
     */
    public Image(Form form)
    {
        this(form, form);
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
     * Sets the alignment of the image.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not left, right, middle, top, or bottom and this is strict.
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
                    throw new IllegalArgumentException("align must be left, right, top, texttop, middle, absmiddle, " +
                                                       "baseline, bottom, absbottom. "+align+" is invalid.");
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
     * Overrides setAttribute to make sure that align is set properly.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if align is not set properly.
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