package com.zitego.markup.html.tag.block;

import com.zitego.util.Constant;
import java.util.Vector;

/**
 * Represents different block format types such as paragraph or div.
 *
 * @author John Glorioso
 * @version $Id: BlockFormatType.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class BlockFormatType extends Constant
{
    public static final BlockFormatType SPAN = new BlockFormatType("Span");
    public static final BlockFormatType BLOCK_QUOTE = new BlockFormatType("Block Quote");
    public static final BlockFormatType DIV = new BlockFormatType("Division");
    public static final BlockFormatType PARAGRAPH = new BlockFormatType("Paragraph");
    public static final BlockFormatType ADDRESS = new BlockFormatType("Address");
    /** Gets incremented as list types are initialized. */
    private static int _nextId = 0;
    /** The method types. */
    private static Vector _types;

    /**
     * Creates a new block format type.
     *
     * @param desc The description.
     */
    private BlockFormatType(String desc)
    {
        super(_nextId++, desc);
        if (_types == null) _types = new Vector();
        _types.add(this);
    }

    /**
     * Returns an BlockFormatType based on the id passed in. If the id does not match the id of
     * a constant, then we return null. If there are two constants with the same id, then
     * the first one is returned.
     *
     * @param id The constant id.
     * @return BlockFormatType
     */
    public static BlockFormatType evaluate(int id)
    {
        return (BlockFormatType)Constant.evaluate(id, _types);
    }

    /**
     * Returns an BlockFormatType based on the description passed in. If the description does not
     * match the description of a constant, then we return null. If there are two constants
     * with the same description, then the first one is returned.
     *
     * @param name The description.
     * @return BlockFormatType
     */
    protected static BlockFormatType evaluate(String name)
    {
        return (BlockFormatType)Constant.evaluate(name, _types);
    }

    public Vector getTypes()
    {
        return _types;
    }
}