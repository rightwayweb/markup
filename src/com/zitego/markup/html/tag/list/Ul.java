package com.zitego.markup.html.tag.list;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import java.util.Hashtable;

/**
 * This class represents an html unordered list tag. Unordered list tags have only
 * one attribute. It is type. This class must have an HtmlMarkupTag as the parent. This
 * class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Ul.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see ListFactory#getList(ListType, HtmlMarkupTag)
 */
public class Ul extends List
{
    /** The valid types. */
    private static Hashtable _types = new Hashtable();
    static
    {
        _types.put("disc", "1");
        _types.put("square", "1");
        _types.put("circle", "1");
    }

    /**
     * Creates a new list tag with no parent.
     */
    public Ul()
    {
        super("ul");
    }
    
    /**
     * Creates a new list tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Ul(HtmlMarkupTag parent)
    {
        super("ul", parent);
    }

    /**
     * Sets the type for the list. Type can be be disc, square, or circle.
     *
     * @param type The type.
     * @throws IllegalArgumentException if the type is invalid and this is strict.
     */
    public void setTypeAttribute(String type) throws IllegalArgumentException
    {
        if (type != null)
        {
            type = type.toLowerCase();
            if ( isStrict() )
            {
                if (_types.get(type) == null)
                {
                    throw new IllegalArgumentException("type: "+type+" is invalid for list type UNORDERED");
                }
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
     * @throws IllegalArgumentException if the attribute is type the value is invalid.
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
        return ListType.UNORDERED;
    }
}