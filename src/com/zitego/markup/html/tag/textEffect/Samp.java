package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html samp tag. A samp tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Samp.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Samp extends TextEffect
{
    /**
     * Creates a new samp tag with no parent.
     */
    public Samp()
    {
        super("samp");
    }
    
    /**
     * Creates a new samp tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Samp(HtmlMarkupTag parent)
    {
        super("samp", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.SAMP;
    }
}