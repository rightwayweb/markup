package com.zitego.markup;

import com.zitego.format.*;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.Newline;
import java.util.Vector;

/**
 * This class represents an markup content comment. A comment must have
 * a parent markup content. The comment is either one that is a single
 * line comment (one with only two forward slashes at the beginning) that
 * ends when a newline or carriage return is reached or one that is a
 * wrapped comment (start with a forward slash/asterisk and ends with an
 * asterisk/forward slash).
 *
 * @author John Glorioso
 * @version $Id: Comment.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Comment extends MarkupContent
{
    /** A single line comment. */
    public static final int SINGLE_LINE = 0;
    /** A wrapped comment. */
    public static final int WRAPPED = 1;
    /** The type of comment this is. Wrapped by default. */
    private int _type = WRAPPED;
    /** The comment. */
    private String _comment;

    /**
     * Creates a new comment with no parent.
     */
    public Comment()
    {
        super();
    }

    /**
     * Creates a new comment with a parent.
     *
     * @param parent The parent tag.
     */
    public Comment(MarkupContent parent)
    {
        super(parent);
    }

    /**
     * Creates a new comment with a parent and type.
     *
     * @param parent The parent tag.
     * @param type The type of comment.
     */
    public Comment(MarkupContent parent, int type)
    {
        this(parent);
        _type = type;
    }

    /**
     * Sets the comment.
     *
     * @param comment The comment.
     */
    public void setComment(String comment)
    {
        _comment = comment;
    }

    /**
     * Returns the comment.
     *
     * @return String
     */
    public String getComment()
    {
        return _comment;
    }

    /**
     * Sets the type of comment this is. If this is a single line comment, a new line will <u>always</u>
     * be appended regardless of whether this has a newline or not.
     *
     * @param type The type.
     * @throws IllegalArgumentException if the type is not either SINGLE_LINE or WRAPPED.
     */
    public void setType(int type)
    {
        if (type != SINGLE_LINE && type != WRAPPED) throw new IllegalMarkupException("Type is not valid");
        _type = type;
        setChanged();
    }

    /**
     * Returns the type of comment this is.
     *
     * @return int
     */
    public int getType()
    {
        return _type;
    }

    /**
     * This method checks the given text to see if it is a comment. If it is, then it is parsed
     * and Comments are created with the given parent. The FormatType parameter is not used in
     * this class, but it present in case it is needed for any extending passes. If this class
     * is not extended, the type can be null. It returns an array of MarkupContent. The array
     * should contain either Comment objects or TextContent objects.
     *
     * @param txt The text to check.
     * @param parent The parent (can be null).
     * @param type The format type for parsing the comments.
     * @return MarkupContent[]
     * @throws IllegalMarkupException if the markup is not legal for comments.
     * @throws UnsupportedFormatException if the format type is not supported.
     */
    public static MarkupContent[] checkForComments(StringBuffer txt, MarkupContent parent, FormatType type)
    throws IllegalMarkupException, UnsupportedFormatException
    {
        MarkupContent[] comments = new MarkupContent[0];
        if (txt == null) return comments;

        Vector tmp = new Vector();
        StringBuffer pre = removeLeadingSpaces(txt);
        if ( pre.length() > 0 && parent != null && parent.preserveWhiteSpace() ) tmp.add( addWhiteSpace(pre.toString(), parent) );
        while( txt.length() > 1 && txt.charAt(0) == '/' && (txt.charAt(1) == '/' || txt.charAt(1) == '*') )
        {
            Comment c = ( parent != null ? new Comment(parent) : new Comment() );
            c.parse(txt, type);
            tmp.add(c);
            pre = removeLeadingSpaces(txt);
            if ( pre.length() > 0 && parent != null && parent.preserveWhiteSpace() ) tmp.add( addWhiteSpace(pre.toString(), parent) );
        }
        comments = new MarkupContent[tmp.size()];
        tmp.copyInto(comments);
        return comments;
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            if ( preserveWhiteSpace() )
            {
                return getComment();
            }
            else
            {
                StringBuffer ret = new StringBuffer()
                    .append( getPadding() ).append("/");
                if (_type == WRAPPED) ret.append("* ");
                else ret.append("/ ");
                ret.append( getComment() );
                if (_type == WRAPPED)
                {
                    ret.append(" */");
                    if ( hasNewline() ) ret.append(Newline.CHARACTER);
                }
                else
                {
                    ret.append(Newline.CHARACTER);
                }
                return ret.toString();
            }
        }
        else
        {
            return (String)getCachedContent(type);
        }
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;

        removeLeadingSpaces(text);
        //The next two characters should be "//" or "/*". Error if not.
        if (text.length() < 2 || text.charAt(0) != '/') throw new IllegalMarkupException("Invalid comment: "+text);
        if (text.charAt(1) == '/') _type = SINGLE_LINE;
        else if (text.charAt(1) == '*') _type = WRAPPED;
        else throw new IllegalMarkupException("Invalid comment: "+text);

        //Remove that
        text.delete(0, 2);
        //Look for extra *
        while (text.length() > 0 && text.charAt(0) == '*')
        {
            text.deleteCharAt(0);
        }
        removeLeadingSpaces(text);

        //Set the comment
        StringBuffer comment = new StringBuffer();
        while (text.length() > 0)
        {
            char c = text.charAt(0);
            if ( _type == SINGLE_LINE && (c == '\r' || c == '\n') )
            {
                //At the end here...
                setComment( comment.toString().trim() );
                text.deleteCharAt(0);
                if (text.charAt(0) == '\r' || text.charAt(0) == '\n') text.deleteCharAt(0);
            }
            else if (c == '*')
            {
                //If the current char is an asterisk, see if the next is the end or not
                //and make sure that we don't just have a line of them to the end
                StringBuffer asterisks = new StringBuffer();
                asterisks.append(c);
                text.deleteCharAt(0);
                while (text.length() > 0 && (c=text.charAt(0)) == '*')
                {
                    asterisks.append(c);
                    text.deleteCharAt(0);
                }
                if (c == '/')
                {
                    //At the end here...
                    text.deleteCharAt(0);
                    break;
                }
                else
                {
                    comment.append(asterisks);
                }
            }
            else
            {
                comment.append(c);
            }
            text.deleteCharAt(0);
        }
        _comment = comment.toString().trim();
    }
}