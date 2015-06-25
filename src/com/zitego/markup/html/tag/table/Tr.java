package com.zitego.markup.html.tag.table;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.form.Form;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.html.tag.form.Form;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class represents an html tr tag. A tr tag must have a parent tag and
 * that parent tag must be either a Table tag, a Thead tag, a Tbody tag, or
 * a Tfoot tag. A tr tag has a few attributes. These attributes are align,
 * bgcolor, and valign. This class is up to date as of W3C specification
 * version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Tr.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TrParent#createRow
 */
public class Tr extends EventDrivenTag
{
    /** The cells. */
    private Vector _cells = new Vector();
    /** A hashtable of the allowable alignment options. */
    private static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("center", "1");
        _allowableAligns.put("right", "1");
        _allowableAligns.put("justify", "1");
    }
    /** A hashtable of the allowable valign options. */
    private static Hashtable _allowableValigns = new Hashtable();
    static
    {
        _allowableValigns.put("top", "1");
        _allowableValigns.put("middle", "1");
        _allowableValigns.put("bottom", "1");
        _allowableValigns.put("baseline", "1");
    }

    /**
     * Creates a new Tr tag with no parent.
     */
    public Tr()
    {
        super("tr");
    }

    /**
     * Creates a new Tr tag with a TrParent.
     *
     * @param parent The parent Table tag.
     */
    public Tr(TrParent parent)
    {
        super("tr", parent);
    }

    /**
     * Creates a new Tr tag with a Form parent. The parent of the form should be a TrParent.
     *
     * @param parent The parent form tag.
     */
    public Tr(Form parent)
    {
        super("tr", parent);
    }

    /**
     * Sets the alignment of the row.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not left, center, right, or justify and this is strict.
     */
    public void setAlign(String align) throws IllegalArgumentException
    {
        if (align != null)
        {
            align = align.toLowerCase();
            if ( isStrict() )
            {
                //Convert middle to center
                if ( align.equals("middle") ) align = "center";
                if (_allowableAligns.get(align) == null)
                {
                    throw new IllegalArgumentException("align must be left, center, right, or justify. "+align+" is invalid.");
                }
            }
        }
        super.setAttribute("align", align);
    }

    /**
     * Returns the alignment of the tag.
     *
     * @return String
     */
    public String getAlign()
    {
        return getAttributeValue("align");
    }

    /**
     * Sets the bgcolor of the tag.
     *
     * @param bgcolor The bgcolor.
     */
    public void setBgColor(String bgcolor)
    {
        setAttribute("bgcolor", bgcolor);
    }

    /**
     * Returns the bgcolor of the tag.
     *
     * @return String
     */
    public String getBgColor()
    {
        return getAttributeValue("bgcolor");
    }

    /**
     * Sets the valign for the cell.
     *
     * @param valign The valign.
     * @throws IllegalArgumentException if the valign is invalid and this is strict.
     */
    public void setValign(String valign) throws IllegalArgumentException
    {
        if (valign != null)
        {
            valign = valign.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableValigns.get(valign) == null)
                {
                    throw new IllegalArgumentException("valign must be top, middle, bottom, or baseline. " +
                                                       valign + " is invalid.");
                }
            }
        }
        super.setAttribute("valign", valign);
    }

    /**
     * Returns the valign for the cell.
     *
     * @return String
     */
    public String getValign()
    {
        return getAttributeValue("valign");
    }

    /**
     * Creates a new table cell at the end of the row.
     *
     * @return Td
     */
    public Td createCell()
    {
        return addCell( new Td(this) );
    }

    /**
     * Creates a new table cell at the specified index. If one already exists there, it
     * will be replaced.
     *
     * @param index The index.
     * @return Td
     */
    public Td createCellAt(int index)
    {
        return createCellAt(index, true);
    }

    /**
     * Creates a new table cell at the specified index. If one already exists there and the
     * replace flag is true, it will be replaced. If it is false, it will be added at the
     * index moving everything else to the right (index increments by 1).
     *
     * @param index The index.
     * @param replace Whether to replace the existing cell.
     * @return Td
     */
    public Td createCellAt(int index, boolean replace)
    {
        return addCell(index, new Td(this), replace);
    }

    /**
     * Adds the given cell to the end of this row and returns it.
     *
     * @param cell The cell to add.
     * @return Td
     * @throws IllegalArgumentException if the cell is null
     */
    public Td addCell(Td cell) throws IllegalArgumentException
    {
        return addCell(_cells.size(), cell, false);
    }

    /**
     * Adds the given cell to this row at the specified index and returns it. If a cell already exists
     * there, then it is replaced.
     *
     * @param index The index to add at.
     * @param cell The cell to add.
     * @return Td
     * @throws IllegalArgumentException if the cell is null
     */
    public Td addCell(int index, Td cell) throws IllegalArgumentException
    {
        return addCell(index, cell, true);
    }

    /**
     * Adds the given cell to this row at the specified index and returns it. If a cell already exists
     * there and the replace parameter is true, then it is replaced. If the parameter is false
     * the cell is added at the index of the cell that is specified and pushes the existing
     * cells right.
     *
     * @param index The index to add at.
     * @param cell The cell to add.
     * @param replace Whether to replace the existing cell if it is there.
     * @return Td
     * @throws IllegalArgumentException if the cell is null
     */
    public Td addCell(int index, Td cell, boolean replace) throws IllegalArgumentException
    {
        if (cell == null) throw new IllegalArgumentException("Cell cannot be null");
        if (index < 0) throw new IllegalArgumentException("index: " +index+ " is invalid.");

        //Make sure we are adding to the end if it is greater than the size
        if ( index > _cells.size() ) index = _cells.size();

        cell.setParent(this);
        int bodyIndex = 0;
        if (index > 0)
        {
            //Get the index of the cell to add in front of
            bodyIndex = getBody().indexOf( _cells.get(index-1) )+1;
        }

        //Insert into the cells vector
        if ( index == _cells.size() ) _cells.add(cell);
        else if (replace) _cells.set(index, cell);
        else _cells.add(index, cell);

        Table t = getParentTable();
        if (t != null) t.addCell(this, index);
        return (Td)moveBodyContentTo(bodyIndex, cell);
    }

    /**
     * Removes the given cell from the row and the body.
     *
     * @param cell The cell to remove.
     */
    public void removeCell(Td cell)
    {
        if ( cell == null || !_cells.contains(cell) ) return;
        removeBodyContent(cell);
        int indexOf = _cells.indexOf(cell);
        _cells.remove(cell);
        Table t = getParentTable();
        if (t != null) t.removeCell( this, cell.getMapId() );
    }

    /**
     * Removes the cell at the given index from the row and the body.
     *
     * @param index The index.
     */
    public void removeCellAt(int index)
    {
        removeCell( getCell(index) );
    }

    /**
     * Returns the index of the given cell or -1 if it doesn't exist.
     *
     * @param cell The cell.
     * @return int
     */
    public int indexOf(Td cell)
    {
        return _cells.indexOf(cell);
    }

    /**
     * Clears all the cells from the row.
     */
    public void clearCells()
    {
        while (getCells().size() > 0)
        {
            Td cell = getCell(0);
            cell.clearContent();
            removeCell(cell);
        }
        Table t = getParentTable();
        if (t != null) t.clearCells(this);
    }

    /**
     * Creates a new table header cell.
     *
     * @return Th
     */
    public Th createHeaderCell()
    {
        return (Th)addCell( new Th(this) );
    }

    /**
     * Returns the parent table of this row.
     *
     * @return Table
     */
    public Table getParentTable()
    {
        MarkupContent parent = getParent();
        //The parent will either be a table or a thead, tbody, or tfooter all of which have only a table
        //as a parent. The parent will be either a TrParent or a Form. If it is not a table, then a Table
        //is assumed to be the parent of a parent. If it is not there, then null is returned.
        if (parent instanceof Table) return (Table)parent;
        else if (parent != null) return (Table)parent.getParent();
        else return null;
    }

    /**
     * Returns the first cell in the Vector. If there are no cells yet, it returns null.
     *
     * @return Td
     */
    public Td getFirstCell()
    {
        if (_cells.size() > 0) return getCell(0);
        else return null;
    }

    /**
     * Returns the last cell in the Vector. If there are no cells yet, it returns null.
     *
     * @return Td
     */
    public Td getLastCell()
    {
        if (_cells.size() > 0) return getCell(_cells.size()-1);
        else return null;
    }

    /**
     * Returns the cell at the specified index.
     *
     * @param index The cell index.
     * @return Td
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Td getCell(int index) throws IndexOutOfBoundsException
    {
        return (Td)_cells.get(index);
    }

    /**
     * Returns all of the cells in this row.
     *
     * @return Vector
     */
    public Vector getCells()
    {
        return _cells;
    }

    /**
     * Returns the total colspan of the cells in this row.
     *
     * @return int
     */
    public int getTotalColspan()
    {
        int ret = 0;
        int size = _cells.size();
        for (int i=0; i<size; i++)
        {
            ret += getCell(i).getColspan();
        }
        return ret;
    }

    /**
     * Overrides setAttribute to make sure that align and valign are valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if align or valign are invalid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "align".equals(name) )
        {
            setAlign(val);
        }
        else if ( "valign".equals(name) )
        {
            setValign(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);

        int size = getBodySize();
        Table parent = getParentTable();
        if (parent != null) parent.addRow();
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            if (c instanceof Td)
            {
                _cells.add(c);
            }
        }
    }
}