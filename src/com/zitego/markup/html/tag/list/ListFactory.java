package com.zitego.markup.html.tag.list;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class creates lists depending on the list type passed in.
 *
 * @author John Glorioso
 * @version $Id: ListFactory.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see ListType
 * @see com.zitego.markup.html.tag.HtmlMarkupTag#createList(ListType)
 */
public class ListFactory
{
    /**
     * Returns a list tag given the parent and the type.
     *
     * @param type The type.
     * @param parent The parent tag.
     * @return List
     * @throws IllegalArgumentException if the list type is not valid.
     */
    public static List getList(ListType type, HtmlMarkupTag parent) throws IllegalArgumentException
    {
        if (type == ListType.ORDERED) return new Ol(parent);
        else if (type == ListType.UNORDERED) return new Ul(parent);
        else if (type == ListType.DEFINITION) return new Dl(parent);
        else throw new IllegalArgumentException("Invalid list type: "+type);
    }
}