package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html form hidden element. A hidden element must have a
 * parent tag and that parent tag must be an HtmlMarkupTag or a Form. It has no attributes.
 * This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Hidden.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Hidden extends Input
{
    /**
     * Creates a new hidden tag with no parent.
     */
    public Hidden()
    {
        super("hidden");
    }
    
    /**
     * Creates a new hidden tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Hidden (HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new hidden element with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public Hidden(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "hidden");
    }

    /**
     * Creates a new hidden element.
     *
     * @param form The parent form.
     */
    public Hidden(Form form)
    {
        this(form, form);
    }

    /**
     * Overrides Input's setDisabled as hidden elements cannot be disabled unless this is not strict.
     *
     * @param disabled Whether the item is disabled.
     * @throws IllegalArgumentException as this is not a valid set.
     */
    public void setDisabled(boolean disabled) throws IllegalArgumentException
    {
        if ( isStrict() ) throw new IllegalArgumentException("Hidden elements cannot be disabled.");
        else super.setDisabled(disabled);
    }
}