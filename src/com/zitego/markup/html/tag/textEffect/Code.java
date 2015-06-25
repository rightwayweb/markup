package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html code tag. A code tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Code.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Code extends TextEffect
{
    /**
     * Creates a new code tag with no parent.
     */
    public Code()
    {
        super("code");
    }
    
    /**
     * Creates a new code tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Code(HtmlMarkupTag parent)
    {
        super("code", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.CODE;
    }
}