package com.zitego.markup.html.tag.table;

import com.zitego.markup.*;
import com.zitego.markup.html.HtmlMarkupMap;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.markup.html.tag.VariableSize;
import com.zitego.markup.html.tag.textEffect.*;
import com.zitego.format.*;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class represents an html table tag. A table tag must have a parent tag and
 * that parent tag must be an HtmlMarkupTag. A table tag has many attributes. These
 * attributes are align, bgcolor, border, cellpadding, cellspacing, frame, rules,
 * summary, height, and width. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Table.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see com.zitego.markup.html.tag.HtmlMarkupTag#createTable()
 */
public class Table extends TrParent
{
    /** A hashtable of the allowable frame options. */
    private static Hashtable _allowableFrames = new Hashtable();
    static
    {
        _allowableFrames.put("void", "1");
        _allowableFrames.put("above", "1");
        _allowableFrames.put("below", "1");
        _allowableFrames.put("hsides", "1");
        _allowableFrames.put("lhs", "1");
        _allowableFrames.put("rhs", "1");
        _allowableFrames.put("vsides", "1");
        _allowableFrames.put("box", "1");
        _allowableFrames.put("border", "1");
    }
    /** A hashtable of the allowable rules options. */
    private static Hashtable _allowableRules = new Hashtable();
    static
    {
        _allowableRules.put("none", "1");
        _allowableRules.put("groups", "1");
        _allowableRules.put("rows", "1");
        _allowableRules.put("cols", "1");
        _allowableRules.put("all", "1");
    }
    /** The alignment options. */
    private static String[] _alignmentOptions = new String[] { "left", "center", "right" };
    /** The vertical alignment options. */
    private static String[] _valignmentOptions = new String[] { "top", "middle", "bottom" };
    /** The table map. */
    private TableMap _map;

    public static void main(String[] args) throws Exception
    {
        Table t = new Table();
        Tr row = t.createRow();
        Td cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.addBodyContent("row "+row.getMapId()+" cell "+cell.getMapId());
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.addBodyContent("row "+row.getMapId()+" cell "+cell.getMapId());
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.addBodyContent("row "+row.getMapId()+" cell "+cell.getMapId());
        row = t.createRow();
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.addBodyContent("row "+row.getMapId()+" cell "+cell.getMapId());
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.addBodyContent("row "+row.getMapId()+" cell "+cell.getMapId());
        row = t.createRow();
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.addBodyContent("row "+row.getMapId()+" cell "+cell.getMapId());
        row = t.createRow();
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.addBodyContent("row "+row.getMapId()+" cell "+cell.getMapId());
        cell.setColspan(5);
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.addBodyContent("row "+row.getMapId()+" cell "+cell.getMapId());

        System.out.println("html:");
        System.out.println( t.format(FormatType.HTML) );
        System.out.println("\r\nmap:");
        t.debugMap();

        t.mergeCells(new TableLocation(1,1), CellDirection.W);

        System.out.println("html:");
        System.out.println( t.format(FormatType.HTML) );
        System.out.println("\r\nmap:");
        t.debugMap();

        for (int i=0; i<t.getRows().size(); i++)
        {
            row = t.getRow(i);
            for (int j=0; j<row.getCells().size(); j++)
            {
                System.out.print( (j>0?", ":"") + row.getCell(j).getTableLocation() );
            }
            System.out.println();
        }
    }

    /**
     * Creates a new Table tag with no parent.
     */
    public Table()
    {
        super("table", _alignmentOptions, _valignmentOptions);
        setMap( new HtmlMarkupMap() );
        _map = new TableMap(this);
    }

    /**
     * Creates a new Table tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Table(HtmlMarkupTag parent)
    {
        super("table", parent, _alignmentOptions, _valignmentOptions);
        _map = new TableMap(this);
    }

    /**
     * Creates a new Table tag with the specified HtmlMarkupParent and
     * returns it.
     *
     * @param parent The parent tag.
     * @return Table
     */
    public static Table createTable(HtmlMarkupTag parent)
    {
        return new Table(parent);
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
     * Sets the border size (in pixels) of the table.
     *
     * @param border The border size.
     * @throws IllegalArgumentException if the size is negative and this is strict.
     */
    public void setBorder(int border) throws IllegalArgumentException
    {
        if (border < 0 && isStrict() )
        {
            throw new IllegalArgumentException("border size must be positive.");
        }
        super.setAttribute( "border", String.valueOf(border) );
    }

    /**
     * Returns the border size in pixels. If it is not set, then it returns 0.
     *
     * @return int
     */
    public int getBorder()
    {
        return getIntAttributeValue("border");
    }

    /**
     * Sets the cellpadding of the table. It can be an exact number of pixels or an integer percentage.
     *
     * @param padding The cellpadding.
     * @throws IllegalArgumentException if the cellpadding is not valid.
     */
    public void setCellPadding(String padding) throws IllegalArgumentException
    {
        super.setAttribute( "cellpadding", (padding != null ? new VariableSize(padding, false).toString() : null) );
    }

    /**
     * Returns the cellpadding of the table.
     *
     * @return String
     */
    public String getCellPadding()
    {
        return getAttributeValue("cellpadding");
    }

    /**
     * Sets the cellspacing of the table. It can be an exact number of pixels or an integer percentage.
     *
     * @param spacing The cellspacing.
     * @throws IllegalArgumentException if the cellspacing is not valid.
     */
    public void setCellSpacing(String spacing) throws IllegalArgumentException
    {
        super.setAttribute( "cellspacing", (spacing != null ? new VariableSize(spacing, false).toString() : null) );
    }

    /**
     * Returns the cellspacing of the table.
     *
     * @return String
     */
    public String getCellSpacing()
    {
        return getAttributeValue("cellspacing");
    }

    /**
     * Sets the height of the table. It can be an exact number of pixels or an integer percentage.
     *
     * @param height The height.
     * @throws IllegalArgumentException if the height is not valid.
     */
    public void setHeight(String height) throws IllegalArgumentException
    {
        super.setAttribute( "height", (height != null ? new VariableSize(height, false).toString() : null) );
    }

    /**
     * Removes the width attribute.
     */
    public void removeHeight()
    {
        removeAttribute("height");
    }

    /**
     * Returns the height of the table.
     *
     * @return String
     */
    public String getHeight()
    {
        return getAttributeValue("height");
    }

    /**
     * Sets the width of the table. It can be an exact number of pixels or an integer percentage.
     *
     * @param width The width.
     * @throws IllegalArgumentException if the width is not valid.
     */
    public void setWidth(String width) throws IllegalArgumentException
    {
        super.setAttribute( "width", (width != null ? new VariableSize(width, false).toString() : null) );
    }

    /**
     * Removes the width attribute.
     */
    public void removeWidth()
    {
        removeAttribute("width");
    }

    /**
     * Returns the width of the table.
     *
     * @return String
     */
    public String getWidth()
    {
        return getAttributeValue("width");
    }

    /**
     * Sets the frame for the border of the table.
     *
     * @param frame The frame.
     * @throws IllegalArgumentException if the frame is invalid and this is strict.
     */
    public void setFrame(String frame) throws IllegalArgumentException
    {
        if (frame != null)
        {
            frame = frame.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableFrames.get(frame) == null)
                {
                    throw new IllegalArgumentException("frame must be void, above, below, hsides, lhs, rhs, " +
                                                       "vsides, box, or border. "+frame+" is invalid.");
                }
            }
        }
        super.setAttribute("frame", frame);
    }

    /**
     * Returns the frame for the border of the table.
     *
     * @return String
     */
    public String getFrame()
    {
        return getAttributeValue("frame");
    }

    /**
     * Sets the rules for the border of the table.
     *
     * @param rules The rules.
     * @throws IllegalArgumentException if the rules is invalid and this is strict.
     */
    public void setRules(String rules) throws IllegalArgumentException
    {
        if (rules != null)
        {
            rules = rules.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableRules.get(rules) == null)
                {
                    throw new IllegalArgumentException("rules must be none, groups, rows, cols, or all. "+rules+" is invalid.");
                }
            }
        }
        super.setAttribute("rules", rules);
    }

    /**
     * Returns the rules for the border of the table.
     *
     * @return String
     */
    public String getRules()
    {
        return getAttributeValue("rules");
    }

    /**
     * Sets the summary of the table.
     *
     * @param summary The summary.
     */
    public void setSummary(String summary)
    {
        setAttribute("summary", summary);
    }

    /**
     * Returns the summary of the tag.
     *
     * @return String
     */
    public String getSummary()
    {
        return getAttributeValue("summary");
    }

    /**
     * Overrides setAttribute to make sure that border is numeric as well as making
     * sure that height, width, cellpadding, and cellspacing are legitimate VariableSizes.
     * Also, it makes sure that align, frame, and rules are valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is border and the value is not numeric.
     * @throws IllegalArgumentException if the width, height, cellpadding, or cellspacing are
     *                                  not valid VariableSize or if align, frame, or rules are invalid.
     */
    public void setAttribute(String name, String val) throws NumberFormatException, IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "border".equals(name) )
        {
            setBorder( Integer.parseInt(val) );
        }
        else if ( "height".equals(name) )
        {
            setHeight(val);
        }
        else if ( "width".equals(name) )
        {
            setWidth(val);
        }
        else if ( "cellpadding".equals(name) )
        {
            setCellPadding(val);
        }
        else if ( "cellspacing".equals(name) )
        {
            setCellSpacing(val);
        }
        else if ( "frame".equals(name) )
        {
            setFrame(val);
        }
        else if ( "rules".equals(name) )
        {
            setRules(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    /**
     * Overrides setMap(MarkupMap) to make sure that the table map is recreated with the new
     * ids.
     *
     * @param map The markup map.
     * @throws IllegalArgumentException if the map is null.
     */
    protected void setMap(MarkupMap map)
    {
        boolean mapChanging = ( map != getMap() );
        super.setMap(map);

        //Only need to do this if we are changing maps
        if (_map != null && mapChanging)
        {
            Vector rows = getRows();
            int size = rows.size();
            if (size > 0)
            {
                //Clear the original table map to use the new ids
                _map = new TableMap(this);
                for (int i=0; i<size; i++)
                {
                    Tr row = (Tr)rows.get(i);
                    //Add this row to the map
                    _map.addRow();
                    Vector cells = row.getCells();
                    int size2 = cells.size();
                    for (int j=0; j<size2; j++)
                    {
                        //Add the cell to the table map
                        addCell(row, j);
                    }
                }
            }
        }
    }

    /**
     * Adds the given row to the table at the given index. If the thead, tfoot, or tbody
     * have already been defined, then an IllegalStateException is thrown. The row
     * is returned when done.
     *
     * @param index The index to add to.
     * @param row The row to add.
     * @return Tr
     * @throws IllegalStateException if thead, tfoot, or tbody have already been created and this is strict.
     */
    public Tr addRow(int index, Tr row) throws IllegalArgumentException
    {
        if ( isStrict() )
        {
            if (getFirstBodyContent() instanceof Thead)
            {
                throw new IllegalStateException("rows must be added to thead, tfoot, or tbody");
            }
        }
        row = super.addRow(index, row);
        _map.addRow();
        return row;
    }

    void addRow()
    {
        _map.addRow();
    }

    public Tr moveRowTo(int index, Tr row) throws IllegalArgumentException
    {
        int indexOf = indexOf(row);
        //Return if we are not moving anywhere
        if (index == indexOf) return row;

        row = super.moveRowTo(index, row);
        _map.moveRowTo(index, indexOf);
        return row;
    }

    public void removeRow(Tr row)
    {
        int indexOf = indexOf(row);
        super.removeRow(row);
        _map.removeRow(indexOf);
    }

    public void removeRowAt(int index)
    {
        removeRow( getRow(index) );
    }

    void addCell(Tr row, int cellIndex)
    {
        _map.addCell
        (
            indexOf(row),
            row.getCell(cellIndex).getMapId(),
            ( cellIndex == 0 ? -1 : row.getCell(cellIndex-1).getMapId() )
        );
    }

    void removeCell(Tr row, int cellId)
    {
        _map.removeCell(indexOf(row), cellId);
    }

    void clearCells(Tr row)
    {
        _map.clearCells( indexOf(row) );
    }

    void registerCell(Tr row, Td cell)
    {
        //Return if the cell does not exist in the table yet
        TableLocation loc = cell.getTableLocation();
        if (loc == null) return;
        if (loc.row >= 0 && loc.column >= 0) _map.registerCell( indexOf(row), cell.getMapId() );
    }

    public void debugMap()
    {
        if (_map != null) _map.debug();
        else System.out.println("_map is null");
    }

    /**
     * Creates a table head tag. Rows that are added to the table head tag appear in the thead
     * section. This must be created before the tfoot and tbody tags have been created. If there
     * are already rows in the table, an IllegalStateException is thrown.
     *
     * @return Thead
     * @throws IllegalStateException if there are already rows.
     */
    public Thead createThead() throws IllegalStateException
    {
        if (getRows().size() > 0) throw new IllegalStateException("Rows have already been added to this table");
        return new Thead(this);
    }

    /**
     * Creates a table foot tag. Rows that are added to the table head tag appear in the tfoot
     * section. This must be created after thead tag has been created. If there are already rows
     * in the table, an IllegalStateException is thrown.
     *
     * @return Tfoot
     * @throws IllegalStateException if there are already rows and this is strict.
     */
    public Tfoot createTfoot() throws IllegalStateException
    {
        if (getRows().size() > 0 && isStrict() ) throw new IllegalStateException("Rows have already been added to this table");
        return new Tfoot(this);
    }

    /**
     * Creates a table body tag. Rows that are added to the table body tag appear in the tbody
     * section. This must be created after the thead and tfoot tags have been created. If it is
     * created out of order or there are already rows in the table, an IllegalStateException is
     * thrown.
     *
     * @return Tbody
     * @throws IllegalStateException if there are already rows or the thead and tfoot tags do
     *                               not already exist and this is strict.
     */
    public Tbody createTbody() throws IllegalStateException
    {
        if (getRows().size() > 0 && isStrict() )
        {
            throw new IllegalStateException("Rows have already been added to this table");
        }
        MarkupContent head = getFirstBodyContent();
        MarkupContent foot = getLastBodyContent();
        if ( isStrict() )
        {
            if ( head == null || !(head instanceof Thead) || foot == null || !(foot instanceof Tfoot) )
            {
                throw new IllegalStateException("Thead and Tfoot must first be created.");
            }
        }
        return new Tbody(this);
    }

    /**
     * Overrides parent to clear the rows from the Thead, Tfoot, and Tbody as well.
     */
    public void clearRows()
    {
        super.clearRows();
        MarkupContent content = getFirstBodyContent();
        if (content instanceof Thead) ( (Thead)content ).clearRows();
        MarkupBody body = getBody();
        int size = (body != null ? body.size() : 0);
        if (size > 1)
        {
            content = getBodyContent(1);
            if (content instanceof Tfoot) ( (Tfoot)content ).clearRows();
            if (body.size() > 2)
            {
                content = getBodyContent(2);
                if (content instanceof Tbody) ( (Tbody)content ).clearRows();
            }
        }
        _map.clearRows();
    }

    /**
     * Creates a table caption with the specified text and places it as the first body content within
     * the table. If one already exists, then it is replaced.
     *
     * @param text The text.
     * @return Caption
     */
    public Caption createCaption(String text)
    {
        return createCaption(text, null);
    }

    /**
     * Creates a table caption with the specified text and block formatting and places it as the first body
     * content within the table. If one already exists, then it is replaced. If the effect is null, then one
     * is not set.
     *
     * @param text The text.
     * @param type The text effect.
     * @return Caption
     */
    public Caption createCaption(String text, TextEffectType effectType)
    {
        Caption ret = null;
        if (effectType != null)
        {
            TextEffect effect = TextEffectFactory.getTextEffect(effectType, this);
            ret = new Caption(effect);
        }
        else
        {
            ret = new Caption(this);
        }
        ret.setIsOnOwnLine(true);
        ret.createTextContent(text);
        MarkupContent c = getBodyContent(0);
        if (c instanceof Caption) removeBodyContent(c);
        return (Caption)moveBodyContentTo(0, ret);
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        //_map.balanceTable();
        String content = super.generateContent(type);
        _map.resetColumnPointer();
        return content;
    }

    /**
     * Returns a cell given the table location or null if the location is invalid.
     *
     * @param loc The location of the cell.
     * @return Td
     */
    public Td getCell(TableLocation loc)
    {
        try
        {
            Tr row = getRow(loc.row);
            return row.getCell(loc.column);
        }
        catch (IndexOutOfBoundsException iobe)
        {
            return null;
        }
    }

    /**
     * Returns a cell given the table location of the originating cell and the cell direction to get
     * the cell from in relation to the origin. Returns null if either the origin does not exist or
     * a cell does not exist in the specified direction.
     *
     * @param loc The origin cell.
     * @param dir The cell direction.
     * @return Td
     * @throws IllegalArgumentException if either the location or the direction is null.
     */
    public Td getCell(TableLocation loc, CellDirection dir) throws IllegalArgumentException
    {
        if (loc == null) throw new IllegalArgumentException("Table location cannot be null");
        if (dir == null) throw new IllegalArgumentException("Cell direction cannot be null");
        try
        {
            int row = -1;
            int col = -1;
            if (dir == CellDirection.N || dir ==  CellDirection.NE || dir == CellDirection.NW) row = loc.row - 1;
            else if (dir == CellDirection.E || dir == CellDirection.W) row = loc.row;
            else if (dir == CellDirection.S || dir == CellDirection.SE || dir == CellDirection.SW) row = loc.row + 1;

            if (dir == CellDirection.N || dir == CellDirection.S) col = loc.column;
            else if (dir == CellDirection.NE || dir == CellDirection.SE || dir == CellDirection.E) col = loc.column + 1;
            else if (dir == CellDirection.NW || dir == CellDirection.SW || dir == CellDirection.W) col = loc.column - 1;

            Tr r = getRow(row);
            return r.getCell(col);
        }
        catch (IndexOutOfBoundsException iobe)
        {
            return null;
        }
    }

    /**
     * Merges two cells given the origin cell and a direction to merge in. Currently, only east and west
     * are supported. The two cells are merged into one with the following rules.<br>
     * <br>
     * First, the resulting cell has a colspan that is the colspan of the two cells incremented by one.
     * For example, if cell one has a colspan of 2 and the second cell has no colspan, then the resulting
     * colspan is 3. If neither cell has a colspan then the resulting cell will have a colspan of 2.<br>
     * <br>
     * Second, the contents of the cells are merged together with the originating cell's contents first.
     * If the cells are both empty (@see Td#isEmpty), then the resulting cell is empty as well using the
     * originating cell's empty content.<br>
     * <br>
     * The resulting cell is returned.
     *
     * @param loc The origin cell's location.
     * @param dir The direction to merge.
     * @return Td
     * @throws IllegalArgumentException if the merge is not either CellDirection.EAST or CellDirection.WEST.
     */
    public Td mergeCells(TableLocation loc, CellDirection dir) throws IllegalArgumentException
    {
        if ( dir == null || (dir != CellDirection.E && dir != CellDirection.W) )
        {
            throw new IllegalArgumentException("direction must be east or west.");
        }
        Td originCell = getCell(loc);
        Td mergeCell = getCell(loc, dir);
        if (originCell == null) throw new IllegalArgumentException("originCell does not exist");
        if (mergeCell == null) throw new IllegalArgumentException("mergeCell does not exist");

        //Figure out what the colspan should be
        int colspan = originCell.getColspan() + mergeCell.getColspan();

        //Remove the merge cell
        getRow(mergeCell.getTableLocation().row).removeCell(mergeCell);

        originCell.setColspan(colspan);
        if ( !mergeCell.isEmpty() )
        {
            //Remove the nbsp or the blank image if there is one
            if ( originCell.isEmpty() ) originCell.clearContent();
            int size = mergeCell.getBodySize();
            for (int i=0; i<size; i++)
            {
                originCell.addBodyContent( mergeCell.getBodyContent(i) );
            }
        }
        return originCell;
    }

    /**
     * Splits the given cell into two cells. A split occurs from west to east. In other words,
     * if a cell is the third in a row of five, the row will then have six cells after the split.
     * The split cell will remain the third cell and the new one will be the fourth. All other
     * cells will be moved east.<br>
     * <br>
     * If the cell to be split has a colspan, then it remains in that cell and the new cell will
     * not have a colspan. This method returns a two element array containing the resulting two
     * cells from the split. The original cell is element one and the new cell is element two.
     *
     * @param loc The location of the cell to split.
     * @return Td[]
     * @throws IllegalArgumentException if the table location is null or invalid.
     */
    public Td[] splitCell(TableLocation loc) throws IllegalArgumentException
    {
        Td originCell = getCell(loc);
        if (originCell == null) throw new IllegalArgumentException("originCell does not exist");
        Tr row = (Tr)originCell.getParent();
        Td cell2 = row.createCellAt(loc.column+1);
        return new Td[] { originCell, cell2 };
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalArgumentException, UnsupportedFormatException
    {
        super.parseText(text, type);
        //Need to add the cells to the map
        int size = getRows().size();
        for (int i=0; i<size; i++)
        {
            Tr row = getRow(i);
            int cells = row.getCells().size();
            for (int j=0; j<cells; j++)
            {
                addCell(row, j);
                //Need to set colspan if they have it
                Td cell = row.getCell(j);
                if (cell.getColspan() > 1) registerCell(row, cell);
            }
        }
    }
}