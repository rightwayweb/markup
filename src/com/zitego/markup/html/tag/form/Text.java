package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;

/**
 * This class represents an html form text tag. A text tag must have a parent tag and
 * that parent tag must be an HtmlMarkupTag or a Form. A text tag has a few attributes. They are
 * readonly, size, and maxlength. This class is up to date as of W3C specification
 * version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Text.java,v 1.2 2013/04/04 02:06:46 jglorioso Exp $
 */
public class Text extends Input implements Sizeable
{
   /**
     * Creates a new select tag with no parent.
     */
    public Text()
    {
        this("text");
    }

    /**
     * Creats a new text tag with the type.
     *
     * @param tag The tag name.
     */
    protected Text(String tag)
    {
        super(tag);
    }

    /**
     * Creates a new select tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Text(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new text tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Text(HtmlMarkupTag parent, Form form)
    {
        this(parent, form, "text");
    }

    /**
     * Creates a new text tag.
     *
     * @param form The parent form.
     */
    public Text(Form form)
    {
        this(form, form);
    }

    /**
     * Creats a new text tag with the type.
     *
     * @param form The parent form.
     * @param tag The tag name.
     */
    protected Text(Form form, String tag)
    {
        super(form, tag);
    }

    /**
     * Creates a new text tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     * @param tag The tag name.
     */
    protected Text(HtmlMarkupTag parent, Form form, String tag)
    {
        super(parent, form, tag);
    }

    /**
     * Sets the size of the field.
     *
     * @param size The size.
     * @throws IllegalArgumentException if the size is negative and this is strict.
     */
    public void setSize(int size) throws IllegalArgumentException
    {
        if (size < 0 && isStrict() )
        {
            throw new IllegalArgumentException("size must be positive.");
        }
        super.setAttribute( "size", String.valueOf(size) );
    }

    /**
     * Returns the size of the field.
     *
     * @return int
     */
    public int getSize()
    {
        return getIntAttributeValue("size");
    }

    /**
     * Sets the max length of the field.
     *
     * @param max The max length.
     * @throws IllegalArgumentException if the max length is negative and this is strict.
     */
    public void setMaxLength(int max) throws IllegalArgumentException
    {
        if (max < 0 && isStrict() )
        {
            throw new IllegalArgumentException("max length must be positive.");
        }
        super.setAttribute( "maxlength", String.valueOf(max) );
    }

    /**
     * Returns the maxlength.
     *
     * @return int
     */
    public int getMaxLength()
    {
        return getIntAttributeValue("maxlength");
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
     * Overrides setAttribute to make sure that size and maxlength
     * are numeric as well as making sure that readonly remains boolean.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is size or maxlength
     *                               and the value is not numeric.
     */
    public void setAttribute(String name, String val) throws NumberFormatException
    {
        if (name != null) name = name.toLowerCase();
        if ( "size".equals(name) )
        {
            setSize( Integer.parseInt(val) );
        }
        else if ( "maxlength".equals(name) )
        {
            setMaxLength( Integer.parseInt(val) );
        }
        else if ( "readonly".equals(name) )
        {
            super.setAttribute(name);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    public String getStartTag(FormatType type) throws UnsupportedFormatException
    {
        if ( preserveWhiteSpace() && removeType() ) removeAttribute("type");
        String ret = super.getStartTag(type);
        if ( preserveWhiteSpace() &&  removeType() ) setAttribute("type", "text");
        return ret;
    }

    /**
     * For extending classes to determine whether type= should be removed from the
     * attributes before formatting the start tag. Default is true.
     *
     * @return boolean
     */
    protected boolean removeType()
    {
        return false;
    }
}
