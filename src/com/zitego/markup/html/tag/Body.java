package com.zitego.markup.html.tag;

import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.html.HtmlMarkupMap;
import java.util.Vector;

/**
 * This class represents an html body tag. A body tag  has the following attributes:
 * background, bgcolor, text, link, alink, vlink, onload, onunload, onblur, onfocus,
 * class, id, lang, and style. This class must have an HTML tag or NoFrames tag as
 * the parent. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Body.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Html#getBodyTag()
 */
public class Body extends EventDrivenTag
{
    /** This is used to set the body onload tag for pre-loading images. */
    private Vector _imagesToPreload = new Vector();

    /**
     * Creates a new Body tag with no parent.
     */
    public Body()
    {
        super("body");
    }

    /**
     * Creates a new Body tag with an HTML tag parent.
     *
     * @param parent The parent.
     */
    public Body(Html parent)
    {
        super("body", parent);
    }

    /**
     * Creates a new Body tag with an HTML tag parent.
     *
     * @param parent The parent.
     */
    public Body(NoFrames parent)
    {
        super("body", parent);
    }

    /**
     * Sets the background of the tag.
     *
     * @param background The background.
     */
    public void setBackground(String background)
    {
        setAttribute("background", background);
    }

    /**
     * Returns the background of the tag.
     *
     * @return String
     */
    public String getBackground()
    {
        return getAttributeValue("background");
    }

    /**
     * Sets the background color of the tag.
     *
     * @param bgcolor The background color.
     */
    public void setBgColor(String bgcolor)
    {
        setAttribute("bgcolor", bgcolor);
    }

    /**
     * Returns the background color of the tag.
     *
     * @return String
     */
    public String getBgColor()
    {
        return getAttributeValue("bgcolor");
    }

    /**
     * Sets the text color of the tag.
     *
     * @param col The text color.
     */
    public void setText(String col)
    {
        setAttribute("text", col);
    }

    /**
     * Returns the text color of the tag.
     *
     * @return String
     */
    public String getText()
    {
        return getAttributeValue("text");
    }

    /**
     * Sets the link color of the tag.
     *
     * @param link The link.
     */
    public void setLink(String link)
    {
        setAttribute("link", link);
    }

    /**
     * Returns the link color of the tag.
     *
     * @return String
     */
    public String getLink()
    {
        return getAttributeValue("link");
    }

    /**
     * Sets the alink color of the tag.
     *
     * @param alink The alink color.
     */
    public void setAlink(String alink)
    {
        setAttribute("alink", alink);
    }

    /**
     * Returns the alink color of the tag.
     *
     * @return String
     */
    public String getAlink()
    {
        return getAttributeValue("alink");
    }

    /**
     * Sets the vlink color of the tag.
     *
     * @param vlink The vlink color.
     */
    public void setVlink(String vlink)
    {
        setAttribute("vlink", vlink);
    }

    /**
     * Returns the vlink color of the tag.
     *
     * @return String
     */
    public String getVlink()
    {
        return getAttributeValue("vlink");
    }

    /**
     * Sets the marginheight of the frame. The value of the marginheight must be
     * greater than or equal to 0 if we are set to strict.
     *
     * @param height The margin height.
     * @throws IllegalArgumentException if the value is negative.
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
     * greater than or equal to 0 if we are set to strict.
     *
     * @param width The margin width.
     * @throws IllegalArgumentException if the value is negative.
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
     * Returns the value of the marginwidth attribute. If the value is not set, -1
     * will be returned.
     *
     * @return int
     */
    public int getMarginWidth()
    {
        return getIntAttributeValue("marginwidth");
    }

    /**
     * Overrides setAttribute to make sure that marginheight and marginwidth are numeric.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is marginheight or marginwidth and
     *                               the value is not numeric.
     */
    public void setAttribute(String name, String val) throws NumberFormatException
    {
        if (name != null) name = name.toLowerCase();
        try
        {
            if ( "marginheight".equals(name) )
            {
                setMarginHeight( Integer.parseInt(val) );
            }
            else if ( "marginwidth".equals(name) )
            {
                setMarginWidth( Integer.parseInt(val) );
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
     * Sets the onload event.
     *
     * @param func The function to call.
     */
    public void setOnload(String func)
    {
        setJsEventHandler("onLoad", func);
    }

    /**
     * Returns the onload event.
     *
     * @return String
     */
    public String getOnload()
    {
        return getJsEventHandlerString("onLoad");
    }

    /**
     * Sets the onUnload event.
     *
     * @param func The function to call.
     */
    public void setOnUnload(String func)
    {
        setJsEventHandler("onUnload", func);
    }

    /**
     * Returns the onUnload event.
     *
     * @return String
     */
    public String getOnUnload()
    {
        return getJsEventHandlerString("onUnload");
    }

    /**
     * Adds the specified image to the vector of images to pre-load. If it is already in there,
     * then it is ignored. This also makes a call to the header to register the menu.js javascript
     * source file. It will register /js/menu.js, so you will need to make sure that file is present
     * in the root js directory of the server for whatever the domain will be.
     *
     * @param src The image src.
     */
    public void addImageToPreload(String src)
    {
        if ( !_imagesToPreload.contains(src) ) _imagesToPreload.add(src);
        Head h = ( (HtmlMarkupMap)getMap() ).getHeadTag();
        if (h != null) h.registerJsSourceFile("/js/menu.js");
        setChanged();
    }

    /**
     * Overrides add line break because line breaks are inside the body, not after it.
     */
    public void addLineBreak() throws IllegalArgumentException
    {
        addLineBreak( new BR(this) );
    }

    /**
     * Overrides add line break because line breaks are inside the body, not after it.
     *
     * @param br The line break.
     */
    public void addLineBreak(BR br) throws IllegalArgumentException
    {
        addBodyContent(br);
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            int size = _imagesToPreload.size();
            if (size > 0)
            {
                StringBuffer onload = new StringBuffer("preloadImages(");
                for (int i=0; i<size; i++)
                {
                    onload.append( (i > 0 ? ",'" : "'") ).append( _imagesToPreload.get(i) ).append("'");
                }
                onload.append(")");
                setOnload( onload.toString() );
            }
            return super.generateContent(type);
        }
        else
        {
            return (String)getCachedContent(type);
        }
    }
}