package com.zitego.markup.html.tag.list;

/**
 * This class represents an html definition list data item tag. Definition
 * list item tags do not have any attributes. This class must have a Dl as the
 * parent. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Dd.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Dl#createListItem()
 */
public class Dd extends ListItem
{
    /**
     * Creates a new list item tag with no parent.
     */
    public Dd()
    {
        super("dd");
    }
    
    /**
     * Creates a new list item tag with an Dl parent.
     *
     * @param parent The list parent.
     */
    public Dd(Dl parent)
    {
        super("dd", parent);
    }
}