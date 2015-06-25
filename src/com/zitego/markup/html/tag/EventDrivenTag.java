package com.zitego.markup.html.tag;

import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.tag.TagAttribute;
import com.zitego.markup.tag.AttributeList;
import java.util.Vector;

/**
 * This class represents a base html that is a root for other displayable/
 * event drive tags. What this means is that it has some root attributes such
 * as class, id, style, and javascript event handlers. Pretty much all tags
 * will extend this class aside from tags such as HTML and HEAD. The tag must
 * have a parent tag. This class is up to date as of W3C specification version
 * 4.01.
 *
 * @author John Glorioso
 * @version $Id: EventDrivenTag.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public abstract class EventDrivenTag extends HtmlMarkupTag
{
    /** The start tag. */
    private String _startTag;
    /** The javascript event handlers. */
    protected AttributeList _jsEventHandlers = new AttributeList();
    /** This is just a property that is the root directory of a domain that contains javascript source files. Default is /js/ */
    private String _rootJsDirectory = "/js/";

    /**
     * Creates a new event driven tag with an parent tag.
     *
     * @param parent The parent tag.
     */
    protected EventDrivenTag(HtmlMarkupTag parent)
    {
        super(parent);
    }

    /**
     * Creates a new event drive tag with a name and no parent tag.
     * A parent is needed to format the event driven tag to html however.
     * An UnsupportedFormatType exception will be thrown if the parent is
     * not specified before format(FormatType.HTML) is called.
     *
     * @param name The tag name.
     */
    protected EventDrivenTag(String name)
    {
        super(name);
    }

    /**
     * Creates a new event driven tag with a name and a parent tag.
     *
     * @param name The tag name.
     * @param parent The parent tag.
     */
    protected EventDrivenTag(String name, HtmlMarkupTag parent)
    {
        super(name, parent);
    }

    /**
     * Sets the lang of the tag.
     *
     * @param lang The lang.
     */
    public void setLang(String lang)
    {
        setAttribute("lang", lang);
    }

    /**
     * Returns the lang of the tag.
     *
     * @return String
     */
    public String getLang()
    {
        return getAttributeValue("lang");
    }

    /**
     * Sets the specified javascript event handler.
     *
     * @param handler The event handler.
     */
    protected void setJsEventHandler(JsEventHandler handler)
    {
        if (handler == null) return;
        if (handler.getValue() == null)
        {
            removeJsEventHandler( handler.getName() );
            return;
        }
        setChanged();
        JsEventHandler existingHandler = getJsEventHandler( handler.getName() );
        if (existingHandler == null)
        {
            _jsEventHandlers.add(handler);
        }
        else
        {
            //Set the one that is already in there
            existingHandler.setJsFunctions( handler.getJsFunctions() );
        }
    }

    /**
     * Sets a JsEventHandler given the action and function.
     *
     * @param handler The event.
     * @param action The function.
     */
    public void setJsEventHandler(String handler, String action)
    {
        JsEventHandler h = new JsEventHandler(handler, action);
        h.setStrict( isStrict() );
        h.setPreserveWhiteSpace( preserveWhiteSpace() );
        setJsEventHandler(h);
    }

    /**
     * Sets the event handlers for this tag given a list.
     *
     * @param handlers The js event handlers.
     */
    public void setJsEventHandlers(AttributeList handlers)
    {
        if (handlers == null) return;

        int size = handlers.size();
        for (int i=0; i<size; i++)
        {
            TagAttribute a = (TagAttribute)handlers.get(i);
            setJsEventHandler( a.getName(), a.getValue() );
        }
    }

    /**
     * Removes the specified javascript event handler.
     *
     * @param name The name of the handler to remove.
     */
    public void removeJsEventHandler(String name)
    {
        JsEventHandler h = getJsEventHandler(name);
        if (h != null)
        {
            _jsEventHandlers.remove(h);
            setChanged();
        }
    }

    /**
     * Returns the specified javascript event handler.
     *
     * @param name the handler name.
     */
    protected final JsEventHandler getJsEventHandler(String name)
    {
        if (name == null) return null;

        name = name.toLowerCase();
        int size = _jsEventHandlers.size();
        for (int i=0; i<size; i++)
        {
            JsEventHandler h = (JsEventHandler)_jsEventHandlers.get(i);
            if ( name.equals(h.getName()) ) return h;
        }
        return null;
    }

    /**
     * Returns the value of the named javascript event handler.
     *
     * @param name the handler name.
     * @return String
     */
    protected final String getJsEventHandlerString(String name)
    {
        JsEventHandler handler = getJsEventHandler(name);
        if (handler != null) return handler.getJsFunctionString();
        else return null;
    }

    /**
     * Returns the javascript event handlers.
     *
     * @return AttributeList
     */
    public AttributeList getJsEventHandlers()
    {
        return _jsEventHandlers;
    }

    /**
     * Returns all of the javascript event handlers.
     *
     * @return String
     */
    public String getJsEventHandlerString()
    {
        StringBuffer ret = new StringBuffer();
        int size = _jsEventHandlers.size();
        for (int i=0; i<size; i++)
        {
            ret.append(" ").append( _jsEventHandlers.get(i) );
        }
        return ret.toString();
    }

    /**
     * Sets the onClick javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnClick(String func)
    {
        setJsEventHandler("onClick", func);
    }

    /**
     * Returns the onClick javascript event.
     *
     * @return String
     */
    public String getOnClick()
    {
        return getJsEventHandlerString("onClick");
    }

    /**
     * Sets the onFocus javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnFocus(String func)
    {
        setJsEventHandler("onFocus", func);
    }

    /**
     * Returns the onFocus javascript event.
     *
     * @return String
     */
    public String getOnFocus()
    {
        return getJsEventHandlerString("onFocus");
    }

    /**
     * Sets the onBlur javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnBlur(String func)
    {
        setJsEventHandler("onBlur", func);
    }

    /**
     * Returns the onBlur javascript event.
     *
     * @return String
     */
    public String getOnBlur()
    {
        return getJsEventHandlerString("onBlur");
    }

    /**
     * Sets the onSelect javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnSelect(String func)
    {
        setJsEventHandler("onSelect", func);
    }

    /**
     * Returns the onSelect javascript event.
     *
     * @return String
     */
    public String getOnSelect()
    {
        return getJsEventHandlerString("onSelect");
    }

    /**
     * Sets the onChange javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnChange(String func)
    {
        setJsEventHandler("onChange", func);
    }

    /**
     * Returns the onChange javascript event.
     *
     * @return String
     */
    public String getOnChange()
    {
        return getJsEventHandlerString("onChange");
    }

    /**
     * Sets the onDblClick javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnDblClick(String func)
    {
        setJsEventHandler("onDblClick", func);
    }

    /**
     * Returns the onDblClick javascript event.
     *
     * @return String
     */
    public String getOnDblClick()
    {
        return getJsEventHandlerString("onDblClick");
    }

   /**
     * Sets the onKeyDown javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnKeyDown(String func)
    {
        setJsEventHandler("onKeyDown", func);
    }

    /**
     * Returns the onKeyDown javascript event.
     *
     * @return String
     */
    public String getOnKeyDown()
    {
        return getJsEventHandlerString("onKeyDown");
    }

    /**
     * Sets the onKeyPress javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnKeyPress(String func)
    {
        setJsEventHandler("onKeyPress", func);
    }

    /**
     * Returns the onKeyPress javascript event.
     *
     * @return String
     */
    public String getOnKeyPress()
    {
        return getJsEventHandlerString("onKeyPress");
    }

    /**
     * Sets the onKeyUp javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnKeyUp(String func)
    {
        setJsEventHandler("onKeyUp", func);
    }

    /**
     * Returns the onKeyUp javascript event.
     *
     * @return String
     */
    public String getOnKeyUp()
    {
        return getJsEventHandlerString("onKeyUp");
    }

    /**
     * Sets the onMouseDown javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnMouseDown(String func)
    {
        setJsEventHandler("onMouseDown", func);
    }

    /**
     * Returns the onMouseDown javascript event.
     *
     * @return String
     */
    public String getOnMouseDown()
    {
        return getJsEventHandlerString("onMouseDown");
    }

    /**
     * Sets the onMouseUp javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnMouseUp(String func)
    {
        setJsEventHandler("onMouseUp", func);
    }

    /**
     * Returns the setOnMouseUp javascript event.
     *
     * @return String
     */
    public String getOnMouseUp()
    {
        return getJsEventHandlerString("setOnMouseUp");
    }

    /**
     * Sets the onMouseOver javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnMouseOver(String func)
    {
        setJsEventHandler("onMouseOver", func);
    }

    /**
     * Returns the onMouseOver javascript event.
     *
     * @return String
     */
    public String getOnMouseOver()
    {
        return getJsEventHandlerString("onMouseOver");
    }

    /**
     * Sets the onMouseOut javascript event.
     *
     * @param func The javascript function.
     */
    public void setOnMouseOut(String func)
    {
        setJsEventHandler("onMouseOut", func);
    }

    /**
     * Returns the onMouseOut javascript event.
     *
     * @return String
     */
    public String getOnMouseOut()
    {
        return getJsEventHandlerString("onMouseOut");
    }

    /**
     * Sets the tab index of this form element.
     *
     * @param index The tab index.
     */
    public void setTabIndex(int index)
    {
        super.setAttribute( "tabindex", String.valueOf(index) );
    }

    /**
     * Returns the tab index of this form element. If the tab index is not set,
     * it returns -1.
     *
     * @return int
     */
    public int getTabIndex()
    {
        return getIntAttributeValue("tabindex", -1);
    }

    /**
     * Sets the root javascript directory relative to the domain.
     *
     * @param dir The directory.
     */
    public void setRootJsDirectory(String dir)
    {
        _rootJsDirectory = dir;
    }

    /**
     * Returns the root javascript directory.
     *
     * @return String
     */
    public String getRootJsDirectory()
    {
        return _rootJsDirectory;
    }

    /**
     * Adds the javascript event handlers to the start tag.
     *
     * @param type The format type of this start tag.
     * @return String
     */
    public String getStartTag(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer( super.getStartTag(type) );
            if (_jsEventHandlers.size() > 0)
            {
                ret.insert( ret.lastIndexOf(">"), _jsEventHandlers.toString(type) );
            }
            _startTag = ret.toString();
        }
        return _startTag;
    }

    public void setAttribute(String name, String val)
    {
        name = name.toLowerCase();
        if ( name.equals("onblur") ) setOnBlur(val);
        else if ( name.equals("onchange") ) setOnChange(val);
        else if ( name.equals("onclick") ) setOnClick(val);
        else if ( name.equals("ondblclick") ) setOnDblClick(val);
        else if ( name.equals("onfocus") ) setOnFocus(val);
        else if ( name.equals("onkeydown") ) setOnKeyDown(val);
        else if ( name.equals("onkeypress") ) setOnKeyPress(val);
        else if ( name.equals("onkeyup") ) setOnKeyUp(val);
        else if ( name.equals("onmousedown") ) setOnMouseDown(val);
        else if ( name.equals("onmouseout") ) setOnMouseOut(val);
        else if ( name.equals("onmouseover") ) setOnMouseOver(val);
        else if ( name.equals("onmouseup") ) setOnMouseUp(val);
        else if ( name.equals("onselect") ) setOnSelect(val);
        else if ( "tabindex".equals(name) )
        {
            if (val == null)
            {
                removeAttribute(name);
            }
            else
            {
                try
                {
                    setTabIndex( Integer.parseInt(val) );
                }
                catch (NumberFormatException nfe)
                {
                    if ( isStrict() ) throw nfe;
                }
            }
        }
        else super.setAttribute(name, val);
    }
}