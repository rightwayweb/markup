package com.zitego.markup.html.tag.list;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import java.util.Hashtable;

/**
 * This class represents an html ordered list tag. Ordered list tags have two optional
 * attributes. They are type and start. This class must have an HtmlMarkupTag as the
 * parent. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Ol.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see ListFactory#getList(ListType, HtmlMarkupTag)
 */
public class Ol extends List
{
    /** The valid types. */
    private static Hashtable _types = new Hashtable();
    static
    {
        _types.put("A", "1");
        _types.put("a", "1");
        _types.put("I", "1");
        _types.put("i", "1");
        _types.put("1", "1");
    }
    
    /**
     * Creates a new list tag with no parent.
     */
    public Ol()
    {
        super("ol");
    }

    /**
     * Creates a new list tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Ol(HtmlMarkupTag parent)
    {
        super("ol", parent);
    }

    /**
     * Sets the start for the ordered list.
     *
     * @param start The start.
     */
    public void setStart(String start)
    {
        setAttribute("start", start);
    }

    /**
     * Returns the start for this list.
     *
     * @return String
     */
    public String getStart()
    {
        return getAttributeValue("start");
    }

    /**
     * Sets the type for the ordered list. Type can be number (default), letter, or roman numeral.
     *
     * @param type The type.
     * @throws IllegalArgumentException if the type is invalid and this is strict.
     */
    public void setTypeAttribute(String type) throws IllegalArgumentException
    {
        if (type != null && isStrict() )
        {
            if (_types.get(type) == null)
            {
                throw new IllegalArgumentException("type: "+type+" is invalid for list type ORDERED");
            }
        }
        super.setAttribute("type", type);
    }

    /**
     * Returns the type for this list.
     *
     * @return String
     */
    public String getTypeAttribute()
    {
        return getAttributeValue("type");
    }

    /**
     * Overrides setAttribute to make sure that type is valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the attribute is type and it is invalid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "type".equals(name) )
        {
            setTypeAttribute(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    /**
     * Creates a new Li list item.
     *
     * @return Li
     */
    public ListItem createListItem()
    {
        return new Li(this);
    }

    public ListType getType()
    {
        return ListType.ORDERED;
    }
}