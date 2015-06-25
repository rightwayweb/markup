package com.zitego.markup.html;

import com.zitego.markup.MarkupMap;
import com.zitego.markup.MarkupContent;
import com.zitego.markup.html.tag.*;
import java.util.Iterator;
import java.util.Map;

/**
 * This is an extension of the MarkupMap and keeps track of major pieces of the
 * html page such as the html, head, and body tags.
 *
 * @author John Glorioso
 * @version $Id: HtmlMarkupMap.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class HtmlMarkupMap extends MarkupMap
{
    /** The Html tag. */
    protected Html _html;
    /** The base path of all BasePath tags in this map. */
    private String _basePath;

    /**
     * Creates a new HtmlMarkupMap.
     */
    public HtmlMarkupMap()
    {
        super();
    }

    /**
     * Creates a new HtmlMarkupMap using the given MarkupMap.
     *
     * @param MarkupMap
     */
    public HtmlMarkupMap(MarkupMap map)
    {
        super(map);
    }

    /**
     * Overrides store to get the html tag.
     *
     * @param MarkupContent
     * @return int
     */
    public int store(MarkupContent content)
    {
        int id = super.store(content);
        if (_html == null && content instanceof Html)
        {
            _html = (Html)content;
        }
        //if this is an instance of BasePath and there is a base path set, then set it
        else if (_basePath != null && content instanceof BasePath)
        {
            ( (BasePath)content ).setBasePath(_basePath);
        }
        return id;
    }

    /**
     * Returns the html tag.
     *
     * @return Html
     */
    public Html getHtmlTag()
    {
        return _html;
    }

    /**
     * Returns the head tag. This returns null if the html tag has not yet been set.
     *
     * @return Head
     */
    public Head getHeadTag()
    {
        return (_html != null ? _html.getHead() : null);
    }

    /**
     * Returns the body tag. This returns null if the html tag has not yet been set.
     *
     * @return Body
     */
    public Body getBodyTag()
    {
        return (_html != null ? _html.getBodyTag() : null);
    }

    /**
     * This goes through each content in the map and calls the setBasePath method if it is
     * an instance of BasePath.
     *
     * @param String The base path.
     */
    public void setBasePath(String path)
    {
        _basePath = path;
        for (Iterator i=entrySet().iterator(); i.hasNext();)
        {
            MarkupContent c = (MarkupContent)get( i.next() );
            if (c instanceof BasePath) ( (BasePath)c ).setBasePath(path);
        }
    }

    /**
     * Returns the base path that this map uses.
     *
     * @return String
     */
    public String getBasePath()
    {
        return _basePath;
    }
}