package com.zitego.markup.html.tag;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.Newline;
import com.zitego.markup.tag.TagAttributeValue;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import java.util.Vector;

/**
 * This class represents an html anchor tag. An anchor tag has the following attributes:
 * href, name, and target. This class must have an HtmlMarkupTag as the parent. This class
 * is up to date as of W3C specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Anchor.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 * @see HtmlMarkupTag#createAnchor(String)
 * @see HtmlMarkupTag#createLink(String)
 * @see com.zitego.jsp.LinkTag
 */
public class Anchor extends EventDrivenTag implements BasePath
{
    /** Whether this link is underlined. If false, then the style text-decoration: none; attribute is set. */
    private boolean _underlined = true;
    /** The toolbar tip for this anchor. */
    private String _toolTip;
    /** This is set if the context path is set in the html parent is set and this img href has
      * a base domain that is different. **/
    protected String _basePath;
    private String _endTag;

    public static void main(String[] args) throws Exception
    {
        Anchor a = new Anchor();
        a.parse(new StringBuffer("<a href=\"asd\" style=\"text-decoration: none;\" onmouseover=\"window.status='test';return true\">asd</a>"), FormatType.HTML);
        System.out.println( a.format(FormatType.HTML) );
    }

    /**
     * Creates a new A tag with no parent. This is for a stand alone link to be used
     * with something like the LinkTag.
     */
    public Anchor()
    {
        super("a");
        setHasEndTagOnSameLine(true);
    }

    /**
     * Creates a new A tag with an HtmlMarkupTag parent.
     *
     * @param parent The parent.
     */
    public Anchor(HtmlMarkupTag parent)
    {
        super("a", parent);
        setHasEndTagOnSameLine(true);
    }

    /**
     * Sets the href of the tag.
     *
     * @param href The href.
     */
    public void setHref(String href)
    {
        if (_basePath != null && href.indexOf(_basePath) == 0 && href.length() > _basePath.length()+1) href = href.substring(_basePath.length()+1);
        setAttribute("href", href);
    }

    /**
     * Returns the href of the tag.
     *
     * @return String
     */
    public String getHref()
    {
        return getAttributeValue("href");
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
        String href = getHref();
        if (href == null)
        {
            return null;
        }
        else if (_basePath == null)
        {
            return href;
        }
        else if (href.indexOf("http://") == -1 && href.charAt(0) == '/')
        {
            return _basePath+href;
        }
        else
        {
            return href;
        }
    }

    // -------------------------------- End BasePath Interface ------------------------------ //

    /**
     * Sets the name attribute of the tag.
     *
     * @param name The name attribute.
     */
    public void setNameAttribute(String name)
    {
        setAttribute("name", name);
    }

    /**
     * Returns the name attribute of the tag.
     *
     * @return String
     */
    public String getNameAttribute()
    {
        return getAttributeValue("name");
    }

    /**
     * Sets the target of the tag.
     *
     * @param target The target.
     */
    public void setTarget(String target)
    {
        setAttribute("target", target);
    }

    /**
     * Returns the target of the tag.
     *
     * @return String
     */
    public String getTarget()
    {
        return getAttributeValue("target");
    }

    /**
     * Sets whether this link is underlined.
     *
     * @param underlined The underlined flag.
     */
    public void setUnderlined(boolean underlined)
    {
        _underlined = underlined;
        if (_underlined) removeStyle("text-decoration");
        else setStyle("text-decoration", "none");
    }

    /**
     * Returns whether this link is underlined.
     *
     * @return boolean
     */
    public boolean isUnderlined()
    {
        return _underlined;
    }

    /**
     * Sets the tool tip for the anchor. This creates an onMouseOver and an onMouseOut
     * javascript event that will not interfere with any others already set. If the tool
     * tip is null, then it is removed.
     *
     * @param tip the tool tip.
     */
    public void setToolTip(String tip)
    {
        _toolTip = tip;

        JsEventHandler over = getJsEventHandler("onMouseOver");
        StringBuffer overString = new StringBuffer();
        if (over != null)
        {
            Vector funcs = over.getJsFunctions();
            int size = funcs.size();
            int count = 0;
            for (int i=0; i<size; i++)
            {
                TagAttributeValue v = (TagAttributeValue)funcs.get(i);
                String func = v.toString();
                if (func.indexOf("window.status=") == 0)
                {
                    //Remove the next one too cause it is return true
                    i++;
                    func = (String)funcs.get(i);
                    if (func.indexOf("return true") != 0) i--;
                }
                else
                {
                    overString.append( (count++ > 0 ? ";" : "") ).append(func);
                }
            }
            removeJsEventHandler("onMouseOver");
        }

        JsEventHandler out = getJsEventHandler("onMouseOut");
        StringBuffer outString = new StringBuffer();
        if (out != null)
        {
            Vector funcs = out.getJsFunctions();
            int size = funcs.size();
            int count = 0;
            for (int i=0; i<size; i++)
            {
                TagAttributeValue v = (TagAttributeValue)funcs.get(i);
                String func = v.toString();
                if (func.indexOf("window.status=") == 0)
                {
                    //Remove the next one too cause it is return true
                    i++;
                    func = (String)funcs.get(i);
                    if (func.indexOf("return true") != 0) i--;
                }
                else
                {
                    outString.append( (count++ > 0 ? ";" : "") ).append(func);
                }
            }
            removeJsEventHandler("onMouseOut");
        }

        if (_toolTip != null)
        {
            overString.append( (overString.length() > 0 ? "" : ";") ).append("window.status='").append(_toolTip).append("';return true");
            outString.append( (outString.length() > 0 ? "" : ";") ).append("window.status='';return true");
        }
        if (overString.length() > 0) setOnMouseOver( overString.toString() );
        if (outString.length() > 0) setOnMouseOut( outString.toString() );
    }

    /**
     * Returns the tool tip.
     *
     * @return String
     */
    public String getToolTip()
    {
        return _toolTip;
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        super.parseText(text, type);
        //Set tool tip and underline
        String val = getOnMouseOver();
        if (val != null)
        {
            int index = val.indexOf("'");
            if (index > -1) _toolTip = val.substring( index+1, val.indexOf("'", index+1) );
        }
        val = getStyle();
        if (val != null)
        {
            val = val.toLowerCase();
            if (val.indexOf("text-decoration") > -1 && val.indexOf("none") > -1) _underlined = false;
            else _underlined = true;
        }
    }

    /*public String getStartTag(FormatType type) throws UnsupportedFormatException
    {
        setIsOnOwnLine(true);
        return super.getStartTag(type);
    }*/

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