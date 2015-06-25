package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html strong tag. A strong tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Strong.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Strong extends TextEffect
{
    /**
     * Creates a new sub tag with no parent.
     */
    public Strong()
    {
        super("strong");
    }
    
    /**
     * Creates a new strong tag with an HtmlMarkupTag parent.
     *
     * @param HtmlMarkupTag The parent.
     */
    public Strong(HtmlMarkupTag parent)
    {
        super("strong", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.STRONG;
    }
}