package com.zitego.markup;

import com.zitego.markup.tag.MarkupTag;
import com.zitego.markup.tag.UnknownTag;
import com.zitego.markup.tag.UnnecessaryEndTag;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.StaticProperties;
import java.util.Vector;

/**
 * This is a factory for producing markup content from a raw string. Call getInstance to have an
 * instance of a MarkupFactory returned. The markup factory returned will be instantiated in the following
 * manner. If there is no System property set by name of "markup_factory", one will be created using
 * the class name of this class. If you want a subclass of this to be returned, you must set the property
 * with the class name of MarkupFactory to instantiate. Additionally, the ClassLoader of this class will
 * be used unless the StaticProperties property "markup_factory_classloader" is set with with the
 * ClassLoader that can instantiate the MarkupFactory.
 *
 * @author John Glorioso
 * @version $Id: MarkupFactory.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class MarkupFactory
{
    /**
     * Returns an instance of the MarkupFactory to use. See class notes for details on manipulation of
     * the type of MarkupFactory returned.
     *
     * @return MarkupFactory
     * @throws RuntimeException if an error occurs creating the markup factory.
     */
    public static synchronized MarkupFactory getInstance()
    {
        MarkupFactory ret = null;
        String cp = System.getProperty("markup_factory");
        try
        {
            if (cp == null) cp = MarkupFactory.class.getName();
            ClassLoader l = (ClassLoader)StaticProperties.getProperty("markup_factory_classloader");
            if (l == null) l = MarkupFactory.class.getClassLoader();
            ret = (MarkupFactory)Class.forName(cp, true, l).newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Could not create markup factory: "+cp, e);
        }
        return ret;
    }

    /**
     * If the first character is a less then (&lt;) then we will build a tag,
     * otherwise we return a TextContent. The parent of the tag is set
     * if it is not null. The beginning of the instring to the end of the content
     * is then truncated. If the content is null or zero length, then we return a zero
     * length array. The keep parsing flag indicates whether we should keep parsing
     * after the end of the first markup content. In this case, more then one piece
     * can be returned. Parsing is done on a strict basis.
     *
     * @param in The content to parse.
     * @param parent The parent.
     * @param type The type of format to parse.
     * @param keepParsing Whether to continue parsing after we reach the end of the first content.
     * @return MarkupContent[]
     */
    public MarkupContent[] parse(StringBuffer in, MarkupContent parent, FormatType type, boolean keepParsing)
    throws IllegalMarkupException, UnsupportedFormatException
    {
        return parse(in, parent, type, keepParsing, true);
    }

    /**
     * If the first character is a less then (&lt;) then we will build a tag,
     * otherwise we return a TextContent. The parent of the tag is set
     * if it is not null. The beginning of the instring to the end of the content
     * is then truncated. If the content is null or zero length, then we return a zero
     * length array. The keep parsing flag indicates whether we should keep parsing
     * after the end of the first markup content. In this case, more then one piece
     * can be returned.
     *
     * @param in The content to parse.
     * @param parent The parent.
     * @param type The type of format to parse.
     * @param keepParsing Whether to continue parsing after we reach the end of the first content.
     * @param strict Whether or not parsing should be strict.
     * @return MarkupContent[]
     */
    public MarkupContent[] parse(StringBuffer in, MarkupContent parent, FormatType type, boolean keepParsing, boolean strict)
    throws IllegalMarkupException, UnsupportedFormatException
    {
        return parse(in, parent, type, keepParsing, true, false);
    }

    /**
     * If the first character is a less then (&lt;) then we will build a tag,
     * otherwise we return a TextContent. The parent of the tag is set
     * if it is not null. The beginning of the instring to the end of the content
     * is then truncated. If the content is null or zero length, then we return a zero
     * length array. The keep parsing flag indicates whether we should keep parsing
     * after the end of the first markup content. In this case, more then one piece
     * can be returned.
     *
     * @param in The content to parse.
     * @param parent The parent.
     * @param type The type of format to parse.
     * @param keepParsing Whether to continue parsing after we reach the end of the first content.
     * @param strict Whether or not parsing should be strict.
     * @param preserveSpaces Whether to preserve spaces and not do additional formatting.
     * @return MarkupContent[]
     */
    public MarkupContent[] parse(StringBuffer in, MarkupContent parent, FormatType type, boolean keepParsing, boolean strict, boolean preserveSpaces)
    throws IllegalMarkupException, UnsupportedFormatException
    {
        if (in == null) return null;
        Vector retTags = new Vector();
        //See if we are returning text or a tag
        StringBuffer preContent = MarkupContent.removeLeadingSpaces(in);
        if ( preContent.length() > 0 && (preserveSpaces || (parent != null && !parent.trimChildText())) ) retTags.add( createTextContent(preContent, parent, type, preserveSpaces) );

        //If this is an end tag, we have to check to see if it is the end tag of the parent.
        //If so, return and let the tag finish up. If not, skip past it because it is misplaced
        //and continue parsing
        while (in.length() > 1 && in.charAt(0) == '<' && in.charAt(1) == '/')
        {
            int index = in.indexOf(">");
            if (index == -1) index = in.length();
            //Check the tag
            String tag = in.substring(2,index);
            if ( parent != null && parent instanceof MarkupTag && ((MarkupTag)parent).getTagName().equalsIgnoreCase(tag) )
            {
                return contentArray(retTags, null);
            }
            else
            {
                if (preserveSpaces)
                {
                    String endTag = in.substring(0, index+1);
                    UnnecessaryEndTag end = ( parent != null ? new UnnecessaryEndTag(parent, endTag) : new UnnecessaryEndTag(endTag) );
                    end.setStrict(strict);
                    end.setPreserveWhiteSpace(preserveSpaces);
                    retTags.add(end);
                }
                in.delete(0, index+1);
                preContent = MarkupContent.removeLeadingSpaces(in);
                if (preserveSpaces && preContent.length() > 0) retTags.add( createTextContent(preContent, parent, type, preserveSpaces) );
            }
        }
        if (in.length() == 0) return contentArray(retTags, null);
        if (in.charAt(0) == '<')
        {
            //Create the tag
            retTags.add( createTag(in, parent, type, strict, preserveSpaces) );
        }
        else
        {
            //Create the text content
            retTags.add( createTextContent(in, parent, type, preserveSpaces) );
        }
        MarkupContent[] more = new MarkupContent[0];
        if (keepParsing && in.length() > 0) more = parse(in, parent, type, keepParsing, strict, preserveSpaces);

        return contentArray(retTags, more);
    }

    /**
     * Returns a markup content array of the vector of markup content.
     *
     * @return MarkupContent[]
     */
    protected MarkupContent[] contentArray(Vector content, MarkupContent[] more)
    {
        if (content == null) content = new Vector();
        if (more == null) more = new MarkupContent[0];
        //Add more content to the content vector
        for (int i=0; i<more.length; i++)
        {
            if (more[i] != null) content.add(more[i]);
        }
        //Remove any null content
        for (int i=0; i<content.size(); i++)
        {
            if (content.get(i) == null) content.remove(i--);
        }
        MarkupContent[] ret = new MarkupContent[content.size()];
        content.copyInto(ret);
        return ret;
    }

    /**
     * Creates a markup content given the tag name. This method expects that the first character is
     * always a <. If it isn't then bad things may happen.
     *
     * @param in The content.
     * @param parent The parent content.
     * @param type The type of format to use when parsing.
     * @param strict Whether the parsing done should be strict.
     * @param preserveSpaces Whether to preserve spaces and not do additional formatting.
     * @return MarkupTag
     * @throws IllegalMarkupException if the text is invalid.
     * @throws UnsupportedFormatException if the format type is not parsable.
     */
    protected MarkupTag createTag(StringBuffer in, MarkupContent parent, FormatType type, boolean strict, boolean preserveSpaces)
    throws IllegalMarkupException, UnsupportedFormatException
    {
        MarkupTag ret = null;
        String tag = getTagName(in);
        try
        {
            ret = createTagByName(tag, parent);
            if (ret == null) ret = createUnknownTag(tag, parent);
        }
        catch (Exception e)
        {
            throw new IllegalMarkupException("Illegal markup tag: "+tag+" could not be created: ", e);
        }
        if (parent == null)
        {
            ret.setStrict(strict);
            ret.setPreserveWhiteSpace(preserveSpaces);
        }
        ret.parse(in, type);
        return ret;
    }

    /**
     * Returns the tag name given the content string. This will not delete the tag
     * name from the text string.
     *
     * @param in The content.
     * @return String
     * @throws IllegalMarkupException if the tag is invalid.
     */
    protected String getTagName(StringBuffer in) throws IllegalMarkupException
    {
        if (in == null) return null;

        MarkupContent.removeLeadingSpaces(in);
        if (in.charAt(0) != '<') throw new IllegalMarkupException("Invalid tag: "+in);

        StringBuffer tag = new StringBuffer();
        int size = in.length();
        //Special case comment tag
        if (in.indexOf("<!--") == 0) return "!--";
        for (int i=1; i<size; i++)
        {
            char c = in.charAt(i);
            if (c == ' ' || c == '>' || c == '<' || c == '\r' || c == '\n') break;
            else tag.append(c);
        }
        return tag.toString();
    }

    /**
     * Throws an exception cause there are no tags to build. See HtmlMarkupFactory.
     *
     * @param name Not used.
     * @param parent Not used.
     * @return MarkupTag
     * @throws Exception.
     */
    protected MarkupTag createTagByName(String name, MarkupContent parent) throws Exception
    {
        throw new Exception("Cannot create tags by name in MarkupFactory");
    }

    /**
     * Creates and returns an TextContent given the content string and
     * a parent.
     *
     * @param in The content.
     * @param parent The parent tag.
     * @param type The type of format to parse.
     * @param preserveSpaces Whether or not to preserve spaces.
     * @return TextContent
     */
    protected TextContent createTextContent(StringBuffer in, MarkupContent parent, FormatType type, boolean preserveSpaces)
    throws IllegalMarkupException, UnsupportedFormatException
    {
        if (in == null) return null;

        TextContent ret = ( parent != null ? new TextContent(parent) : new TextContent() );
        ret.setPreserveWhiteSpace(preserveSpaces);
        ret.parse(in, type);
        return ret;
    }

    /**
     * Creates and returns an UnknownTag given the tag name and parent.
     *
     * @param tag The tag name.
     * @param parent The parent.
     * @return MarkupTag
     */
    protected MarkupTag createUnknownTag(String tag, MarkupContent parent)
    {
        if ( parent == null || !(parent instanceof MarkupTag) ) return new UnknownTag(tag);
        else return new UnknownTag( tag, (MarkupTag)parent );
    }
}