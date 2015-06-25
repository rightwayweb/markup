package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html form radio element. A radio element must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form. It has no attributes.
 * This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Radio.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Radio extends Checkbox
{
    /**
     * Creates a new radio tag with no parent.
     */
    public Radio()
    {
        super("radio");
    }
    
    /**
     * Creates a new radio tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Radio(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new radio element with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Radio(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "radio");
    }

    /**
     * Creates a new radio element.
     *
     * @param form The parent form.
     */
    public Radio(Form form)
    {
        this(form, form);
    }
}