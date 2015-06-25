package com.zitego.markup.html.tag;

import com.zitego.markup.TextContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;
import java.util.Hashtable;

/**
 * This class represents an html param tag. A param tag several attributes. These
 * attributes are name, type, value and valuetype. This class must have either an
 * Applet tag or an ObjectTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Param.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Param extends HtmlMarkupTag
{
    /** A hashtable of the allowable value type options. */
    protected static Hashtable _allowableValueTypes = new Hashtable();
    static
    {
        _allowableValueTypes.put("data", "1");
        _allowableValueTypes.put("ref", "1");
        _allowableValueTypes.put("object", "1");
    }

    /**
     * Creates a new param tag with no parent.
     */
    public Param()
    {
        super("param");
    }

    /**
     * Creates a new param tag with an ObjectTag parent.
     *
     * @param parent The parent.
     */
    public Param(ObjectTag parent)
    {
        super("param", parent);
    }

    /**
     * Creates a new param tag with an Applet parent.
     *
     * @param parent The parent.
     */
    public Param(Applet parent)
    {
        super("param", parent);
    }

    /**
     * Sets the mime type of the param.
     *
     * @param type The type.
     */
    public void setType(String type)
    {
        setAttribute("type", type);
    }

    /**
     * Returns the mime type of the param.
     *
     * @return String
     */
    public String getType()
    {
        return getAttributeValue("type");
    }

    /**
     * Sets the name of the object.
     *
     * @param name The name.
     */
    public void setName(String name)
    {
        setAttribute("name", name);
    }

    /**
     * Returns the name of the object.
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
        setAttribute("value", value);
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
     * Sets the value type of the param.
     *
     * @param type The value type.
     * @throws IllegalArgumentException if the type is not data, ref, or object if this is strict.
     */
    public void setValueType(String type) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (_allowableValueTypes.get(type) == null)
            {
                throw new IllegalArgumentException("value type: "+type+" is invalid.");
            }
        }
        super.setAttribute("valuetype", type);
    }

    /**
     * Returns the value type of the param.
     *
     * @return String
     */
    public String getValueType()
    {
        return getAttributeValue("valuetype");
    }

    /**
     * Overrides setAttribute to make sure that valuetype is valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the attribute is valuetype and the value is not valid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "valuetype".equals(name) )
        {
            setValueType(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    /**
     * This cannot create text content, so it throws a IllegalMarkupException if this is strict.
     *
     * @param txt Not used.
     * @return TextContent
     * @throws IllegalMarkupException
     */
    public TextContent createTextContent(String txt) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text content.");
        else return super.createTextContent(txt);
    }

    /**
     * This cannot create a table, so it throws an IllegalMarkupException if this is strict.
     *
     * @return Table
     * @throws IllegalMarkupException
     */
    public Table createTable() throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create a tables.");
        else return super.createTable();
    }

    /**
     * This cannot create an image, so it throws an IllegalMarkupException if this is strict.
     *
     * @param src The src of the image.
     * @return Img
     * @throws IllegalMarkupException
     */
    public Img createImage(String src) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create images.");
        else return super.createImage(src);
    }

    /**
     * This cannot create a text effect, so it throws an IllegalMarkupException if this is strict.
     *
     * @param type The type of text effect.
     * @throws IllegalMarkupException
     */
    public TextEffect createTextEffect(TextEffectType type) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text effects.");
        else return super.createTextEffect(type);
    }
}