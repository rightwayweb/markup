package com.zitego.markup.html.tag.list;

import com.zitego.util.Constant;
import java.util.Vector;

/**
 * Represents different list types such as ordered and unordered.
 *
 * @author John Glorioso
 * @version $Id: ListType.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class ListType extends Constant
{
    public static final ListType ORDERED = new ListType("Ordered List");
    public static final ListType UNORDERED = new ListType("Unordered List");
    public static final ListType DEFINITION = new ListType("Definition");
    /** Gets incremented as list types are initialized. */
    private static int _nextId = 0;
    /** The method types. */
    private static Vector _types;

    /**
     * Creates a new List.
     *
     * @param desc The description.
     */
    private ListType(String desc)
    {
        super(_nextId++, desc);
        if (_types == null) _types = new Vector();
        _types.add(this);
    }

    /**
     * Returns an ListType based on the id passed in. If the id does not match the id of
     * a constant, then we return null. If there are two constants with the same id, then
     * the first one is returned.
     *
     * @param id The constant id.
     * @return ListType
     */
    public static ListType evaluate(int id)
    {
        return (ListType)Constant.evaluate(id, _types);
    }

    /**
     * Returns an Constant based on the description passed in. If the description does not
     * match the description of a constant, then we return null. If there are two constants
     * with the same description, then the first one is returned.
     *
     * @param name The description.
     * @return ListType
     */
    protected static ListType evaluate(String name)
    {
        return (ListType)Constant.evaluate(name, _types);
    }

    public Vector getTypes()
    {
        return _types;
    }
}