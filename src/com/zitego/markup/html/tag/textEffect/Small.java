package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html small tag. A small tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Small.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Small extends TextEffect
{
    /**
     * Creates a new small tag with no parent.
     */
    public Small()
    {
        super("small");
    }
    
    /**
     * Creates a new small tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Small(HtmlMarkupTag parent)
    {
        super("small", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.SMALL;
    }
}