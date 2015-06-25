package com.zitego.markup.html.tag.form;

import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.tag.AttributeList;
import com.zitego.markup.tag.TagAttribute;
import com.zitego.markup.html.tag.JsEventHandler;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.http.HttpMethodType;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import java.util.Vector;

/**
 * This class represents an html form. It is not displayable, therefore does
 * not extend the EventDrivenTag, but it does have two javascript event handlers.
 * They are onsubmit and onreset. It has one required attribute and that is action.
 * The optional attributes are enctype, method, name, and target. The tag must
 * have a parent tag that is an HtmlMarkupTag. This class is up to date as of
 * W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Form.java,v 1.2 2008/11/18 18:34:37 jglorioso Exp $
 */
public class Form extends HtmlMarkupTag
{
    /** The start tag. */
    private String _startTag;
    /** The form elements. */
    private Vector _elements;
    /** The onsubmit and onreset javascript event handlers. */
    protected AttributeList _jsEventHandlers = new AttributeList();

    /**
     * Creates a form with no parent.
     */
    public Form()
    {
        super("form");
    }

    /**
     * Creates a form with a parent.
     *
     * @param parent The parent tag.
     */
    public Form(HtmlMarkupTag parent)
    {
        super("form", parent);
    }

    /**
     * Creates a form with a name and no parent for adding elements to.
     *
     * @param name The name.
     */
    public Form(String name)
    {
        super("form");
        setNameAttribute(name);
    }

    /**
     * Creates a new form tag with a parent HtmlMarkupTag.
     *
     * @param name The form name.
     * @param parent The parent tag.
     */
    public Form(String name, HtmlMarkupTag parent)
    {
        super("form", parent);
        setNameAttribute(name);
    }

    /**
     * Sets the action of the form.
     *
     * @param action The action.
     */
    public void setAction(String action)
    {
        setAttribute("action", action);
    }

    /**
     * Returns the action of the form.
     *
     * @return String
     */
    public String getAction()
    {
        return getAttributeValue("action");
    }

    /**
     * Sets the name of the form.
     *
     * @param name The name.
     */
    public void setNameAttribute(String name)
    {
        setAttribute("name", name);
    }

    /**
     * Returns the name of the form.
     *
     * @return String
     */
    public String getNameAttribute()
    {
        return getAttributeValue("name");
    }

    /**
     * Sets the encoding type of the form.
     *
     * @param enctype The enctype.
     */
    public void setEnctype(String enctype)
    {
        setAttribute("enctype", enctype);
    }

    /**
     * Returns the encoding type of the form.
     *
     * @return String
     */
    public String getEnctype()
    {
        return getAttributeValue("enctype");
    }

    /**
     * Sets the method type of the form.
     *
     * @param method The method.
     * @throws IllegalArgumentException if the form method is null.
     */
    public void setMethodType(HttpMethodType method) throws IllegalArgumentException
    {
        if (method == null)
        {
            throw new IllegalArgumentException("Method type cannot be null");
        }
        setAttribute( "method", method.getDescription() );
    }

    /**
     * Returns the method of the form.
     *
     * @return HttpMethodType
     */
    public HttpMethodType getMethodType()
    {
        return (HttpMethodType)HttpMethodType.evaluate( getAttributeValue("method") );
    }

    /**
     * Sets the target frame for the form action.
     *
     * @param target The target.
     */
    public void setTarget(String target)
    {
        setAttribute("target", target);
    }

    /**
     * Returns the target frame for the form action.
     *
     * @return String
     */
    public String getTarget()
    {
        return getAttributeValue("target");
    }

    /**
     * Sets the onsubmit handler.
     *
     * @param handler The handler.
     */
    public void setOnSubmit(String handler)
    {
        setHandler("onsubmit", handler);
    }

    /**
     * Returns the onsubmit javascript event.
     *
     * @return String
     */
    public String getOnSubmit()
    {
        return getJsEventHandlerString("onsubmit");
    }

    /**
     * Sets the onreset handler.
     *
     * @param handler The handler.
     */
    public void setOnReset(String handler)
    {
        setHandler("onreset", handler);
    }

    /**
     * Returns the onreset javascript event.
     *
     * @return String
     */
    public String getOnReset()
    {
        return getJsEventHandlerString("onreset");
    }

    /**
     * Sets a JsEventHandler given the action and function.
     *
     * @param event The event.
     * @param action The action.
     */
    private void setHandler(String event, String action)
    {
        if (action == null)
        {
            removeJsEventHandler(event);
            return;
        }
        JsEventHandler handler = new JsEventHandler(event.toLowerCase(), action);
        handler.setStrict( isStrict() );
        handler.setPreserveWhiteSpace( preserveWhiteSpace() );
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
            if ( name.equalsIgnoreCase(h.getName()) ) return h;
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
     * To be called by form element classes. Adds an element to this form. If the element's
     * getForm does not return this form, then it is not added. Option and OptGroup cannot
     * be added either as they both have another form element as their parent.
     *
     * @param elem The element to add.
     */
    public void addElement(FormElement elem)
    {
        if ( elem != null && !(elem instanceof Option) && !(elem instanceof OptGroup) )
        {
            Vector elements = getElements();
            if ( elem.getForm() == this && !elements.contains(elem) ) elements.add(elem);
        }
    }

    /**
     * Returns the Vector of elements.
     *
     * @return Vector
     */
    public Vector getElements()
    {
        if (_elements == null) _elements = new Vector();
        return _elements;
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
                ret.insert( ret.lastIndexOf(">"), _jsEventHandlers.toString() );
            }
            _startTag = ret.toString();
        }
        return _startTag;
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);
        //Search body for FormElements
        Vector elements = getElements();
        Vector foundElements = search(FormElement.class);
        int size = foundElements.size();
        for (int i=0; i<size; i++)
        {
            Object elem = foundElements.get(i);
            if ( !(elem instanceof Option || elem instanceof OptGroup) ) elements.add(elem);
        }
        TagAttribute val = getAttribute("onsubmit");
        if (val != null) setOnSubmit( val.getValue() );
        removeAttribute("onsubmit");
        val = getAttribute("onsubmit");
        if (val != null) setOnReset( val.getValue() );
        removeAttribute("onreset");
    }

    public void setAttribute(String name, String val)
    {
        name = name.toLowerCase();
        if ( name.equals("onsubmit") ) setOnSubmit(val);
        else if ( name.equals("onreset") ) setOnReset(val);
        else super.setAttribute(name, val);
    }
}