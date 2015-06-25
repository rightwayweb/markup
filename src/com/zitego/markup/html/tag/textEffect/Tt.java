package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html tt tag. A tt tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Tt.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Tt extends TextEffect
{
    /**
     * Creates a new tt tag with no parent.
     */
    public Tt()
    {
        super("tt");
    }
    
    /**
     * Creates a new tt tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Tt(HtmlMarkupTag parent)
    {
        super("tt", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.TELETYPE;
    }
}