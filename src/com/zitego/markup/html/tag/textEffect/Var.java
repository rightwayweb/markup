package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html var tag. A var tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Var.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Var extends TextEffect
{
    /**
     * Creates a new var tag with no parent.
     */
    public Var()
    {
        super("var");
    }
    
    /**
     * Creates a new var tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Var(HtmlMarkupTag parent)
    {
        super("var", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.VAR;
    }
}