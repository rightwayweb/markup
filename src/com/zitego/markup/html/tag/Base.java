package com.zitego.markup.html.tag;

import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.TextContent;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;

/**
 * This class represents an html base tag in the head section. A base tag must have
 * a parent tag and that parent tag must be a Head tag. A base tag has only one
 * attribute. This attribute is an href. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Base.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Base extends HtmlMarkupTag
{
    /**
     * Creates a new Base tag with no parent.
     */
    public Base()
    {
        super("base");
        setHasEndTag(false);
    }
    
    /**
     * Creates a new Base tag with an Head tag parent.
     *
     * @param parent The parent head tag.
     */
    public Base(Head parent)
    {
        super("base", parent);
        setHasEndTag(false);
    }

    /**
     * Creates a new Base tag with an Head tag parent and an href.
     *
     * @param parent The parent head tag.
     * @param href The href.
     */
    public Base(Head parent, String href)
    {
        this(parent);
        setHref(href);
    }

    /**
     * Sets the href of the tag.
     *
     * @param lang The href.
     */
    public void setHref(String lang)
    {
        setAttribute("href", lang);
    }

    /**
     * Returns the href of the tag.
     *
     * @return String
     */
    public String getHref()
    {
        return getAttributeValue("href");
    }

    /**
     * This cannot create text content, so it throws a IllegalMarkupException unless
     * we are not set to strict.
     *
     * @param txt Not used.
     * @return TextContent
     * @throws IllegalMarkupException
     */
    public TextContent createTextContent(String txt) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text content.");
        else return super.createTextContent(txt);
    }

     /**
     * This cannot create a table, so it throws an IllegalMarkupException unless
     * we are not set to strict.
     *
     * @return Table
     * @throws IllegalMarkupException
     */
    public Table createTable() throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create a tables.");
        else return super.createTable();
    }

    /**
     * This cannot create an image, so it throws an IllegalMarkupException unless
     * we are not set to strict.
     *
     * @param src The src.
     * @return Img
     * @throws IllegalMarkupException
     */
    public Img createImage(String src) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create images.");
        else return super.createImage(src);
    }

    /**
     * This cannot create a text effect, so it throws an IllegalMarkupException unless
     * we are not set to strict.
     *
     * @param type The text effect.
     * @throws IllegalMarkupException
     */
    public TextEffect createTextEffect(TextEffectType type) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text effects.");
        else return super.createTextEffect(type);
    }
}