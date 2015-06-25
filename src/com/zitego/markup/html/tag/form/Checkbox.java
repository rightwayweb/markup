package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html form checkbox tag. A checkbox must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form. A checkbox tag has one
 * attribute that specifies whether it is checked or not. This class is up to date
 * as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Checkbox.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Checkbox extends Input
{
    /**
     * Creates a new checkbox tag with no parent.
     */
    public Checkbox()
    {
        super("checkbox");
    }
    
    /**
     * Creates a new checkbox tag with a type and no parent.
     *
     * @param type The type of checkbox.
     */
    public Checkbox(String type)
    {
        super(type);
    }
    
    /**
     * Creates a new checkbox tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Checkbox(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new checkbox tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     * @param type The type of checkbox.
     */
    public Checkbox(HtmlMarkupTag parent, Form form, String type)
    {
        super(parent, form, type);
    }

    /**
     * Creates a new checkbox tag with a parent form.
     *
     * @param form The parent form.
     */
    public Checkbox(Form form)
    {
        this(form, form);
    }

    /**
     * Creates a new checkbox tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Checkbox(HtmlMarkupTag parent, Form form)
    {
        this(parent, form, "checkbox");
    }

    /**
     * Sets whether or not the checkbox is checked.
     *
     * @param checked Whether the option is checked.
     */
    public void setChecked(boolean checked)
    {
        if (checked) super.setAttribute("checked");
        else removeAttribute("checked");
    }

    /**
     * Returns whether or not the checkbox is checked.
     *
     * @return boolean
     */
    public boolean isChecked()
    {
        if (getAttribute("checked") != null) return true;
        else return false;
    }

    /**
     * Overrides setAttribute to make sure that checked remains boolean.
     *
     * @param name The name.
     * @param val The value.
     */
    public void setAttribute(String name, String val)
    {
        if (name != null) name = name.toLowerCase();
        if ( "checked".equals(name) )
        {
            super.setAttribute(name);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }
}