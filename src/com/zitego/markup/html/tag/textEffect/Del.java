package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * This class represents an html del tag. A del tag has two attributes. They are
 * cite and datetime. This class must have an HtmlMarkupTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Del.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Del extends TextEffect
{
    /** The format the datetime attribute must be in (yyyyMMdd). */
    protected static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd");

    /**
     * Creates a new del tag with no parent.
     */
    public Del()
    {
        super("del");
    }
    
    /**
     * Creates a new del tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Del(HtmlMarkupTag parent)
    {
        super("del", parent);
    }

    /**
     * Sets the cite url of the tag.
     *
     * @param cite The url.
     */
    public void setCite(String cite)
    {
        setAttribute("cite", cite);
    }

    /**
     * Returns the cite url of the tag.
     *
     * @return String
     */
    public String getCite()
    {
        return getAttributeValue("cite");
    }

    /**
     * Sets the date/time of the inserted text in the format of yyyyMMdd.
     *
     * @param dt The date/time string.
     * @throws IllegalArgumentException if the date string is not valid.
     */
    public void setDateTime(String dt) throws IllegalArgumentException
    {
        //Validate by parsing
        try
        {
            FORMAT.parse(dt);
            super.setAttribute("datetime", dt);
        }
        catch (ParseException pe)
        {
            throw new IllegalArgumentException("datetime must be in yyyyMMdd format: "+dt+" is invalid.");
        }
    }

    /**
     * Returns the date/time the text was inserted.
     *
     * @return String
     */
    public String getDateTime()
    {
        return getAttributeValue("datetime");
    }

    /**
     * Overrides setAttribute to make sure that the date/time string is in the proper format.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the attribute is datetime and the value is not valid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "datetime".equals(name) )
        {
            setDateTime(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    public TextEffectType getType()
    {
        return TextEffectType.DELETED;
    }
}