package com.zitego.markup.html.tag;

import com.zitego.markup.IllegalMarkupException;
import com.zitego.util.TextUtils;

/**
 * This class represents an html header (H1-H6) tag. Header tags have no attributes, however
 * they are constructed with the header size. This size can be changed at any time. This class
 * must have an HtmlMarkupTag as the parent. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Header.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Header extends EventDrivenTag
{
    /** The header size. */
    protected int _size = 1;

    /**
     * Creates a new H tag with a size.
     *
     * @param size The header size.
     * @throws IllegalArgumentException if the header size is not between 1-6.
     */
    public Header(Integer size) throws IllegalArgumentException
    {
        this( size.intValue() );
    }

    /**
     * Creates a new H tag with a size.
     *
     * @param size The header size.
     * @throws IllegalArgumentException if the header size is not between 1-6.
     */
    public Header(int size) throws IllegalArgumentException
    {
        super("h");
        setHeaderSize(size);
        setIsOnOwnLine(true);
    }

    /**
     * Creates a new H tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     * @param size The header size.
     * @throws IllegalArgumentException if the header size is not between 1-6.
     */
    public Header(HtmlMarkupTag parent, Integer size) throws IllegalArgumentException
    {
        this( parent, size.intValue() );
    }

    /**
     * Creates a new H tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     * @param size The header size.
     * @throws IllegalArgumentException if the header size is not between 1-6.
     */
    public Header(HtmlMarkupTag parent, int size) throws IllegalArgumentException
    {
        super("h", parent);
        setHeaderSize(size);
        setIsOnOwnLine(true);
    }

    /**
     * Sets the header size.
     *
     * @param size The size.
     * @throws IllegalArgumentException if the header size is not between 1-6 and this is strict.
     */
    public void setHeaderSize(int size) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (size < 1 || size > 6)
            {
                throw new IllegalArgumentException("Header size must be between 1-6. "+size+" is invalid.");
            }
        }
        _size = size;
        setTagName("h"+_size);
    }

    /**
     * Returns the header size.
     *
     * @return int
     */
    public int getHeaderSize()
    {
        return _size;
    }

    protected void validateTagName(StringBuffer text)
    {
        boolean err = false;
        String tag = TextUtils.getTextUpTo(text, new char[]{' ','>','<','\r','\n'});
        //String tag = (text != null && text.length() >= 2 ? text.substring(0,2) : "");
        tag = tag.toLowerCase();
        if (tag.length() >= 2)
        {
            if (tag.charAt(0) != 'h') err = true;
            try
            {
                _size = Integer.parseInt( tag.substring(1) );
                if (_size < 1 || _size > 6) err = true;
            }
            catch (NumberFormatException nfe)
            {
                err = true;
            }
        }
        if (err) throw new IllegalMarkupException
        (
            "Header tag <"+tag+"> is not valid at "+TextUtils.trunc(text.toString(), 25)
        );
    }
}