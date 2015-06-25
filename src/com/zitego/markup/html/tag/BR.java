package com.zitego.markup.html.tag;

import com.zitego.markup.TextContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;

/**
 * This class represents an html line break tag. A line break tag has no attributes
 * and no end tag. This class must have an HtmlMarkupTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: BR.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see HtmlMarkupTag#addLineBreak()
 */
public class BR extends HtmlMarkupTag
{
    /**
     * Creates a new br tag with no parent.
     */
    public BR()
    {
        super("br");
        setHasEndTag(false);
        setAddClosingSlashInStart(true);
    }

    /**
     * Creates a new br tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public BR(HtmlMarkupTag parent)
    {
        super("br", parent);
        setHasEndTag(false);
        setAddClosingSlashInStart(true);
    }

    /**
     * This cannot create text content, so it throws a IllegalMarkupException if this is set to strict.
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
     * This cannot create a table, so it throws an IllegalMarkupException if this is set to strict.
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
     * This cannot create an image, so it throws an IllegalMarkupException if this is set to strict.
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
     * This cannot create a text effect, so it throws an IllegalMarkupException if this is set to strict.
     *
     * @param type The type of text effect.
     * @throws IllegalMarkupException
     */
    public TextEffect createTextEffect(TextEffectType type) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text effects.");
        else return super.createTextEffect(type);
    }

    protected boolean addToParentOnInit()
    {
        return false;
    }
}