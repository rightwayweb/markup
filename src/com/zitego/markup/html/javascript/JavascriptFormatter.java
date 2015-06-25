package com.zitego.markup.html.javascript;

/**
 * This class is static with methods to convert content from plain text
 * to javascript. Essentially, it escapes certain "reserved" characters that are used
 * in javascript such as single and double quotes. In addition, it escapes
 * newlines and carriage returns.
 *
 * @author John Glorioso
 * @version $Id: JavascriptFormatter.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class JavascriptFormatter
{
    public static void main(String[] args)
    {
        System.out.println( escape(args[0]) );
    }

    /**
     * This method escapes content for javascript by converting specific characters
     * (described above).
     *
     * @param String The content to escape.
     * @return String
     */
    public static String escape(String content)
    {
        if (content == null) return null;
        content = content.replaceAll("\"", "\\\\\"");
        content = content.replaceAll("'", "\\\\'");
        content = content.replaceAll("\r", "\\\\r");
        content = content.replaceAll("\n", "\\\\n");
        return content;
    }

    /**
     * This method unescapes content from javascript by converting specific characters
     * as described above.
     *
     * @param String The content to unescape.
     * @return String
     */
    public static String unescape(String content)
    {
        if (content == null) return null;
        content = content.replaceAll("\\\\\"", "\"");
        content = content.replaceAll("\\\\'", "'");
        content = content.replaceAll("\\\\r", "\r");
        content = content.replaceAll("\\\\n", "\n");
        return content;
    }
}