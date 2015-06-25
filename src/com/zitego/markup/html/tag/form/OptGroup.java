package com.zitego.markup.html.tag.form;

/**
 * This class represents an html option grouptag. An option group must have a
 * parent tag and that parent tag must be a Select tag. An option group tag has
 * two attributes. They are disabled and label. setName and setValue cannot be called.
 * This class is up to date as of W3C specification
 * version 4.01.
 *
 * @author John Glorioso
 * @version $Id: OptGroup.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class OptGroup extends FormElement
{
    /**
     * Creates a new select option group tag with no parent.
     */
    public OptGroup()
    {
        super("optgroup");
    }
    
    /**
     * Creates a new select option group tag with a Select parent.
     *
     * @param parent The parent Select tag.
     */
    public OptGroup(Select parent)
    {
        super(parent, parent.getForm(), "optgroup");
    }

    /**
     * Sets the label of the select option.
     *
     * @param label The label.
     */
    public void setLabel(String label)
    {
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
     * Overrides setName to throw an exception as it is not used if this is strict.
     *
     * @param name Not used.
     * @throws IllegalArgumentException
     */
    public void setName(String name) throws IllegalArgumentException
    {
        if ( isStrict() ) throw new IllegalArgumentException("setName cannot be called for select option groups.");
        else super.setName(name);
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
     * Overrides setAttribute to make sure that that setName and setValue are not called.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if setName or setValue is called.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "name".equals(name) )
        {
            setName(val);
        }
        else if ( "value".equals(name) )
        {
            setValue(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }
}