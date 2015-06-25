package com.zitego.markup.html.tag;

import com.zitego.markup.tag.*;
import com.zitego.markup.*;
import com.zitego.markup.html.HtmlMarkupMap;
import com.zitego.markup.html.HtmlMarkupFactory;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;

/**
 * This class represents an html tag. An html tag cannot have a parent
 * tag and has no attributes. This class is up to date as of W3C
 * specification version 4.01.
 *
 * @author John Glorioso
 * @version $Id: Html.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class Html extends HtmlMarkupTag
{
    /** An HTML DocType tag for this document. */
    private Doctype _docType;
    /** The Head object. */
    private Head _head;
    /** The Body object. */
    private Body _body;
    /** The Frameset tag. */
    private FrameSet _frameset;
    /** The base path if any for this html document. There will be an associated Base tag with this. */
    private String _basePath;

    public static void main(String[] args) throws Exception
    {
        Html html = new Html();
        html.setStrict(false);
        String in = args[0];
        java.io.File f = new java.io.File(in);
        if (f.exists())
        {
            java.io.BufferedReader filein = new java.io.BufferedReader( new java.io.FileReader(in) );
            StringBuffer instr = new StringBuffer();
            while ( (in=filein.readLine()) != null )
            {
                instr.append(in).append("\n");
            }
            in = instr.toString();
        }
        html.parseText(new StringBuffer(in), FormatType.HTML);
        System.out.println(html.format(FormatType.HTML));
    }

    /**
     * Creates a new Html tag.
     */
    public Html()
    {
        this( new HtmlMarkupMap() );
    }

    /**
     * Creates a new Html tag with a base url. This creates and adds a Base tag to the body.
     *
     * @param basePath The base path.
     */
    public Html(String basePath)
    {
        this(new HtmlMarkupMap(), basePath);
    }

    /**
     * Creates a new Html tag with an HtmlMarkupMap.
     *
     * @param map The map.
     */
    public Html(HtmlMarkupMap map)
    {
        super(map, "html");
        _docType = new Doctype(this);
        setHasNewline(false);
    }

    /**
     * Creates a new Html tag with a base url and an HtmlMap.
     * This creates and adds a Base tag to the body.
     *
     * @param map The map.
     * @param basePath The base path.
     */
    public Html(HtmlMarkupMap map, String basePath)
    {
        this(map);
        setBasePath(basePath);
    }

    /**
     * Returns the head for the html page. If it has not yet been created, it will
     * automatically create it and return it. This Head object can be manipulated
     * and the Html object will reflect the changes properly.
     *
     * @return Head
     */
    public Head getHead()
    {
        if (_head == null) _head = new Head(this);
        return _head;
    }

    /**
     * Returns the body for the html page. If it has not yet been created, it will
     * automatically create it and return it. This Body object can be manipulated
     * and the Html object will reflect the changes properly.
     *
     * @return Body
     */
    public Body getBodyTag()
    {
        if (_body == null) _body = new Body(this);
        return _body;
    }

    /**
     * Sets the base path.
     *
     * @param path The base path.
     */
    public void setBasePath(String path)
    {
        getHead().setBasePath(path);
    }

    /**
     * Returns the base path (Base tag href) of this html document.
     *
     * @return String
     */
    public String getBasePath()
    {
        return getHead().getBasePath();
    }

    /**
     * Returns the frameset for this for this html page. If a frameset already exists, then that
     * is returned.
     *
     * @return FrameSet
     * @throws IllegalStateException if a body has already been created.
     */
    public FrameSet getFrameSet()
    {
        if (_body != null) throw new IllegalStateException("Html body has already been created");
        if (_frameset == null) _frameset = new FrameSet(this);
        return _frameset;
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        StringBuffer ret = new StringBuffer();
        if (type == FormatType.HTML)
        {
            ret.append( _docType.format(type) ).append(Newline.CHARACTER).append(Newline.CHARACTER)
               .append( super.generateContent(type) );
        }
        else
        {
            ret.append( super.generateContent(type) );
        }
        return ret.toString();
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;

        //Clean leading spaces
        removeLeadingSpaces(text);

        if (text.length() < 5) return;
        String tag = text.substring(1, 5);
        if ( tag.equalsIgnoreCase("html") )
        {
            super.parseText(text, type);
            int size = getBodySize();
            for (int i=0; i<size; i++)
            {
                MarkupContent c = getBodyContent(i);
                if (c instanceof Body) _body = (Body)c;
                else if (c instanceof Head) _head = (Head)c;
                else if (c instanceof FrameSet) _frameset = (FrameSet)c;
            }
        }
        else
        {
            //We use the html markup factory in the case where there are tags before the opening html tag. The only
            //one we possibly expect is a doctype tag, but sometimes comments come before as well
            MarkupContent[] contents = HtmlMarkupFactory.getInstance().parse
            (
                text, null, FormatType.HTML, true, isStrict(), preserveWhiteSpace()
            );
            if (contents == null) contents = new MarkupContent[0];
            for (int i=0; i<contents.length; i++)
            {
                if (contents[i] instanceof Doctype)
                {
                    _docType = (Doctype)contents[i];
                    _docType.setParent(this);
                }
                else if (contents[i] instanceof Html)
                {
                    int size = contents[i].getBodySize();
                    for (int j=0; j<size; j++)
                    {
                        MarkupContent c = contents[i].getBodyContent(j);
                        addBodyContent(c);
                        if (c instanceof Body) _body = (Body)c;
                        else if (c instanceof Head) _head = (Head)c;
                        else if (c instanceof FrameSet) _frameset = (FrameSet)c;
                    }
                }
                else if (contents[i] instanceof CommentTag)
                {
                    addComment( ((CommentTag)contents[i]).getComment() );
                }
                else
                {
                    addBodyContent(contents[i]);
                }
            }
        }
    }
}