package com.zitego.markup.html.tag.table;

/**
 * This is a simple class that represents a table location of a cell. The location is simple a
 * row/column storage. This does <u>not</u> identify where a cell is in the <code>TableMap</code>.
 * toString and parseString are provided for converting to and from a string. There are no
 * accessor or setter methods. The values are retrievable and modifiable directly through the
 * row and column public access member variables for convenience.
 *
 * @author John Glorioso
 * @version $Id: TableLocation.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class TableLocation
{
    /** The row that the cell resides in. */
    public int row = -1;
    /** The column that the cell resides in. */
    public int column = -1;

    /**
     * Creates a new table location with a row and column index.
     *
     * @param row The row.
     * @param column The column.
     */
    public TableLocation(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns a string representation of this location in the format of row:column.
     *
     * @return String
     */
    public String toString()
    {
        return new StringBuffer().append(row).append(":").append(column).toString();
    }

    /**
     * Returns a TableLocation given a string. The format must be row:column.
     *
     * @param in The string representation of this location.
     * @return TableLocation
     * @throws IllegalArgumentException if the location is not parsable.
     * @throws NumberFormatException if the row or column is not numeric.
     */
    public static TableLocation parseString(String in) throws IllegalArgumentException, NumberFormatException
    {
        if (in == null) throw new IllegalArgumentException("string cannot be null");
        int index = in.indexOf(":");
        if (index == -1) throw new IllegalArgumentException("String not in format of <row:column>: "+in);
        int row = Integer.parseInt( in.substring(0, index) );
        int col = Integer.parseInt( in.substring(index+1) );
        return new TableLocation(row, col);
    }
}