package com.zitego.markup;

import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.TextUtils;
import java.util.Vector;

/**
 * This is an abstract class to represent a markup content component.
 * All classes that extend this must implement
 * <code>String format(FormatType)</code> to return the specified
 * type of markup. The extending class must handle throwing
 * UnsupportedFormatException for any format that is not supported by
 * the class. This class handles mapping markup content with a
 * <code>MarkupMap</code> that is passed in the constructor. The map
 * generates an id for the content.
 *
 * @author John Glorioso
 * @version $Id: MarkupContent.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public abstract class MarkupContent extends com.zitego.util.CachedContent implements MarkupConverter
{
    /** An id for the element. */
    private int _mapId = -1;
    /** A markup map to store direct access to content. */
    private MarkupMap _map;
    /** An optional parent content class. */
    private MarkupContent _parent;
    /** Whether this tag should print out padding before it's start tag. Default is true. */
    private boolean _hasPadding = true;
    /** Whether this tag should print out a newline after it's end tag. Default is true. */
    private boolean _hasNewline = true;
    /** The MarkupBody of this tag. */
    private MarkupBody _body;
    /** The line breaks to append after the end tag. */
    private Vector _lineBreaks = new Vector();
    /** Whether we should format this as root or not. */
    private boolean _formatAsRoot = false;
    /** Whether parsing should be done strictly. This is to be interpreted by the parse method. Default is true. */
    private boolean _strict = true;
    /** Whether to preserve all white space while parsing and not format further. This is to be interpreted by the parse method. Default is false. */
    private boolean _preserveWhiteSpace = false;

    /**
     * Creates a new markup content without a parent.
     */
    protected MarkupContent() { }

    /**
     * Creates a new markup content with a parent. The map is set with the parent's map.
     *
     * @param parent The parent content.
     * @throws IllegalArgumentException if the parent is null
     */
    protected MarkupContent(MarkupContent parent)
    {
        if (parent == null) throw new IllegalArgumentException("parent cannot be null");
        setParent(parent);
    }

    /**
     * Creates a new markup content with a map.
     *
     * @param map The markup map.
     * @throws IllegalArgumentException if the map is null.
     */
    protected MarkupContent(MarkupMap map)
    {
        setMap(map);
    }

    /**
     * Sets the markup map. If the map has changed, then all children need a new
     * id from the given map.
     *
     * @param map The markup map.
     * @throws IllegalArgumentException if the map is null.
     */
    protected void setMap(MarkupMap map)
    {
        if (map == null) throw new IllegalArgumentException("map cannot be null");

        //Reset the map id if we have a new map
        if (_map != map && _mapId > -1) _mapId = -1;
        _map = map;
        _mapId = _map.store(this);
        //Set all children to this map.
        MarkupBody body = getBody();
        if (body != null)
        {
            int size = body.size();
            for (int i=0; i<size; i++)
            {
                MarkupContent c = getBodyContent(i);
                c.setMap(_map);
            }
        }
    }

    /**
     * Overwrites the id by replacing the given content in the map with this one.
     *
     * @param content The content to replace.
     */
    protected void replaceContentInMap(MarkupContent content)
    {
        if (_map == null) return;
        _mapId = _map.overwrite(content, this);
    }

    /**
     * Sets whether this tag should have its padding printed out before
     * the start tag.
     *
     * @param flag The padding flag.
     */
    public void setHasPadding(boolean flag)
    {
        _hasPadding = flag;
    }

    /**
     * Returns whether we should print out padding before the start tag.
     *
     * @return boolean
     */
    public boolean hasPadding()
    {
        return _hasPadding;
    }

    /**
     * Sets whether we should print out a newline after the
     * end tag.
     *
     * @param flag The newline flag.
     */
    public void setHasNewline(boolean flag)
    {
        _hasNewline = flag;
    }

    /**
     * Returns whether we should print out a newline after the
     * end tag or not.
     *
     * @return boolean
     */
    public boolean hasNewline()
    {
        return _hasNewline;
    }

    /**
     * Sets whether parsing is strict or not. The meaning of this is implemented by subclasses. This
     * automatically sets all children as being non strict as well. Any content added to this content
     * will in turn be marked according to this setting.
     *
     * @param strict The strict flag.
     */
    public void setStrict(boolean strict)
    {
        _strict = strict;
        //Set all children.
        MarkupBody body = getBody();
        int size = body.size();
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            c.setStrict(strict);
        }
    }

    /**
     * Returns whether or not parsing is done on a strict basis. The meaning of this is implemented
     * by subclasses.
     *
     * @return boolean
     */
    public boolean isStrict()
    {
        return _strict;
    }

    /**
     * Sets whether to preserve whitespace and not format further. The meaning of this is implemented
     * by subclasses, but typically it means that generating content will not product indentation or
     * generated newline characters. This automatically sets all children as well. Any content added
     * to this content will in turn be marked according to this setting.
     *
     * @param preserve The preserve whitespace flag.
     */
    public void setPreserveWhiteSpace(boolean preserve)
    {
        _preserveWhiteSpace = preserve;
        //Set all children.
        MarkupBody body = getBody();
        int size = body.size();
        for (int i=0; i<size; i++)
        {
            MarkupContent c = getBodyContent(i);
            c.setPreserveWhiteSpace(preserve);
        }
    }

    /**
     * Returns whether or not we are preserving white space and not formatting. The meaning of this
     * is implemented by subclasses.
     *
     * @return boolean
     */
    public boolean preserveWhiteSpace()
    {
        return _preserveWhiteSpace;
    }

    /**
     * Adds a line break to be printed out after the content. The line
     * break is in the form of a newline content. This will need to be
     * overridden to add more specific types of breaks. generateContent
     * needs to call getLineBreaks to handle printing them out.
     */
    public void addLineBreak()
    {
        addLineBreak( new Newline(this) );
    }

    /**
     * Adds the given line break content to be printed out after the
     * content. The line given can be any MarkupContent, so beware when
     * using this method. generateContent needs to call getLineBreaks to
     * handle printing them out.
     */
    protected final void addLineBreak(MarkupContent br)
    {
        //Set that this tag does not have a newline printed out afterward
        //cause the generateContent method should handle printing out the
        //line breaks
        setHasNewline(false);
        br.setHasPadding(false);
        br.setHasNewline(false);
        _lineBreaks.add(br);
    }

    /**
     * Creates a new newline in the body content.
     */
    public void addNewline()
    {
        addBodyContent( new Newline(this) );
    }

    /**
     * Returns the Vector of line breaks.
     *
     * @return Vector
     */
    public Vector getLineBreaks()
    {
        return _lineBreaks;
    }

    /**
     * Gets the map id of the element.
     *
     * @return int
     */
    public final int getMapId()
    {
        return _mapId;
    }

    /**
     * Returns the markup map.
     *
     * @return MarkupMap
     */
    public final MarkupMap getMap()
    {
        return _map;
    }

    /**
     * Returns whether or not this content is the root content
     * element or not.
     *
     * @return boolean
     */
    public boolean isRoot()
    {
        return (_parent == null);
    }

    /**
     * Returns the number of levels deep which is the same as the
     * number of parents this content has.
     *
     * @return int
     */
    public int getDeepness()
    {
        if (_parent == null || _formatAsRoot) return 0;
        else return _parent.getDeepness()+1;
    }

    /**
     * Returns the number of levels deep for the specific format type.
     *
     * @param type The format type to count
     * @return int
     */
    public int getDeepness(FormatType type)
    {
        if (_parent == null || _formatAsRoot)
        {
            return 0;
        }
        else
        {
            int parentDeepness = _parent.getDeepness(type);
            int selfDeepness = (countDeepness(type) ? 1 : 0);
            return parentDeepness+selfDeepness;
        }
    }

    /**
     * This needs to be overridden to get to count for a specific content type.
     *
     * @param type The format type to count deepness for.
     * @return Whether to count deepness or not.
     */
    public boolean countDeepness(FormatType type)
    {
        return false;
    }

    /**
     * Shows the parents of this.
     */
    public void showParents()
    {
        System.out.println(this+" parent is "+_parent);
        if (_parent != null) _parent.showParents();
    }

    /**
     * Shows the parents of this of the specified type.
     */
    public void showParents(FormatType type)
    {
        if ( countDeepness(type) ) System.out.println(this+" parent is "+_parent);
        if (_parent != null) _parent.showParents(type);
    }

    /**
     * Returns a string of padding for this content based on how
     * many parents it has.
     *
     * @return String
     */
    public String getPadding()
    {
        StringBuffer ret = new StringBuffer();
        if (_hasPadding)
        {
            int deepness = getDeepness();
            for (int i=0; i<deepness; i++)
            {
                ret.append(" ");
            }
        }
        return ret.toString();
    }

    /**
     * Returns a string of padding for this content based on how
     * many parents it has.
     *
     * @param type The type of content to get padding for.
     * @return String
     */
    public String getPadding(FormatType type)
    {
        StringBuffer ret = new StringBuffer();
        int deepness = getDeepness(type);
        for (int i=0; i<deepness; i++)
        {
            ret.append(" ");
        }
        return ret.toString();
    }

    /**
     * Overrides cached content setChanged so that parent objects get changed too.
     */
    protected void setChanged()
    {
        super.setChanged();
        if (_parent != null) _parent.setChanged();
    }

    /**
     * Sets this and all children to be changed, but does not set parents as changed.
     */
    protected void setChildrenChanged()
    {
        super.setChanged();
        MarkupBody body = getBody();
        int size = body.size();
        for (int i=0; i<size; i++)
        {
            ( (MarkupContent)body.get(i) ).setChildrenChanged();
        }
    }

    /**
     * Adds text to the MarkupBody as TextContent and returns it. If the last MarkupBody element
     * was text content, then it is appended to that. Otherwise, it will create
     * a new TextContent and add that.
     *
     * @param txt The content to add.
     * @return TextContent
     * @throws IllegalArgumentException if the content is null.
     */
    public TextContent addBodyContent(String txt) throws IllegalArgumentException
    {
        if (txt == null) throw new IllegalArgumentException("Text can not be null.");
        MarkupContent c = getLastBodyContent();
        if (c != null && c instanceof TextContent)
        {
            ( (TextContent)c ).appendText(txt);
            setChanged();
        }
        else
        {
            c = createTextContent(txt);
        }
        return (TextContent)c;
    }

    /**
     * Adds content to the MarkupBody and returns it.
     *
     * @param content The content to add.
     * @throws IllegalArgumentException if the content is null.
     * @return MarkupContent
     */
    public MarkupContent addBodyContent(MarkupContent content) throws IllegalArgumentException
    {
        int index = getBody().size();
        return addBodyContentAt(index, content);
    }

    /**
     * Adds content to the beginning of the MarkupBody and returns it.
     *
     * @param content The content to add.
     * @throws IllegalArgumentException if the content is null.
     * @return MarkupContent
     */
    public MarkupContent addBodyContentAtBeginning(MarkupContent content) throws IllegalArgumentException
    {
        return addBodyContentAt(0, content);
    }

    /**
     * Adds content to the MarkupBody at the specified index and returns it. If the content
     * already exists at the specified or another index, then it is not added.
     *
     * @param index The index at which to add the content.
     * @param content The content to add.
     * @return MarkupContent
     * @throws IllegalArgumentException if the content is null.
     */
    public MarkupContent addBodyContentAt(int index, MarkupContent content) throws IllegalArgumentException
    {
        //Make sure it is not null
        if (content == null) throw new IllegalArgumentException("content cannot be null");

        MarkupBody body = getBody();

        //See if we already have this or not
        if ( body.contains(content) ) return content;

        body.add(index, content);
        setChanged();

        return content;
    }

    /**
     * Moves the content in the MarkupBody to the specified index and returns it. If the content
     * does not exist in the body, then it is added at the specified index.
     *
     * @param index The index at which to add the content.
     * @param content The content to add.
     * @return MarkupContent
     * @throws IllegalArgumentException if the content is null.
     */
    public MarkupContent moveBodyContentTo(int index, MarkupContent content) throws IllegalArgumentException
    {
        //Make sure it is not null
        if (content == null) throw new IllegalArgumentException("content cannot be null");

        MarkupBody body = createMarkupBody();

        //See if it is already in the spot asked for
        if (body.indexOf(content) == index) return content;

        //Remove it if it is in there
        if ( body.contains(content) ) removeBodyContent(content);

        return addBodyContentAt(index, content);
    }

    /**
     * Moves the specified to move MarkupContent to the index before the specified MarkupContent. If the
     * specified MarkupContent is not found or is null, the to move content is moved at the end of
     * the body content. If the to move content is not in the body, it is added.
     *
     * @param before The content to move before.
     * @param toMove The content to move.
     * @throws IllegalArgumentException if the content to move is null.
     */
    public MarkupContent moveBodyContentToBefore(MarkupContent before, MarkupContent toMove) throws IllegalArgumentException
    {
        int index = 0;
        MarkupBody body = getBody();
        if (before != null) index = body.indexOf(before);
        else index = -1;
        if (index < 0) index = body.size();
        return moveBodyContentTo(index, toMove);
    }

    /**
     * Moves the specified to move MarkupContent to the index after the specified MarkupContent. If the
     * specified MarkupContent is not found or is null, the to move content is moved to the end of
     * the body content. If the content to move is not in the body, then it is added.
     *
     * @param after The content to move after.
     * @param toMove The content to move.
     * @throws IllegalArgumentException if the content to move is null.
     */
    public MarkupContent moveBodyContentToAfter(MarkupContent after, MarkupContent toMove) throws IllegalArgumentException
    {
        int index = 0;
        MarkupBody body = getBody();
        if (after != null) index = body.indexOf(after)+1;
        else index = -1;
        if (index < 0) index = body.size();
        return moveBodyContentTo(index, toMove);
    }

    /**
     * Clears all body content from the body.
     */
    public void clearContent()
    {
        while (getBody().size() > 0)
        {
            removeBodyContent( getBodyContent(0) );
        }
    }

    /**
     * Removes the specified MarkupContent from the MarkupBody and returns the index of where it was.
     *
     * @param content The content to remove.
     * @return int
     */
    public int removeBodyContent(MarkupContent content)
    {
        MarkupBody body = getBody();
        int id = body.indexOf(content);
        if (id > -1)
        {
            body.remove(content);
            if (_map != null) _map.remove(content);
            setChanged();
        }
        return id;
    }

    /**
     * Removes the MarkupContent from the MarkupBody at the specified index.
     *
     * @param index The index of the content to remove.
     * @throws IndexOutOfBoundsException
     */
    public void removeBodyContentAt(int index) throws IndexOutOfBoundsException
    {
        removeBodyContent( getBodyContent(index) );
    }

    /**
     * Returns the index of the given body content.
     *
     * @param content The content.
     * @return int
     */
    public int indexOfInBody(MarkupContent content)
    {
        return getBody().indexOf(content);
    }

    /**
     * Returns the first body content or null if there is no content.
     *
     * @return MarkupContent
     */
    public MarkupContent getFirstBodyContent()
    {
        MarkupBody body = getBody();
        if (body.size() > 0) return (MarkupContent)body.get(0);
        else return null;
    }

    /**
     * Returns the last body content or null if there is none.
     *
     * @return MarkupContent The content to add.
     */
    public MarkupContent getLastBodyContent()
    {
        MarkupBody body = getBody();
        if (body.size() > 0) return (MarkupContent)body.get(body.size()-1);
        else return null;
    }

    /**
     * Returns the body content at the specified index.
     *
     * @param index The index of the content to get.
     * @return MarkupContent
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public MarkupContent getBodyContent(int index) throws IllegalArgumentException
    {
        return (MarkupContent)getBody().get(index);
    }

    /**
     * Returns the MarkupBody.
     *
     * @return MarkupBody
     */
    protected MarkupBody getBody()
    {
        if (_body == null) _body = createMarkupBody();
        return _body;
    }

    /**
     * Returns the number of elements in the body.
     *
     * @return int
     */
    public int getBodySize()
    {
        return getBody().size();
    }

    /**
     * Creates a new TextContent from the given content.
     *
     * @param txt The text.
     * @return TextContent
     */
    public TextContent createTextContent(String txt)
    {
        return new TextContent(this, txt);
    }

    /**
     * To be defined by child classes to generate the requested content. If the System
     * property "debug" is set to the value "1", then this will print the message
     * "com.zitego.markup.MarkupContent: Generating Content".
     *
     * @param type The type of content to generate.
     * @return String
     * @throws UnsupportedFormatException when we cannot generate the requested type.
     */
    protected abstract String generateContent(FormatType type) throws UnsupportedFormatException;

    /**
     * Performs the same function as format, but formats this content as if it had no parent,
     * so there will be no padding on the front of it.
     *
     * @param type The type of format to produce.
     * @return String
     * @throws UnsupportedFormatException if the format is not supported by this content.
     */
    public String formatAsRoot(FormatType type) throws UnsupportedFormatException
    {
        _formatAsRoot = true;
        //Set this and all children to changed to force it to format the way we want
        setChanged();
        setChildrenChanged();
        String ret = format(type);
        _formatAsRoot = false;
        //Set this and all children to changed again so next time we format, it will do what we want again.
        setChanged();
        setChildrenChanged();
        return ret;
    }

    public final String format(FormatType type) throws UnsupportedFormatException
    {
        if (type == null) throw new UnsupportedFormatException("FormatType cannot be null.");
        if (_debug) System.out.println(getClass()+": content has "+(hasChanged()?"":"not ")+"changed");
        if ( hasChanged() )
        {
            if (_debug) System.out.println(getClass() + ": Generating Content1");
            cacheContent( type, generateContent(type) );
        }
        //See if we have set this content yet
        String content = (String)getCachedContent(type);
        if (content == null)
        {
            if (_debug) System.out.println(getClass() + ": Generating Content2");
            setChanged();
            content = generateContent(type);
            cacheContent(type, content);
        }
        return content;
    }

    /**
     * Removes the leading spaces, newlines, and carriage returns from the given StringBuffer and
     * returns them as a new StringBuffer.
     *
     * @param in The buffer to cleanse.
     * @return StringBuffer
     */
    public static StringBuffer removeLeadingSpaces(StringBuffer in)
    {
        return TextUtils.removeLeadingCharacters(in, new char[] { ' ', '\r', '\n', '\t' });
    }

    /**
     * Returns the next word in the StringBuffer. A word is a string of characters that are not
     * a space, newline, or carriage return. This will truncate the word from the string and return
     * it. If the string is null or it does not contain a word, then null is returned.
     *
     * @param in The text to check.
     * @return String
     */
    public static String getNextWord(StringBuffer in)
    {
        if (in == null) return null;
        removeLeadingSpaces(in);

        return TextUtils.getTextUpTo(in, ' ');
    }

    /**
     * Returns the parent or null if it is not set.
     *
     * @return MarkupContent
     */
    public MarkupContent getParent()
    {
        return _parent;
    }

    /**
     * Sets the parent. If this is in the parent's body, it is removed.
     *
     * @param parent The parent.
     */
    public void setParent(MarkupContent parent)
    {
        //No need to do anything if the parent is already set
        if (parent == _parent) return;
        if (_parent != null) _parent.removeBodyContent(this);
        _parent = parent;
        if (_parent != null)
        {
            if (_parent.getMap() != null) setMap( _parent.getMap() );
            if ( addToParentOnInit() ) _parent.addBodyContent(this);
            setStrict( _parent.isStrict() );
            setPreserveWhiteSpace( _parent.preserveWhiteSpace() );
        }
        setChanged();
        setChildrenChanged();
    }

    /**
     * Returns this markup content as markup content.
     *
     * @return MarkupContent
     */
    public MarkupContent getAsMarkupContent()
    {
        return this;
    }

    /**
     * Returns if this class is supposed to add the itself to the parent's body
     * on construction. This method returns true. If content is not supposed to be
     * added on construction, you need to override this method to return false.
     *
     * @return boolean
     */
    protected boolean addToParentOnInit()
    {
        return true;
    }

    /**
     * Creates a new markup body to return. It does not care if a body is already set.
     *
     * @return MarkupBody
     */
    protected MarkupBody createMarkupBody()
    {
        return new MarkupBody(this);
    }

    /**
     * Searches through this content's body for the given markup content type and returns a
     * Vector of matching tags. No heirarchy is kept.
     *
     * @param c The class.
     * @return Vector
     */
    public Vector search(Class c)
    {
        Vector ret = new Vector();
        if (c != null)
        {
            if ( c.isAssignableFrom(getClass()) ) ret.add(this);
            //Go through the body
            int size = getBodySize();
            for (int i=0; i<size; i++)
            {
                ret.addAll( getBodyContent(i).search(c) );
            }
        }
        return ret;
    }

    /**
     * Parses the give object as the given type and adheres to whether formatting is kept or not.
     *
     * @param objToParse The object.
     * @param type The format type.
     * @throws IllegalMarkupException if the content cannot be parsed.
     * @throws UnsupportedFormatException if the format is not valid.
     */
    public void parse(Object objToParse, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (objToParse == null) return;
        else if (objToParse instanceof StringBuffer) parseText( (StringBuffer)objToParse, type );
        else if (objToParse instanceof String) parseText(new StringBuffer((String)objToParse), type);
        else throw new IllegalMarkupException( "Unable to parse object type: "+objToParse.getClass() );
    }

    /**
     * Needs to be implemented by extending classes.
     *
     * @param text The text to parse.
     * @param type The format type to parse as.
     */
    public abstract void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException;

    /**
     * Adds the given whitespace to the parent and sets the preserve whitespace flag based on the parent.
     *
     * @param whitespace The whitespace to add.
     * @param parent The parent.
     * @return TextContent
     */
    public static TextContent addWhiteSpace(String whitespace, MarkupContent parent)
    {
        TextContent text = null;
        if (parent != null)
        {
            text = parent.createTextContent(whitespace);
            text.setPreserveWhiteSpace( parent.preserveWhiteSpace() );
        }
        else
        {
            text = new TextContent();
            text.setText(whitespace);
        }
        return text;
    }

    /**
     * Returns whether or not to trim child text elements when generating content. Default is true when
     * not preserving white space.
     *
     * @return boolean
     */
    public boolean trimChildText()
    {
        return !_preserveWhiteSpace;
    }
}