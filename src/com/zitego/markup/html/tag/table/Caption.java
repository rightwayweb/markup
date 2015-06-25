package com.zitego.markup.html.tag.table;

import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This class represents an html caption tag. A caption tag has only one
 * attribute. This attribute is align. Align can be left, right, top (default),
 * and bottom. This class must have an HtmlMarkupTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Caption.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Table#createCaption(String)
 * @see Table#createCaption(String, com.zitego.markup.html.tag.textEffect.TextEffectType)
 */
public class Caption extends EventDrivenTag
{
    /**
     * Creates a new caption tag with no parent.
     */
    public Caption()
    {
        super("caption");
    }
    
    /**
     * Creates a new caption tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Caption(HtmlMarkupTag parent)
    {
        super("caption", parent);
    }

    /**
     * Sets the alignment of the caption.
     *
     * @param align The align option.
     * @throws IllegalArgumentException if the alignment is not top, bottom, left, or right and this is strict.
     */
    public void setAlign(String align) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if ( !"top".equalsIgnoreCase(align) &&
                 !"left".equalsIgnoreCase(align) &&
                 !"bottom".equalsIgnoreCase(align) &&
                 !"right".equalsIgnoreCase(align) )
            {
                throw new IllegalArgumentException("align option: "+align+" is invalid.");
            }
        }
        setAttribute("align", align);
    }

    /**
     * Returns the alignment of the caption.
     *
     * @return String
     */
    public String getAlign()
    {
        return getAttributeValue("align");
    }
}