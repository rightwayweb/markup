package com.zitego.markup.html.tag.table;

import com.zitego.markup.MarkupMap;
import com.zitego.markup.tag.SpecialChar;

/**
 * To store in a tabular manner. This is to allow cell retrieval by CellDirection. A cell with
 * a column span of two will result in two cell entries in the table map with it's id. A cell
 * with a rowspan of two will result in two cell entries in two rows.
 *
 * The number of rows and cells are initialized at a base of ten rows and ten columns. Once that
 * value is surpassed, the table array is re-allocated. A marker is kept for the current row/col
 * max values. For example, the base may be 10x10, but the current usage may only be 3x2.
 * Un-populated cells are filled with -1. Populated cells are filled with the the id of the cell
 * that occupies it. The number of columns are guaranteed to be equal for every row.
 *
 * @author John Glorioso
 * @version $Id: TableMap.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
class TableMap
{
    private Table _table;
    private int _defaultRows = 10;
    private int _defaultCols = 10;
    private int _rowPointer = -1;
    private int _colPointer = -1;
    private int[][] _map;

    TableMap(Table table)
    {
        _table = table;
        clearRows();
    }

    void reallocateRows()
    {
        reallocate(_defaultRows, 0);
    }

    void reallocateCols()
    {
        reallocate(0, _defaultCols);
    }

    void reallocate(int rows, int cols)
    {
        //Copy the old map
        int[][] tmp = _map;
        //Create a new map with the re-allocated size. We have to do this check on the j size because
        //When it is initialized, the i size 0, so there is no j length
        _map = new int[_map.length+rows][ (_map.length == 0 || _map[0] == null ? 0 : _map[0].length)+cols ];
        for (int i=0; i<_map.length; i++)
        {
            for (int j=0; j<_map[i].length; j++)
            {
                if (i < tmp.length && j < tmp[i].length) _map[i][j] = tmp[i][j];
                else _map[i][j] = -1;
            }
        }
    }

    void balanceTable()
    {
        //Go through each row and make sure there is an nbsp for each unfilled column (-1)
        //that is less than the current col pointer
        for (int i=0; i<=_rowPointer; i++)
        {
            Tr row = _table.getRow(i);
            for (int j=0; j<=_colPointer; j++)
            {
                if (_map[i][j] == -1)
                {
                    Td cell = row.createCell();
                    cell.setIsOnOwnLine(true);
                    cell.addBodyContent( SpecialChar.NBSP.getSymbol() );
                }
            }
        }
    }

    void registerCell(int rowIndex, int cellId)
    {
        //This only gets called when colspan changes. Make sure that the map is
        //updated to reflect the colspan. Start at one cause there is already a
        //cell. We just need to expand it to the colspan.
        //First strip the existing cell down to one col in case there was a previous
        //colspan set

        stripCell(rowIndex, cellId, false);
        MarkupMap map = _table.getMap();
        if (map == null) return;
        Td cell = (Td)map.get(cellId);
        if (cell == null) return;
        int colspan = cell.getColspan();
        for (int i=1; i<colspan; i++)
        {
            addCell(rowIndex, cellId, cellId);
        }
    }

    void addRow()
    {
        //Just need to make sure that there is enough room
        if (++_rowPointer >= _map.length) reallocateRows();
    }

    void moveRowTo(int to, int from)
    {
        if (from < 0 || to > _rowPointer) throw new ArrayIndexOutOfBoundsException("Cannot move row from "+from+" to "+to);
        //Copy the row to a holder, then bump everything up one or down one depending on which way
        //we are moving the row
        int[] tmp = _map[from];
        int dir = (from < to ? 1 : -1);
        int index = from;
        while (index != to)
        {
            _map[index] = _map[index+dir];
            index += dir;
        }
        _map[to] = tmp;
    }

    void createRowAt(int index)
    {
        if (index < 0 || index > _rowPointer) throw new ArrayIndexOutOfBoundsException("Cannot create row at "+index);
        //Add a row and move everything at the index down one
        addRow();
        //Loop backward through to the index. The row pointer is at a blank row now that we added one,
        //so we are moving from the row before that
        int[] tmp = _map[_rowPointer];
        for (int i=_rowPointer-1; i>=index; i--)
        {
            _map[i+1] = _map[i];
        }
        _map[index] = tmp;
    }

    void removeRow(int index)
    {
        //Remove the row at the given index, then bump everything up one
        for (int i=index; i<=_rowPointer; i++)
        {
            _map[i] = _map[i+1];
        }
        _rowPointer--;
    }

    void clearRows()
    {
        _map = new int[0][0];
        reallocateRows();
        reallocateCols();
        //reset the row and column pointers
        _rowPointer = -1;
        _colPointer = -1;
    }

    void addCell(int rowIndex, int cellId, int afterId)
    {
        //Look for the cell. If we can't find it, then it gets added at the end.
        //If the after id is -1, then it gets added at the beginning
        int cellIndex = 0;
        if (afterId > -1)
        {
            for (; cellIndex<=_colPointer; cellIndex++)
            {
                //Look ahead to see if the next cell is this id still (or that we are at the end)
                if ( _map[rowIndex][cellIndex] == afterId && (cellIndex == _colPointer || _map[rowIndex][cellIndex+1] != afterId) )
                {
                    cellIndex++;
                    break;
                }
                else if (_map[rowIndex][cellIndex] == -1)
                {
                    break;
                }
            }
        }

        //Figure out if we need to move the column pointer
        int ptr = cellIndex;
        //Start from just after cell index and look for the first -1 to see where this row's content ends
        for (;ptr<_map[rowIndex].length; ptr++)
        {
            if (_map[rowIndex][ptr] == -1) break;
        }
        //Only increment the column pointer if this row has grown larger then the longest
        if (_colPointer < ptr) _colPointer = ptr;
        if (_colPointer+1 >= _map[rowIndex].length) reallocateCols();

        //Take the newly added cell and stick it in after the given cell index. Move everything over
        //if need be.
        for (int j=_colPointer; j>=cellIndex; j--)
        {
            _map[rowIndex][j+1] = _map[rowIndex][j];
        }
        _map[rowIndex][cellIndex] = cellId;
    }

    void removeCell(int rowIndex, int cellId)
    {
        stripCell(rowIndex, cellId, true);
    }

    void stripCell(int rowIndex, int cellId, boolean removeLast)
    {
        int cellIndex = -1;
        boolean done = false;
        //Gotta keep removing until we have gotten rid of all cells or all but one with this id
        //depends on removeLast flag
        int offset = (removeLast ? 0 : 1);
        while (!done)
        {
            for (int j=0; j<=_colPointer; j++)
            {
                if (_map[rowIndex][j] == cellId)
                {
                    cellIndex = j;
                    break;
                }
            }
            //Nothing to remove if it isn't there
            if (cellIndex == -1) return;

            //See if we should remove this
            if (cellIndex == _colPointer || _map[rowIndex][cellIndex+offset] != cellId)
            {
                done = true;
            }
            else
            {
                //Remove the cell at the given row/cell index, then bump everything back one
                for (int j=cellIndex; j<=_colPointer && _map[rowIndex][j] != -1; j++)
                {
                    _map[rowIndex][j] = _map[rowIndex][j+1];
                }
            }
        }
        //Reset the column pointer.
        resetColumnPointer();
    }

    void resetColumnPointer()
    {
        //Look for the longest row
        _colPointer = 0;
        MarkupMap map = _table.getMap();
        for (int i=0; i<=_rowPointer; i++)
        {
            //Go through the rows backwards so that we can replace the nbsp with -1
            for (int j=_map[i].length-1; j>=0; j--)
            {
                if (_map[i][j] != -1)
                {
                    Td cell = (Td)map.get(_map[i][j]);
                    if (j > _colPointer) _colPointer = j;
                    break;
                }
            }
        }
    }

    void clearCells(int rowIndex)
    {
        for (int j=0; j<_map[rowIndex].length; j++)
        {
            _map[rowIndex][j] = -1;
        }
    }

    void debug()
    {
        System.out.println(_map+": pointers: "+_rowPointer+":"+_colPointer);
        for (int i=0; i<_map.length; i++)
        {
            for (int j=0; j<_map[i].length; j++)
            {
                System.out.print( (j>0?",":"") );
                System.out.print(_map[i][j]);
            }
            System.out.println();
        }
    }
}