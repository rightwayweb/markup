package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html italics tag. An italics tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Italic.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Italic extends TextEffect
{
    /**
     * Creates a new I tag with no parent.
     */
    public Italic()
    {
        super("i");
    }
    
    /**
     * Creates a new I tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Italic(HtmlMarkupTag parent)
    {
        super("i", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.ITALIC;
    }
}