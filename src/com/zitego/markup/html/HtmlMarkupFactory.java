package com.zitego.markup.html;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.TextContent;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.MarkupFactory;
import com.zitego.markup.tag.MarkupTag;
import com.zitego.markup.tag.Doctype;
import com.zitego.markup.html.tag.HtmlMarkupTag;
import com.zitego.markup.html.tag.UnknownHtmlTag;
import com.zitego.markup.html.tag.HtmlCommentTag;
import com.zitego.markup.html.tag.Html;
import com.zitego.markup.html.javascript.Statement;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.StaticProperties;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.reflect.Constructor;

/**
 * This is a factory for producing html markup content from a raw string. Call getInstance to have an
 * instance of a MarkupFactory returned. The markup factory returned will be instantiated in the following
 * manner. If there is no System property set by name of "markup_factory", one will be created using
 * the class name of this class. If you want a subclass of this to be returned, you must set the property
 * with the class name of MarkupFactory to instantiate. Additionally, the ClassLoader of this class will
 * be used unless the StaticProperties property "markup_factory_classloader" is set with with the
 * ClassLoader that can instantiate the MarkupFactory.
 *
 * @author John Glorioso
 * @version $Id: HtmlMarkupFactory.java,v 1.4 2013/04/04 02:06:41 jglorioso Exp $
 */
public class HtmlMarkupFactory extends MarkupFactory
{
    private static Hashtable _tags = new Hashtable();
    static
    {
        try
        {
            _tags.put( "!--", Class.forName("com.zitego.markup.html.tag.HtmlCommentTag") );
            _tags.put( "!doctype", Class.forName("com.zitego.markup.tag.Doctype") );
            _tags.put( "a", Class.forName("com.zitego.markup.html.tag.Anchor") );
            _tags.put( "applet", Class.forName("com.zitego.markup.html.tag.Applet") );
            _tags.put( "area", Class.forName("com.zitego.markup.html.tag.Area") );
            _tags.put( "base", Class.forName("com.zitego.markup.html.tag.Base") );
            _tags.put( "body", Class.forName("com.zitego.markup.html.tag.Body") );
            _tags.put( "br", Class.forName("com.zitego.markup.html.tag.BR") );
            _tags.put( "frame", Class.forName("com.zitego.markup.html.tag.Frame") );
            _tags.put( "frameset", Class.forName("com.zitego.markup.html.tag.FrameSet") );
            _tags.put( "head", Class.forName("com.zitego.markup.html.tag.Head") );
            _tags.put( "h1", Class.forName("com.zitego.markup.html.tag.Header") );
            _tags.put( "h2", Class.forName("com.zitego.markup.html.tag.Header") );
            _tags.put( "h3", Class.forName("com.zitego.markup.html.tag.Header") );
            _tags.put( "h4", Class.forName("com.zitego.markup.html.tag.Header") );
            _tags.put( "h5", Class.forName("com.zitego.markup.html.tag.Header") );
            _tags.put( "h6", Class.forName("com.zitego.markup.html.tag.Header") );
            _tags.put( "hr", Class.forName("com.zitego.markup.html.tag.HR") );
            _tags.put( "html", Class.forName("com.zitego.markup.html.tag.Html") );
            _tags.put( "iframe", Class.forName("com.zitego.markup.html.tag.Iframe") );
            _tags.put( "img", Class.forName("com.zitego.markup.html.tag.Img") );
            _tags.put( "link", Class.forName("com.zitego.markup.html.tag.Link") );
            _tags.put( "map", Class.forName("com.zitego.markup.html.tag.Map") );
            _tags.put( "meta", Class.forName("com.zitego.markup.html.tag.Meta") );
            _tags.put( "nobr", Class.forName("com.zitego.markup.html.tag.NoBR") );
            _tags.put( "noframes", Class.forName("com.zitego.markup.html.tag.NoFrames") );
            _tags.put( "noscript", Class.forName("com.zitego.markup.html.tag.NoScript") );
            _tags.put( "object", Class.forName("com.zitego.markup.html.tag.ObjectTag") );
            _tags.put( "param", Class.forName("com.zitego.markup.html.tag.Param") );
            _tags.put( "script", Class.forName("com.zitego.markup.html.tag.Script") );
            _tags.put( "style", Class.forName("com.zitego.markup.html.tag.Style") );
            _tags.put( "title", Class.forName("com.zitego.markup.html.tag.Title") );
            _tags.put( "address", Class.forName("com.zitego.markup.html.tag.block.Address") );
            _tags.put( "blockquote", Class.forName("com.zitego.markup.html.tag.block.BlockQuote") );
            _tags.put( "div", Class.forName("com.zitego.markup.html.tag.block.Div") );
            _tags.put( "p", Class.forName("com.zitego.markup.html.tag.block.Paragraph") );
            _tags.put( "span", Class.forName("com.zitego.markup.html.tag.block.Span") );
            _tags.put( "button", Class.forName("com.zitego.markup.html.tag.form.Button") );
            _tags.put( "checkbox", Class.forName("com.zitego.markup.html.tag.form.Checkbox") );
            _tags.put( "fieldset", Class.forName("com.zitego.markup.html.tag.form.FieldSet") );
            _tags.put( "file", Class.forName("com.zitego.markup.html.tag.form.File") );
            _tags.put( "form", Class.forName("com.zitego.markup.html.tag.form.Form") );
            _tags.put( "hidden", Class.forName("com.zitego.markup.html.tag.form.Hidden") );
            _tags.put( "image", Class.forName("com.zitego.markup.html.tag.form.Image") );
            _tags.put( "label", Class.forName("com.zitego.markup.html.tag.form.Label") );
            _tags.put( "legend", Class.forName("com.zitego.markup.html.tag.form.Legend") );
            _tags.put( "optgroup", Class.forName("com.zitego.markup.html.tag.form.OptGroup") );
            _tags.put( "option", Class.forName("com.zitego.markup.html.tag.form.Option") );
            _tags.put( "password", Class.forName("com.zitego.markup.html.tag.form.Password") );
            _tags.put( "radio", Class.forName("com.zitego.markup.html.tag.form.Radio") );
            _tags.put( "reset", Class.forName("com.zitego.markup.html.tag.form.Reset") );
            _tags.put( "select", Class.forName("com.zitego.markup.html.tag.form.Select") );
            _tags.put( "submit", Class.forName("com.zitego.markup.html.tag.form.Submit") );
            _tags.put( "text", Class.forName("com.zitego.markup.html.tag.form.Text") );
            _tags.put( "textarea", Class.forName("com.zitego.markup.html.tag.form.TextArea") );
            _tags.put( "dd", Class.forName("com.zitego.markup.html.tag.list.Dd") );
            _tags.put( "dl", Class.forName("com.zitego.markup.html.tag.list.Dl") );
            _tags.put( "dt", Class.forName("com.zitego.markup.html.tag.list.Dt") );
            _tags.put( "li", Class.forName("com.zitego.markup.html.tag.list.Li") );
            _tags.put( "ol", Class.forName("com.zitego.markup.html.tag.list.Ol") );
            _tags.put( "ul", Class.forName("com.zitego.markup.html.tag.list.Ul") );
            _tags.put( "caption", Class.forName("com.zitego.markup.html.tag.table.Caption") );
            _tags.put( "table", Class.forName("com.zitego.markup.html.tag.table.Table") );
            _tags.put( "tbody", Class.forName("com.zitego.markup.html.tag.table.Tbody") );
            _tags.put( "td", Class.forName("com.zitego.markup.html.tag.table.Td") );
            _tags.put( "tfoot", Class.forName("com.zitego.markup.html.tag.table.Tfoot") );
            _tags.put( "th", Class.forName("com.zitego.markup.html.tag.table.Th") );
            _tags.put( "thead", Class.forName("com.zitego.markup.html.tag.table.Thead") );
            _tags.put( "tr", Class.forName("com.zitego.markup.html.tag.table.Tr") );
            _tags.put( "big", Class.forName("com.zitego.markup.html.tag.textEffect.Big") );
            _tags.put( "b", Class.forName("com.zitego.markup.html.tag.textEffect.Bold") );
            _tags.put( "cite", Class.forName("com.zitego.markup.html.tag.textEffect.Cite") );
            _tags.put( "code", Class.forName("com.zitego.markup.html.tag.textEffect.Code") );
            _tags.put( "del", Class.forName("com.zitego.markup.html.tag.textEffect.Del") );
            _tags.put( "dfn", Class.forName("com.zitego.markup.html.tag.textEffect.Dfn") );
            _tags.put( "em", Class.forName("com.zitego.markup.html.tag.textEffect.Em") );
            _tags.put( "font", Class.forName("com.zitego.markup.html.tag.textEffect.Font") );
            _tags.put( "ins", Class.forName("com.zitego.markup.html.tag.textEffect.Ins") );
            _tags.put( "i", Class.forName("com.zitego.markup.html.tag.textEffect.Italic") );
            _tags.put( "kbd", Class.forName("com.zitego.markup.html.tag.textEffect.Kbd") );
            _tags.put( "pre", Class.forName("com.zitego.markup.html.tag.textEffect.Pre") );
            _tags.put( "samp", Class.forName("com.zitego.markup.html.tag.textEffect.Samp") );
            _tags.put( "small", Class.forName("com.zitego.markup.html.tag.textEffect.Small") );
            _tags.put( "strike", Class.forName("com.zitego.markup.html.tag.textEffect.Strike") );
            _tags.put( "strong", Class.forName("com.zitego.markup.html.tag.textEffect.Strong") );
            _tags.put( "sub", Class.forName("com.zitego.markup.html.tag.textEffect.Sub") );
            _tags.put( "sup", Class.forName("com.zitego.markup.html.tag.textEffect.Sup") );
            _tags.put( "tt", Class.forName("com.zitego.markup.html.tag.textEffect.Tt") );
            _tags.put( "u", Class.forName("com.zitego.markup.html.tag.textEffect.Underline") );
            _tags.put( "var", Class.forName("com.zitego.markup.html.tag.textEffect.Var") );
            _tags.put( "xmp", Class.forName("com.zitego.markup.html.tag.textEffect.Xmp") );
        }
        catch (ClassNotFoundException cnfe)
        {
            throw new RuntimeException("Could initialize class tag map: "+cnfe);
        }
    }
    private static Hashtable _allowableInputTypes = new Hashtable();
    static
    {
        _allowableInputTypes.put("button", "1");
        _allowableInputTypes.put("checkbox", "1");
        _allowableInputTypes.put("file", "1");
        _allowableInputTypes.put("hidden", "1");
        _allowableInputTypes.put("image", "1");
        _allowableInputTypes.put("password", "1");
        _allowableInputTypes.put("radio", "1");
        _allowableInputTypes.put("reset", "1");
        _allowableInputTypes.put("submit", "1");
        _allowableInputTypes.put("text", "1");
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length < 1)
        {
            System.out.println("Usage: java com.zitego.markup.html.HtmlMarkupFactory <html file path> [<preserve whitespace flag>]");
            System.exit(1);
        }
        String contents = com.zitego.util.FileUtils.getFileContents( new java.io.File(args[0]) );
        boolean preserve = (args.length > 1 ? new Boolean(args[1]).booleanValue() : false);
        MarkupContent[] content = getInstance().parse(new StringBuffer(contents), null, FormatType.HTML, true, false, preserve);
        for (int i=0; i<content.length; i++)
        {
            System.out.print( content[i].format(com.zitego.format.FormatType.HTML) );
        }
    }

    /**
     * Returns an instance of the HtmlMarkupFactory to use. See class notes for details on manipulation of
     * the type of MarkupFactory returned.
     *
     * @return MarkupFactory
     * @throws RuntimeException if an error occurs creating the markup factory.
     */
    public static MarkupFactory getInstance()
    {
        String cp = System.getProperty("markup_factory");
        if (cp == null) System.setProperty( "markup_factory", HtmlMarkupFactory.class.getName() );
        ClassLoader l = (ClassLoader)StaticProperties.getProperty("markup_factory_classloader");
        if (l == null) StaticProperties.setProperty( "markup_factory_classloader", HtmlMarkupFactory.class.getClassLoader() );
        return MarkupFactory.getInstance();
    }

    /**
     * Creates an html document given some text.
     *
     * @param in The content to parse.
     * @return MarkupContent
     */
    public static Html parseDocument(StringBuffer in) throws IllegalMarkupException, UnsupportedFormatException
    {
        Html html = new Html();
        html.parse(in, FormatType.HTML);
        return html;
    }

    /**
     * Returns the tag name given the start and end index in the content string.
     * This method handles special tags such as the input tag where
     * the tag name is always input, but the class is retrieved based on the
     * type attribute.
     *
     * @param in The content.
     * @return String
     */
    protected String getTagName(StringBuffer in) throws IllegalMarkupException
    {
        String tag = super.getTagName(in);
        if ( "input".equals(tag) )
        {
            //Set the name to the type attribute
            //Look for the closing > and make sure it is not in quotes in an attribute
            //Count the quotes up to the > and if it is even, then it is the closing >
            //If it is odd, then we are inside a quote
            int endIndex = in.indexOf(">");
            int quoteIndex = -1;
            int quoteCount = 0;
            while ( (quoteIndex=in.indexOf("\"", quoteIndex+1)) < endIndex && quoteIndex != -1 && endIndex != -1)
            {
                quoteCount++;
                // Advance quote index until it is -1 or after end index
                while ( (quoteIndex=in.indexOf("\"", quoteIndex+1)) < endIndex && quoteIndex != -1 )
                {
                    quoteCount++;
                }
                // Even number of quotes means we found the end index
                if (quoteCount % 2 == 0) break;
                // No more quotes, then increment it after end index so it doesn't start over again
                else quoteIndex = endIndex + 1;
                endIndex = in.indexOf(">", endIndex + 1);
            }
            if (endIndex == -1) return tag;
            String tmp = in.substring(0, endIndex);
            Matcher m = Pattern.compile("type=\"?'?([a-zA-Z]+)\"?'?", Pattern.CASE_INSENSITIVE).matcher(tmp);
            //If there is no match or it is invalid, then it is a text field
            if ( m.find() ) tag = m.group(1);
            if (tag == null || _allowableInputTypes.get(tag.toLowerCase()) == null) tag = "text";
        }
        return tag;
    }

    /**
     * Returns a markup content given the name and parent.
     *
     * @param name The content.
     * @param parent The parent.
     * @return MarkupTag
     * @throws Exception if an error occurs creating the tag.
     */
    protected MarkupTag createTagByName(String name, MarkupContent parent) throws Exception
    {
        if (name == null) return null;
        name = name.toLowerCase();
        Class tagClass = getTagClass(name);
        if (tagClass == null) return null;

        //TO DO - figure a more eloquent way of handling special cases like br and h1-h6
        if ( tagClass == _tags.get("!--") )
        {
            //Comment tag
            if (parent != null)
            {
                if (parent instanceof HtmlMarkupTag) return new HtmlCommentTag( (HtmlMarkupTag)parent );
                else if (parent instanceof Statement) return new HtmlCommentTag( (Statement)parent );
                else return new HtmlCommentTag();
            }
            else return new HtmlCommentTag();
        }
        else if ( tagClass == _tags.get("!doctype") )
        {
            //Doctype tag
            return new Doctype();
        }
        Object[] args = (tagClass == _tags.get("h1") ? new Object[] { new Integer(name.substring(1)) } : null);

        HtmlMarkupTag ret = (HtmlMarkupTag)createTagInstance(tagClass, parent, args);
        //If this is a br, it needs to be added to the last body content in the parent
        if (ret instanceof com.zitego.markup.html.tag.BR && parent != null)
        {
            int size = parent.getBodySize();
            if (size == 0)
            {
                parent.addBodyContent(ret);
            }
            else
            {
                MarkupContent c = parent.getBodyContent(size-1);
                if (c instanceof HtmlMarkupTag) ret = ( (HtmlMarkupTag)c ).createLineBreak();
                else parent.addBodyContent(ret);
            }
        }
        return ret;
    }

    /**
     * Returns the class for the given tag name.
     *
     * @param name The tag name.
     * @return Class
     */
    protected Class getTagClass(String name)
    {
        return (Class)_tags.get(name);
    }

    /**
     * Creates a tag given the class and the parent tag.
     *
     * @param tagClass The class of the tag to create.
     * @param parent The parent.
     * @throws Exception if an error occurs creating the tag.
     */
    protected MarkupTag createTagInstance(Class tagClass, MarkupContent parent) throws Exception
    {
        return createTagInstance(tagClass, parent, null);
    }

    /**
     * Creates a tag given the class and the parent tag. In addition, you can specify an
     * array of arguments for the constructor that are besides the parent. For example, an
     * h1 tag would have an integer of 1 used as well as the parent for the constructor, so
     * a one element length array with an Integer can be passed in. Note: the parameters
     * must be in declared order or the tag will not be created. The parent is always assumed
     * to be the first parameter unless it is null. In that case, it is ignored.
     *
     * @param tagClass The class of the tag to create.
     * @param parent The parent.
     * @param args The parameters of the constructor (not including the parent).
     * @throws Exception if an error occurs creating the tag.
     */
    protected MarkupTag createTagInstance(Class tagClass, MarkupContent parent, Object[] args) throws Exception
    {
        MarkupTag ret = null;
        if (tagClass != null)
        {
            Vector params = new Vector();
            //Add the parent tag to the params if it is set
            if (parent != null) params.add(parent);
            //Add all the args passed in
            if (args != null)
            {
                for (int i=0; i<args.length; i++)
                {
                    params.add(args[i]);
                }
            }
            //Re-create the args
            args = new Object[params.size()];
            params.copyInto(args);
            //Get the constructors
            Constructor[] c = tagClass.getConstructors();
            //Go through looking for the right one
            for (int i=0; i<c.length; i++)
            {
                //Need a constructor that takes the parameters specified
                Class[] cargs = c[i].getParameterTypes();
                if (cargs.length == args.length)
                {
                    boolean match = true;
                    //This will still work for 0 length arrays
                    for (int j=0; j<cargs.length; j++)
                    {
                        //Make sure all params are the same type
                        if ( !cargs[j].isAssignableFrom(args[j].getClass()) )
                        {
                            match = false;
                            break;
                        }
                    }
                    if (match)
                    {
                        ret = (HtmlMarkupTag)c[i].newInstance(args);
                        break;
                    }
                }
            }
            //If we got here, then we don't know what the tag is so return null and let the calling method
            //figure it out
            return ret;
        }
        return ret;
    }

    /**
     * Creates and returns an HtmlTextContent given the content string and
     * a parent.
     *
     * @param in The content.
     * @param parent The parent html tag.
     * @param type The type of format to use.
     * @param preserveSpaces Whether or not to preserve spaces.
     * @return TextContent
     */
    protected TextContent createTextContent(StringBuffer in, MarkupContent parent, FormatType type, boolean preserveSpaces)
    throws IllegalMarkupException, UnsupportedFormatException
    {
        if (in == null) return null;

        HtmlTextContent ret = ( parent != null ? new HtmlTextContent(parent) : new HtmlTextContent() );
        ret.setPreserveWhiteSpace(preserveSpaces);
        ret.parse(in, type);
        return ret;
    }

    protected MarkupTag createUnknownTag(String tag, MarkupContent parent)
    {
        if ( parent == null || !(parent instanceof HtmlMarkupTag) ) return new UnknownHtmlTag(tag);
        else return new UnknownHtmlTag( tag, (HtmlMarkupTag)parent );
    }
}
