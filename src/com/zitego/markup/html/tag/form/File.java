package com.zitego.markup.html.tag.form;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html form file tag. A file must have a parent tag and
 * that parent tag must be an HtmlMarkupTag or a Form. A file tag has one attribute that
 * specifies whether it is checked or not. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: File.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class File extends Input
{
    /**
     * Creates a new file tag with no parent..
     */
    public File()
    {
        super("file");
    }
    
    /**
     * Creates a new file tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public File(HtmlMarkupTag parent)
    {
        this(parent, null);
    }

    /**
     * Creates a new file tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param form The parent form.
     */
    public File(HtmlMarkupTag parent, Form form)
    {
        super(parent, form, "file");
    }

    /**
     * Creates a new file tag.
     *
     * @param form The parent form.
     */
    public File(Form form)
    {
        this(form, form);
    }

    /**
     * Sets the list of allowable mimetypes.
     *
     * @param types A comma delimited string of mime-types.
     */
    public void setAccept(String types)
    {
        setAttribute("accept", types);
    }

    /**
     * Returns the comma delimited string of mime types to accept.
     *
     * @return String
     */
    public String getAccept()
    {
        return getAttributeValue("accept");
    }

    /**
     * Overidden to insure it is not set unless this is not strict.
     *
     * @param value The value that is ignored.
     * @throws IllegalArgumentException
     */
    public void setValue(String value) throws IllegalArgumentException
    {
        if ( isStrict() ) throw new IllegalArgumentException("Cannot set value in File input type.");
        else super.setValue(value);
    }
}