package com.zitego.markup.html.tag.list;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import java.util.Hashtable;

/**
 * This class represents an html definition list tag. Definition list tags have no
 * attributes. This class must have an HtmlMarkupTag as the parent. This class is
 * up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Dl.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see ListFactory#getList(ListType, HtmlMarkupTag)
 */
public class Dl extends List
{
    /**
     * Creates a new list tag with no parent.
     */
    public Dl()
    {
        super("dl");
    }
    
    /**
     * Creates a new list tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Dl(HtmlMarkupTag parent)
    {
        super("dl", parent);
    }

    /**
     * This will create a definition term, then a definition description and so on.
     * This order is required for definition lists.
     *
     * @return ListItem
     */
    public ListItem createListItem() throws IllegalArgumentException
    {
        //Need to see which we need to create now. The order has to be dt then dd
        MarkupContent content = getLastBodyContent();
        //If there is no list items yet or the last item was not a term (meaning it was either a
        //non list item or it was a description), then a term is next
        if ( (content == null || !(content instanceof Dt)) )
        {
            return new Dt(this);
        }
        //If there are list items and the last one was
        else
        {
            return new Dd(this);
        }
    }

    public ListType getType()
    {
        return ListType.DEFINITION;
    }
}