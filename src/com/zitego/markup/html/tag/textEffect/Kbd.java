package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html kbd tag. A kbd tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Kbd.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Kbd extends TextEffect
{
    /**
     * Creates a new kbd tag with no parent.
     */
    public Kbd()
    {
        super("kbd");
    }
    
    /**
     * Creates a new kbd tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Kbd(HtmlMarkupTag parent)
    {
        super("kbd", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.KEYBOARD;
    }
}