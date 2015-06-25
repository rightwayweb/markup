package com.zitego.markup.xml;

import com.zitego.markup.*;
import com.zitego.format.*;

/**
 * This class hold multiple MarkupContents that makeup the
 * body of an XmlTag. Content can be added by calling
 * the <code>addContent(MarkupContent)</code> method. When
 * format is called, if the MarkupContent is not of type
 * XmlTag, then XmlFormatter.escape will be called on the
 * content.
 *
 * @author John Glorioso
 * @version $Id: XmlMarkupBody.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class XmlMarkupBody extends MarkupBody
{
    /**
     * Creates a new XmlMarkupBody with a parent XmlTag.
     *
     * @param XmlTag The parent.
     */
    public XmlMarkupBody(XmlTag parent)
    {
        super(parent);
    }

    public String format(FormatType type) throws UnsupportedFormatException
    {
        StringBuffer ret = new StringBuffer();
        int size = size();
        for (int i=0; i<size; i++)
        {
            MarkupContent content = (MarkupContent)get(i);
            if (content instanceof XmlTag) ret.append( content.format(type) );
            else ret.append( content.format(type) );
        }
        return ret.toString();
    }
}