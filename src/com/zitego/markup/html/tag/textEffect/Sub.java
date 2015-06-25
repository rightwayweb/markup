package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html sub tag. A sub tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Sub.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Sub extends TextEffect
{
    /**
     * Creates a new sub tag with no parent.
     */
    public Sub()
    {
        super("sub");
    }
    
    /**
     * Creates a new sub tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Sub(HtmlMarkupTag parent)
    {
        super("sub", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.SUBSCRIPT;
    }
}