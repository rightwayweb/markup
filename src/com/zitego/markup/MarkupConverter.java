package com.zitego.markup;

import com.zitego.format.*;

/**
 * This interface is a base interface for all markup content interfaces to extend.
 *
 * @author John Glorioso
 * @version $Id: MarkupConverter.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public interface MarkupConverter extends Formattable
{
    /**
     * Returns the button's parent.
     *
     * @return MarkupContent
     */
    public MarkupContent getParent();

    /**
     * Sets the parent MarkupContent of the button display.
     *
     * @param MarkupContent The parent.
     */
    public void setParent(MarkupContent parent);

    /**
     * Returns this object as MarkupContent.
     *
     * @return MarkupContent
     */
    public MarkupContent getAsMarkupContent();

    /**
     * Parses the given a presumably parsable object to set properties in this object.
     *
     * @param Object The parsable object.
     * @param FormatType The type to format to.
     * @throws IllegalMarkupException if the text is not valid.
     * @throws UnsupportedFormatException if the format is not valid.
     */
    public void parse(Object objToParse, FormatType type) throws IllegalMarkupException, UnsupportedFormatException;
}