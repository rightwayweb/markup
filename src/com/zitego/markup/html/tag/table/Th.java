package com.zitego.markup.html.tag.table;

import java.util.Hashtable;

/**
 * This class represents an html th tag. A th tag must have a parent tag and
 * that parent tag must be Tr. A th tag has many attributes. These
 * attributes are align, bgcolor, colspan, height, nowrap, rowspan, valign,
 * and width. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Th.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Tr#createHeaderCell
 */
public class Th extends Td
{
    /**
     * Creates a new Th tag with no parent.
     */
    public Th()
    {
        super();
        setTagName("th");
    }
    
    /**
     * Creates a new Th tag with a Tr parent.
     *
     * @param Tr The parent Tr tag.
     */
    public Th(Tr parent)
    {
        super(parent);
        setTagName("th");
    }
}