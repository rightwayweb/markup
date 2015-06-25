package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html cite tag. A cite tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Cite.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Cite extends TextEffect
{
    /**
     * Creates a new cite tag with no parent.
     */
    public Cite()
    {
        super("cite");
    }
    
    /**
     * Creates a new cite tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Cite(HtmlMarkupTag parent)
    {
        super("cite", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.CITE;
    }
}