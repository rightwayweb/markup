package com.zitego.markup.html.tag;

/**
 * This class represents an html map tag used in images. A map tag has only an id and a name
 * attribute. This class must have an HtmlMarkupTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Map.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Map extends EventDrivenTag
{
    /**
     * Creates a new Map tag with no parent.
     */
    public Map()
    {
        super("map");
    }
    
    /**
     * Creates a new Map tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Map(HtmlMarkupTag parent)
    {
        super("map", parent);
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