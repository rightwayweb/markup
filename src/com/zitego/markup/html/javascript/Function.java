package com.zitego.markup.html.javascript;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.MarkupBody;
import com.zitego.markup.Newline;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.html.tag.Script;
import com.zitego.markup.html.tag.HtmlCommentTag;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.TextUtils;
import java.util.Vector;

/**
 * This class represents a javascript function that exists within a script tag.
 *
 * @author John Glorioso
 * @version $Id: Function.java,v 1.2 2008/05/12 18:15:05 jglorioso Exp $
 */
public class Function extends Statement
{
    /** The name of the function. */
    protected String _name;
    /** The arguments for the function. */
    protected Vector _arguments = new Vector();
    /** The statements of this function. */
    protected Vector _statements = new Vector();
    private StringBuffer _spaceAfterArgs = new StringBuffer();

    /**
     * Creates a new javascript function with a parent.
     *
     * @param parent The parent.
     */
    public Function(Script parent)
    {
        super(parent);
    }

    /**
     * Creates a new javascript function with a name and Script parent.
     *
     * @param name The function name.
     * @param parent The parent.
     * @throws IllegalArgumentException if the function name is null.
     */
    public Function(String name, Script parent)
    {
        super(parent);
        setName(name);
    }

    /**
     * Creates a new javascript function with a parent.
     *
     * @param parent The parent.
     */
    public Function(HtmlCommentTag parent)
    {
        super(parent);
    }

    /**
     * Creates a new javascript function with a name and HtmlCommentTag parent.
     *
     * @param name The function name.
     * @param parent The parent.
     * @throws IllegalArgumentException if the function name is null.
     */
    public Function(String name, HtmlCommentTag parent)
    {
        super(parent);
        setName(name);
    }

    /**
     * Creates a new javascript function with no parent, a function name,
     * arguments for the function (can be null), and statements for the function.
     *
     * @param function The function name.
     * @param args The function arguments.
     * @param statements The function statements.
     * @throws IllegalArgumentException if the text is null.
     */
    public Function(String name, String[] args, String[] statements) throws IllegalArgumentException
    {
        super();
        setName(name);
        if (args != null)
        {
            for (int i=0; i<args.length; i++)
            {
                addArgument(args[i]);
            }
        }
        if (statements != null)
        {
            for (int i=0; i<statements.length; i++)
            {
                addStatement(statements[i]);
            }
        }
    }

    /**
     * Creates a new javascript function with a Script parent, a function name,
     * arguments for the function (can be null), and statements for the function.
     *
     * @param name The function name.
     * @param args The function arguments.
     * @param statements The function statements.
     * @param parent The parent.
     * @throws IllegalArgumentException if the text is null.
     */
    public Function(String name, String[] args, String[] statements, Script parent) throws IllegalArgumentException
    {
        this(name, parent);
        if (args != null)
        {
            for (int i=0; i<args.length; i++)
            {
                addArgument(args[i]);
            }
        }
        if (statements != null)
        {
            for (int i=0; i<statements.length; i++)
            {
                addStatement(statements[i]);
            }
        }
    }

    /**
     * Sets the function name.
     *
     * @param name The name.
     * @throws IllegalArgumentException if the name is null.
     */
    public void setName(String name) throws IllegalArgumentException
    {
        if (name == null) throw new IllegalArgumentException("Function name cannot be null");
        _name = name;
    }

    /**
     * Returns the function name.
     *
     * @return String
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Adds an argument to the list of arguments.
     *
     * @param arg The argument name.
     */
    public void addArgument(String arg)
    {
        if (arg != null) _arguments.add(arg);
    }

    /**
     * Returns the arguments of this function.
     *
     * @return Vector
     */
    public Vector getArguments()
    {
        return _arguments;
    }

    /**
     * Adds a statement to the function. If the statement is null or the statement is
     * an empty string, then a newline is added.
     *
     * @param statement The statement.
     */
    public void addStatement(String statement)
    {
        if ( statement == null || "".equals(statement) ) addNewline();
        else new Statement(statement, this);
    }

    /**
     * Returns the statements of this function. This does not include newlines.
     *
     * @return Vector
     */
    public Vector getStatements()
    {
        return _statements;
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            boolean preserve = preserveWhiteSpace();
            String padding = (!preserve ? getPadding() : "");
            String newline = (!preserve ? Newline.CHARACTER : "");
            StringBuffer ret = new StringBuffer();
            ret.append(padding).append("function ").append( _name.trim() ).append("(");
            int size = _arguments.size();
            for (int i=0; i<size; i++)
            {
                ret.append( (i > 0 ? ", " : "") ).append( _arguments.get(i) );
            }
            ret.append(")").append( (preserve ? _spaceAfterArgs.toString() : "") ).append(newline).append(padding).append("{").append(newline);
            size = _statements.size();
            for (int i=0; i<size; i++)
            {
                ret.append( ((Statement)_statements.get(i)).format(type) );
            }
            ret.append(padding).append("}").append(newline);
            return ret.toString();
        }
        else
        {
            return (String)getCachedContent(type);
        }
    }

    public String toString()
    {
        String ret = null;
        try
        {
            ret = format(FormatType.TEXT);
        }
        catch (UnsupportedFormatException ufe)
        {
            ret = ufe.toString();
        }
        return ret;
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;

        boolean preserve = preserveWhiteSpace();
        //Clean leading spaces
        if (!preserve) removeLeadingSpaces(text);
        //Get past the function term
        TextUtils.getTextUpTo(text, ' ');

        if (!preserve) removeLeadingSpaces(text);
        setName( TextUtils.getTextUpTo(text, '(') );
        text.deleteCharAt(0);

        //Set args
        String argstr = TextUtils.getTextUpTo(text, ')');
        text.deleteCharAt(0);
        String[] args = TextUtils.split(argstr, ',');
        for (int i=0; i<args.length; i++)
        {
            _arguments.add( (!preserve ? args[i].trim() : args[i]) );
        }
        _spaceAfterArgs = removeLeadingSpaces(text);

        //Step through looking for balanced {} and find the end of the function
        //If they are unbalanced, then this will fail. We start with a {, so the
        //left brace will be more then right by default
        StringBuffer text2 = new StringBuffer();
        int leftBrace = 0;
        int rightBrace = 0;
        boolean done = (text.length() == 0);
        while (!done)
        {
            char c = text.charAt(0);
            text.deleteCharAt(0);
            if (c == '{') leftBrace++;
            else if (c == '}') rightBrace++;
            text2.append(c);
            done = (text.length() == 0 || text.indexOf("}") == -1 || rightBrace == leftBrace);
        }
        //Remove the first { and the last }
        text2.deleteCharAt( text2.indexOf("{") );
        text2.deleteCharAt( text2.lastIndexOf("}") );
        JavascriptBody.parseJs(text2, this, type);
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