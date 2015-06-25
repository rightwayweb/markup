package com.zitego.markup.html.tag;

import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.TextContent;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;

/**
 * This class represents an html link tag in the head section. A link tag must have
 * a parent tag and that parent tag must be a Head tag. A link tag has a couple
 * attributes. These attributes are charset, href, rel, and type. This class is up
 * to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Link.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Link extends HtmlMarkupTag
{
    /**
     * Creates a new Link tag with no parent.
     */
    public Link()
    {
        super("link");
        setHasEndTag(false);
    }
    
    /**
     * Creates a new Link tag with an Head tag parent.
     *
     * @param parent The parent head tag.
     */
    public Link(Head parent)
    {
        super("link", parent);
        setHasEndTag(false);
    }

    /**
     * Sets the charset of the tag.
     *
     * @param charset The charset.
     */
    public void setCharset(String charset)
    {
        setAttribute("charset", charset);
    }

    /**
     * Returns the charset of the tag.
     *
     * @return String
     */
    public String getCharset()
    {
        return getAttributeValue("charset");
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
     * Sets the rel of the tag.
     *
     * @param rel The rel.
     */
    public void setRel(String rel)
    {
        setAttribute("rel", rel);
    }

    /**
     * Returns the rel of the tag.
     *
     * @return String
     */
    public String getRel()
    {
        return getAttributeValue("rel");
    }

    /**
     * Sets the type of the tag.
     *
     * @param type The type.
     */
    public void setType(String type)
    {
        setAttribute("type", type);
    }

    /**
     * Returns the type of the tag.
     *
     * @return String
     */
    public String getType()
    {
        return getAttributeValue("type");
    }

    /**
     * This cannot create text content, so it throws a IllegalMarkupException if this is strict.
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
     * This cannot create a table, so it throws an IllegalMarkupException if this is strict.
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
     * This cannot create an image, so it throws an IllegalMarkupException if this is strict.
     *
     * @param src The src of the image.
     * @return Img
     * @throws IllegalMarkupException
     */
    public Img createImage(String src) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create images.");
        else return super.createImage(src);
    }

    /**
     * This cannot create a text effect, so it throws an IllegalMarkupException if this is strict.
     *
     * @param type The type of text effect.
     * @throws IllegalMarkupException
     */
    public TextEffect createTextEffect(TextEffectType type) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text effects.");
        else return super.createTextEffect(type);
    }
}