package com.zitego.markup.html.tag;

import com.zitego.markup.*;
import com.zitego.markup.html.HtmlTextContent;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;
import com.zitego.format.*;

/**
 * This class represents an html title tag. A title tag must have
 * a parent tag and that parent tag must be a Head tag. A title tag has no
 * attributes. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Title.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Title extends HtmlMarkupTag
{
    /** The title. */
    protected HtmlTextContent _text;

    /**
     * Creates a new Title tag with no parent. This is bad form and you should use
     * the title with a Head parent.
     */
    public Title()
    {
        super("title");
        setIsOnOwnLine(true);
    }

    /**
     * Creates a new Title tag with an html parent. This is bad form and you should use
     * the title with head parent instead.
     *
     * @param parent The parent html tag.
     */
    public Title(Html parent)
    {
        super("title", parent);
        setIsOnOwnLine(true);
    }

    /**
     * Creates a new Title tag with a Head tag parent.
     *
     * @param parent The parent head tag.
     */
    public Title(Head parent)
    {
        super("title", parent);
        setIsOnOwnLine(true);
    }

    /**
     * Creates a new Title tag with a Head tag parent.
     *
     * @param parent The parent head tag.
     * @param text The title text.
     */
    public Title(Head parent, String text)
    {
        this(parent);
        setText(text);
    }

    /**
     * Sets the title text.
     *
     * @param text The title text.
     */
    public void setText(String text)
    {
        if (_text == null) _text = new HtmlTextContent(this, text);
        else _text.setText(text);
    }

    /**
     * Returns the title text.
     *
     * @return String
     */
    public String getText()
    {
        return (_text != null ? _text.getText() : null);
    }

    /**
     * This cannot create text content, so it throws a IllegalMarkupException unless strict is
     * not set to true.
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
     * This cannot create a table, so it throws an IllegalMarkupException unless strict
     * is set to true.
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
     * This cannot create an image, so it throws an IllegalMarkupException unless strict
     * is set to true.
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
     * This cannot create a text effect, so it throws an IllegalMarkupException unless strict
     * is set to true.
     *
     * @param type The type of effect to create.
     * @throws IllegalMarkupException
     */
    public TextEffect createTextEffect(TextEffectType type) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text effects.");
        else return super.createTextEffect(type);
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);
        //Set the title
        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            if (c instanceof HtmlTextContent)
            {
                _text = (HtmlTextContent)c;
                break;
            }
        }
    }
}