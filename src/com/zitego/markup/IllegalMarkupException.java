package com.zitego.markup;

/**
 * An exception to be thrown when Markup content is illegally used or called.
 *
 * @author John Glorioso
 * @version $Id: IllegalMarkupException.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
*/
public class IllegalMarkupException extends RuntimeException
{
    public IllegalMarkupException(String msg)
    {
        super(msg);
    }

    public IllegalMarkupException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}