package com.zitego.markup.html.tag;

/**
 * This class represents an html noscript tag. A noscript tag has no attributes. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: NoScript.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class NoScript extends HtmlMarkupTag
{
    /**
     * Creates a new noscript tag with no parent.
     */
    public NoScript()
    {
        super("noscript");
    }
    
    /**
     * Creates a new noscript tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public NoScript(HtmlMarkupTag parent)
    {
        super("noscript", parent);
    }
}