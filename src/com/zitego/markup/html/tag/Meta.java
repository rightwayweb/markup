package com.zitego.markup.html.tag;

import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.TextContent;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;

/**
 * This class represents an html meta tag in the head section. A meta tag must have
 * a parent tag and that parent tag must be a Head tag. A meta tag has a couple
 * attributes. These attributes are name, content, and http-equiv. This class is up
 * to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Meta.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Head#createMetaTag()
 */
public class Meta extends HtmlMarkupTag
{
    /**
     * Creates a new Meta tag with no parent.
     */
    public Meta()
    {
        super("meta");
        setIsOnOwnLine(true);
        setHasEndTag(false);
    }
    
    /**
     * Creates a new Meta tag with a Head tag parent.
     *
     * @param parent The parent head tag.
     */
    public Meta(Head parent)
    {
        super("meta", parent);
        setIsOnOwnLine(true);
        setHasEndTag(false);
    }

    /**
     * Sets the name of the tag.
     *
     * @param name The charset.
     */
    public void setName(String name)
    {
        setAttribute("name", name);
    }

    /**
     * Returns the name of the tag.
     *
     * @return String
     */
    public String getName()
    {
        return getAttributeValue("name");
    }

    /**
     * Sets the content of the tag.
     *
     * @param content The content.
     */
    public void setContent(String content)
    {
        setAttribute("content", content);
    }

    /**
     * Returns the content of the tag.
     *
     * @return String
     */
    public String getContent()
    {
        return getAttributeValue("content");
    }

    /**
     * Sets the http-equiv of the tag.
     *
     * @param httpEquiv The http-equiv.
     */
    public void setHttpEquiv(String httpEquiv)
    {
        setAttribute("http-equiv", httpEquiv);
    }

    /**
     * Returns the http-equiv of the tag.
     *
     * @return String
     */
    public String getHttpEquiv()
    {
        return getAttributeValue("http-equiv");
    }

    /**
     * This cannot create text content, so it throws a IllegalMarkupException.
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
     * This cannot create a table, so it throws an IllegalMarkupException.
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
     * This cannot create an image, so it throws an IllegalMarkupException.
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