package com.zitego.markup.html.tag.table;

import com.zitego.markup.*;
import com.zitego.markup.html.tag.*;
import com.zitego.markup.tag.SpecialChar;
import com.zitego.format.*;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class represents an html td tag. A td tag must have a parent tag and
 * that parent tag must be Tr. A td tag has many attributes. These
 * attributes are align, bgcolor, colspan, height, nowrap, rowspan, valign,
 * and width. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Td.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Tr#createCell()
 */
public class Td extends EventDrivenTag
{
    /** A hashtable of the allowable alignment options. */
    protected static Hashtable _allowableAligns = new Hashtable();
    static
    {
        _allowableAligns.put("left", "1");
        _allowableAligns.put("center", "1");
        _allowableAligns.put("right", "1");
        _allowableAligns.put("justify", "1");
    }
    /** A hashtable of the allowable valign options. */
    protected static Hashtable _allowableValigns = new Hashtable();
    static
    {
        _allowableValigns.put("top", "1");
        _allowableValigns.put("middle", "1");
        _allowableValigns.put("bottom", "1");
        _allowableValigns.put("baseline", "1");
    }
    private String _endTag;

    /**
     * Creates a new Td tag with no parent.
     */
    public Td()
    {
        super("td");
        setHasEndTagOnSameLine(true);
    }

    /**
     * Creates a new Td tag with a Tr parent.
     *
     * @param Tr The parent Tr tag.
     */
    public Td(Tr parent)
    {
        super("td", parent);
        setHasEndTagOnSameLine(true);
    }

    /**
     * Sets the alignment of the cell.
     *
     * @param String The alignment.
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
     * @param String The bgcolor.
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
     * Sets the colspan of the td.
     *
     * @param int The colspan.
     * @throws IllegalArgumentException if the colspan is negative and this is strict.
     */
    public void setColspan(int colspan) throws IllegalArgumentException
    {
        if (colspan < 0 && !isStrict() ) throw new IllegalArgumentException("colspan must be positive.");
        super.setAttribute( "colspan", String.valueOf(colspan) );
        Table t = getParentTable();
        if (t != null) t.registerCell( (Tr)getParent(), this );
    }

    /**
     * Returns the colspan.
     *
     * @return int
     */
    public int getColspan()
    {
        return getIntAttributeValue("colspan", 1);
    }

    /**
     * Sets the rowspan of the td.
     *
     * @param int The rowspan.
     * @throws IllegalArgumentException if the rowspan is negative and this is strict.
     */
    public void setRowspan(int rowspan) throws IllegalArgumentException
    {
        if (rowspan < 0 && isStrict() )
        {
            throw new IllegalArgumentException("rowspan must be positive.");
        }
        super.setAttribute( "rowspan", String.valueOf(rowspan) );
    }

    /**
     * Returns the rowspan.
     *
     * @return int
     */
    public int getRowspan()
    {
        return getIntAttributeValue("rowspan", 1);
    }

    /**
     * Sets the height of the cell. It can be an exact number of pixels or an integer percentage.
     *
     * @param String The height.
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
     * Returns the height of the cell.
     *
     * @return String
     */
    public String getHeight()
    {
        return getAttributeValue("height");
    }

    /**
     * Sets the width of the cell. It can be an exact number of pixels or an integer percentage.
     *
     * @param String The width.
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
     * Returns the width of the cell.
     *
     * @return String
     */
    public String getWidth()
    {
        return getAttributeValue("width");
    }

    /**
     * Sets whether nowrap is on.
     *
     * @param boolean Whether nowrap is on.
     */
    public void setNoWrap(boolean nowrap)
    {
        if (nowrap) super.setAttribute("nowrap");
        else removeAttribute("nowrap");
    }

    /**
     * Returns whether or not nowrap is on.
     *
     * @return boolean
     */
    public boolean getNoWrap()
    {
        return (getAttribute("nowrap") != null);
    }

    /**
     * Sets the valign for the cell.
     *
     * @param String The valign.
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
     * Overrides setAttribute to make sure that colspan and rowspan are numeric as well as making
     * sure that height and width are legitimate VariableSizes. Also, it makes sure that align
     * and valign are valid.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is colspan or rowspan and the value is not numeric.
     * @throws IllegalArgumentException if the width or height are not valid VariableSize or if align or
     *                                  valign are invalid.
     */
    public void setAttribute(String name, String val) throws NumberFormatException, IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "colspan".equals(name) )
        {
            setColspan( Integer.parseInt(val) );
        }
        else if ( "rowspan".equals(name) )
        {
            setRowspan( Integer.parseInt(val) );
        }
        else if ( "height".equals(name) )
        {
            setHeight(val);
        }
        else if ( "width".equals(name) )
        {
            setWidth(val);
        }
        else if ( "align".equals(name) )
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

    /**
     * Returns the table that this belongs to.
     *
     * @return Table
     */
    public Table getParentTable()
    {
        Tr parent = (Tr)getParent();
        if (parent != null) return parent.getParentTable();
        else return null;
    }

    /**
     * Returns whether or not this cell is empty. Empty means that it has either no body content, a text
     * body content that is an nbsp, or a blank 1x1 image defined by the BlankImg class.
     *
     * @return boolean
     * @see com.zitego.markup.html.tag.BlankImg
     */
    public boolean isEmpty()
    {
        int size = getBodySize();
        if (size == 0) return true;
        if (size > 1) return false;
        MarkupContent c = getFirstBodyContent();
        if ( c instanceof TextContent && c.toString().trim().equals(SpecialChar.NBSP.getSymbol()) ) return true;
        if (c instanceof BlankImg) return true;
        return false;
    }

    /**
     * Returns the table location.
     *
     * @return TableLocation
     */
    public TableLocation getTableLocation()
    {
        Table t = getParentTable();
        Tr row = (Tr)getParent();
        if (t == null || row == null) return null;
        else return new TableLocation( t.indexOf(row), row.indexOf(this) );
    }

    /*public String getEndTag(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer().append("</").append( getTagName() ).append(">");
            if ( getParent() != null && hasNewline() ) ret.append(Newline.CHARACTER);
            Vector breaks = getLineBreaks();
            int size = breaks.size();
            for (int i=0; i<size; i++)
            {
                ret.append( ((MarkupContent)breaks.get(i)).format(type) );
            }
            if (size > 0 && !isEmbeddedInLine() ) ret.append(Newline.CHARACTER);
            _endTag = ret.toString();
        }
        return _endTag;
    }*/
}