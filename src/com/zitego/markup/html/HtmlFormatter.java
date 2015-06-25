package com.zitego.markup.html;

/**
 * This class is static with methods to convert content from plain text
 * to html. Essentially, it escapes certain "reserved" characters that are used
 * in an html document such as the &gt; sign and the &lt; sign. In addition, it
 * converts newlines and carriage returns to &lt;br&gt;s. if the convertLineBreaks
 * flag is set to true.
 *
 * @author John Glorioso
 * @version $Id: HtmlFormatter.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class HtmlFormatter
{
    /**
     * This method escapes content for html by converting specific characters
     * (described above).
     *
     * @param String The content to escape.
     * @return String
     */
    public static String escape(String content)
    {
        return escape(content, true);
    }

    /**
     * This method escapes content for html by converting specific characters
     * (described above). If the convertLineBreaks flag is true, then it will
     * convert \r\n to &lt;br&gt;.
     *
     * @param String The content to escape.
     * @param boolean Whether to convert line breaks to br.
     * @return String
     */
    public static String escape(String content, boolean convertLineBreaks)
    {
        if (content == null) return null;
        content = content.replaceAll("&", "&amp;");
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
        if (convertLineBreaks)
        {
            content = content.replaceAll("\r\n", "<br>");
            content = content.replaceAll("\r", "<br>");
            content = content.replaceAll("\n", "<br>");
        }
        return content;
    }

    /**
     * This method unescapes content from html by converting specific characters
     * as described above.
     *
     * @param String The content to unescape.
     * @return String
     */
    public static String unescape(String content)
    {
        if (content == null) return null;
        content = content.replaceAll("&amp;", "&");
        content = content.replaceAll("&lt;", "<");
        content = content.replaceAll("&gt;", ">");
        return content;
    }

    /**
     * Replaces any spaces in the given string to non-breaking spaces &nbsp;.
     *
     * @param String The string to convert.
     * @return String
     */
    public static String convertSpaces(String in)
    {
        if (in == null) return null;
        in = in.replaceAll(" ", "&nbsp;");
        return in;
    }
}