package com.zitego.markup.html.tag;

/**
 * This class represents an html area tag. An area tag has only an id and name attribute.
 * This class must have an EventDrivenTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Area.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Area extends EventDrivenTag
{
    /**
     * Creates a new Area tag with no parent.
     */
    public Area()
    {
        super("area");
    }
    
    /**
     * Creates a new Area tag with a Map parent.
     *
     * @param parent The parent.
     */
    public Area(Map parent)
    {
        super("area", parent);
    }

    /**
     * Sets the name attribute of the tag.
     *
     * @param name The name attribute.
     */
    public void setNameAttribute(String name)
    {
        setAttribute("name", name);
    }

    /**
     * Returns the name attribute of the tag.
     *
     * @return String
     */
    public String getNameAttribute()
    {
        return getAttributeValue("name");
    }
}