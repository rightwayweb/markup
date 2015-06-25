package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.format.FormatType;

/**
 * This class represents an html select tag. A select tag must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form tag. A textarea
 * tag has three attributes. They are cols, rows, and readonly. setValue cannot be called.
 * This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: TextArea.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class TextArea extends FormElement
{
    /**
     * Creates a new textarea tag with no parent.
     */
    public TextArea()
    {
        super("textarea");
        setHasEndTag(true);
    }

    /**
     * Creates a new textarea tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public TextArea(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new textarea tag with an HtmlMarkupTag parent and a form.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public TextArea(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "textarea");
        setHasEndTag(true);
    }

    /**
     * Creates a new textarea tag with a Form parent.
     *
     * @param form The parent Form.
     */
    public TextArea(Form form)
    {
        this(form, form);
    }

    /**
     * Overrides setValue to throw an exception as it is not used unless this is not strict.
     *
     * @param value Not used.
     * @throws IllegalArgumentException
     */
    public void setValue(String value) throws IllegalArgumentException
    {
        if ( isStrict() ) throw new IllegalArgumentException("setValue cannot be called for select option groups.");
        else super.setValue(value);
    }

    /**
     * Sets the number of rows of the textarea.
     *
     * @param rows The number of rows.
     * @throws IllegalArgumentException if the number of rows is negative if this is strict.
     */
    public void setRows(int rows) throws IllegalArgumentException
    {
        if (rows < 0 && isStrict() )
        {
            throw new IllegalArgumentException("rows must be positive.");
        }
        super.setAttribute( "rows", String.valueOf(rows) );
    }

    /**
     * Returns the number of rows in the textarea.
     *
     * @return int
     */
    public int getRows()
    {
        return getIntAttributeValue("rows");
    }

    /**
     * Sets the number of columns in the textarea.
     *
     * @param cols The number of columns.
     * @throws IllegalArgumentException if the number of columns is negative and this is strict.
     */
    public void setCols(int cols) throws IllegalArgumentException
    {
        if (cols < 0 && isStrict() )
        {
            throw new IllegalArgumentException("cols must be positive.");
        }
        super.setAttribute( "cols", String.valueOf(cols) );
    }

    /**
     * Returns the number of columns in the textarea.
     *
     * @return int
     */
    public int getCols()
    {
        return getIntAttributeValue("cols");
    }

    /**
     * Sets whether this is readonly or not.
     *
     * @param ro Whether this is readonly.
     */
    public void setReadOnly(boolean ro)
    {
        if (ro) super.setAttribute("readonly");
        else removeAttribute("readonly");
    }

    /**
     * Returns whether or not this is readonly.
     *
     * @return boolean
     */
    public boolean isReadonly()
    {
        return (getAttribute("readonly") != null);
    }

    /**
     * Overrides setAttribute to make sure that that setValue is not called, that rows and cols
     * are numeric, and that readonly remains boolean.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if setValue is called.
     * @throws NumberFormatException if the rows or cols is not numeric.
     */
    public void setAttribute(String name, String val) throws NumberFormatException, IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "value".equals(name) )
        {
            setValue(val);
        }
        else if ( "readonly".equals(name) )
        {
            super.setAttribute(name);
        }
        else if ( "rows".equals(name) )
        {
            setRows( Integer.parseInt(val) );
        }
        else if ( "cols".equals(name) )
        {
            setCols( Integer.parseInt(val) );
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    public String getStartTag(FormatType type) throws UnsupportedFormatException
    {
        setIsOnOwnLine(true);
        return super.getStartTag(type);
    }

    public String getEndTag(FormatType type) throws UnsupportedFormatException
    {
        String ret = super.getEndTag(type);
        setIsOnOwnLine(false);
        return ret;
    }

    public boolean trimChildText()
    {
        return false;
    }
}