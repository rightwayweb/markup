package com.zitego.markup.html.tag;

import com.zitego.markup.tag.TagAttribute;
import com.zitego.markup.tag.TagAttributeValue;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.TextUtils;
import java.util.Vector;

/**
 * Represents a javascript event handler. The handler has a name
 * and an vector of javascript function calls. All double quotes
 * are changed to single quotes.
 *
 * @author John Glorioso
 * @version $Id: JsEventHandler.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class JsEventHandler extends TagAttribute
{
    /** The Vector of javascript functions. */
    protected Vector _jsFunctions;
    /** The function string. */
    private String _funcString = "";

    /**
     * Creates a new JsEventHandler with a name.
     *
     * @param name The name.
     */
    public JsEventHandler(String name)
    {
        super(name);
        _jsFunctions = new Vector();
    }

    /**
     * Creates a new JsEventHandler with a name and a single
     * javascript function.
     *
     * @param name The name.
     * @param function The function.
     */
    public JsEventHandler(String name, String function)
    {
        this(name);
        addJsFunction(function);
    }

    /**
     * Returns the Vector of functions.
     *
     * @return Vector
     */
    public Vector getJsFunctions()
    {
        return _jsFunctions;
    }

    /**
     * Sets the Vector of functions.
     *
     * @param funcs The functions
     */
    public void setJsFunctions(Vector funcs)
    {
        _jsFunctions = new Vector();
        int size = funcs.size();
        for (int i=0; i<size; i++)
        {
            //See if we are getting strings or TagAttributeValues
            Object obj = funcs.elementAt(i);
            if (obj instanceof TagAttributeValue) addJsFunction( (TagAttributeValue)obj );
            else addJsFunction( (String)obj );
        }
    }

    /**
     * Adds a function to the Vector.
     *
     * @param func The function call.
     */
    public void addJsFunction(String func)
    {
        func = func.replaceAll("\"", "'");
        String[] funcs = TextUtils.split(func, ';');
        for (int i=0; i<funcs.length; i++)
        {
            addJsFunction( new TagAttributeValue(funcs[i]) );
        }
    }

    /**
     * Adds a function to the Vector.
     *
     * @param func The function call.
     */
    public void addJsFunction(TagAttributeValue func)
    {
        if (func == null) return;
        int size = _jsFunctions.size();
        boolean found = false;
        for (int i=0; i<size; i++)
        {
            TagAttributeValue val = (TagAttributeValue)_jsFunctions.get(i);
            if ( val.toString().equalsIgnoreCase(func.toString()) )
            {
                found = true;
                break;
            }
        }
        if (!found)
        {
            _jsFunctions.add(func);
            setChanged();
        }
    }

    /**
     * Returns the javascript functions as a string.
     *
     * @return String
     */
    public String getJsFunctionString()
    {
        return getJsFunctionString(FormatType.TEXT);
    }

    /**
     * Returns the javascript functions as an html string.
     *
     * @param type The format type.
     * @return String
     */
    public String getJsFunctionString(FormatType type)
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer();
            int size = _jsFunctions.size();
            for (int i=0; i<size; i++)
            {
                try
                {
                    ret.append( (i > 0 ? ";" : "") ).append( ((TagAttributeValue)_jsFunctions.get(i)).format(type) );
                }
                catch (UnsupportedFormatException ufe)
                {
                    throw new RuntimeException("Could not format the JS function as type: "+type);
                }
            }
            _funcString = ret.toString();
        }
        return _funcString;
    }

    public void setValue(String val)
    {
        addJsFunction(val);
    }

    public void setValue(TagAttributeValue val)
    {
        addJsFunction( val.toString() );
    }

    public String getValue()
    {
        return getJsFunctionString();
    }

    public TagAttributeValue getTagAttributeValue()
    {
        return new TagAttributeValue( getJsFunctionString() );
    }

    /**
     * Converts this event handler to a string representation.
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
            throw new RuntimeException("Could not return attribute as format type as text", ufe);
        }
    }

    /**
     * Converts this event handler to a string representation. TO DO - Allow for a way to have
     * markup content in the value.
     *
     * @param type The format type.
     * @return String
     */
    public String toString(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer()
                .append( getName() ).append("=\"").append( getJsFunctionString() )
                .append("\"");
            cacheContent( this, ret.toString() );
        }
        return (String)getCachedContent(this);
    }
}