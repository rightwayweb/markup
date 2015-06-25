package com.zitego.markup.tag;

import com.zitego.markup.TextContent;
import com.zitego.markup.MarkupContent;

/**
 * This is just a class that holds the content of an end tag to print out that is
 * otherwise unnecessary in the html structure.
 *
 * @author John Glorioso
 * @version $Id: UnnecessaryEndTag.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class UnnecessaryEndTag extends TextContent
{
    /**
     * Creates new tag with the end tag text and no parent.
     *
     * @param text The text.
     */
    public UnnecessaryEndTag(String text)
    {
        super(text);
    }

    /**
     * Creates new tag with the a parent and the end tag.
     *
     * @param parent The parent content.
     * @param text The text.
     */
    public UnnecessaryEndTag(MarkupContent parent, String text)
    {
        super(parent, text);
    }
}