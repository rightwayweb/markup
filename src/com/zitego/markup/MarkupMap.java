package com.zitego.markup;

/**
 * This is just a simple class that maps MarkupContent to
 * an integer id for the markup content class itself.
 *
 * @author John Glorioso
 * @version $Id: MarkupMap.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class MarkupMap extends java.util.HashMap
{
    protected static int ID_COUNTER = 0;
    /** This map's id. */
    protected int _id = ID_COUNTER++;
    /** The next id to give out. */
    protected int _nextId = 0;

    /**
     * Creates a new MarkupMap.
     */
    protected MarkupMap()
    {
        super();
    }

    /**
     * Creates a new MarkupMap using the given MarkupMap.
     *
     * @param MarkupMap
     */
    public MarkupMap(MarkupMap map)
    {
        super(map);
    }

    /**
     * Stores a MarkupContent and returns the id it was stored under.
     *
     * @param MarkupContent
     * @return int
     * @throws IllegalArgumentException if the content is null.
     */
    public int store(MarkupContent content)
    {
        if (content == null) throw new IllegalArgumentException("Content cannot be null");
        if (get( content.getMapId() ) != null)
        {
            return content.getMapId();
        }
        else
        {
            put(new Integer(_nextId), content);
            return _nextId++;
        }
    }

    /**
     * Stores the given markup content overwriting the specified current content. If
     * the specified overwrite content does not exist, then a new id is retrieved.
     * If it does exist, then we return that id.
     *
     * @param MarkupContent The old content getting overwritten.
     * @param MarkupContent The new content to overwrite with.
     * @return int
     * @throws IllegalArgumentException if the content is null.
     */
    protected int overwrite(MarkupContent oldContent, MarkupContent newContent)
    {
        if (newContent == null) throw new IllegalArgumentException("Content cannot be null");
        int id = 0;

        if (oldContent != null && get( oldContent.getMapId() ) != null)
        {
            id = oldContent.getMapId();
            remove( new Integer(id) );
        }
        else
        {
            id = _nextId++;
        }

        put(new Integer(id), newContent);
        return id;
    }

    /**
     * Returns an MarkupContent based on the id passed in.
     *
     * @param int The id of the MarkupContent to get.
     * @return MarkupContent
     */
    public MarkupContent get(int id)
    {
        return (MarkupContent)super.get( new Integer(id) );
    }
}