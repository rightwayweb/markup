package com.zitego.markup.html.tag.textEffect;

import com.zitego.util.Constant;
import java.util.Vector;

/**
 * Represents different font style elements such as bold or underline.
 *
 * @author John Glorioso
 * @version $Id: TextEffectType.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class TextEffectType extends Constant
{
    public static final TextEffectType CITE = new TextEffectType("Citation");
    public static final TextEffectType BIG = new TextEffectType("Big Text");
    public static final TextEffectType BOLD = new TextEffectType("Bold");
    public static final TextEffectType CODE = new TextEffectType("Computer Code");
    public static final TextEffectType DEFINITION = new TextEffectType("Definition Term");
    public static final TextEffectType DELETED = new TextEffectType("Deleted Text");
    public static final TextEffectType EMPHASIS = new TextEffectType("Emphasized Text");
    public static final TextEffectType FONT = new TextEffectType("Font");
    public static final TextEffectType INSERT = new TextEffectType("Inserted Text");
    public static final TextEffectType ITALIC = new TextEffectType("Italic");
    public static final TextEffectType KEYBOARD = new TextEffectType("Keyboard Text");
    public static final TextEffectType PRE = new TextEffectType("Pre-formatted Text (Markup Allowed)");
    public static final TextEffectType SAMP = new TextEffectType("Sample Computer Code");
    public static final TextEffectType SMALL = new TextEffectType("Small Text");
    public static final TextEffectType STRIKE = new TextEffectType("Strike-through Text");
    public static final TextEffectType STRONG = new TextEffectType("Strong Text");
    public static final TextEffectType SUBSCRIPT = new TextEffectType("Subscript");
    public static final TextEffectType SUPERSCRIPT = new TextEffectType("Superscript");
    public static final TextEffectType TELETYPE = new TextEffectType("Teletype Text");
    public static final TextEffectType UNDERLINE = new TextEffectType("Underlined Text");
    public static final TextEffectType VAR = new TextEffectType("Variable Term");
    public static final TextEffectType XMP = new TextEffectType("Pre-formatted Text (Exclude Markup)");
    /** Gets incremented as format types are initialized. */
    private static int _nextId = 0;
    /** The method types. */
    private static Vector _types;

    /**
     * Creates a new TextEffect.
     *
     * @param desc The description.
     */
    private TextEffectType(String desc)
    {
        super(_nextId++, desc);
        if (_types == null) _types = new Vector();
        _types.add(this);
    }

    /**
     * Returns an TextEffectType based on the id passed in. If the id does not match the id of
     * a constant, then we return null. If there are two constants with the same id, then
     * the first one is returned.
     *
     * @param id The constant id.
     * @return TextEffectType
     */
    public static TextEffectType evaluate(int id)
    {
        return (TextEffectType)Constant.evaluate(id, _types);
    }

    /**
     * Returns an Constant based on the description passed in. If the description does not
     * match the description of a constant, then we return null. If there are two constants
     * with the same description, then the first one is returned.
     *
     * @param name The description.
     * @return TextEffectType
     */
    protected static TextEffectType evaluate(String name)
    {
        return (TextEffectType)Constant.evaluate(name, _types);
    }

    public Vector getTypes()
    {
        return _types;
    }
}