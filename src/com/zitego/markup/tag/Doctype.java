package com.zitego.markup.tag;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.html.tag.Html;
import com.zitego.format.*;

/**
 * This class represents an html DOCTYPE tag. This tag is a bit different from the rest
 * because it is not within an html tagset and has no parent tags. In addition, there are
 * no attributes so to speak. It is only used to specify the type of html document. These
 * types are STRICT, TRANSITIONAL, and FRAMESET. STRICT is for a strict implementation of
 * html and to be used with cascading style sheets (CSS). Transitional (default) is
 * for older browsers that may not necessarily support CSS. FRAMESET is to be used with
 * framed documents. In addition to html, xhtml doctype declarations can be made. The only
 * two setters are setMarkupType(int) and setDocType(int). This class is up to date as
 * of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Doctype.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Doctype extends MarkupTag
{
    public static final int HTML = 0;
    public static final int XHTML = 1;
    public static final int STRICT = 0;
    public static final int TRANSITIONAL = 1;
    public static final int FRAMESET = 2;

    /**
     * Creates a new DOCTYPE tag with no html parent. Parent should be set later.
     */
    public Doctype()
    {
        super("DOCTYPE");
        setHasEndTag(false);
        setMarkupType(HTML);
        setDocType(TRANSITIONAL);
    }

    /**
     * Creates a new DOCTYPE tag with an html tag.
     *
     * @param htmlTag The html tag.
     */
    public Doctype(Html htmlTag)
    {
        super(htmlTag.getMap(), "DOCTYPE");
        setHasEndTag(false);
        setMarkupType(HTML);
        setDocType(TRANSITIONAL);
    }

    /**
     * Overrides set parent so that the map is all that gets set.
     *
     * @param parent The parent.
     */
    public void setParent(MarkupContent parent)
    {
        setMap( parent.getMap() );
    }

    /**
     * Sets the markup type.
     *
     * @param type The markup type.
     * @throws IllegalArgumentException if the type is not HTML or XHTML.
     */
    public void setMarkupType(int type) throws IllegalArgumentException
    {
        if (type != HTML && type != XHTML)
        {
            throw new IllegalArgumentException("Invalid markup type: "+type);
        }
        super.setAttribute( "markup_type", String.valueOf(type) );
    }

    /**
     * Returns the markup type.
     *
     * @return int
     */
    public int getMarkupType()
    {
        return getIntAttributeValue("markup_type", HTML);
    }

    /**
     * Sets the document type of the tag.
     *
     * @param type The document type.
     * @throws IllegalArgumentException if the document type is not STRICT, TRANSITIONAL, or FRAMESET.
     */
    public void setDocType(int type) throws IllegalArgumentException
    {
        if (type != TRANSITIONAL && type != STRICT && type != FRAMESET)
        {
            throw new IllegalArgumentException("Invalid document type: "+type);
        }
        super.setAttribute( "doctype", String.valueOf(type) );
    }

    /**
     * Returns the document type.
     *
     * @return int
     */
    public int getDocType()
    {
        return getIntAttributeValue("doctype", TRANSITIONAL);
    }

    /**
     * Overrides setAttribute to make sure that markup_type and doctype are numeric.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is markup_type or doctype
     *                               and the value is not numeric.
     */
    public void setAttribute(String name, String val) throws NumberFormatException
    {
        if (name != null) name = name.toLowerCase();
        if ( "markup_type".equals(name) )
        {
            setMarkupType( Integer.parseInt(val) );
        }
        else if ( "doctype".equals(name) )
        {
            setDocType( Integer.parseInt(val) );
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            int docType = getDocType();
            int markupType = getMarkupType();
            StringBuffer ret = new StringBuffer()
                .append("<!").append( getTagName() );
            if (markupType == XHTML) ret.append(" html ");
            else ret.append(" HTML ");
            ret.append("PUBLIC \"-//W3C//DTD ");

            if (markupType == XHTML) ret.append("XHTML 1.0 ");
            else ret.append("HTML 4.01").append( (docType != STRICT ? " ": "") );

            if (docType == STRICT && markupType == XHTML) ret.append("Strict");
            else if (docType == TRANSITIONAL) ret.append("Transitional");
            else ret.append("Frameset");
            ret.append("//EN\" \"http://www.w3.org/TR/");

            if (markupType == XHTML) ret.append("xhtml1/DTD/xhtml1-");
            else ret.append("html4/");

            if (docType == STRICT) ret.append("strict");
            else if (docType == TRANSITIONAL) ret.append( (markupType == XHTML ? "transitional" : "loose") );
            else ret.append("frameset");
            ret.append(".dtd\">");

            return ret.toString();
        }
        else
        {
            return (String)getCachedContent(type);
        }
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;

        removeLeadingSpaces(text);
        int index = text.indexOf(">");
        //Check to see if they forgot a closing tag
        int nextTagIndex = text.indexOf("<", 1);
        if (index > nextTagIndex) index = nextTagIndex-1;
        if (index == -1) throw new IllegalMarkupException("Invalid doctype tag: "+text);

        String tag = text.substring(0, index+1);
        tag = tag.toLowerCase();

        if (tag.indexOf("<!doctype") != 0) throw new IllegalMarkupException("Invalid doctype tag: "+tag);

        if (tag.indexOf("strict") > -1)
        {
            setDocType(STRICT);
            setMarkupType(XHTML);
        }
        else
        {
            setDocType(TRANSITIONAL);
            setMarkupType(HTML);
        }
        text.delete(0, index+1);
    }
}