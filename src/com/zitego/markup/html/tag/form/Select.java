package com.zitego.markup.html.tag.form;

import com.zitego.markup.*;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.format.*;
import java.util.Vector;

/**
 * This class represents an html select tag. A select tag must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form tag. A select tag has
 * two attributes. They are multiple and size. setValue cannot be called.
 * This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Select.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Select extends FormElement implements Sizeable
{
    /** The options. */
    private Vector _options;

    /**
     * Creates a new select tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Select(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new select tag with an HtmlMarkupTag parent and a form.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Select(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "select");
        setHasEndTag(true);
    }

    /**
     * Creates a new select tag with a Form parent.
     *
     * @param form The parent Form.
     */
    public Select(Form form)
    {
        this(form, form);
    }

    /**
     * Creates a new select tag with no parent. This is meant to be
     * used for constant lookup types.
     */
    public Select()
    {
        super("select");
        setHasEndTag(true);
    }

    /**
     * Adds an option to the list.
     *
     * @param opt The option to add.
     */
    public void addOption(Option opt)
    {
        if (_options == null) _options = new Vector();
        if ( opt != null && !_options.contains(opt) ) _options.add(opt);
    }

    /**
     * Removes an option from the list based on the value. If more than one option exists
     * with the given value, they are all removed.
     *
     * @param val The value of the option to remove.
     */
    public void removeOption(String val)
    {
        if (_options != null && val != null)
        {
            for (int i=0; i<_options.size(); i++)
            {
                Option opt = (Option)_options.get(i);
                if ( val.equals(opt.getValue()) )
                {
                    removeBodyContent( (Option)_options.get(i) );
                    _options.remove(i--);
                }
            }
        }
    }

    /**
     * Removes all options from this select object.
     */
    public void clearOptions()
    {
        if (_options != null)
        {
            _options.clear();
            clearContent();
        }
    }

    /**
     * Returns the options.
     *
     * @return Vector
     */
    public Vector getOptions()
    {
        return _options;
    }

    /**
     * Sets the selected option based on the value.
     *
     * @param val The value.
     */
    public void setSelected(String val)
    {
        if (val == null || _options == null) return;

        int size = _options.size();
        for (int i=0; i<size; i++)
        {
            Option opt = (Option)_options.get(i);
            if ( val.equals(opt.getValue()) ) opt.setSelected(true);
            else opt.setSelected(false);
        }
    }

    /**
     * Sets whether or not this select is multi.
     *
     * @param mult Whether the select is multi.
     */
    public void setMultiple(boolean mult)
    {
        if (mult) super.setAttribute("multiple");
        else removeAttribute("multiple");
    }

    /**
     * Returns whether or not this select is multi.
     *
     * @return boolean
     */
    public boolean isMultiple()
    {
        if (getAttribute("multiple") != null) return true;
        else return false;
    }

    /**
     * Overrides setValue to throw an exception as it is not used if this is strict.
     *
     * @param name Not used.
     * @throws IllegalArgumentException
     */
    public void setValue(String name) throws IllegalArgumentException
    {
        if ( isStrict() ) throw new IllegalArgumentException("setValue cannot be called for select option groups.");
        else super.setValue(name);
    }

    /**
     * Sets the size of the select.
     *
     * @param size The size.
     * @throws IllegalArgumentException if the size is negative and this is strict.
     */
    public void setSize(int size) throws IllegalArgumentException
    {
        if ( isStrict() && size < 0)
        {
            throw new IllegalArgumentException("size must be positive.");
        }
        super.setAttribute( "size", String.valueOf(size) );
    }

    /**
     * Returns the size of the select.
     *
     * @return int
     */
    public int getSize()
    {
        return getIntAttributeValue("size");
    }

    /**
     * Here only for the Sizeable interface. This is not implemented or used
     * for the Select object.
     *
     * @param max Not used.
     */
    public void setMaxLength(int max) throws IllegalArgumentException { }

    /**
     * Overrides setAttribute to make sure that that setValue is not called, that size
     * is numeric, and that multiple remains boolean.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if setValue is called.
     * @throws NumberFormatException if the size is not numeric.
     */
    public void setAttribute(String name, String val) throws NumberFormatException, IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "value".equals(name) )
        {
            setValue(val);
        }
        else if ( "multiple".equals(name) )
        {
            super.setAttribute(name);
        }
        else if ( "size".equals(name) )
        {
            setSize( Integer.parseInt(val) );
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    public MarkupContent addBodyContentAt(int index, MarkupContent content) throws IllegalArgumentException
    {
        MarkupContent ret = super.addBodyContentAt(index, content);
        if (content instanceof Option) addOption( (Option)content );
        return ret;
    }
}