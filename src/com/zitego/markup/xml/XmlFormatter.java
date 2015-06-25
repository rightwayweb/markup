package com.zitego.markup.xml;

/**
 * This class is static with methods to convert content from one type of markup
 * to xml. Essentially, it escapes certain "reserved" characters that are used
 * in an xml document such as the &gt; sign and the &lt; sign. In addition, it
 * converts newlines and carriage returns to \n and \r respectively.
 *
 * @author John Glorioso
 * @version $Id: XmlFormatter.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class XmlFormatter
{
    public static void main(String[] args) throws Exception
    {
        String esc = args[0];
        System.out.println("before: "+esc);
        esc = escape(esc);
        System.out.println("escaped: "+esc);
        esc = unescape(esc, true);
        System.out.println("unescaped: "+esc);
        esc = escape(esc);
        System.out.println("escaped: "+esc);
        esc = unescape(esc, true);
        System.out.println("unescaped: "+unescape(esc));
    }

    /**
     * This method escapes content for xml by converting specific characters
     * (described above).
     *
     * @param String The content to escape.
     * @return String
     */
    public static String escape(String content)
    {
        if (content == null) return null;
        content = content.replaceAll("&", "&amp;");
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
        content = content.replaceAll("\r", "\\\\r");
        content = content.replaceAll("\n", "\\\\n");
        return content;
    }

    /**
     * This method escapes already escaped content to html by converting & into &amp;
     * (described above).
     *
     * @param String The content to escape.
     * @return String
     */
    public static String escapeToHtml(String content)
    {
        if (content == null) return null;
        content = content.replaceAll("&amp;", "&amp;amp;");
        content = content.replaceAll("&lt;", "&amp;lt;");
        content = content.replaceAll("&gt;", "&amp;gt;");
        content = content.replaceAll("&lt;", "&amp;lt;");
        return content;
    }

    /**
     * This method unescapes content from xml by converting specific characters
     * as described above. Greater than, less than, and ampersands are assumed to
     * be already escaped as most XML parsers automatically do this. If you wish
     * to escape them anyway, call unescape(String, true).
     *
     * @param String The content to unescape.
     * @return String
     */
    public static String unescape(String content)
    {
        return unescape(content, false);
    }

    /**
     * This method unescapes content from xml by converting specific characters
     * as described above. Greater than, less than, and ampersands are only
     * escaped if the fullEscape flag is true.
     *
     * @param String The content to unescape.
     * @param boolean Whether to escape greater than, less than, and ampersand.
     * @return String
     */
    public static String unescape(String content, boolean fullEscape)
    {
        if (content == null) return null;
        content = content.replaceAll("\\\\r", "\r");
        content = content.replaceAll("\\\\n", "\n");
        if (fullEscape)
        {
            content = content.replaceAll("&amp;", "&");
            content = content.replaceAll("&lt;", "<");
            content = content.replaceAll("&gt;", ">");
        }
        return content;
    }
}