package com.zitego.markup.html.tag.table;

import com.zitego.util.Constant;
import java.util.Vector;

/**
 * This class is used for navigation of a table from a cell to another cell.
 * The directions are general map directions and can be applied from any cell.
 *
 * @author John Glorioso
 * @version $Id: CellDirection.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class CellDirection extends Constant
{
    public static final CellDirection N = new CellDirection("North");
    public static final CellDirection S = new CellDirection("South");
    public static final CellDirection E = new CellDirection("East");
    public static final CellDirection W = new CellDirection("West");
    public static final CellDirection NE = new CellDirection("North East");
    public static final CellDirection NW = new CellDirection("North West");
    public static final CellDirection SE = new CellDirection("South East");
    public static final CellDirection SW = new CellDirection("South West");
    /** Gets incremented as format types are initialized. */
    private static int _nextId = 0;
    /** To keep track of each type. */
    private static Vector _directions;

    /**
     * Creates a new CellDirection given the description.
     *
     * @param desc The description.
     */
    private CellDirection(String desc)
    {
        super(_nextId++, desc);
        if (_directions == null) _directions = new Vector();
        _directions.add(this);
    }

    /**
     * Returns an CellDirection based on the id passed in. If the id does not match the id of
     * a constant, then we return null. If there are two constants with the same id, then
     * the first one is returned.
     *
     * @param id The constant id.
     * @return CellDirection
     */
    public static CellDirection evaluate(int id)
    {
        return (CellDirection)Constant.evaluate(id, _directions);
    }

    /**
     * Returns an Constant based on the description passed in. If the description does not
     * match the description of a constant, then we return null. If there are two constants
     * with the same description, then the first one is returned.
     *
     * @param name The description.
     * @return CellDirection
     */
    protected static CellDirection evaluate(String name)
    {
        return (CellDirection)Constant.evaluate(name, _directions);
    }

    /**
     * Returns a cell direction based on the two table locations passed in. They have to be within
     * one cell distance of each other on a row/column basis or null is returned. If either cell
     * is null, then null is returned.
     *
     * @param cell1 The first cell.
     * @param cell2 The second cell.
     * @return CellDirection
     */
    public static CellDirection evaluate(TableLocation cell1, TableLocation cell2)
    {
        if (cell1 == null || cell2 == null) return null;
        switch (cell1.row - cell2.row)
        {
            case 0:
                //Either E, W, or null
                switch (cell1.column - cell2.column)
                {
                    case 1: return W;
                    case -1: return E;
                    default: return null;
                }

            case 1:
                //Either NW, N, NE, or null
                switch (cell1.column - cell2.column)
                {
                    case 1: return NW;
                    case 0: return N;
                    case -1: return NE;
                    default: return null;
                }

            case -1:
                //Either SW, S, SE, or null
                switch (cell1.column - cell2.column)
                {
                    case 1: return SW;
                    case 0: return S;
                    case -1: return SE;
                    default: return null;
                }

            default:
                return null;
        }
    }

    public Vector getTypes()
    {
        return _directions;
    }
}