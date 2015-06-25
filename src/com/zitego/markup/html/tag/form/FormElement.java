package com.zitego.markup.html.tag.form;

import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.MarkupContent;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.markup.html.tag.EventDrivenTag;

/**
 * This abstract class represents an html form element tag. An element several
 * attributes. They are its parent form, name, value and whether it is disabled
 * or not. A FormElement has a parent tag of either a Form or an HtmlMarkupTag.
 *
 * @author John Glorioso
 * @version $Id: FormElement.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public abstract class FormElement extends EventDrivenTag
{
    /** The parent form. */
    private Form _form;

    /**
     * Creates a new form element with an HtmlMarkupTag parent, a parent Form,
     * and the name of the form element tag.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     * @param tagName The tag name.
     */
    public FormElement(HtmlMarkupTag parent, Form form, String tagName)
    {
        super(tagName, parent);
        setHasEndTag(false);
        _form = form;
        if (_form != null) _form.addElement(this);
    }

   /**
     * Creates a new form element with no parent or form.
     *
     * @param tagName The tag name.
     */
    public FormElement(String tagName)
    {
        super(tagName);
    }

    /**
     * Returns the parent form.
     *
     * @return Form
     */
    public Form getForm()
    {
        return _form;
    }

    /**
     * Sets the name of tag.
     *
     * @param name The name.
     */
    public void setName(String name)
    {
        super.setAttribute("name", name);
    }

    /**
     * Returns the name of tag we have.
     *
     * @return String
     */
    public String getName()
    {
        return getAttributeValue("name");
    }

    /**
     * Sets the value of tag.
     *
     * @param value The value.
     */
    public void setValue(String value)
    {
        super.setAttribute("value", value);
    }

    /**
     * Returns the value of tag.
     *
     * @return String
     */
    public String getValue()
    {
        return getAttributeValue("value");
    }

    /**
     * Sets whether or not this is disabled.
     *
     * @param disabled Whether this is disabled.
     */
    public void setDisabled(boolean disabled)
    {
        if (disabled) super.setAttribute("disabled");
        else removeAttribute("disabled");
    }

    /**
     * Returns whether or not this is disabled.
     *
     * @return boolean
     */
    public boolean isDisabled()
    {
        if (getAttribute("disabled") != null) return true;
        else return false;
    }

    /**
     * Overrides setAttribute to make sure that disabled remains boolean and tabindex
     * is an integer.
     *
     * @param name The name.
     * @param val The value.
     */
    public void setAttribute(String name, String val)
    {
        if (name != null) name = name.toLowerCase();
        if ( "disabled".equals(name) )
        {
            if (val == null) setDisabled(false);
            else setDisabled( new Boolean(val).booleanValue() );
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);
        //Look for the parent form if not specified
        if (_form == null)
        {
            //Check parent first
            MarkupContent parent = getParent();
            while (_form == null && parent != null)
            {
                if (parent instanceof Form)
                {
                    _form = (Form)parent;
                }
                else
                {
                    //Go through parent's children, and so on up the tree till we find a form
                    for (int i=0; i<parent.getBodySize(); i++)
                    {
                        MarkupContent c = parent.getBodyContent(i);
                        if (c instanceof Form)
                        {
                            _form = (Form)c;
                            break;
                        }
                    }
                    parent = parent.getParent();
                }
            }
        }
    }
}