package com.zitego.format;

/**
 * An exception to be thrown when FormatTypes are not supported by
 * a Formattable class.
 *
 * @author John Glorioso
 * @version $Id: UnsupportedFormatException.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
*/
public class UnsupportedFormatException extends Exception
{
    public UnsupportedFormatException(String msg)
    {
        super(msg);
    }

    public UnsupportedFormatException(String msg, Throwable root)
    {
        super(msg, root);
    }
}