package com.zitego.markup.html.tag;

import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.markup.*;
import com.zitego.markup.tag.CommentTag;
import com.zitego.markup.tag.MarkupTag;
import com.zitego.markup.html.*;
import com.zitego.markup.html.tag.textEffect.*;
import com.zitego.markup.html.tag.list.*;
import com.zitego.markup.html.tag.block.*;
import com.zitego.markup.html.tag.table.Table;

/**
 * This is the root class of all html markup tags. This tag is different
 * then the Html class as that class is the embodiment of the actual "html"
 * tag and this class is a parent html class to all html tags.
 *
 * @author John Glorioso
 * @version $Id: HtmlMarkupTag.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class HtmlMarkupTag extends MarkupTag
{
    /** The style declaration for this tag. */
    private StyleDeclaration _styleDeclaration;

    /**
     * Creates a new html markup tag.
     *
     * @param tagName The tag name.
     */
    protected HtmlMarkupTag(String tagName)
    {
        super(tagName);
    }

    /**
     * Creates a new html markup tag.
     *
     * @param map The map to use.
     * @param tagName The tag name.
     */
    protected HtmlMarkupTag(HtmlMarkupMap map, String tagName)
    {
        super(tagName, map);
    }

    /**
     * Creates a new html markup tag with an HtmlMarkup tag parent.
     *
     * @param parent The parent tag.
     */
    protected HtmlMarkupTag(HtmlMarkupTag parent)
    {
        super(parent);
    }

    /**
     * Creates a new html markup tag with a tag name and an HtmlMarkup tag parent.
     *
     * @param tagName The tag name.
     * @param parent The parent tag.
     */
    protected HtmlMarkupTag(String tagName, HtmlMarkupTag parent)
    {
        super(tagName, parent);
    }

    /**
     * Creates a new html markup tag with a tag name and an HtmlMarkup tag parent.
     *
     * @param tagName The tag name.
     * @param parent The parent content.
     */
    protected HtmlMarkupTag(String tagName, MarkupContent parent)
    {
        super(tagName, parent);
    }

    /**
     * Sets the style(s) of the tag given the full style string. If the string
     * is null, then all styles are removed. Otherwise, the styles are set as the
     * absolute. Any other preset styles are removed.
     *
     * @param style The style string.
     */
    public void setStyle(String style)
    {
        if (style == null)
        {
            _styleDeclaration = null;
        }
        else
        {
            _styleDeclaration = new StyleDeclaration();
            _styleDeclaration.setPreserveWhiteSpace( preserveWhiteSpace() );
            try
            {
                _styleDeclaration.parse(style, FormatType.HTML);
            }
            catch (Exception e)
            {
                throw new IllegalArgumentException( "Could not set style: "+style+". "+e.toString() );
            }
            if (_styleDeclaration.getStyleDeclarationElements().size() == 0) _styleDeclaration = null;
        }
        try
        {
            if (_styleDeclaration != null) setAttribute( "style", _styleDeclaration.format(FormatType.HTML) );
            else removeAttribute("style");
        }
        catch (UnsupportedFormatException ufe)
        {
            throw new IllegalArgumentException( "Could not set style: "+style+". "+ufe.toString() );
        }
    }

    /**
     * Sets the style of the tag. This internally uses a StyleDeclaration element.
     * It will set the given style with the given value. If the style already exists,
     * then it will replace it. If the value is null, then it will remove it. If there
     * are no values left or the style passed in is null, then the entire style tag
     * is reset.
     *
     * @param style The style.
     * @param value The value.
     */
    public void setStyle(String style, String value)
    {
        if (style == null)
        {
            _styleDeclaration = null;
        }
        else
        {
            if (_styleDeclaration == null)
            {
                _styleDeclaration = new StyleDeclaration();
                _styleDeclaration.setPreserveWhiteSpace( preserveWhiteSpace() );
            }
            _styleDeclaration.setProperty(style, value);
            if (_styleDeclaration.getStyleDeclarationElements().size() == 0) _styleDeclaration = null;
        }
        try
        {
            if (_styleDeclaration != null) setAttribute( "style", _styleDeclaration.format(FormatType.HTML) );
            else removeAttribute("style");
        }
        catch (UnsupportedFormatException ufe)
        {
            throw new IllegalArgumentException( "Could not set style: "+style+". "+ufe.toString() );
        }
    }

    /**
     * Removes the entire style attribute.
     */
    public void removeStyle()
    {
        setStyle(null, null);
    }

    /**
     * Removes the specified style from the attributes.
     */
    public void removeStyle(String style)
    {
        setStyle(style, null);
    }

    /**
     * Returns the style of the tag.
     *
     * @return String
     */
    public String getStyle()
    {
        return getAttributeValue("style");
    }

    /**
     * Sets the class of the tag.
     *
     * @param c The class.
     */
    public void setClassAttribute(String c)
    {
        setAttribute("class", c);
    }

    /**
     * Returns the class of the tag.
     *
     * @return String
     */
    public String getClassAttribute()
    {
        return getAttributeValue("class");
    }

    /**
     * Adds an html line break to be printed out after the end tag.
     *
     * @throws IllegalArgumentException if the parent is null and this is strict.
     */
    public void addLineBreak() throws IllegalArgumentException
    {
        HtmlMarkupTag parent = (HtmlMarkupTag)getParent();
        if (isStrict() && parent == null)
        {
            throw new IllegalArgumentException("line breaks cannot be added to a root html tag.");
        }
        //We use the parent cause the breaks are after the text and part of
        //the parent
        addLineBreak( new BR(parent) );
    }

    /**
     * Creates and returns an html line break with this as the parent.
     *
     * @return BR
     */
    public BR createLineBreak()
    {
        BR br = new BR(this);
        addLineBreak(br);
        return br;
    }

    public TextContent createTextContent(String txt)
    {
        return new HtmlTextContent(this, txt);
    }

    /**
     * Creates a new horizontal rule (HR) tag.
     *
     * @return HR
     */
    public HR createHR()
    {
        return new HR(this);
    }

    /**
     * Creates a new table with this tag as the parent.
     *
     * @return Table
     */
    public Table createTable()
    {
        return Table.createTable(this);
    }

    /**
     * Creates a new image tag with the provided src and this tag as the parent.
     *
     * @param src The src.
     * @return Img
     */
    public Img createImage(String src)
    {
        return new Img(this, src);
    }

    /**
     * Creates a new text effect tag with this as the parent
     * given the type.
     *
     * @param type The type of text effect.
     * @return TextEffect
     */
    public TextEffect createTextEffect(TextEffectType type)
    {
        return TextEffectFactory.getTextEffect(type, this);
    }

    /**
     * Creates a list given the type.
     *
     * @param type The type of list.
     * @return List
     */
    public List createList(ListType type)
    {
        return ListFactory.getList(type, this);
    }

    /**
     * Creates a block format tag given the type.
     *
     * @param type The type of block format.
     * @return BlockFormat
     */
    public BlockFormat createBlockFormat(BlockFormatType type)
    {
        return BlockFormatFactory.getBlockFormat(type, this);
    }

    /**
     * Creates a new anchor tag given the anchor name. This is not a link. The tag
     * will end up looking like <a name="<name>">.
     *
     * @param name The name.
     * @return Anchor
     */
    public Anchor createAnchor(String name)
    {
        Anchor a = new Anchor(this);
        a.setNameAttribute(name);
        return a;
    }

    /**
     * Creates a new anchor tag given the href.
     *
     * @param href The href.
     * @return Anchor
     */
    public Anchor createLink(String href)
    {
        Anchor a = new Anchor(this);
        a.setHref(href);
        return a;
    }

    /**
     * Creates a header with the given size.
     *
     * @param size The size of the header.
     */
    public Header createHeaderTag(int size)
    {
        return new Header(this, size);
    }

    /**
     * A convenience method for getting the head tag out of the HtmlMarkupMap. This is the same
     * as calling ( (HtmlMarkupMap)this.getMap() ).getHeadTag().
     *
     * @return Head
     */
    public Head getHeadTag()
    {
        return ( (HtmlMarkupMap)getMap() ).getHeadTag();
    }

    /**
     * A convenience method for getting the body tag out of the HtmlMarkupMap. This is the same
     * as calling ( (HtmlMarkupMap)this.getMap() ).getBodyTag().
     *
     * @return Body
     */
    public Body getBodyTag()
    {
        return ( (HtmlMarkupMap)getMap() ).getBodyTag();
    }

    /**
     * A convenience method for getting the html tag out of the HtmlMarkupMap. This is the same
     * as calling ( (HtmlMarkupMap)this.getMap() ).getHtmlTag().
     *
     * @return Html
     */
    public Html getHtmlTag()
    {
        return ( (HtmlMarkupMap)getMap() ).getHtmlTag();
    }

    protected MarkupBody createMarkupBody()
    {
        return new HtmlMarkupBody(this);
    }

    /**
     * Adds an html comment to the body and returns the comment tag.
     *
     * @param comment The comment.
     * @return HtmlCommentTag
     */
    public CommentTag addComment(String comment)
    {
        return new HtmlCommentTag(this, comment);
    }

    protected void setMap(MarkupMap map)
    {
        if (map != null)
        {
            if ( !(map instanceof HtmlMarkupMap) ) map = new HtmlMarkupMap(map);
            if (getMap() != null) ( (HtmlMarkupMap)map ).setBasePath( ((HtmlMarkupMap)getMap()).getBasePath() );
        }
        super.setMap(map);
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);
        String style = getStyle();
        if (style != null)
        {
            _styleDeclaration = new StyleDeclaration();
            _styleDeclaration.setPreserveWhiteSpace( preserveWhiteSpace() );
            _styleDeclaration.parseText(new StringBuffer(style), type);
            setAttribute( "style", _styleDeclaration.format(FormatType.HTML) );
        }
    }
}