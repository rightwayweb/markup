package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html u tag. A u tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Underline.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Underline extends TextEffect
{
    /**
     * Creates a new u tag with no parent.
     */
    public Underline()
    {
        super("u");
    }
    
    /**
     * Creates a new u tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Underline(HtmlMarkupTag parent)
    {
        super("u", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.UNDERLINE;
    }
}