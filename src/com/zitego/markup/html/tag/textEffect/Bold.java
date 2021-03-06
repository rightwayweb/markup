package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html bold tag. A bold tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Bold.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Bold extends TextEffect
{
    /**
     * Creates a new B tag with no parent.
     */
    public Bold()
    {
        super("b");
    }
    
    /**
     * Creates a new B tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Bold(HtmlMarkupTag parent)
    {
        super("b", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.BOLD;
    }
}