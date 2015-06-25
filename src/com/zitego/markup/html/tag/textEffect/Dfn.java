package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html dfn tag. A dfn tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Dfn.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Dfn extends TextEffect
{
    /**
     * Creates a new dfn tag with a no parent.
     */
    public Dfn()
    {
        super("dfn");
    }
    
    /**
     * Creates a new dfn tag with a an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Dfn(HtmlMarkupTag parent)
    {
        super("dfn", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.DEFINITION;
    }
}