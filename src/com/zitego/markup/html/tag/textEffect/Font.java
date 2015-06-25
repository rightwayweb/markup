package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html font tag. A font tag has the following attributes:
 * color, face, and size. This class must have an HtmlMarkupTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Font.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Font extends TextEffect
{
    /**
     * Creates a new font tag with no parent.
     */
    public Font()
    {
        super("font");
    }
    
    /**
     * Creates a new font tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Font(HtmlMarkupTag parent)
    {
        super("font", parent);
    }

    /**
     * Sets the color of the font.
     *
     * @param color The color.
     */
    public void setColor(String color)
    {
        setAttribute("color", color);
    }

    /**
     * Returns the color.
     *
     * @return String
     */
    public String getColor()
    {
        return getAttributeValue("color");
    }

    /**
     * Sets the face of the font.
     *
     * @param face The face.
     */
    public void setFace(String face)
    {
        setAttribute("face", face);
    }

    /**
     * Returns the face.
     *
     * @return String
     */
    public String getFace()
    {
        return getAttributeValue("face");
    }

    /**
     * Sets the size of the font.
     *
     * @param size The size.
     */
    public void setSize(String size)
    {
        setAttribute("size", size);
    }

    /**
     * Returns the size.
     *
     * @return String
     */
    public String getSize()
    {
        return getAttributeValue("size");
    }

    public TextEffectType getType()
    {
        return TextEffectType.FONT;
    }
}