package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html sup tag. A sup tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Sup.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Sup extends TextEffect
{
    /**
     * Creates a new sup tag with no parent.
     */
    public Sup()
    {
        super("sup");
    }
    
    /**
     * Creates a new sup tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Sup(HtmlMarkupTag parent)
    {
        super("sup", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.SUPERSCRIPT;
    }
}