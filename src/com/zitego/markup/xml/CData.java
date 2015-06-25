package com.zitego.markup.xml;

import com.zitego.markup.*;
import com.zitego.format.*;
import com.zitego.util.TextUtils;
import org.w3c.dom.CDATASection;

/**
 * This is a class to represents a CDATA section in an xml document.
 *
 * @author John Glorioso
 * @version $Id: CData.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class CData extends TextContent
{
    private static final String PREFIX = "<![CDATA[";
    private static final String SUFFIX = "]]>";
    /**
     * Creates new CData with a parent.
     *
     * @param XmlTag The parent tag.
     */
    public CData(XmlTag parent)
    {
        super(parent);
    }

    /**
     * Creates new CData with a a parent and some text.
     *
     * @param XmlTag The parent tag.
     * @param String The text.
     */
    public CData(XmlTag parent, String text)
    {
        super(parent, text);
    }

    /**
     * Returns a String representation of this.
     *
     * @return String
     */
    public String toString()
    {
        StringBuffer ret = new StringBuffer( getPadding() )
            .append(PREFIX).append( getText() ).append(SUFFIX);
        if ( hasNewline() ) ret.append(Newline.CHARACTER);
        return ret.toString();
    }

    /**
     * Parses the given string and sets the text until it reaches the ]]> characters.
     *
     * @param StringBuffer The text.
     * @param FormatType The format type.
     * @throws IllegalMarkupException if the text is invalid.
     * @throws UnsupportedFormatException if the format is not supported.
     */
    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        setText( TextUtils.getTextUpTo(text, SUFFIX) );
    }

    public void parse(Object objToParse, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (objToParse instanceof CDATASection) parseText( new StringBuffer(((CDATASection)objToParse).getNodeValue()), type );
        else super.parse(objToParse, type);
    }
}