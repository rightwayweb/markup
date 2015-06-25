package com.zitego.markup.html.tag.table;

/**
 * This class represents an html tbody tag. A tbody tag must have a parent tag and
 * that parent tag must be a Table tag. A tr tag has two attributes. These attributes
 * are align and valign. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Tbody.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Table#createTbody()
 */
public class Tbody extends TrParent
{
    /**
     * Creates a new Tbody tag with no parent.
     */
    public Tbody()
    {
        super
        (
            "tbody",
            new String[] { "left", "center", "right", "justify" },
            new String[] { "top", "middle", "bottom", "baseline" }
        );
    }
    
    /**
     * Creates a new Tbody tag with a Table parent.
     *
     * @param parent The parent Table tag.
     */
    public Tbody(Table parent)
    {
        super
        (
            "tbody", parent,
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