package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html form password element. A password element must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form. It has no attributes.
 * This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Password.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Password extends Text
{
    /**
     * Creates a new password element with no parent.
     */
    public Password()
    {
        super("password");
    }

    /**
     * Creates a new password tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Password(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new password element.
     *
     * @param Form The parent form.
     */
    public Password(Form form)
    {
        this(form, form);
    }

    /**
     * Creates a new password element with an HtmlMarkupTag parent.
     *
     * @param HtmlMarkupTag The parent HtmlMarkupTag.
     * @param Form The parent form.
     */
    public Password(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "password");
    }

    protected boolean removeType()
    {
        return false;
    }
}