package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html strike tag. A strike tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Strike.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Strike extends TextEffect
{
    /**
     * Creates a new strike tag with no parent.
     */
    public Strike()
    {
        super("strike");
    }
    
    /**
     * Creates a new strike tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Strike(HtmlMarkupTag parent)
    {
        super("strike", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.STRIKE;
    }
}