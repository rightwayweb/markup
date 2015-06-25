package com.zitego.markup.html.tag;

import com.zitego.markup.TextContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;

/**
 * This class represents an html frame tag. A frame tag must have a parent tag and
 * that parent tag must be a Frameset tag. A frame tag has many attributes. These
 * attributes are frameborder, longdesc, marginheight, marginwidth, name, noresize,
 * scrolling, and src. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Frame.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see FrameSet#createFrame()
 */
public class Frame extends HtmlMarkupTag implements BasePath
{
    /** This is set if the context path is set in the html parent is set and this img href has
      * a base domain that is different. **/
    protected String _basePath;

    /**
     * Creates a new Frame tag with no parent.
     */
    protected Frame()
    {
        super("frame");
    }

    /**
     * Creates a new Frame tag with a tagname (to be used for extending classes) and no parent.
     *
     * @param tagName The tagname.
     */
    protected Frame(String tagName)
    {
        super(tagName);
    }

    /**
     * Creates a new Frame tag with an HtmlMarkupTag parent and a tagname (to
     * be used for extending classes).
     *
     * @param tagName The tagname.
     * @param parent The parent tag.
     */
    protected Frame(String tagName, HtmlMarkupTag parent)
    {
        super(tagName, parent);
    }

    /**
     * Creates a new Frame tag with a Frameset tag parent.
     *
     * @param parent The parent frameset tag.
     */
    public Frame(FrameSet parent)
    {
        super("frame", parent);
    }

    /**
     * Sets the frameborder of the frame. The value of the frameborder must be either
     * 1 or 0 for on or off respectively.
     *
     * @param flag The frameborder value.
     * @throws IllegalArgumentException if the value is not 1 or 0 and this is strict.
     */
    public void setFrameBorder(int flag) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (flag != 0 && flag != 1)
            {
                throw new IllegalArgumentException("frameborder must be 1 or 0.");
            }
        }
        super.setAttribute( "frameborder", String.valueOf(flag) );
    }

    /**
     * Returns the value of the frameborder attribute. If the value is not set,
     * -1 will be returned.
     *
     * @return int
     */
    public int getFrameBorder()
    {
        int border = -1;
        String b = getAttributeValue("frameborder");
        if (b != null) border = Integer.parseInt(b);
        return border;
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
     * Sets the marginheight of the frame. The value of the marginheight must be
     * greater than or equal to 0.
     *
     * @param height The margin height.
     * @throws IllegalArgumentException if the value is negative and this is strict.
     */
    public void setMarginHeight(int height) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (height < 0)
            {
                throw new IllegalArgumentException("marginheight must be greater than zero.");
            }
        }
        super.setAttribute( "marginheight", String.valueOf(height) );
    }

    /**
     * Returns the value of the margineheight attribute. If the value is not set,
     * -1 will be returned.
     *
     * @return int
     */
    public int getMarginHeight()
    {
        return getIntAttributeValue("marginheight");
    }

    /**
     * Sets the marginwidth of the frame. The value of the marginwidth must be
     * greater than or equal to 0.
     *
     * @param width The margin width.
     * @throws IllegalArgumentException if the value is negative and this is strict.
     */
    public void setMarginWidth(int width) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (width < 0)
            {
                throw new IllegalArgumentException("marginwidth must be greater than zero.");
            }
        }
        super.setAttribute( "marginwidth", String.valueOf(width) );
    }

    /**
     * Returns the value of the marginewidth attribute. If the value is not set, -1
     * will be returned.
     *
     * @return int
     */
    public int getMarginWidth()
    {
        return getIntAttributeValue("marginwidth");
    }

    /**
     * Sets the name of the tag.
     *
     * @param name The charset.
     */
    public void setName(String name)
    {
        setAttribute("name", name);
    }

    /**
     * Returns the name of the tag.
     *
     * @return String
     */
    public String getName()
    {
        return getAttributeValue("name");
    }

    /**
     * Sets whether the user can resize the frame. The value true means they cannot resize,
     * false means they can.
     *
     * @param noresize Whether they can resize.
     */
    public void setNoresize(boolean noresize)
    {
        if (noresize) setAttribute("noresize");
        else removeAttribute("noresize");
    }

    /**
     * Returns whether or not they can resize.
     *
     * @return boolean
     */
    public boolean getNoresize()
    {
        return (getAttribute("noresize") != null);
    }

    /**
     * Sets the scrolling of the tag.
     *
     * @param scrolling The scrolling type.
     * @throws IllegalArgumentException if the value is not yes, no, or auto and this is strict.
     */
    public void setScrolling(String scrolling) throws IllegalArgumentException
    {
        if (scrolling != null)
        {
            scrolling = scrolling.toLowerCase();
            if ( isStrict() )
            {
                if ( !"yes".equals(scrolling) && !"no".equals(scrolling) && !"auto".equals(scrolling) )
                {
                    throw new IllegalArgumentException("scrolling type is invalid: "+scrolling);
                }
            }
        }
        setAttribute("scrolling", scrolling);
    }

    /**
     * Returns the scrolling of the tag.
     *
     * @return String
     */
    public String getScrolling()
    {
        return getAttributeValue("scrolling");
    }

    /**
     * Sets the src of the frame.
     *
     * @param src The src url.
     */
    public void setSrc(String src)
    {
        if (_basePath != null && src.indexOf(_basePath) == 0) src = src.substring(_basePath.length()+1);
        setAttribute("src", src);
    }

    /**
     * Returns the src of the tag.
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
     * Overrides setAttribute to make sure that frameborder, marginheight, and marginwidth
     * are numeric.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is frameborder, marginheight, or
     *                               marginwidth and the value is not numeric.
     */
    public void setAttribute(String name, String val) throws NumberFormatException
    {
        if (name != null) name = name.toLowerCase();
        try
        {
            if ( "frameborder".equals(name) )
            {
                setFrameBorder( Integer.parseInt(val) );
            }
            else if ( "marginheight".equals(name) )
            {
                setMarginHeight( Integer.parseInt(val) );
            }
            else if ( "marginwidth".equals(name) )
            {
                setMarginWidth( Integer.parseInt(val) );
            }
            else if ( "noresize".equals(name) )
            {
                super.setAttribute(name);
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

    /**
     * This cannot create text content, so it throws a IllegalMarkupException if this is strict.
     *
     * @param txt Not used.
     * @return TextContent
     * @throws IllegalMarkupException
     */
    public TextContent createTextContent(String txt) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text content.");
        else return super.createTextContent(txt);
    }

     /**
     * This cannot create a table, so it throws an IllegalMarkupException if this is strict.
     *
     * @return Table
     * @throws IllegalMarkupException
     */
    public Table createTable() throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create a tables.");
        else return super.createTable();
    }

    /**
     * This cannot create an image, so it throws an IllegalMarkupException if this is strict.
     *
     * @param src The src of the image.
     * @return Img
     * @throws IllegalMarkupException
     */
    public Img createImage(String src) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create images.");
        else return super.createImage(src);
    }

    /**
     * This cannot create a text effect, so it throws an IllegalMarkupException if this is strict.
     *
     * @param type The type of text effect.
     * @throws IllegalMarkupException
     */
    public TextEffect createTextEffect(TextEffectType type) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text effects.");
        else return super.createTextEffect(type);
    }
}