package com.zitego.markup.tag;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.MarkupFactory;
import com.zitego.markup.TextContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;

/**
 * Encapsulates a tag attribute value. Typically, a tag attribute value is text, but
 * this class supports multiple MarkupContents.
 *
 * @author John Glorioso
 * @version $Id: TagAttributeValue.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class TagAttributeValue extends MarkupContent
{
    private MarkupContent[] _content;

    /**
     * Creates a new tag attribute value.
     */
    public TagAttributeValue()
    {
        this( (String)null );
    }

    /**
     * Creates a new tag attribute value from text.
     *
     * @param val The value.
     */
    public TagAttributeValue(String val)
    {
        this( (val != null ? new TextContent(val) : (MarkupContent)null) );
    }

    /**
     * Creates a new tag attribute value from a single MarkupContent.
     *
     * @param val The value.
     */
    public TagAttributeValue(MarkupContent val)
    {
        this( new MarkupContent[] { val } );
    }

    /**
     * Creates a new tag attribute value from a MarkupContent array.
     *
     * @param val The content.
     */
    public TagAttributeValue(MarkupContent[] val)
    {
        super();
        setValue(val);
    }

    /**
     * Sets the content of this value.
     *
     * @param content The markup content.
     */
    public void setValue(MarkupContent[] content)
    {
        setChanged();
        _content = content;
        if (_content == null) _content = new MarkupContent[0];
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer();
            for (int i=0; i<_content.length; i++)
            {
                if (_content[i] != null) ret.append( _content[i].format(type) );
                else ret.append("null");
            }
            cacheContent( type, ret.toString().replaceAll("\"", "\\\"").replaceAll("\r\n", "") );
        }
        return (String)getCachedContent(type);
    }

    public void parseText(StringBuffer txt, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        setValue( MarkupFactory.getInstance().parse(txt, null, type, true, isStrict(), preserveWhiteSpace()) );
    }

    /**
     * Returns the attribute value as text.
     *
     * @return String
     */
    public String toString()
    {
        try
        {
            return format(FormatType.TEXT);
        }
        catch (UnsupportedFormatException ufe)
        {
            throw new RuntimeException("value could not be formatted");
        }
    }
}