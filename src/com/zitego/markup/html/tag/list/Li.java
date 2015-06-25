package com.zitego.markup.html.tag.list;

/**
 * This class represents an html list item tag. List item tags do not have any
 * attributes. This class must have a Ul tag or an Ol as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Li.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Ol#createListItem()
 * @see Ul#createListItem()
 */
public class Li extends ListItem
{
    /**
     * Creates a new list item tag with no parent.
     */
    public Li()
    {
        super("li");
    }
    
    /**
     * Creates a new list item tag with an Ol parent.
     *
     * @param parent The list parent.
     */
    public Li(Ol parent)
    {
        super("li", parent);
    }

    /**
     * Creates a new list item tag with an Ul parent.
     *
     * @param parent The list parent.
     */
    public Li(Ul parent)
    {
        super("li", parent);
    }
}