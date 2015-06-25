package com.zitego.markup.html.tag.list;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html list. This class is abstract and must be extended
 * to implement addListItem.
 *
 * @author John Glorioso
 * @version $Id: List.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see ListFactory#getList(ListType, HtmlMarkupTag)
 */
public abstract class List extends EventDrivenTag
{
    /**
     * Creates a new list tag with a tag name and no parent.
     *
     * @param tagName The tag name.
     */
    protected List(String tagName)
    {
        super(tagName);
    }
    
    /**
     * Creates a new list tag with an HtmlMarkupTag parent and a tag name.
     *
     * @param tagName The tag name.
     * @param parent The parent.
     */
    protected List(String tagName, HtmlMarkupTag parent)
    {
        super(tagName, parent);
    }

    /**
     * Creates and adds a list item to this list.
     */
    public abstract ListItem createListItem() throws IllegalArgumentException;

    /**
     * Returns the type of list.
     *
     * @return ListType
     */
    public abstract ListType getType();
}