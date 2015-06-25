package com.zitego.markup;

import com.zitego.format.*;
import com.zitego.markup.tag.SpecialChar;

/**
 * This is a class to represent a new line character component. It can be
 * added to any document with markup content.
 *
 * @author John Glorioso
 * @version $Id: Newline.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Newline extends MarkupContent
{
    /** A newline char. */
    public static final String CHARACTER = "\r\n";

    /**
     * Creates a newline content with no parent.
     */
    public Newline()
    {
        super();
    }

    /**
     * Creates a newline content.
     *
     * @param parent The parent content.
     */
    public Newline(MarkupContent parent)
    {
        super(parent);
    }

    /**
     * Returns a String representation of this.
     *
     * @return String
     */
    public String toString()
    {
        return CHARACTER;
    }

    /**
     * Writes out a newline character. There is no padding.
     *
     * @return String
     * @throws UnsupportedFormatException
     */
    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        return CHARACTER;
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        //Does nothing except strips off the first char if it is \r\n
        if (text != null && text.length() > 2 && text.charAt(0) == '\r' && text.charAt(1) == '\n') text.delete(0, 2);
    }

    public void parse(Object objToParse, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (objToParse == null) return;
        else if (objToParse instanceof StringBuffer) parseText( (StringBuffer)objToParse, type );
        else if (objToParse instanceof String) parseText( new StringBuffer((String)objToParse), type );
        else throw new IllegalMarkupException( "Unable to parse object type: "+objToParse.getClass() );
    }

    protected boolean addToParentOnInit()
    {
        return false;
    }
}