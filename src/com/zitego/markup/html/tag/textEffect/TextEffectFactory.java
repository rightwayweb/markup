package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * Handles creating different text effect tags.
 *
 * @author John Glorioso
 * @version $Id: TextEffectFactory.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectType
 * @see com.zitego.markup.html.tag.HtmlMarkupTag#createTextEffect(TextEffectType)
 */
public class TextEffectFactory
{
    /**
     * Returns a text effect tag given the parent and the type.
     *
     * @param type The type.
     * @param parent The parent tag.
     * @return TextEffect
     * @throws IllegalArgumentException if the effect type is not valid.
     */
    public static TextEffect getTextEffect(TextEffectType type, HtmlMarkupTag parent) throws IllegalArgumentException
    {
        if (type == TextEffectType.CITE) return new Cite(parent);
        else if (type == TextEffectType.BIG) return new Big(parent);
        else if (type == TextEffectType.BOLD) return new Bold(parent);
        else if (type == TextEffectType.CODE) return new Code(parent);
        else if (type == TextEffectType.DEFINITION) return new Dfn(parent);
        else if (type == TextEffectType.DELETED) return new Del(parent);
        else if (type == TextEffectType.EMPHASIS) return new Em(parent);
        else if (type == TextEffectType.FONT) return new Font(parent);
        else if (type == TextEffectType.INSERT) return new Ins(parent);
        else if (type == TextEffectType.ITALIC) return new Italic(parent);
        else if (type == TextEffectType.KEYBOARD) return new Kbd(parent);
        else if (type == TextEffectType.PRE) return new Pre(parent);
        else if (type == TextEffectType.SAMP) return new Samp(parent);
        else if (type == TextEffectType.SMALL) return new Small(parent);
        else if (type == TextEffectType.STRIKE) return new Strike(parent);
        else if (type == TextEffectType.STRONG) return new Strong(parent);
        else if (type == TextEffectType.SUBSCRIPT) return new Sub(parent);
        else if (type == TextEffectType.SUPERSCRIPT) return new Sup(parent);
        else if (type == TextEffectType.TELETYPE) return new Tt(parent);
        else if (type == TextEffectType.UNDERLINE) return new Underline(parent);
        else if (type == TextEffectType.VAR) return new Var(parent);
        else if (type == TextEffectType.XMP) return new Xmp(parent);
        else throw new IllegalArgumentException("Invalid text effect type: "+type);
    }
}