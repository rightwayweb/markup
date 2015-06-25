package com.zitego.markup.html;

import com.zitego.markup.*;
import com.zitego.markup.html.tag.BR;
import com.zitego.markup.html.tag.HtmlMarkupTag;

/**
 * This is a class to represent a plain text.
 *
 * @author John Glorioso
 * @version $Id: HtmlTextContent.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see com.zitego.markup.html.tag.HtmlMarkupTag#createTextContent(String)
 * @see com.zitego.markup.html.tag.textEffect.TextEffect#addText(String)
 * @see com.zitego.markup.html.tag.block.BlockFormat#addText(String)
 */
public class HtmlTextContent extends TextContent
{
    /**
     * Creates new text with no parent.
     */
    public HtmlTextContent()
    {
        super();
    }

    /**
     * Creates new text content with a parent.
     *
     * @param parent The parent content.
     */
    public HtmlTextContent(MarkupContent parent)
    {
        super(parent);
    }

    /**
     * Creates new text content with a a parent and some text.
     *
     * @param parent The parent content.
     * @param text The text.
     */
    public HtmlTextContent(MarkupContent parent, String text)
    {
        super(parent, text);
    }

    public void addLineBreak()
    {
        //We use the parent cause the breaks are after the text and part of
        //the parent
        MarkupContent parent = getParent();
        if (parent instanceof HtmlMarkupTag) addLineBreak( ((HtmlMarkupTag)parent).createLineBreak() );
        else addLineBreak( new Newline(parent) );
    }
}