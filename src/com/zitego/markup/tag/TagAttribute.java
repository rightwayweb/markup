package com.zitego.markup.tag;

import com.zitego.format.UnsupportedFormatException;
import com.zitego.format.FormatType;

/**
 * Encapsulates a markup tag attribute. Each attribute has a name and a value.
 * If the value is left as null, then only the name will be printed out when
 * toString is called.
 *
 * @author John Glorioso
 * @version $Id: TagAttribute.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class TagAttribute extends com.zitego.util.CachedContent
{
    /** The name. */
    protected String _name;
    /** The value. */
    protected TagAttributeValue _value;
    private boolean _forceSingleQuotes = false;
    private boolean _strict = true;
    private boolean _preserveWhiteSpace = false;

    /**
     * Creates a new attribute with a name.
     *
     * @param name The name.
     * @throws IllegalArgumentException if the name is null.
     */
    public TagAttribute(String name) throws IllegalArgumentException
    {
        setName(name);
    }

    /**
     * Creates a new attribute with a name and a value.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the name is null.
     */
    public TagAttribute(String name, String val) throws IllegalArgumentException
    {
        this(name);
        setValue(val);
    }

    /**
     * Creates a new attribute with a name and a value.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the name is null.
     */
    public TagAttribute(String name, TagAttributeValue val) throws IllegalArgumentException
    {
        this(name);
        setValue(val);
    }

    /**
     * Sets the name.
     *
     * @param name The name.
     * @throws IllegalArgumentException if the name is null.
     */
    public void setName(String name) throws IllegalArgumentException
    {
        if (name != null)
        {
            _name = name;
            setChanged();
        }
        else
        {
            throw new IllegalArgumentException("name cannot be null");
        }
    }

    /**
     * Returns the name.
     *
     * @return String
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Sets the value. If there is a double quote in the value, then it is
     * escaped; The value is retrieved and stored as MarkupContent. This is set
     * as FormatType.TEXT. Use setValue(val, type) to specify the format type.
     *
     * @param val The value.
     */
    public void setValue(String val)
    {
        TagAttributeValue value = new TagAttributeValue(val);
        value.setStrict( isStrict() );
        value.setPreserveWhiteSpace( preserveWhiteSpace() );
        setValue( (val == null ? null : value) );
    }

    /**
     * Sets the value.
     *
     * @param val The value.
     */
    public void setValue(TagAttributeValue val)
    {
        setChanged();
        _value = val;
    }

    /**
     * Returns the value as FormatType.TEXT.
     *
     * @return String
     */
    public String getValue()
    {
        if (_value == null)
        {
            return null;
        }
        else
        {
            try
            {
                return _value.format(FormatType.TEXT);
            }
            catch (UnsupportedFormatException ufe)
            {
                throw new RuntimeException("value could not be formatted");
            }
        }
    }

    /**
     * Returns the TagAttributeValue.
     *
     * @return TagAttributeValue
     */
    public TagAttributeValue getTagAttributeValue()
    {
        return _value;
    }

    /**
     * Returns the attribute as a name/value string.
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
     * Returns the attribute as a name/value string.
     *
     * @param type The format type.
     * @return String
     */
    public String toString(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer(_name);
            char quote = (_forceSingleQuotes ? '\'' : '"');
            if (_value != null)
            {
                ret.append("=").append(quote).append( _value.format(type) ).append(quote);
            }
            cacheContent( this, ret.toString() );
        }
        return (String)getCachedContent(this);
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

    /**
     * Sets whether parsing is strict or not. The meaning of this is implemented by subclasses. This
     * automatically sets all children as being non strict as well. Any content added to this content
     * will in turn be marked according to this setting.
     *
     * @param strict The strict flag.
     */
    public void setStrict(boolean strict)
    {
        _strict = strict;
        if (_value != null) _value.setStrict(strict);
    }

    /**
     * Returns whether or not parsing is done on a strict basis. The meaning of this is implemented
     * by subclasses.
     *
     * @return boolean
     */
    public boolean isStrict()
    {
        return _strict;
    }

    /**
     * Sets whether to preserve whitespace and not format further. The meaning of this is implemented
     * by subclasses, but typically it means that generating content will not product indentation or
     * generated newline characters. This automatically sets all children as well. Any content added
     * to this content will in turn be marked according to this setting.
     *
     * @param preserve The preserve whitespace flag.
     */
    public void setPreserveWhiteSpace(boolean preserve)
    {
        _preserveWhiteSpace = preserve;
        if (_value != null) _value.setPreserveWhiteSpace(preserve);
    }

    /**
     * Returns whether or not we are preserving white space and not formatting. The meaning of this
     * is implemented by subclasses.
     *
     * @return boolean
     */
    public boolean preserveWhiteSpace()
    {
        return _preserveWhiteSpace;
    }
}