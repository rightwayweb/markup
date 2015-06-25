package com.zitego.markup.html.tag.form;

import com.zitego.markup.*;
import com.zitego.markup.html.HtmlTextContent;
import com.zitego.format.*;

/**
 * This class represents an html form select option tag. An option must have a
 * parent tag and that parent tag must be either a Select tag or an OptionGroup.
 * An option tag has several attributes. They are disabled, label, selected, and
 * value. setName cannot be called. This class is up to date as of W3C specification
 * version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Option.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Option extends FormElement
{
    /** The option text. */
    protected HtmlTextContent _text;

    /**
     * Creates a new select option tag with no parent.
     */
    public Option()
    {
        super("option");
        setHasEndTag(false);
    }

    /**
     * Creates a new select option tag with an OptGroup parent.
     *
     * @param parent The parent OptGroup tag.
     */
    public Option(OptGroup parent)
    {
        super(parent, parent.getForm(), "option");
        setHasEndTag(false);
    }

    /**
     * Creates a new select option tag with a Select parent.
     *
     * @param parent The parent Select tag.
     */
    public Option(Select parent)
    {
        super(parent, parent.getForm(), "option");
        setHasEndTag(false);
    }

    /**
     * Sets whether or not this option is selected.
     *
     * @param sel Whether the option is selected.
     */
    public void setSelected(boolean sel)
    {
        if (sel) super.setAttribute("selected");
        else removeAttribute("selected");
    }

    /**
     * Returns whether or not this option is selected.
     *
     * @return boolean
     */
    public boolean isSelected()
    {
        if (getAttribute("selected") != null) return true;
        else return false;
    }

    /**
     * Sets the label of the select option.
     *
     * @param label The label.
     * @throws IllegalArgumentException if the parent is not an OptGroup and this is strict.
     */
    public void setLabel(String label)
    {
        if ( isStrict() && !(getParent() instanceof OptGroup) )
        {
            throw new IllegalArgumentException("label can only be set with OptGroup");
        }
        super.setAttribute("label", label);
    }

    /**
     * Returns the label.
     *
     * @return String
     */
    public String getLabel()
    {
        return getAttributeValue("label");
    }

    /**
     * Sets the text of the option by making it the first body content.
     *
     * @param txt The text.
     */
    public void setText(String txt)
    {
        if (_text == null) _text = new HtmlTextContent(this);
        _text.setText(txt);
    }

    /**
     * Returns the text of the option.
     *
     * @return String
     */
    public String getText()
    {
        if (_text != null) return _text.getText();
        else return null;
    }

    /**
     * Overrides setName to throw an exception as it is not used if this is strict.
     *
     * @param name Not used.
     * @throws IllegalArgumentException
     */
    public void setName(String name) throws IllegalArgumentException
    {
        if ( isStrict() ) throw new IllegalArgumentException("setName cannot be called for select options.");
        else super.setName(name);
    }

    /**
     * Overrides setAttribute to make sure that selected remains boolean, that setName is not
     * called, and label is set properly.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if setName is called or label is not set with an OptGroup.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "selected".equals(name) )
        {
            super.setAttribute(name);
        }
        else if ( "name".equals(name) )
        {
            setName(val);
        }
        else if ( "label".equals(name) )
        {
            setLabel(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    /**
     * Overrides addBodyContent to make sure only text content is added if this is strict.
     *
     * @param content The content.
     * @throws IllegalStateException if the content is not text.
     */
    public MarkupContent addBodyContent(MarkupContent content) throws IllegalStateException
    {
        if ( content instanceof TextContent || !isStrict() )
        {
            MarkupContent ret = null;
            setHasEndTag(true);
            ret = super.addBodyContent(content);
            setHasEndTag(false);
            return ret;
        }
        else
        {
            throw new IllegalStateException("Cannot add non text body content to an option.");
        }
    }

    /**
     * Overrides addBodyContent to make only text content is added if this is strict.
     *
     * @param index The index to add at.
     * @param content The content.
     * @throws IllegalStateException if the content is not text.
     */
    public MarkupContent addBodyContentAt(int index, MarkupContent content) throws IllegalStateException
    {
        if ( content instanceof TextContent || !isStrict() )
        {
            MarkupContent ret = null;
            setHasEndTag(true);
            ret = super.addBodyContentAt(index, content);
            setHasEndTag(false);
            return ret;
        }
        else
        {
            throw new IllegalStateException("Cannot add non text body content to an option.");
        }
    }

    /**
     * Overrides addBodyContent to make only text content is added if this is strict.
     *
     * @param content The content.
     * @throws IllegalStateException if the content is not text.
     */
    public MarkupContent addBodyContentAtBeginning(MarkupContent content) throws IllegalStateException
    {
        if ( content instanceof TextContent || !isStrict() )
        {
            MarkupContent ret = null;
            setHasEndTag(true);
            ret = super.addBodyContentAtBeginning(content);
            setHasEndTag(false);
            return ret;
        }
        else
        {
            throw new IllegalStateException("Cannot add non text body content to an option.");
        }
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);

        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            if (c instanceof HtmlTextContent)
            {
                _text = (HtmlTextContent)c;
                break;
            }
        }
    }
}