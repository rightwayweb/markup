package com.zitego.markup.html.tag.table;

import com.zitego.markup.*;
import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.format.*;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This is an abstract class that is the parent of any html tag that can have table
 * rows (table, thead, tfoot, and tbody). The commonality is that they all can create
 * a row and have an alignment.
 *
 * @author John Glorioso
 * @version $Id: TrParent.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public abstract class TrParent extends EventDrivenTag
{
    /** A hashtable of the allowable alignment options. */
    private Hashtable _allowableAligns;
    /** A hashtable of the allowable alignment options. */
    private Hashtable _allowableValigns;
    /** The rows */
    private Vector _rows;

    /**
     * Creates a new TrParent tag with a tag name, align options, and valign options. If align or
     * valign should not be set, then pass in null. A parent is needed to format the TrParent to
     * html however. An UnsupportedFormatType exception will be thrown if the parent is
     * not specified before format(FormatType.HTML) is called.
     *
     * @param tagName The tagname.
     * @param alignOpts The align options.
     * @param valignOpts The valign options.
     */
    protected TrParent(String tagName, String[] alignOpts, String[] valignOpts)
    {
        super(tagName);
        setAlignmentOptions(alignOpts);
        setVerticalAlignmentOptions(valignOpts);
        _rows = new Vector();
    }

    /**
     * Creates a new TrParent tag with an HtmlMarkupTag parent, tag name, align
     * options, and valign options. If align or valign should not be set, then
     * pass in null.
     *
     * @param tagName The tagname.
     * @param parent The parent HtmlMarkupTag.
     * @param alignOpts The align options.
     * @param valignOpts The valign options.
     */
    protected TrParent(String tagName, HtmlMarkupTag parent, String[] alignOpts, String[] valignOpts)
    {
        super(tagName, parent);
        setAlignmentOptions(alignOpts);
        setVerticalAlignmentOptions(valignOpts);
        _rows = new Vector();
    }

    private void setAlignmentOptions(String[] opts)
    {
        if (opts != null)
        {
            _allowableAligns = new Hashtable();
            for (int i=0; i<opts.length; i++)
            {
                _allowableAligns.put(opts[i].toLowerCase(), "1");
            }
        }
    }

    private void setVerticalAlignmentOptions(String[] opts)
    {
        if (opts != null)
        {
            _allowableValigns = new Hashtable();
            for (int i=0; i<opts.length; i++)
            {
                _allowableValigns.put(opts[i].toLowerCase(), "1");
            }
        }
    }

    /**
     * Sets the alignment of the TrParent.
     *
     * @param align The alignment.
     * @throws IllegalArgumentException if the alignment is not valid and this is strict.
     */
    public void setAlign(String align) throws IllegalArgumentException
    {
        if (align != null)
        {
            align = align.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableAligns == null || _allowableAligns.get(align) == null)
                {
                    throw new IllegalArgumentException("align: "+align+" is invalid or cannot be set in this class.");
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
     * Sets the valign of the TrParent.
     *
     * @param valign The valignment.
     * @throws IllegalArgumentException if the valign is not valid and this is strict.
     */
    public void setValign(String valign) throws IllegalArgumentException
    {
        if (valign != null)
        {
            valign = valign.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableValigns == null || _allowableValigns.get(valign) == null)
                {
                    throw new IllegalArgumentException("valign: "+valign+" is invalid or cannot be set in this class.");
                }
            }
        }
        super.setAttribute("valign", valign);
    }

    /**
     * Returns the valignment of the tag.
     *
     * @return String
     */
    public String getValign()
    {
        return getAttributeValue("valign");
    }

    /**
     * Returns the index of the specified row or -1 if the row does not exist.
     *
     * @return int
     */
    public int indexOf(Tr row)
    {
        return _rows.indexOf(row);
    }

    /**
     * Adds the given row to the table and sets the parent
     * to this and returns it.
     *
     * @param row The row to add.
     * @return Tr
     */
    public Tr addRow(Tr row)
    {
        return addRow(_rows.size(), row);
    }

    /**
     * Adds the given row to the table at the specified index and sets the parent
     * to this and returns it.
     *
     * @param index The index to add the row.
     * @param row The row to add.
     * @return Tr
     */
    public Tr addRow(int index, Tr row)
    {
        row.setParent(this);
        _rows.add(index, row);
        return (Tr)moveBodyContentTo(index, row);
    }

    /**
     * Creates and adds a new TrParent row.
     *
     * @return Tr
     */
    public Tr createRow()
    {
        return createRowAt( getRows().size() );
    }

    /**
     * Creates and adds a new TrParent row at the specified index.
     *
     * @param index Where to add the row.
     * @return Tr
     * @throws IndexOutBoundsException
     */
    public Tr createRowAt(int index) throws IndexOutOfBoundsException
    {
        return addRow( index, new Tr(this) );
    }

    /**
     * Creates a row with a single cell in it that has a colspan of the number of cells
     * in the previous row (if there is a previous row with more then one cell) with
     * a non-breaking space as the cell content.
     *
     * @return Tr
     */
    public Tr createEmptyRow()
    {
        Tr row = createRow();
        Td cell = row.createCell();
        cell.addBodyContent("&nbsp;");
        if (_rows.size() > 1)
        {
            //Get the last row
            int colspan = getRow(_rows.size()-2).getTotalColspan();
            if (colspan > 1) cell.setColspan(colspan);
        }
        return row;
    }

    /**
     * Creates the specified number of empty rows.
     *
     * @param num The number of empty rows to create.
     */
    public void createEmptyRows(int num)
    {
        for (int i=0; i<=num; i++)
        {
            createEmptyRow();
        }
    }

    /**
     * Moves the row to the specified index and returns it. If the row
     * does not exist in the trparent, then it is added at the specified index.
     * This does <u>not</u> account for rowspan. You must manually change rowspan.
     *
     * @param index The index at which to move the row.
     * @param row The row to move.
     * @return Tr
     * @throws IllegalArgumentException if the row is null.
     */
    public Tr moveRowTo(int index, Tr row) throws IllegalArgumentException
    {
        //Make sure it is not null
        if (row == null) throw new IllegalArgumentException("row cannot be null");

        //Remove it if it is in there
        if ( _rows.contains(row) )
        {
            _rows.remove(row);
            removeBodyContent(row);
        }
        _rows.add(index, row);
        addBodyContentAt(index, row);
        return row;
    }

    /**
     * Removes the given row from the TrParent and the body.
     *
     * @param row The row to remove.
     */
    public void removeRow(Tr row)
    {
        if ( row == null || !_rows.contains(row) ) return;
        removeBodyContent(row);
        _rows.remove(row);
    }

    /**
     * Removes the row at the given index from the TrParent and the body.
     *
     * @param index The index.
     */
    public void removeRowAt(int index)
    {
        removeRow( getRow(index) );
    }

    /**
     * Returns the first row in the Vector. If there are no rows yet, it returns null.
     *
     * @return Tr
     */
    public Tr getFirstRow()
    {
        if (_rows.size() > 0) return getRow(0);
        else return null;
    }

    /**
     * Returns the last row in the Vector. If there are no rows yet, it returns null.
     *
     * @return Tr
     */
    public Tr getLastRow()
    {
        if (_rows.size() > 0) return getRow(_rows.size()-1);
        else return null;
    }

    /**
     * Returns the row at the specified index.
     *
     * @param index The row index.
     * @return Tr
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Tr getRow(int index) throws IndexOutOfBoundsException
    {
        return (Tr)_rows.get(index);
    }

    /**
     * Returns all of the rows in this TrParent.
     *
     * @return Vector
     */
    public Vector getRows()
    {
        return _rows;
    }

    /**
     * Removes all rows from the TrParent.
     */
    public void clearRows()
    {
        while (getRows().size() > 0)
        {
            Tr row = getRow(0);
            row.clearCells();
            removeRow(row);
        }
    }

    /**
     * Overrides setAttribute to make sure that align and valign are valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if align and valign or invalid.
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
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            if (c instanceof Tr)
            {
                _rows.add(c);
            }
        }
    }
}