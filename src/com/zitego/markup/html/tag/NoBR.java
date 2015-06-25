package com.zitego.markup.html.tag;

import com.zitego.markup.TextContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;

/**
 * This class represents an html non-line break tag. A non-line break tag has no attributes.
 * This class must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: NoBR.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see HtmlMarkupTag#addLineBreak()
 */
public class NoBR extends HtmlMarkupTag
{
    /**
     * Creates a new nobr tag with no parent.
     */
    public NoBR()
    {
        super("nobr");
    }
    
    /**
     * Creates a new nobr tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public NoBR(HtmlMarkupTag parent)
    {
        super("nobr", parent);
    }
}