package com.zitego.markup.html.tag;

import com.zitego.markup.*;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;
import com.zitego.format.*;
import java.util.Vector;
import java.util.StringTokenizer;

/**
 * This class represents an html frameset tag. A frameset tag must have a parent tag and
 * that parent tag must be an Html tag. A frameset tag has two attributes. These
 * attributes are rows and cols. This class is up to date as of W3C specification
 * version 4.01.
 *
 * @author John Glorioso
 * @version $Id: FrameSet.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see Html#getFrameSet()
 */
public class FrameSet extends HtmlMarkupTag
{
    /** The Frameset tag. */
    protected NoFrames _noframes;

    /**
     * Creates a new FrameSet tag with no parent.
     */
    public FrameSet()
    {
        super("frameset");
    }

    /**
     * Creates a new FrameSet tag with an Html tag parent.
     *
     * @param parent The parent Html tag.
     */
    public FrameSet(Html parent)
    {
        super("frameset", parent);
    }

    /**
     * Creates a new FrameSet tag with a FrameSet parent.
     *
     * @param parent The frameset parent.
     */
    public FrameSet(FrameSet parent)
    {
        super("frameset", parent);
    }

    /**
     * Sets the cols of the frameset. The cols is a comma delimited string of values. The
     * values are either integers specifying the exact number of pixels, a percentage, or *.
     * The format of this is: colwidth[%]|*[,[ ]colwidth[%]|*...] where colwidth is an integer.
     *
     * @param cols The cols specification.
     * @throws IllegalArgumentException if the string does not match the spec.
     */
    public void setCols(String cols) throws IllegalArgumentException
    {
        //Validate first
        parseDivisionString(cols);
        super.setAttribute("cols", cols);
    }

    /**
     * Sets the cols of the frameset and converts them to the string spec from a Vector.
     *
     * @param cols The cols.
     * @throws IllegalArgumentException if the vector elements are not either an integer,
     *                                  an integer%, or *.
     */
    public void setCols(Vector cols) throws IllegalArgumentException
    {
        super.setAttribute( "cols", getDivisionString(cols) );
    }

    /**
     * Returns the cols specification as a String.
     *
     * @return String
     */
    public String getCols()
    {
        return getAttributeValue("cols");
    }

    /**
     * Returns the cols string spec as a Vector.
     *
     * @return Vector
     */
    public Vector getColsVector()
    {
        return parseDivisionString( getCols() );
    }

    /**
     * Sets the rows of the frameset. The rows is a comma delimited string of values. The
     * values are either integers specifying the exact number of pixels, a percentage, or *.
     * The format of this is: colwidth[%]|*[,[ ]colwidth[%]|*...] where colwidth is an integer.
     *
     * @param rows The rows specification.
     * @throws IllegalArgumentException if the string does not match the spec.
     */
    public void setRows(String rows) throws IllegalArgumentException
    {
        //Validate first
        parseDivisionString(rows);
        super.setAttribute("rows", rows);
    }

    /**
     * Sets the rows of the frameset and converts them to the string spec from a Vector.
     *
     * @param rows The rows.
     * @throws IllegalArgumentException if the vector elements are not either an integer,
     *                                  an integer%, or *.
     */
    public void setRows(Vector rows) throws IllegalArgumentException
    {
        super.setAttribute( "rows", getDivisionString(rows) );
    }

    /**
     * Returns the rows specification as a String.
     *
     * @return String
     */
    public String getRows()
    {
        return getAttributeValue("rows");
    }

    /**
     * Returns the row string spec as a Vector.
     *
     * @return Vector
     */
    public Vector getRowsVector()
    {
        return parseDivisionString( getRows() );
    }

    /**
     * Converts a Vector of division specs to the specification string in the format of
     * colwidth[%]|*[,[ ]colwidth[%]|*...] where colwidth is an integer.
     *
     * @param rows The rows.
     * @throws IllegalArgumentException if the vector elements are not either an integer,
     *                                  an integer%, or *.
     */
    private String getDivisionString(Vector specs) throws IllegalArgumentException
    {
        if (specs == null) return null;

        int size = specs.size();
        StringBuffer ret = new StringBuffer();
        for (int i=0; i<size; i++)
        {
            if (i > 0) ret.append(",");
            ret.append( new VariableSize((String)specs.get(i)).toString() );
        }
        return ret.toString();
    }

    /**
     * Converts a specification string in the format of
     * colwidth[%]|*[,[ ]colwidth[%]|*...] where colwidth is an integer to a
     * Vector of String specs.
     *
     * @return Vector The rows.
     * @throws IllegalArgumentException if the specification string is unparsable.
     */
    private Vector parseDivisionString(String specs)
    {
        Vector ret = new Vector();
        if (specs != null)
        {
            StringTokenizer st = new StringTokenizer(specs, ",");
            while ( st.hasMoreTokens() )
            {
                ret.add( new VariableSize(st.nextToken()).toString() );
            }
        }
        return ret;
    }

    /**
     * Overrides setAttribute to make sure that rows and cols are legitimate
     * VariableSize attributes.
     *
     * @param name The name.
     * @param val The value.
     * @throws NumberFormatException if the attribute is rows or cols and the
     *                               value is not a legitimate VariableSize.
     */
    public void setAttribute(String name, String val) throws NumberFormatException
    {
        if (name != null) name = name.toLowerCase();
        if ( "rows".equals(name) )
        {
            setRows(val);
        }
        else if ( "cols".equals(name) )
        {
            setCols(val);
        }
        else
        {
            super.setAttribute(name, val);
        }
    }

    /**
     * This cannot create text content, so it throws a IllegalMarkupException if this is strict.
     *
     * @param txt Not used.
     * @return TextContent
     * @throws IllegalMarkupException
     */
    public TextContent createTextContent(String txt) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text content.");
        else return super.createTextContent(txt);
    }

     /**
     * This cannot create a table, so it throws an IllegalMarkupException if this is strict.
     *
     * @return Table
     * @throws IllegalMarkupException
     */
    public Table createTable() throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create a tables.");
        else return super.createTable();
    }

    /**
     * This cannot create an image, so it throws an IllegalMarkupException if this is strict.
     *
     * @param src The image src.
     * @return Img
     * @throws IllegalMarkupException
     */
    public Img createImage(String src) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create images.");
        else return super.createImage(src);
    }

    /**
     * This cannot create a text effect, so it throws an IllegalMarkupException if this is strict.
     *
     * @param type The type of text effect.
     * @throws IllegalMarkupException
     */
    public TextEffect createTextEffect(TextEffectType type) throws IllegalMarkupException
    {
        if ( isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text effects.");
        else return super.createTextEffect(type);
    }

    /**
     * Creates a new frameset, adds it to this frameset, and returns it.
     *
     * @return FrameSet
     */
    public FrameSet createFrameSet()
    {
        return new FrameSet(this);
    }

    /**
     * Creates a new frame, adds it to this frameset, and returns it.
     *
     * @return Frame
     */
    public Frame createFrame()
    {
        return new Frame(this);
    }

    /**
     * Returns the no frames tag. This can only be called if the parent is an Html tag if we are
     * set to strict. If one does not already exist, it is added to the beginning of the body
     * content.
     *
     * @return NoFrames
     * @throws IllegalStateException if the parent is not an html tag.
     */
    public NoFrames getNoFramesTag()
    {
        if ( isStrict() && !(getParent() instanceof Html) )
        {
            throw new IllegalStateException("NoFrames can only be created for top level frameset tags");
        }
        if (_noframes == null) _noframes = new NoFrames(this);
        return _noframes;
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);

        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            if (c instanceof NoFrames) _noframes = (NoFrames)c;
        }
    }
}