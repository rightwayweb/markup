package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html form reset button tag. A reset button must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form. A reset button has no
 * attributes. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Reset.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Reset extends Input
{
    /**
     * Creates a new reset tag with no parent.
     */
    public Reset()
    {
        super("reset");
    }
    
    /**
     * Creates a new reset tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Reset(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new reset button with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Reset(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "reset");
    }

    /**
     * Creates a new reset button.
     *
     * @param form The parent form.
     */
    public Reset(Form form)
    {
        this(form, form);
    }
}