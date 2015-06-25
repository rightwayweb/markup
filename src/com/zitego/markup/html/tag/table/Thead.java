package com.zitego.markup.html.tag.table;

/**
 * This class represents an html thead tag. A thead tag must have a parent tag and
 * that parent tag must be a Table tag. A tr tag has two attributes. These attributes
 * are align and valign. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Thead.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Table#createThead()
 */
public class Thead extends TrParent
{
    /**
     * Creates a new Thead tag with no parent.
     */
    public Thead()
    {
        super
        (
            "thead",
            new String[] { "left", "center", "right", "justify" },
            new String[] { "top", "middle", "bottom", "baseline" }
        );
    }
    
    /**
     * Creates a new Thead tag with a Table parent.
     *
     * @param parent The parent Table tag.
     */
    public Thead(Table parent)
    {
        super
        (
            "thead", parent,
            new String[] { "left", "center", "right", "justify" },
            new String[] { "top", "middle", "bottom", "baseline" }
        );
    }

    /**
     * Sets the bgcolor of the tag.
     *
     * @param bgcolor The bgcolor.
     */
    public void setBgColor(String bgcolor)
    {
        setAttribute("bgcolor", bgcolor);
    }

    /**
     * Returns the bgcolor of the tag.
     *
     * @return String
     */
    public String getBgColor()
    {
        return getAttributeValue("bgcolor");
    }
}