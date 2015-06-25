package com.zitego.markup.html.javascript;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.TextContent;
import com.zitego.markup.html.HtmlMarkupFactory;
import com.zitego.markup.html.HtmlTextContent;
import com.zitego.markup.html.tag.UnknownHtmlTag;
import com.zitego.markup.html.tag.Script;
import com.zitego.markup.html.tag.HtmlCommentTag;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.TextUtils;

/**
 * This class represents a generic javascript statement that exists within a script tag.
 *
 * @author John Glorioso
 * @version $Id: Statement.java,v 1.3 2008/05/12 18:14:36 jglorioso Exp $
 */
public class Statement extends HtmlTextContent
{
    /**
     * Creates a new statement with no parent.
     */
    protected Statement()
    {
        super();
    }

    /**
     * Creates a new javascript statement with a Script parent.
     *
     * @param parent The parent.
     */
    public Statement(Script parent)
    {
        super(parent);
    }

    /**
     * Creates a new javascript statement with a Function parent.
     *
     * @param parent The parent.
     */
    protected Statement(Function parent)
    {
        super(parent);
    }

    /**
     * Creates a new javascript statement with a HtmlCommentTag parent.
     *
     * @param parent The parent.
     */
    protected Statement(HtmlCommentTag parent)
    {
        super(parent);
    }

    /**
     * Creates a new javascript statement with a Script parent and statement text.
     *
     * @param txt The text for the statement.
     * @param parent The parent.
     * @throws IllegalArgumentException if the text is null.
     */
    public Statement(String txt, Script parent) throws IllegalArgumentException
    {
        super(parent, txt);
    }

    /**
     * Creates a new javascript statement with a Function parent and statement text.
     *
     * @param txt The text for the statement.
     * @param parent The parent.
     * @throws IllegalArgumentException if the text is null.
     */
    public Statement(String txt, Function parent) throws IllegalArgumentException
    {
        super(parent, txt);
    }

    /**
     * Creates a new javascript statement with a HtmlCommentTag parent and statement text.
     *
     * @param txt The text for the statement.
     * @param parent The parent.
     * @throws IllegalArgumentException if the text is null.
     */
    public Statement(String txt, HtmlCommentTag parent) throws IllegalArgumentException
    {
        super(parent, txt);
    }

    public void setText(String text)
    {
        clearContent();
        addBodyContent(text);
        super.setText(text);
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;

        boolean preserve = preserveWhiteSpace();
        //Clean leading spaces
        if (!preserve) removeLeadingSpaces(text);

        String txt = TextUtils.getTextUpTo(text, '\n');
        if (txt == null) txt = "";
        if (text.length() > 0)
        {
            if (text.charAt(0) == ';')
            {
                txt += ";";
                text.deleteCharAt(0);
            }
            else if (text.charAt(0) == '\n' && !preserve)
            {
                text.deleteCharAt(0);
            }
            //Make sure this is not passed the end of a script tag
            int index = txt.indexOf("</script>");
            if (index > -1)
            {
                text.insert( 0, txt.substring(index) );
                txt = txt.substring(0, index);
            }
        }
        if (!preserve) txt = txt.trim();
        MarkupContent[] content = HtmlMarkupFactory.getInstance().parse(new StringBuffer(txt), this, type, true, isStrict(), preserve);
        clearContent();
        StringBuffer buf = new StringBuffer();
        MarkupContent last = getLastBodyContent();
        for (int i=0; i<content.length; i++)
        {
            //< and > will be picked up as unknown tags
            if (content[i] instanceof UnknownHtmlTag)
            {
                content[i] = new TextContent( ((UnknownHtmlTag)content[i]).getOriginalText() );
            }
            if (i != content.length-1 && !preserve) content[i].setHasNewline(false);
            if (i > 0 || preserve) content[i].setHasPadding(false);

            if (content[i] instanceof TextContent)
            {
                buf.append( ((TextContent)content[i]).getText() );
            }
            else
            {
                if (buf.length() > 0) addBodyContent( buf.toString() );
                if (!preserve && last != null) last.setHasNewline(false);
                addBodyContent(content[i]);
                if (!preserve && last != null) last.setHasNewline(false);
                buf.setLength(0);
            }
        }
        if (buf.length() > 0) addBodyContent( buf.toString() );
        if (!preserve && last != null) last.setHasNewline(false);
        if (!preserve) removeLeadingSpaces(text);
    }

    public String getText()
    {
        int size = getBodySize();
        StringBuffer buf = new StringBuffer();
        for (int i=0; i<size; i++)
        {
            try
            {
                MarkupContent c = getBodyContent(i);
                buf.append( c.format(FormatType.HTML) );
            }
            catch (UnsupportedFormatException ufe)
            {
                throw new RuntimeException("Error getting text: ", ufe);
            }
        }
        return buf.toString();
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            StringBuffer ret = new StringBuffer();
            int size = getBodySize();
            for (int i=0; i<size; i++)
            {
                MarkupContent c = getBodyContent(i);
                ret.append( c.format(type) );
            }
            return ret.toString();
        }
        else
        {
            return (String)getCachedContent(type);
        }
    }
}