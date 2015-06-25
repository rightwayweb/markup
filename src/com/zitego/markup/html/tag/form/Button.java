package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html form button tag. A button tag must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form. A button tag has no
 * attributes. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Button.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Button extends Input
{
    /**
     * Creates a new button tag with no parent.
     */
    public Button()
    {
        super("button");
    }
    
    /**
     * Creates a new button tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Button(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new button tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Button(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "button");
    }

    /**
     * Creates a new button tag.
     *
     * @param form The parent form.
     */
    public Button(Form form)
    {
        this(form, form);
    }
}