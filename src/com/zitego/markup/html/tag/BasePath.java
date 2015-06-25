package com.zitego.markup.html.tag;

/**
 * This is an interface for class to implement that has an href or source
 * that is relative to a specific base domain.
 *
 * @author John Glorioso
 * @version $Id: BasePath.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public interface BasePath
{
    /**
     * Sets the base path.
     *
     * @param path The base path.
     */
    public void setBasePath(String path);

    /**
     * Gets the full path including the base.
     *
     * @return String
     */
    public String getFullPath();
}