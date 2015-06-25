package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html label tag. A label tag has one attribute. It is
 * for. This class must have an HtmlMarkupTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Label.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Label extends EventDrivenTag
{
    /**
     * Creates a new label tag with no parent.
     */
    public Label()
    {
        super("label");
    }
    
    /**
     * Creates a new label tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Label(HtmlMarkupTag parent)
    {
        super("label", parent);
    }

    /**
     * Sets the for id of the tag.
     *
     * @param id The id.
     */
    public void setFor(String id)
    {
        setAttribute("for", id);
    }

    /**
     * Returns the for id of the tag.
     *
     * @return String
     */
    public String getFor()
    {
        return getAttributeValue("for");
    }
}