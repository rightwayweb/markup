package com.zitego.markup.html.tag;

import java.util.Hashtable;
import java.util.Vector;
import com.zitego.markup.*;
import com.zitego.markup.html.*;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.*;
import com.zitego.format.*;

/**
 * This class represents an html head tag. A head tag cannot must have
 * a parent tag and that parent tag must be an HTML tag. A head tag has no
 * attributes. This class is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Head.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Head extends HtmlMarkupTag
{
    /** The style tag. */
    private Style _style;
    /** The title. */
    private Title _title;
    /** To keep track of the javascript source files. */
    private Hashtable _jsSourceFiles;
    /** The header's meta tags. */
    private Vector _metaTags;
    /** The list of search engine keywords. */
    protected String _keywords;
    /** The search engine description. */
    protected String _description;
    /** The body text color. */
    protected String _textColor;
    /** The body text font style. */
    protected String _fontStyle;
    /** The body text font size. */
    protected String _fontSize;
    /** The base path for all pages in this document. */
    protected Base _basePath;

    /**
     * Creates a new Head tag with no parent.
     */
    public Head()
    {
        super("head");
        _jsSourceFiles = new Hashtable();
        _metaTags = new Vector();
    }

    /**
     * Creates a new Head tag with an HTML tag parent. This automatically adds the head
     * tag to the parent.
     *
     * @param parent The parent html tag.
     */
    public Head(Html parent)
    {
        super("head", parent);
        _jsSourceFiles = new Hashtable();
        _metaTags = new Vector();
        //Force at top if formatting
        if ( preserveWhiteSpace() ) parent.addBodyContent(this);
        else parent.addBodyContentAtBeginning(this);
    }

    /**
     * Sets the text of the title.
     *
     * @param title The title text.
     */
    public void setTitle(String text)
    {
        if ( text != null && !"".equals(text) )
        {
            if (_title == null)
            {
                _title = new Title(this, text);
                moveBodyContentTo(0, _title);
            }
            else
            {
                _title.setText(text);
            }
        }
        else
        {
            if (_title != null) removeBodyContent(_title);
            _title = null;
        }
    }

    /**
     * Returns the title.
     *
     * @return String
     */
    public String getTitle()
    {
        return (_title != null ? _title.getText() : null);
    }

    /**
     * Creates a new Style tag to insert in the head. If a Style tag already exists, then
     * we return that.
     *
     * @return Style
     */
    public Style getStyleTag()
    {
        if (_style == null) _style = new Style(this);
        return _style;
    }

    /**
     * Adds the given style sheet to the head in the style tag.
     *
     * @param sheet The style sheet.
     */
    public void addStyleSheet(CascadingStyleSheet sheet)
    {
        getStyleTag().setStyles(sheet);
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
     * Sets the list of search engine keywords.
     *
     * @param list The list.
     */
    public void setKeywords(String list)
    {
        _keywords = list;
        if ( list != null && !"".equals(list) )
        {
            Meta meta = getMetaTagByName("keywords");
            if (meta == null) meta = createMetaTag();
            meta.setName("keywords");
            meta.setContent(list);
        }
        else
        {
            removeMetaTag("keywords");
            _keywords = null;
        }
    }

    /**
     * Returns the list of search engine keywords.
     *
     * @return String
     */
    public String getKeywords()
    {
        return _keywords;
    }

    /**
     * Sets the search engine description.
     *
     * @param desc The description.
     */
    public void setDescription(String desc)
    {
        _description = desc;
        if ( desc != null && !"".equals(desc) )
        {
            Meta meta = getMetaTagByName("description");
            if (meta == null) meta = createMetaTag();
            meta.setName("description");
            meta.setContent(desc);
        }
        else
        {
            removeMetaTag("description");
            _description = null;
        }
    }

    /**
     * Returns the search engine description.
     *
     * @return String
     */
    public String getDescription()
    {
        return _description;
    }

    /**
     * Sets the body text color.
     *
     * @param col The text color.
     */
    public void setTextColor(String col)
    {
        _textColor = col;
        Style styleTag = getStyleTag();
        if ( col != null && !"".equals(col) )
        {
            StyleDeclaration style = styleTag.getStyleDeclaration("body");
            if (style == null) style = styleTag.createStyleDeclaration("body");
            style.setProperty("color", col);
            style = styleTag.getStyleDeclaration("td");
            if (style == null) style = styleTag.createStyleDeclaration("td");
            style.setProperty("color", col);
        }
        else
        {
            StyleDeclaration style = styleTag.getStyleDeclaration("body");
            if (style != null) style.removeProperty("color");
            style = styleTag.getStyleDeclaration("td");
            if (style != null) style.removeProperty("color");
        }
    }

    /**
     * Returns the text color.
     *
     * @return String
     */
    public String getTextColor()
    {
        return _textColor;
    }

    /**
     * Sets the body text font style.
     *
     * @param s The font style.
     */
    public void setFontStyle(String s)
    {
        _fontStyle = s;
        Style styleTag = getStyleTag();
        if ( s != null && !"".equals(s) )
        {
            StyleDeclaration style = styleTag.getStyleDeclaration("body");
            if (style == null) style = styleTag.createStyleDeclaration("body");
            style.setProperty("font-family", s);
            style = styleTag.getStyleDeclaration("td");
            if (style == null) style = styleTag.createStyleDeclaration("td");
            style.setProperty("font-family", s);
        }
        else
        {
            StyleDeclaration style = styleTag.getStyleDeclaration("body");
            if (style != null) style.removeProperty("font");
            style = styleTag.getStyleDeclaration("td");
            if (style != null) style.removeProperty("font");
        }
    }

    /**
     * Returns the font style.
     *
     * @return String
     */
    public String getFontStyle()
    {
        return _fontStyle;
    }

    /**
     * Sets the body text font size.
     *
     * @param size The size.
     */
    public void setFontSize(String size)
    {
        _fontSize = size;
        Style styleTag = getStyleTag();
        if ( size != null && !"".equals(size) )
        {
            StyleDeclaration style = styleTag.getStyleDeclaration("body");
            if (style == null) style = styleTag.createStyleDeclaration("body");
            style.setProperty("font-size", size);
            style = styleTag.getStyleDeclaration("td");
            if (style == null) style = styleTag.createStyleDeclaration("td");
            style.setProperty("font-size", size);
        }
        else
        {
            StyleDeclaration style = styleTag.getStyleDeclaration("body");
            if (style != null) style.removeProperty("font-size");
            style = styleTag.getStyleDeclaration("td");
            if (style != null) style.removeProperty("font-size");
        }
    }

    /**
     * Returns the body text size.
     *
     * @return String
     */
    public String getFontSize()
    {
        return _fontSize;
    }

    /**
     * Sets the base path of this head tag.
     *
     * @param path The path.
     */
    public void setBasePath(String path)
    {
        if (_basePath == null)
        {
            _basePath = new Base(this, path);
            moveBodyContentTo(0, _basePath);
        }
        else
        {
            _basePath.setHref(path);
        }
        ( (HtmlMarkupMap)getMap() ).setBasePath(path);
    }

    /**
     * Returns the base path (Base tag href) of this html document.
     *
     * @return String
     */
    public String getBasePath()
    {
        return (_basePath != null ? _basePath.getHref() : null);
    }

    /**
     * Creates a new meta tag, adds it to this header, and returns it.
     *
     * @return Meta
     */
    public Meta createMetaTag()
    {
        Meta tag = new Meta(this);
        if (_title != null) moveBodyContentToAfter(_title, tag);
        else moveBodyContentTo(0, tag);
        _metaTags.add(tag);
        return tag;
    }

    /**
     * Returns a meta tag by name. If the tag does not exist, it returns null.
     *
     * @param name The name attribute of the meta tag.
     * @return Meta
     */
    public Meta getMetaTagByName(String name)
    {
        if (name == null) return null;

        int size = _metaTags.size();
        for (int i=0; i<size; i++)
        {
            Meta tag = (Meta)_metaTags.get(i);
            if ( name.equals(tag.getName()) )
            {
                return tag;
            }
        }
        return null;
    }

    /**
     * Removes the meta tag given the name.
     *
     * @param name the name.
     */
    public void removeMetaTag(String name)
    {
        removeMetaTag( getMetaTagByName(name) );
    }

    /**
     * Removes the given meta tag.
     *
     * @param tag The tag to remove.
     */
    public void removeMetaTag(Meta tag)
    {
        if (tag != null)
        {
            _metaTags.remove(tag);
            removeBodyContent(tag);
        }
    }

    /**
     * Registers the javascript source code file to be displayed by the header and returns the
     * Script tag.
     *
     * @param src The src url for the javascript source file.
     * @return Script
     */
    public Script registerJsSourceFile(String src)
    {
        if (src == null) return null;

        if (_jsSourceFiles.get(src) == null)
        {
            Script s = new Script(this);
            s.setSrc(src);
            s.setLanguage("Javascript");
            s.setIsOnOwnLine(true);
            _jsSourceFiles.put(src, s);
            setChanged();
        }
        return (Script)_jsSourceFiles.get(src);
    }

    protected boolean addToParentOnInit()
    {
        return false;
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);

        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            if (c instanceof Style) _style = (Style)c;
            else if (c instanceof Title) _title = (Title)c;
            else if (c instanceof Script)
            {
                if (_jsSourceFiles == null) _jsSourceFiles = new Hashtable();
                String src = ( (Script)c ).getSrc();
                if (src != null) _jsSourceFiles.put(src, c);
            }
            else if (c instanceof Meta)
            {
                if (_metaTags == null) _metaTags = new Vector();
                _metaTags.add(c);
            }
        }
    }
}