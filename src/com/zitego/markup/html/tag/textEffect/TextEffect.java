package com.zitego.markup.html.tag.textEffect;

import com.zitego.markup.html.HtmlTextContent;
import com.zitego.markup.html.tag.EventDrivenTag;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;

/**
 * This is a base class for all font style element tags. These tags are wrappers
 * around text to apply a certain affect to them such as bold or underline. The
 * child class must implement <code>TextEffectType getType()</code>.
 *
 * @author John Glorioso
 * @version $Id: TextEffect.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see TextEffectFactory#getTextEffect(TextEffectType, HtmlMarkupTag)
 */
public abstract class TextEffect extends EventDrivenTag
{
    /**
     * Creates a new TextEffect tag with a tag name and no parent.
     *
     * @param tagName The tag name.
     */
    protected TextEffect(String tagName)
    {
        super(tagName);
        if ( !preserveWhiteSpace() ) setIsOnOwnLine(true);
    }

    /**
     * Creates a new TextEffect tag with an HtmlMarkupTag parent and
     * a tag name.
     *
     * @param tagName The tag name.
     * @param parent The parent.
     */
    protected TextEffect(String tagName, HtmlMarkupTag parent)
    {
        super(tagName, parent);
        if ( !preserveWhiteSpace() ) setIsOnOwnLine(true);
    }

    /**
     * Adds text to the MarkupBody and returns it as an HtmlTextContent object. If the
     * last MarkupBody element was text content, then it is appended to that. Otherwise,
     * it will create a new HtmlTextContent and add that.
     *
     * @@param txt The text to add.
     * @@return HtmlTextContent
     * @@throws IllegalArgumentException if the content is null.
     */
    public HtmlTextContent addText(String txt) throws IllegalArgumentException
    {
        return (HtmlTextContent)addBodyContent(txt);
    }

    /**
     * Returns the type of the tag.
     *
     * @return TextEffectType
     */
    public abstract TextEffectType getType();
}