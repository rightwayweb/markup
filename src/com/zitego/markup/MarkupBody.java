package com.zitego.markup;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.tag.SpecialChar;
import com.zitego.format.*;
import java.util.Vector;

/**
 * This class hold multiple MarkupContents that makeup the
 * body of a parent MarkupContent. Content can be added by calling
 * the <code>addContent(MarkupContent)</code> method.
 *
 * @author John Glorioso
 * @version $Id: MarkupBody.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class MarkupBody extends Vector implements Formattable
{
    /** The parent. */
    private MarkupContent _parent;

    /**
     * Creates a new MarkupBody with a parent.
     *
     * @param parent The parent.
     */
    public MarkupBody(MarkupContent parent)
    {
        super();
        _parent = parent;
    }

    /**
     * Returns the parent.
     *
     * @return MarkupContent
     */
    public MarkupContent getParent()
    {
        return _parent;
    }

    public String format(FormatType type) throws UnsupportedFormatException
    {
        StringBuffer ret = new StringBuffer();
        int size = size();
        for (int i=0; i<size; i++)
        {
            ret.append( ((MarkupContent)get(i)).format(type) );
        }
        return ret.toString();
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;
        getMarkupFactory().parse
        (
            text,
            _parent,
            type,
            true,
            (_parent != null ? _parent.isStrict() : true),
            (_parent != null ? _parent.preserveWhiteSpace() : false)
        );
    }

    public void parse(Object objToParse, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (objToParse == null) return;
        else if (objToParse instanceof StringBuffer) parseText( (StringBuffer)objToParse, type );
        else if (objToParse instanceof String) parseText( new StringBuffer((String)objToParse), type );
        else throw new IllegalMarkupException( "Unable to parse object type: "+objToParse.getClass() );
    }

    /**
     * Returns the markup factory to be used for parsing.
     *
     * @return MarkupFactory
     */
    protected MarkupFactory getMarkupFactory()
    {
        return MarkupFactory.getInstance();
    }
}