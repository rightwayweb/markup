package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html fieldset tag which draws a small border around form field
 * items. This tag is supported since IE 4.01B2, and Netcape/Mozilla 6.01B1. A fieldset tag
 * has no attributes. This class must have an HtmlMarkupTag as the parent. This class is up
 * to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: FieldSet.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class FieldSet extends EventDrivenTag
{
    /**
     * Creates a new FieldSet tag with no parent.
     */
    public FieldSet()
    {
        super("fieldset");
    }
    
    /**
     * Creates a new FieldSet tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public FieldSet(HtmlMarkupTag parent)
    {
        super("fieldset", parent);
    }
}