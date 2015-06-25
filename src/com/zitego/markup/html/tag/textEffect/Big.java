package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html big tag. A big tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Big.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Big extends TextEffect
{
    /**
     * Creates a new Big tag with no parent.
     */
    public Big()
    {
        super("big");
    }
    
    /**
     * Creates a new Big tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Big(HtmlMarkupTag parent)
    {
        super("big", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.BIG;
    }
}