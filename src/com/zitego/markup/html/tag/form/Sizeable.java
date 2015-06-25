package com.zitego.markup.html.tag.form;

/**
 * A simple interface for classes that implement setSize or setMaxLength.
 *
 * @author John Glorioso
 * @version $Id: Sizeable.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public interface Sizeable
{
    /**
     * Sets the size.
     *
     * @param size The size.
     */
    public void setSize(int size) throws IllegalArgumentException;

    /**
     * Sets the maxlength.
     *
     * @param max The maxlength.
     */
    public void setMaxLength(int max) throws IllegalArgumentException;
}