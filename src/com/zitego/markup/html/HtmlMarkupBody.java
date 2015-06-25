package com.zitego.markup.html;

import com.zitego.markup.*;
import com.zitego.format.*;

/**
 * This class hold multiple MarkupContents that makeup the
 * body of a parent MarkupContent. Content can be added by calling
 * the <code>addContent(MarkupContent)</code> method.
 *
 * @author John Glorioso
 * @version $Id: HtmlMarkupBody.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class HtmlMarkupBody extends MarkupBody
{
    /**
     * Creates a new HtmlMarkupBody.
     *
     * @param content The parent.
     */
    public HtmlMarkupBody(MarkupContent content)
    {
        super(content);
    }

    protected MarkupFactory getMarkupFactory()
    {
        return HtmlMarkupFactory.getInstance();
    }
}