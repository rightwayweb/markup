package com.zitego.format;

import java.util.Vector;
import com.zitego.util.Constant;

/**
 * This constant class defines all possible content types. This
 * class cannot be extended.
 *
 * @author John Glorioso
 * @version $Id: FormatType.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public final class FormatType extends Constant
{
    public static final FormatType XML = new FormatType("XML");
    public static final FormatType HTML = new FormatType("HTML");
    public static final FormatType JSP = new FormatType("JSP");
    public static final FormatType TEXT = new FormatType("TEXT");
    public static final FormatType CSS = new FormatType("CSS");
    /** Gets incremented as format types are initialized. */
    private static int _nextId = 0;
    /** To keep track of each type. */
    private static Vector _types;

    /**
     * Creates a new FormatType given the description.
     *
     * @param String The description.
     */
    private FormatType(String desc)
    {
        super(_nextId++, desc);
        if (_types == null) _types = new Vector();
        _types.add(this);
    }

    /**
     * Returns an FormatType based on the id passed in. If the id does not match the id of
     * a constant, then we return null. If there are two constants with the same id, then
     * the first one is returned.
     *
     * @param int The constant id.
     * @return FormatType
     */
    public static FormatType evaluate(int id)
    {
        return (FormatType)Constant.evaluate(id, _types);
    }

    /**
     * Returns an Constant based on the description passed in. If the description does not
     * match the description of a constant, then we return null. If there are two constants
     * with the same description, then the first one is returned.
     *
     * @param String The description.
     * @return FormatType
     */
    protected static FormatType evaluate(String name)
    {
        return (FormatType)Constant.evaluate(name, _types);
    }

    public Vector getTypes()
    {
        return _types;
    }
}