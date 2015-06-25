package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html xmp tag. An xmp tag has no attributes.
 * This class must have an HtmlMarkupTag as the parent. This class is up to
 * date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Xmp.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public class Xmp extends TextEffect
{
    /**
     * Creates a new xmp tag with no parent.
     */
    public Xmp()
    {
        super("xmp");
    }

    /**
     * Creates a new xmp tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Xmp(HtmlMarkupTag parent)
    {
        super("xmp", parent);
    }

    public TextEffectType getType()
    {
        return TextEffectType.XMP;
    }

    public boolean trimChildText()
    {
        return false;
    }
}