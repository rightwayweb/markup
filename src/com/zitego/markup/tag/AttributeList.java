package com.zitego.markup.tag;

import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import java.util.Vector;

/**
 * This is a simple class that extends Vector for adding tag attributes.
 *
 * @author John Glorioso
 * @version $Id: AttributeList.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class AttributeList extends Vector
{
    private boolean _forceSingleQuotes = false;

    /**
     * Writes out the attributes in name=value, name=value format.
     *
     * @return String
     */
    public String toString()
    {
        try
        {
            return toString(FormatType.TEXT);
        }
        catch (UnsupportedFormatException ufe)
        {
            throw new RuntimeException("Could not return attribute list as text", ufe);
        }
    }

    /**
     * Writes out the attributes in name=value, name=value format.
     *
     * @param type The format type.
     * @return String
     */
    public String toString(FormatType type) throws UnsupportedFormatException
    {
        StringBuffer ret = new StringBuffer();
        int size = size();
        for (int i=0; i<size; i++)
        {
            TagAttribute attr = (TagAttribute)get(i);
            attr.setForceSingleQuotes(_forceSingleQuotes);
            ret.append(" ").append( attr.toString(type) );
            attr.setForceSingleQuotes(false);
        }

        return ret.toString();
    }

    /**
     * Forces the use of single quotes when formatting.
     *
     * @param flag Whether to force formatting of single quotes.
     */
    public void setForceSingleQuotes(boolean flag)
    {
        _forceSingleQuotes = flag;
    }

    /**
     * Returns whether or not we are forcing single quotes or not when formatting tag attributes.
     *
     * @return boolean
     */
    public boolean forceSingleQuotes()
    {
        return _forceSingleQuotes;
    }
}