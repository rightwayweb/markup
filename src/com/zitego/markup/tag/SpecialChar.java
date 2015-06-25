package com.zitego.markup.tag;

import java.util.Vector;
import com.zitego.util.Constant;

/**
 * This is a simple class used to define special characters such as non-breaking space
 * and currency symbols, etc.
 *
 * @author John Glorioso
 * @version $Id: SpecialChar.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public final class SpecialChar extends Constant
{
    public static final SpecialChar NBSP = new SpecialChar("Non-breaking Space", "&nbsp;");
    public static final SpecialChar LESS_THAN = new SpecialChar("Less Than Sign", "&lt;");
    public static final SpecialChar GREATER_THAN = new SpecialChar("Greater Than Sign", "&gt;");
    public static final SpecialChar AMPERSAND = new SpecialChar("Ampersand", "&amp;");
    public static final SpecialChar QUOTE = new SpecialChar("Double Quote", "&quot;");
    public static final SpecialChar INVERTED_EXCLAMATION = new SpecialChar("Inverted Exclamation Point", "&iexcl;");
    public static final SpecialChar CURRENCY = new SpecialChar("Currency Mark", "&curren;");
    public static final SpecialChar CENT = new SpecialChar("Cent Mark", "&cent;");
    public static final SpecialChar POUND = new SpecialChar("British Pound Sign", "&pound;");
    public static final SpecialChar YEN = new SpecialChar("Japanese Yen Sign", "&yen;");
    public static final SpecialChar EURO = new SpecialChar("Euro Symbol", "&euro;");
    public static final SpecialChar BROKEN_VERTICAL_BAR = new SpecialChar("Broken Vertical Bar", "&brvbar;");
    public static final SpecialChar SECTION = new SpecialChar("Section Symbol", "&sect;");
    public static final SpecialChar SPACING_DIAERESIS = new SpecialChar("Spacing Diaeresis", "&uml;");
    public static final SpecialChar COPYRIGHT = new SpecialChar("Copyright Symbol", "&copy;");
    public static final SpecialChar FEMININE_ORDINAL_INDICATOR = new SpecialChar("Feminine Ordinal Indicator", "&ordf;");
    public static final SpecialChar LEFT_ANGLE_QUOTATION_MARK = new SpecialChar("Angle Quotation Mark (left)", "&laquo;");
    public static final SpecialChar NEGATION = new SpecialChar("Negation Symbol", "&not;");
    public static final SpecialChar SOFT_HYPHEN = new SpecialChar("Soft Hyphen", "&shy;");
    public static final SpecialChar REGISTERED_TRADEMARK = new SpecialChar("Registered Trademark Symbol", "&reg;");
    public static final SpecialChar TRADEMARK = new SpecialChar("Trademark Symbol", "&trade;");
    public static final SpecialChar SPACING_MACRON = new SpecialChar("Spacing Macron", "&macr;");
    public static final SpecialChar DEGREE = new SpecialChar("Degree Symbol", "&deg;");
    public static final SpecialChar PLUS_OR_MINUS = new SpecialChar("Plus/Minus Symbol", "&plusmn;");
    public static final SpecialChar SUPERSCRIPT_2 = new SpecialChar("Superscript 2", "&sup2;");
    public static final SpecialChar SUPERSCRIPT_3 = new SpecialChar("Superscript 3", "&sup3;");
    public static final SpecialChar SPACING_ACUTE = new SpecialChar("Spacing Acute Symbol", "&acute;");
    public static final SpecialChar MICRO = new SpecialChar("Micro Symbol", "&micro;");
    public static final SpecialChar PARAGRAPH = new SpecialChar("Paragraph Symbol", "&para;");
    public static final SpecialChar MIDDLE_DOT = new SpecialChar("Middle Dot Symbol", "&middot;");
    public static final SpecialChar SPACING_CEDILLA = new SpecialChar("Spacing Cedilla", "&cedil;");
    public static final SpecialChar SUPERSCRIPT_1 = new SpecialChar("Superscript 1", "&sup1;");
    public static final SpecialChar MASCULINE_ORDINAL_INDICATOR = new SpecialChar("Masculine Ordinal Indicator", "&ordm;");
    public static final SpecialChar ANGLE_QUOTATION_MARK = new SpecialChar("Angle Quotation Mark (RIGHT)", "&raquo;");
    public static final SpecialChar QUARTER_FRACTION = new SpecialChar("Fraction 1/4", "&frac14;");
    public static final SpecialChar HALF_FRACTION = new SpecialChar("Fraction 1/2", "&frac12;");
    public static final SpecialChar THREE_QUARTER_FRACTION = new SpecialChar("Fraction 3/4", "&frac34;");
    public static final SpecialChar INVERTED_QUESTION_MARK = new SpecialChar("Inverted Question Mark", "&iquest;");
    public static final SpecialChar MULTIPLICATION = new SpecialChar("Multiplication Symbol", "&times;");
    public static final SpecialChar DIVISION = new SpecialChar("Division Symbol", "&divide;");
    public static final SpecialChar GRAVE_CAPITAL_A = new SpecialChar("Capital a, grave accent", "&Agrave;");
    public static final SpecialChar ACUTE_CAPITAL_A = new SpecialChar("Capital a, acute accent", "&Aacute;");
    public static final SpecialChar CIRCUMFLEX_CAPITAL_A = new SpecialChar("Capital a, circumflex accent", "&Acirc;");
    public static final SpecialChar TILDE_CAPITAL_A = new SpecialChar("Capital a, tilde", "&Atilde;");
    public static final SpecialChar UMLAUT_CAPITAL_A = new SpecialChar("Capital a, umlaut mark", "&Auml;");
    public static final SpecialChar RING_CAPITAL_A = new SpecialChar("Capital a, ring", "&Aring;");
    public static final SpecialChar CAPITAL_AE = new SpecialChar("Capital ae", "&AElig;");
    public static final SpecialChar CEDILLA_CAPITAL_C = new SpecialChar("Capital c, cedilla", "&Ccedil;");
    public static final SpecialChar GRACE_CAPITAL_E = new SpecialChar("Capital e, grave accent", "&Egrave;");
    public static final SpecialChar ACUTE_CAPITAL_E = new SpecialChar("Capital e, acute accent", "&Eacute;");
    public static final SpecialChar CIRCUMFLEX_CAPITAL_E = new SpecialChar("Capital e, circumflex accent", "&Ecirc;");
    public static final SpecialChar UNLAUT_CAPITAL_E = new SpecialChar("Capital e, umlaut mark", "&Euml;");
    public static final SpecialChar GRAVE_CAPITAL_I = new SpecialChar("Capital i, grave accent", "&Igrave;");
    public static final SpecialChar ACUTE_CAPITAL_I = new SpecialChar("Capital i, acute accent", "&Iacute;");
    public static final SpecialChar CIRCUMFLEX_CAPITAL_I = new SpecialChar("Capital i, circumflex accent", "&Icirc;");
    public static final SpecialChar UMLAUT_CAPITAL_I = new SpecialChar("Capital i, umlaut mark", "&Iuml;");
    public static final SpecialChar ICELANDIC_CAPITAL_ETH = new SpecialChar("Capital eth, Icelandic", "&ETH;");
    public static final SpecialChar TILDE_CAPITAL_N = new SpecialChar("Capital n, tilde", "&Ntilde;");
    public static final SpecialChar GRAVE_CAPITAL_O = new SpecialChar("Capital o, grave accent", "&Ograve;");
    public static final SpecialChar ACUTE_CAPITAL_O = new SpecialChar("Capital o, acute accent", "&Oacute;");
    public static final SpecialChar CIRCUMFLEX_CAPITAL_O = new SpecialChar("Capital o, circumflex accent", "&Ocirc;");
    public static final SpecialChar TILDE_CAPITAL_O = new SpecialChar("Capital o, tilde", "&Otilde;");
    public static final SpecialChar UMLAUT_CAPITAL_O = new SpecialChar("Capital o, umlaut mark", "&Ouml;");
    public static final SpecialChar SLASH_CAPITAL_O = new SpecialChar("Capital o, slash", "&Oslash;");
    public static final SpecialChar GRACE_CAPITAL_U = new SpecialChar("Capital u, grave accent", "&Ugrave;");
    public static final SpecialChar ACUTE_CAPITAL_U = new SpecialChar("Capital u, acute accent", "&Uacute;");
    public static final SpecialChar CIRCUMFLEX_CAPITAL_U = new SpecialChar("Capital u, circumflex accent", "&Ucirc;");
    public static final SpecialChar UMLAUT_CAPITAL_U = new SpecialChar("Capital u, umlaut mark", "&Uuml;");
    public static final SpecialChar ACUTE_CAPITAL_Y = new SpecialChar("Capital y, acute accent", "&Yacute;");
    public static final SpecialChar ICELANDIC_CAPITAL_THORN = new SpecialChar("Capital THORN, Icelandic", "&THORN;");
    public static final SpecialChar GERMAN_SMALL_S = new SpecialChar("Small sharp s, German", "&szlig;");
    public static final SpecialChar GRAVE_SMALL_A = new SpecialChar("Small a, grave accent", "&agrave;");
    public static final SpecialChar ACUTE_SMALL_A = new SpecialChar("Small a, acute accent", "&aacute;");
    public static final SpecialChar CIRCUMFLEX_SMALL_A = new SpecialChar("Small a, circumflex accent", "&acirc;");
    public static final SpecialChar TILDE_SMALL_A = new SpecialChar("Small a, tilde", "&atilde;");
    public static final SpecialChar UMLAUT_SMALL_A = new SpecialChar("Small a, umlaut mark", "&auml;");
    public static final SpecialChar RING_SMALL_A = new SpecialChar("Small a, ring", "&aring;");
    public static final SpecialChar SMALL_AE = new SpecialChar("Small ae", "&aelig;");
    public static final SpecialChar CEDILLA_SMALL_C = new SpecialChar("Small c, cedilla", "&ccedil;");
    public static final SpecialChar GRAVE_SMALL_E = new SpecialChar("Small e, grave accent", "&egrave;");
    public static final SpecialChar ACUTE_SMALL_E = new SpecialChar("Small e, acute accent", "&eacute;");
    public static final SpecialChar CIRCUMFLEX_SMALL_E = new SpecialChar("Small e, circumflex accent", "&ecirc;");
    public static final SpecialChar UMLAUT_SMALL_E = new SpecialChar("Small e, umlaut mark", "&euml;");
    public static final SpecialChar GRAVE_SMALL_I = new SpecialChar("Small i, grave accent", "&igrave;");
    public static final SpecialChar ACUTE_SMALL_I = new SpecialChar("Small i, acute accent", "&iacute;");
    public static final SpecialChar CIRCUMFLEX_SMALL_I = new SpecialChar("Small i, circumflex accent", "&icirc;");
    public static final SpecialChar UMLAUT_SMALL_I = new SpecialChar("Small i, umlaut mark", "&iuml;");
    public static final SpecialChar ICELANDIC_SMALL_ETH = new SpecialChar("Small eth, Icelandic", "&eth;");
    public static final SpecialChar TILDE_SMALL_N = new SpecialChar("Small n, tilde", "&ntilde;");
    public static final SpecialChar GRAVE_SMALL_O = new SpecialChar("Small o, grave accent", "&ograve;");
    public static final SpecialChar ACUTE_SMALL_O = new SpecialChar("Small o, acute accent", "&oacute;");
    public static final SpecialChar CIRCUMFLEX_SMALL_O = new SpecialChar("Small o, circumflex accent", "&ocirc;");
    public static final SpecialChar TILDE_SMALL_O = new SpecialChar("Small o, tilde", "&otilde;");
    public static final SpecialChar UMLAUT_SMALL_O = new SpecialChar("Small o, umlaut mark", "&ouml;");
    public static final SpecialChar SLASH_SMALL_O = new SpecialChar("Small o, slash", "&oslash;");
    public static final SpecialChar GRAVE_SMALL_U = new SpecialChar("Small u, grave accent", "&ugrave;");
    public static final SpecialChar ACUTE_SMALL_U = new SpecialChar("Small u, acute accent", "&uacute;");
    public static final SpecialChar CIRCUMFLEX_SMALL_U = new SpecialChar("Small u, circumflex accent", "&ucirc;");
    public static final SpecialChar UMLAUT_SMALL_U = new SpecialChar("Small u, umlaut mark", "&uuml;");
    public static final SpecialChar ACUTE_SMALL_Y = new SpecialChar("Small y, acute accent", "&yacute;");
    public static final SpecialChar ICELANDIC_SMALL_THORN = new SpecialChar("Small thorn, Icelandic", "&thorn;");
    public static final SpecialChar UMLAUT_SMALL_Y = new SpecialChar("Small y, umlaut mark", "&yuml;");
    public static final SpecialChar LIGATURE_CAPITAL_OE = new SpecialChar("Capital ligature OE", "&OElig;");
    public static final SpecialChar LIGATURE_SMALL_OE = new SpecialChar("Small ligature oe", "&oelig;");
    public static final SpecialChar CARON_CAPITAL_S = new SpecialChar("Capital S with caron", "&Scaron;");
    public static final SpecialChar CARON_SMALL_S = new SpecialChar("Small S with caron", "&scaron;");
    public static final SpecialChar DIARES_CAPITAL_Y = new SpecialChar("Capital Y with diaeres", "&Yuml;");
    public static final SpecialChar CIRCUMFLEX_LETTER_MODIFER = new SpecialChar("Modifier letter circumflex accent", "&circ;");
    public static final SpecialChar SMALL_TILDE = new SpecialChar("Small tilde", "&tilde;");
    public static final SpecialChar EN_SPACE = new SpecialChar("en space", "&ensp;");
    public static final SpecialChar EM_SPACE = new SpecialChar("em space", "&emsp;");
    public static final SpecialChar THIN_SPACE = new SpecialChar("Thin space", "&thinsp;");
    public static final SpecialChar ZERO_WIDTH_NON_JOINER = new SpecialChar("Zero width non-joiner", "&zwnj;");
    public static final SpecialChar ZERO_WIDTH_JOINER = new SpecialChar("Zero width joiner", "&zwj;");
    public static final SpecialChar LEFT_TO_RIGHT_MARK = new SpecialChar("Left-to-right mark", "&lrm;");
    public static final SpecialChar RIGHT_TO_LEFT_MARK = new SpecialChar("Right-to-left mark", "&rlm;");
    public static final SpecialChar EN_DASH = new SpecialChar("en dash", "&ndash;");
    public static final SpecialChar EM_DASH = new SpecialChar("em dash", "&mdash;");
    public static final SpecialChar LEFT_SINGLE_QUOTE = new SpecialChar("Left single quotation mark", "&lsquo;");
    public static final SpecialChar RIGHT_SINGLE_QUOTE = new SpecialChar("Right single quotation mark", "&rsquo;");
    public static final SpecialChar SINGLE_LOW9_QUOTE = new SpecialChar("Single low-9 quotation mark", "&sbquo;");
    public static final SpecialChar LEFT_DOUBLE_QUOTE = new SpecialChar("Left double quotation mark", "&ldquo;");
    public static final SpecialChar RIGHT_DOUBLE_QUOTE = new SpecialChar("Right double quotation mark", "&rdquo;");
    public static final SpecialChar DOUBLE_LOW9_QUOTE = new SpecialChar("Double low-9 quotation mark", "&bdquo;");
    public static final SpecialChar DAGGER = new SpecialChar("Dagger", "&dagger;");
    public static final SpecialChar DOUBLE_DAGGER = new SpecialChar("Double dagger", "&Dagger;");
    public static final SpecialChar PER_MILLE = new SpecialChar("Per mille", "&permil;");
    public static final SpecialChar SINGLE_LEFT_ANGLE_QUOTE = new SpecialChar("Single left-pointing angle quotation", "&lsaquo;");
    public static final SpecialChar SINLE_RIGHT_ANGLE_QUOTE = new SpecialChar("Single right-pointing angle quotation", "&rsaquo;");

    /** Gets incremented as format types are initialized. */
    private static int _nextId = 0;
    /** To keep track of each type. */
    private static Vector _chars;
    /** The symbol. */
    protected String _symbol;

    /**
     * Creates a new SpecialChar given the description and the symbol.
     *
     * @param String The description.
     * @param String The symbol.
     */
    private SpecialChar(String desc, String symbol)
    {
        super(_nextId++, desc);
        _symbol = symbol;
        if (_chars == null) _chars = new Vector();
        _chars.add(this);
    }

    /**
     * Returns the symbol.
     *
     * @return String
     */
    public String getSymbol()
    {
        return _symbol;
    }

    /**
     * Returns an SpecialChar based on the id passed in. If the id does not match the id of
     * a constant, then we return null. If there are two constants with the same id, then
     * the first one is returned.
     *
     * @param int The constant id.
     * @return SpecialChar
     */
    public static SpecialChar evaluate(int id)
    {
        return (SpecialChar)Constant.evaluate(id, _chars);
    }

    /**
     * Returns an SpecialChar based on the symbol passed in.
     *
     * @param Vector The vector of constants to evaluate from.
     * @param String The description.
     * @return SpecialChar
     */
    public static SpecialChar evaluate(String symbol)
    {
        if (symbol == null) return null;

        int count = _chars.size();
        for (int i=0; i<count; i++)
        {
            SpecialChar ret = (SpecialChar)_chars.elementAt(i);
            if ( symbol.equals(ret.getSymbol()) ) return ret;
        }
        return null;
    }

    public String toString()
    {
        return _symbol;
    }

    public Vector getTypes()
    {
        return _chars;
    }
}