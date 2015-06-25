package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;

/**
 * This abstract class represents an html form input tag. An input tag must have a
 * parent tag and that parent tag must be an HtmlMarkupTag. An input tag has several
 * attributes. These attributes are disabled, name, type, and value. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Input.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public abstract class Input extends FormElement
{
    /**
     * Creates a new input tag a type and no parent.
     *
     * @param type The type.
     */
    public Input(String type)
    {
        super("input");
        setHasEndTag(false);
        setType(type);
        setAddClosingSlashInStart(true);
    }

    /**
     * Creates a new input tag with an HtmlMarkupTag parent and a type.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param type The type.
     */
    public Input(HtmlMarkupTag parent, String type)
    {
        this(parent, null, type);
    }

    /**
     * Creates a new input tag with an HtmlMarkupTag parent, and a type.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     * @param type The input tag type.
     */
    protected Input(HtmlMarkupTag parent, Form form, String type)
    {
        super(parent, form, "input");
        setHasEndTag(false);
        setType(type);
        setAddClosingSlashInStart(true);
    }

    /**
     * Creates a new input tag with a Form parent, and a type.
     *
     * @param form The parent form.
     * @param type The input tag type.
     */
    protected Input(Form form, String type)
    {
        this(form, form, type);
    }

    /**
     * Sets the type of input tag.
     *
     * @param type The type.
     */
    private void setType(String type)
    {
        setAttribute("type", type);
    }

    /**
     * Returns the type of input tag we have.
     *
     * @return String
     */
    public String getType()
    {
        return getAttributeValue("type");
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        //So that the order stays the same if preserve
        if ( preserveWhiteSpace() )
        {
            String stype = getType();
            removeAttribute("type");
            super.parseText(text, type);
            if (getAttribute("type") == null) setType(stype);
        }
        else
        {
            super.parseText(text, type);
        }
    }
}