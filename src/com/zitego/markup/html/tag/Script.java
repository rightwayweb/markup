package com.zitego.markup.html.tag;

import com.zitego.markup.TextContent;
import com.zitego.markup.MarkupContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.Newline;
import com.zitego.markup.MarkupBody;
import com.zitego.markup.html.javascript.Statement;
import com.zitego.markup.html.javascript.Function;
import com.zitego.markup.html.javascript.JavascriptBody;
import com.zitego.markup.html.tag.table.Table;
import com.zitego.markup.html.tag.textEffect.TextEffectType;
import com.zitego.markup.html.tag.textEffect.TextEffect;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class represents an html script tag. A script tag must have a parent tag and
 * that parent tag must be either an HtmlMarkupTag or a Head tag. A script tag has several
 * attributes. These attributes are type, charset, defer, language, and src. This class is
 * up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Script.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Script extends HtmlMarkupTag implements BasePath
{
    /** A hashtable of the allowable type options. */
    protected static Hashtable _allowableTypes = new Hashtable();
    static
    {
        _allowableTypes.put("text/ecmascript", "1");
        _allowableTypes.put("text/javascript", "1");
        _allowableTypes.put("text/jscript", "1");
        _allowableTypes.put("text/vbscript", "1");
        _allowableTypes.put("text/vbs", "1");
        _allowableTypes.put("text/xml", "1");
    }
    /** A hashtable of the allowable language options. */
    protected static Hashtable _allowableLanguages = new Hashtable();
    static
    {
        _allowableLanguages.put("javascript", "1");
        _allowableLanguages.put("livescript", "1");
        _allowableLanguages.put("vbscript", "1");
    }
    /** The statements in this script tag. */
    protected Vector _statements = new Vector();
    /** This is set if the context path is set in the html parent is set and this img href has
      * a base domain that is different. **/
    protected String _basePath;

    /**
     * Creates a new Script tag with no parent.
     */
    public Script()
    {
        super("script");
    }

    /**
     * Creates a new Script tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent HtmlMarkupTag.
     */
    public Script(HtmlMarkupTag parent)
    {
        super("script", parent);
    }

    /**
     * Sets the mime type of script.
     *
     * @param type The type.
     * @throws IllegalArgumentException if the type is invalid and this is strict.
     */
    public void setType(String type) throws IllegalArgumentException
    {
        if (type != null)
        {
            type = type.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableTypes.get(type) == null)
                {
                    throw new IllegalArgumentException("type must be text/ecmascript, text/javascript, " +
                                                       "text/jscript, text/vbscript, text/vbs, text/xml. " +
                                                       type + " is invalid.");
                }
            }
        }
        super.setAttribute("type", type);
    }

    /**
     * Returns the mime type of script.
     *
     * @return String
     */
    public String getType()
    {
        return getAttributeValue("type");
    }

    /**
     * Sets the language of the script.
     *
     * @param language The language.
     * @throws IllegalArgumentException if the language is invalid and this is strict.
     */
    public void setLanguage(String language) throws IllegalArgumentException
    {
        if (language != null)
        {
            language = language.toLowerCase();
            if ( isStrict() )
            {
                if (_allowableLanguages.get(language) == null)
                {
                    throw new IllegalArgumentException("language must be javascript, livescript, or vbscript. " +
                                                       language + " is invalid.");
                }
            }
        }
        super.setAttribute("language", language);
    }

    /**
     * Returns the language of script.
     *
     * @return String
     */
    public String getLanguage()
    {
        return getAttributeValue("language");
    }

    /**
     * Sets the charset of the tag.
     *
     * @param charset The charset.
     */
    public void setCharset(String charset)
    {
        setAttribute("charset", charset);
    }

    /**
     * Returns the charset of the tag.
     *
     * @return String
     */
    public String getCharset()
    {
        return getAttributeValue("charset");
    }

    /**
     * Sets whether the browser should defer this script and continue loading.
     *
     * @param defer Whether to defer.
     */
    public void setDefer(boolean defer)
    {
        if (defer) super.setAttribute("defer");
        else removeAttribute("defer");
    }

    /**
     * Returns whether or not the browser should defer.
     *
     * @return boolean
     */
    public boolean getDefer()
    {
        return (getAttribute("defer") != null);
    }

    /**
     * Sets the src url of the image.
     *
     * @param src The src url.
     */
    public void setSrc(String src)
    {
        if (_basePath != null && src.indexOf(_basePath) == 0) src = src.substring(_basePath.length()+1);
        setAttribute("src", src);
    }

    /**
     * Returns the src url of the image.
     *
     * @return String
     */
    public String getSrc()
    {
        return getAttributeValue("src");
    }

    /**
     * Creates a new JsFunction with the given name. If the
     * name is null, then null is returned.
     *
     * @param name The function name.
     */
    public Function createFunction(String name)
    {
        if (name == null) return null;
        Function func = new Function(name, this);
        addFunction(func);
        return func;
    }

    /**
     * Adds a new javascript Function to this script tag if it is
     * not null. If it is already in there, then nothing happens.
     *
     * @param func The function to add.
     */
    public void addFunction(Function func)
    {
        if ( func != null && !_statements.contains(func) )
        {
            func.setParent(this);
        }
    }

    /**
     * Creates a new javascript statement with the given text. If
     * the text is null then null is returned.
     *
     * @param txt The function name.
     */
    public Statement createStatement(String txt)
    {
        if (txt == null) return null;
        Statement stmt = new Statement(txt, this);
        addStatement(stmt);
        return stmt;
    }

    /**
     * Adds a new javascript statement to this script tag.
     *
     * @param stmt The javascript statement to add.
     */
    public void addStatement(Statement stmt)
    {
        if ( stmt != null && !_statements.contains(stmt) )
        {
            stmt.setParent(this);
        }
    }

    /**
     * Returns the javascript statements in this script tag.
     *
     * @return Vector
     */
    public Vector getStatements()
    {
        return _statements;
    }

    // -------------------------------- Start BasePath Interface ------------------------------ //

    public void setBasePath(String base)
    {
        _basePath = base;
    }

    public String getBasePath()
    {
        return _basePath;
    }

    public String getFullPath()
    {
        String src = getSrc();
        if (src == null)
        {
            return null;
        }
        else if (_basePath == null)
        {
            return src;
        }
        else if (src.indexOf("http://") == -1 && src.charAt(0) == '/')
        {
            return _basePath+src;
        }
        else
        {
            return src;
        }
    }

    // -------------------------------- End BasePath Interface ------------------------------ //

    /**
     * Overrides setAttribute to make sure that type and language are valid
     * as well as making sure that defer remains boolean.
     *
     * @param name The name.
     * @param val The value.
     * @throws IllegalArgumentException if the type or language are not valid.
     */
    public void setAttribute(String name, String val) throws IllegalArgumentException
    {
        if (name != null) name = name.toLowerCase();
        if ( "type".equals(name) )
        {
            setType(val);
        }
        else if ( "language".equals(name) )
        {
            setLanguage(val);
        }
        else if ( "defer".equals(name) )
        {
            super.setAttribute(name);
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
        if ( !txt.equals(Newline.CHARACTER) && isStrict() ) throw new IllegalMarkupException(getClass() + " cannot create text content.");
        else return super.createTextContent(txt);
    }

    /**
     * This cannot create a table, so it throws an IllegalMarkupException if it is strict.
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
     * @param src The source of the image.
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

    protected MarkupBody createMarkupBody()
    {
        return new JavascriptBody(this);
    }

    public MarkupContent addBodyContentAt(int index, MarkupContent content) throws IllegalArgumentException
    {
        if (content instanceof Statement) _statements.add(index, content);
        return super.addBodyContentAt(index, content);
    }
}