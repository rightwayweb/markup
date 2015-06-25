package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import java.util.Hashtable;

/**
 * This class represents an html pre tag. A pre tag must have a parent tag and
 * that parent tag must be an EventDrivenTag. A pre tag has only one attribute.
 * This is width. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Pre.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Pre extends TextEffect
{
    /**
     * Creates a new Pre tag with no parent.
     */
    public Pre()
    {
        super("pre");
    }

    /**
     * Creates a new Pre tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Pre(HtmlMarkupTag parent)
    {
        super("pre", parent);
    }

    /**
     * Sets the number of characters wide this pre is.
     *
     * @param width The number of characters.
     * @throws IllegalArgumentException if the width is negative and this is strict.
     */
    public void setWidth(int width) throws IllegalArgumentException
    {
        if (width < 0 && isStrict() )
        {
            throw new IllegalArgumentException("width must be positive.");
        }
        super.setAttribute( "width", String.valueOf(width) );
    }

    /**
     * Removes the width attribute.
     */
    public void removeWidth()
    {
        removeAttribute("width");
    }

    /**
     * Returns the width in characters.
     *
     * @return int
     */
    public int getWidth()
    {
        return getIntAttributeValue("width");
    }

    /**
     * Overrides setAttribute to make sure that width is numeric.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is width and the value is not numeric.
     */
    public void setAttribute(String name, String val) throws NumberFormatException
    {
        if (name != null) name = name.toLowerCase();
        if ( "width".equals(name) )
        {
            setWidth( Integer.parseInt(val) );
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    public TextEffectType getType()
    {
        return TextEffectType.PRE;
    }

    public boolean trimChildText()
    {
        return false;
    }
}