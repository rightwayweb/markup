package com.zitego.markup.html.tag;

/**
 * This class represents a variable size that can be either an absolute integer,
 * an integer percentage, or an *.
 *
 * @author John Glorioso
 * @version $Id: VariableSize.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class VariableSize
{
    /** The size. */
    protected String _size = null;

    /**
     * Creates a new VariableSize object given the specified String. The String can
     * be an absolute integer, an integer percentage or a *.
     *
     * @param size The size.
     * @throws IllegalArgumentException if the string is not valid.
     */
    public VariableSize(String size) throws IllegalArgumentException
    {
        this(size, true);
    }

    /**
     * Creates a new VariableSize object given the specified String. The String can
     * be an absolute integer, an integer percentage or a * if the flag is set that way.
     *
     * @param size The size.
     * @param allowWildCard Whether to allow the wild card.
     * @throws IllegalArgumentException if the string is not valid.
     */
    public VariableSize(String size, boolean allowWildCard) throws IllegalArgumentException
    {
        boolean err = false;
        if (size != null)
        {
            size = size.trim();
            if ( !size.equals("*") )
            {
                try
                {
                    if ( size.endsWith("%") )
                    {
                        Integer.parseInt( size.substring(0, size.length()-1) );
                    }
                    else
                    {
                        Integer.parseInt(size);
                    }
                }
                catch (NumberFormatException nfe)
                {
                    err = true;
                }
            }
            else if (!allowWildCard)
            {
                err = true;
            }
        }
        else
        {
            err = true;
        }
        if (err) throw new IllegalArgumentException("Invalid VariableSize: "+size);

        _size = size;
    }

    public String toString()
    {
        return _size;
    }
}