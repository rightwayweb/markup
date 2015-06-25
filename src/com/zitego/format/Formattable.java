package com.zitego.format;

/**
 * An interface for formatting Objects to a String given
 * a format type.
 *
 * @author John Glorioso
 * @version $Id: Formattable.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public interface Formattable
{
    /**
     * This method is the signature for formatting to a String object
     * given the FormatType.
     *
     * @param FormatType The type of format to perform.
     * @throws UnsupportedFormatException when the FormatType is not supported.
     */
    public String format(FormatType type) throws UnsupportedFormatException;
}