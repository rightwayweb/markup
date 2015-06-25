package com.zitego.markup.html.tag.list;

import com.zitego.markup.html.tag.EventDrivenTag;

/**
 * This class represents an html list item. The class is abstract so must
 * be extended.
 *
 * @author John Glorioso
 * @version $Id: ListItem.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public abstract class ListItem extends EventDrivenTag
{
    /**
     * Creates a new list item tag with a tag name and no parent.
     *
     * @param tagname The tag name.
     */
    protected ListItem(String tagname)
    {
        super(tagname);
        setHasEndTag(false);
    }

    /**
     * Creates a new list item tag with a List parent and a tag name.
     *
     * @param tagname The tag name.
     * @param parent The list parent.
     */
    protected ListItem(String tagname, List parent)
    {
        super(tagname, parent);
        setHasEndTag(false);
    }
}