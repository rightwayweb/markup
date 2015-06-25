package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.format.*;

/**
 * This class represents an html Em (emphasize) tag. An Em tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Em.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Em extends TextEffect
{
    /**
     * Creates a new Em tag with no parent.
     */
    public Em()
    {
        super("em");
    }
    
    /**
     * Creates a new Em tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Em(HtmlMarkupTag parent)
    {
        super("em", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.EMPHASIS;
    }
}