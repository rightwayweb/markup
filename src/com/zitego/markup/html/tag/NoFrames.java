package com.zitego.markup.html.tag;

/**
 * This class represents an html noframes tag. A noframes tag has no attributes. This class
 * must have a FrameSet tag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: NoFrames.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see FrameSet#getNoFramesTag()
 */
public class NoFrames extends HtmlMarkupTag
{
    /**
     * Creates a new noframes tag with no parent.
     */
    public NoFrames()
    {
        super("noframes");
    }
    
    /**
     * Creates a new noframes tag with an FrameSet parent.
     *
     * @param parent The parent.
     */
    public NoFrames(FrameSet parent)
    {
        super("noframes", parent);
        parent.addBodyContentAtBeginning(this);
    }

    protected boolean addToParentOnInit()
    {
        return false;
    }
}