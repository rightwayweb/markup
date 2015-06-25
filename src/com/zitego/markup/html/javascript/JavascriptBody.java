package com.zitego.markup.html.javascript;

import com.zitego.markup.html.HtmlMarkupFactory;
import com.zitego.markup.html.tag.Script;
import com.zitego.markup.html.tag.HtmlCommentTag;
import com.zitego.markup.html.javascript.Function;
import com.zitego.markup.html.javascript.Statement;
import com.zitego.markup.MarkupContent;
import com.zitego.markup.TextContent;
import com.zitego.markup.MarkupBody;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;

/**
 * This class holds multiple Statement objects that makeup the
 * body of a parent Script tag. Content can be added by calling
 * the <code>addContent(MarkupContent)</code> method.
 *
 * @author John Glorioso
 * @version $Id: JavascriptBody.java,v 1.4 2008/05/18 19:19:35 jglorioso Exp $
 */
public class JavascriptBody extends MarkupBody
{
    /**
     * Creates a new JavascriptBody.
     *
     * @param script The parent.
     * @throws
     */
    public JavascriptBody(Script script)
    {
        super(script);
    }

    /**
     * Creates a new JavascriptBody.
     *
     * @param func The parent.
     * @throws
     */
    public JavascriptBody(Function func)
    {
        super(func);
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;

        parseJs(text, getParent(), type);
    }

    /**
     * Parses javascript content from the given text and parent.
     *
     * @param text The text.
     * @param parent The parent.
     * @param type The format type (ignored).
     * @throws IllegalMarkupException
     * @throws UnsupportedFormatException
     */
    public static void parseJs(StringBuffer text, MarkupContent parent, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        StringBuffer preSpace = MarkupContent.removeLeadingSpaces(text);
        if ( preSpace.length() > 0 && parent != null && parent.preserveWhiteSpace() )
        {
            Statement txt = null;
            if (parent instanceof Function) txt = new Statement( (Function)parent );
            else if (parent instanceof Script) txt = new Statement( (Script)parent );
            else if (parent instanceof HtmlCommentTag) txt = new Statement( (HtmlCommentTag)parent );
            else txt = new Statement();
            txt.setPreserveWhiteSpace(true);
            txt.addBodyContent( preSpace.toString() );
        }
        //See if this is the end of a script tag
        if ( text.length() > 8 && text.substring(0, 9).equalsIgnoreCase("</script>") ) return;

        //See if this is a function. If not, it is a Statement
        if ( text.length() > 7 && text.substring(0, 8).equalsIgnoreCase("function") )
        {
            Function f = null;
            if (parent instanceof Script) f = new Function( (Script)parent );
            else if (parent instanceof HtmlCommentTag) f = new Function( (HtmlCommentTag)parent );
            else throw new IllegalMarkupException("Invalid parent: "+(parent != null ? parent.getClass() : null));
            f.parse(text, type);
        }
        else
        {
            Statement s = null;
            //Check for tags here. This is generally unexpected, however overriding
            //special tags may be present in Javascript code
            if (text.length() > 0 && text.charAt(0) == '<')
            {
                Statement newP = null;
                if (parent instanceof Function) newP = new Statement( (Function)parent );
                else if (parent instanceof HtmlCommentTag) newP = new Statement( (HtmlCommentTag)parent );
                else newP = new Statement( (Script)parent );
                HtmlMarkupFactory.getInstance().parse( text, newP, type, false, newP.isStrict(), newP.preserveWhiteSpace() );
            }
            else
            {
                if (parent instanceof Function) s = new Statement( (Function)parent );
                else if (parent instanceof HtmlCommentTag) s = new Statement( (HtmlCommentTag)parent );
                else s = new Statement( (Script)parent );
                s.parse(text, type);
            }
        }

        if (text.length() > 0) parseJs(text, parent, type);
    }
}