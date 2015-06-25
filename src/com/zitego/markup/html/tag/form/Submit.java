package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html form submit button. A submit button must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form. A submit button has no
 * attributes. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Submit.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Submit extends Input
{
    /**
     * Creates a new submit tag with no HtmlMarkupTag parent.
     */
    public Submit()
    {
        super("submit");
    }
    
    /**
     * Creates a new submit tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Submit(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new submit button with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Submit(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "submit");
    }

    /**
     * Creates a new submit button.
     *
     * @param form The parent form.
     */
    public Submit(Form form)
    {
        this(form, form);
    }
}