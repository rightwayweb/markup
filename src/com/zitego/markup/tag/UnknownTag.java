package com.zitego.markup.tag;

import com.zitego.markup.MarkupBody;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;

/**
 * This class is here so that the markup factory does not fail every time someone creates
 * a document with an unexpected tag. This occurs quite frequently since most browsers
 * conveniently ignore that which is does not understand (much like many people I know).
 *
 * @author John Glorioso
 * @version $Id: UnknownTag.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class UnknownTag extends MarkupTag
{
    /**
     * Creates a new unknown tag with a name.
     *
     * @param tag The tag name.
     */
    public UnknownTag(String tag)
    {
        super(tag);
        setHasEndTag(false);
    }

    /**
     * Creates a new unknown tag with a name and a parent.
     *
     * @param tag The tag name.
     * @param parent The parent tag.
     */
    public UnknownTag(String tag, MarkupTag parent)
    {
        super(tag, parent);
        setHasEndTag(false);
    }
}