package com.zitego.markup.tag;

import com.zitego.markup.MarkupContent;
import com.zitego.markup.Newline;
import com.zitego.markup.MarkupBody;
import com.zitego.markup.MarkupMap;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.TextUtils;
import java.util.Vector;

/**
 * This class is abstract and represents a markup tag. The tag
 * has a name, an AttributeList of tag attributes, and a MarkupBody.
 *
 * @author John Glorioso
 * @version $Id: MarkupTag.java,v 1.3 2013/04/04 02:06:50 jglorioso Exp $
 */
public abstract class MarkupTag extends MarkupContent
{
    private String _tagName;
    private AttributeList _attributes;
    /** Whether or not there is an end tag. Default is true. */
    private boolean _hasEndTag = true;
    /** Whether or not this tag is on its own line. Default is false. */
    private boolean _isOnOwnLine;
    /** Whether or not this tag is embedded on the same line of another tag. Default is false. */
    private boolean _isEmbedded;
    private String _startTag;
    private String _endTag;
    /** Whether or not we have the end tag on the same line as the body content. Default is false. */
    private boolean _hasEndTagOnSameLine = false;
    private boolean _foundSingleQuotes = false;
    private boolean _forceSingleQuotes = false;
    private boolean _addClosingSlashInStart = false;

    /**
     * Creates a new markup tag.
     */
    protected MarkupTag()
    {
        super();
    }

    /**
     * Creates a new markup tag with a parent.
     *
     * @param parent The parent tag.
     */
    protected MarkupTag(MarkupTag parent)
    {
        super(parent);
        if (!_isEmbedded) _isEmbedded = false;
        if (!_isOnOwnLine) _isOnOwnLine = false;
    }

    /**
     * Creates a new markup tag with a parent.
     *
     * @param parent The parent content.
     */
    protected MarkupTag(MarkupContent parent)
    {
        super(parent);
        if (!_isEmbedded) _isEmbedded = false;
        if (!_isOnOwnLine) _isOnOwnLine = false;
    }

    /**
     * Creates a new markup tag with a name.
     *
     * @param name The tag name.
     */
    protected MarkupTag(String name)
    {
        this();
        setTagName(name);
    }

    /**
     * Creates a new markup tag with a name and a MarkupMap.
     *
     * @param name The tag name.
     * @param map The markup map.
     */
    protected MarkupTag(String name, MarkupMap map)
    {
        super(map);
        setTagName(name);
    }

    /**
     * Creates a new markup tag with a name and a parent.
     *
     * @param name The tag name.
     * @param parent The parent tag.
     */
    protected MarkupTag(String name, MarkupTag parent)
    {
        this(parent);
        setTagName(name);
    }

    /**
     * Creates a new markup tag with a name and a parent.
     *
     * @param name The tag name.
     * @param parent The parent content.
     */
    protected MarkupTag(String name, MarkupContent parent)
    {
        super(parent);
        setTagName(name);
    }

    /**
     * Creates a new markup tag with a name and a MarkupMap. This should only be used
     * by tags that do not have parents, but share a map.
     *
     * @param map The map
     * @param name The tag name.
     */
    protected MarkupTag(MarkupMap map, String name)
    {
        super(map);
        setTagName(name);
    }

    /**
     * Sets the id of the tag.
     *
     * @param id The id.
     */
    public void setIdAttribute(String id)
    {
        setAttribute("id", id);
    }

    /**
     * Returns the id of the tag.
     *
     * @return String
     */
    public String getIdAttribute()
    {
        return getAttributeValue("id");
    }

    /**
     * Sets the tag name.
     *
     * @param name The name.
     */
    protected void setTagName(String name)
    {
        setChanged();
        _tagName = name;
    }

    /**
     * Returns the tag name.
     *
     * @return String
     */
    public String getTagName()
    {
        return _tagName;
    }

    /**
     * Sets whether or not there is an end tag.
     *
     * @param hasTag Wheher there is an end tag.
     */
    protected void setHasEndTag(boolean hasTag)
    {
        setChanged();
        _hasEndTag = hasTag;
    }

    /**
     * Returns whether or not there is an end tag.
     *
     * @return boolean
     */
    public boolean hasEndTag()
    {
        return _hasEndTag;
    }

    /**
     * Sets a custom attribute.
     *
     * @param name The name of the attribute.
     * @param val The value of the attribute.
     */
    public void setCustomAttribute(String name, String val)
    {
        setAttribute(name, val);
    }

    /**
     * Returns the custom attribute.
     *
     * @param name The custom attribute name.
     * @return String
     */
    public String getCustomAttribute(String name)
    {
        return getAttributeValue(name);
    }

    /**
     * Sets whether this is on it's own line or not. That means that
     * it sets hasPadding to true, all children embeddedInLine to true,
     * and hasNewline to true. This affects all existing children and
     * those that are added later. The padding and new line can still
     * be manually changed on this and all children once they have been
     * added to this tag. Setting isOnOwnLine to false will not
     * change the padding or newline flags on this or the children, it
     * will only prevent children from being set to embedded when they
     * are added.
     *
     * @param flag The own line flag.
     */
    public void setIsOnOwnLine(boolean flag)
    {
        _isOnOwnLine = flag;
        if (_isOnOwnLine)
        {
            setHasPadding(true);
            embedChildren();
            setHasNewline(true);
        }
    }

    /**
     * Returns whether or not this tag and it's children are all on one
     * line.
     *
     * @return boolean
     */
    public boolean isOnOwnLine()
    {
        return _isOnOwnLine;
    }

    /**
     * Sets that this tag and all it's children are embedded on one line.
     * This means that hasPadding is false, children embeddedInLine is
     * true, and hasNewline is false. This affects all existing children and
     * those that are added later. The padding and new line can still
     * be manually changed on this and all children once they have been
     * added to this tag. Setting embeddedInLine to false will not
     * change the padding or newline flags on this or the children, it
     * will only prevent children from being set to embedded when they
     * are added.
     *
     * @param flag The embedded flag.
     */
    public void setIsEmbeddedInLine(boolean flag)
    {
        _isEmbedded = flag;
        if (_isEmbedded)
        {
            setHasPadding(false);
            embedChildren();
            setHasNewline(false);
        }
    }

    /**
     * Returns whether or not this tag and it's children are embedded on one
     * line.
     *
     * @return boolean
     */
    public boolean isEmbeddedInLine()
    {
        return _isEmbedded;
    }

    /**
     * Makes all body content embedded in line.
     */
    private void embedChildren()
    {
        MarkupBody body = getBody();
        if (body == null) return;

        int subTags = body.size();
        for (int i=0; i<subTags; i++)
        {
            MarkupContent subContent = (MarkupContent)body.get(i);
            if (subContent instanceof MarkupTag)
            {
                ( (MarkupTag)subContent ).setIsEmbeddedInLine(true);
            }
            else
            {
                subContent.setHasPadding(false);
                subContent.setHasNewline(false);
            }
        }
    }

    /**
     * Sets whether this tag's end tag must be on the same line as it's children.
     *
     * @param flag The end tag flag.
     */
    public void setHasEndTagOnSameLine(boolean flag)
    {
        _hasEndTagOnSameLine = flag;
    }

    /**
     * Returns whether this tag's end tag must be on the same line as it's children.
     *
     * @return boolean
     */
    public boolean hasEndTagOnSameLine()
    {
        return _hasEndTagOnSameLine;
    }

    /**
     * Sets the attribute with the given name. If it exists, then it is
     * replaced. If it does not, then it is added to the end of the vector.
     * If it does not exist and the allowNull flag is false, then it is
     * not added.
     *
     * @param name The attribute name to set.
     * @param val The tag attribute value to set it as.
     * @param allowNull Whether to allow null values.
     */
    private void setAttribute(String name, TagAttributeValue val, boolean allowNull) throws IllegalArgumentException
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name cannot be null in setAttribute");
        }
        setChanged();
        //See if we have a name already. If so, replace it. Otherwise, add it
        AttributeList attributes = getAttributes();
        TagAttribute a = getAttribute(name);
        if (a == null)
        {
            if (val != null || allowNull)
            {
                a = new TagAttribute(name, val);
                a.setStrict( isStrict() );
                a.setPreserveWhiteSpace( preserveWhiteSpace() );
                attributes.add(a);
            }
        }
        else
        {
            //Set it by reference if not null
            if (val != null || allowNull)
            {
                a.setValue(val);
            }
            else
            {
                attributes.remove(a);
            }
        }
    }

    /**
     * Sets the attribute with the given name. If it exists, then it is
     * replaced. If it does not, then it is added to the end of the vector. If
     * val is null and the attribute exists, then it is removed. If it does
     * not exist, then it is not added.
     *
     * @param name The attribute name to set.
     * @param val The value to set it as.
     */
    public void setAttribute(String name, String val)
    {
        TagAttributeValue value = new TagAttributeValue(val);
        value.setStrict( isStrict() );
        value.setPreserveWhiteSpace( preserveWhiteSpace() );
        setAttribute(name, value, false);
    }

    /**
     * Sets the attribute with the given name. If it exists, then it is
     * replaced. If it does not, then it is added to the end of the vector. If
     * val is null and the attribute exists, then it is removed. If it does
     * not exist, then it is not added.
     *
     * @param name The attribute name to set.
     * @param val The value to set it as.
     */
    public void setAttribute(String name, TagAttributeValue val)
    {
        setAttribute(name, val, false);
    }

    /**
     * Sets an attribute that has only a name and does not have a value.
     *
     * @param name The name.
     */
    public void setAttribute(String name)
    {
        setAttribute(name, null, true);
    }

    /**
     * Sets a list of attributes by taking a String array of attribute=value
     * parameters. If no equal sign is present, then the attribute is assumed
     * to have no value. If the array is null, then all attributes are removed.
     *
     * @param attr The attributes.
     * @throws IllegalArgumentException if the value of an attribute is invalid
     *                                  according to the class spec.
     */
    public void setAttributes(String[] attr) throws IllegalArgumentException
    {
        if (attr == null)
        {
            getAttributes().removeAllElements();
        }
        else
        {
            for (int i=0; i<attr.length; i++)
            {
                int index = attr[i].indexOf("=");
                if (index == -1) setAttribute(attr[i]);
                else setAttribute( attr[i].substring(0, index), attr[i].substring(index+1) );
            }
        }
    }

    /**
     * Sets the attributes in the markup tag given a string. This truncates
     * the in string up to the end of the attributes and will return at the > if there
     * is more text. This assumes the string starts with the first attribute, if it is
     * a space, carriage return, or newline then it will get trimmed until it gets to
     * something different. It parses the attributes as text.
     *
     * @param in The attribute string.
     */
    public void setAttributes(StringBuffer in)
    {
        setAttributes(in, FormatType.TEXT);
    }

    /**
     * Sets the attributes in the markup tag given a string and format type. This truncates
     * the in string up to the end of the attributes and will return at the > if there
     * is more text. This assumes the string starts with the first attribute, if it is
     * a space, carriage return, or newline then it will get trimmed until it gets to
     * something different.
     *
     * @param in The attribute string.
     * @param type The format type for attributes.
     * @throws UnsupportedFormatException if an error occurs parsing the attributes.
     */
    public void setAttributes(StringBuffer in, FormatType type)
    {
        if (in == null) return;

        //Clean leading spaces
        removeLeadingSpaces(in);
        //See if we need to set attributes or not. Until we reach a > or end of string we are still in the start tag
        String name = null;
        boolean inVal = false;
        boolean inQuotes = false;
        char escapeChar = '\\';
        char theQuote = '\'';
        boolean hitText = false;
        int i = -1;
        boolean done = false;
        char c = (char)0;
        char lastChar = (char)0;
        while (!done)
        {
            c = (++i < in.length() ? in.charAt(i) : (char)0);
            //See if we are done. That is a > when we are not in quotes. We check for < also, just
            //in case the html is screwed up and they never closed their opening tag
            done = ( c == (char)0 || (!inQuotes && (c == '>' || c == '<')) );
            if (done && c == '>' && lastChar =='/') setHasEndTag(false);
            if (c == '=' && !inQuotes)
            {
                if (name == null) name = in.substring(0, i);
                inVal = true;
                hitText = false;
                //Delete up to just passed the equals and reset index to -1
                in.delete(0, i+1);
                i = -1;
            }
            else if ( inVal && (hitText || (inQuotes && c == theQuote)) )
            {
                //if we have quotes then only an unescaped end quote, carriage return, or new line can terminate
                //otherwise a space, carriage return, new line, >, or < ends it
                if ( (inQuotes && c == theQuote && lastChar != escapeChar) ||
                     (!inQuotes && (c == ' ' || c == '\r' || c == '\n' || c == '>' || c == '<')) )
                {
                    TagAttributeValue c2 = new TagAttributeValue();
                    c2.setStrict( isStrict() );
                    c2.setPreserveWhiteSpace( preserveWhiteSpace() );
                    try
                    {
                        c2.parse(in.substring(0, i), type);
                    }
                    catch (UnsupportedFormatException ufe)
                    {
                        throw new RuntimeException("Could not parse "+in.substring(0, i), ufe);
                    }
                    setAttribute(name, c2);
                    inVal = false;
                    inQuotes = false;
                    name = null;
                    hitText = false;
                    //Delete up to just past the char we are on and reset index to -1, unless we have that special
                    //case where they did not close the open tag
                    if (c != '<') in.delete(0, i+1);
                    else in.delete(0, i);
                    i = -1;
                    _foundSingleQuotes = (_foundSingleQuotes || theQuote == '\'');
                }
                else if (c == '\r' || c == '\n')
                {
                    //No newlines in a quoted attribute, so remove it
                    in.deleteCharAt(i--);
                }
            }
            else if ( i > 0 && !inVal && (c == ' ' || c == '\r' || c == '\n' || c == '>' || c == '<') )
            {
                //if we hit a space, carriage return, newline, or > and the index is passed 0
                //then we set the name
                name = in.substring(0, i);
                //Delete up to just past the char we are on and reset index to -1, unless we have that special
                //case where they did not close the open tag
                if (c != '<') in.delete(0, i+1);
                else in.delete(0, i);
                i = -1;
                hitText = false;
            }
            else if (c == ' ' || c == '\r' || c == '\n')
            {
                //Get rid of the extra spaces.
                in.deleteCharAt(i--);
            }
            else
            {
                //If we have a name here and we are not in value then we have a solo attribute
                if (name != null && !inVal) setAttribute(name);

                hitText = true;
                //See if char is quote if we are in a value block
                if (inVal && c == '"' || c == '\'')
                {
                    theQuote = c;
                    inQuotes = true;
                    in.delete(0, i+1);
                    i = -1;
                }
            }
            lastChar = c;
        }
        //Special case where the closing > is missing. We don't want to delete the opening < of the next tag
        if (c == '<' && i > -1) in.delete(0, i);
        else in.delete(0, i+1);
    }

    /**
     * Sets a list of attributes by taking an AttributeList and setting each attribute
     * in this tag. Existing attributes will remain unless they are overwritten. If
     * you want to set attributes fresh, then you will need to call remove first.
     *
     * @param attr The attributes.
     * @throws IllegalArgumentException if the value of an attribute is invalid
     *                                  according to the class spec.
     */
    public void setAttributes(AttributeList attr) throws IllegalArgumentException
    {
        if (attr == null) return;

        int size = attr.size();
        for (int i=0; i<size; i++)
        {
            TagAttribute a = (TagAttribute)attr.get(i);
            setAttribute( a.getName(), a.getValue() );
        }
    }

    /**
     * Removes the specified attribute.
     *
     * @param name The name of the attribute to remove.
     */
    public void removeAttribute(String name)
    {
        TagAttribute a = getAttribute(name);
        if (a != null)
        {
            getAttributes().remove(a);
            setChanged();
        }
    }

    /**
     * Returns the specified attribute.
     *
     * @param name the name.
     */
    protected final TagAttribute getAttribute(String name)
    {
        if (name == null) return null;

        //name = name.toLowerCase();
        AttributeList attributes = getAttributes();
        int size = attributes.size();
        for (int i=0; i<size; i++)
        {
            TagAttribute a = (TagAttribute)attributes.get(i);
            if ( name.equalsIgnoreCase(a.getName()) ) return a;
        }
        return null;
    }

    /**
     * Returns the attribute value or null if it does not exist.
     *
     * @param name The attribute name.
     * @return String
     */
    protected final String getAttributeValue(String name)
    {
        TagAttribute a = getAttribute(name);
        if (a == null) return null;
        else return a.getValue();
    }

    /**
     * Returns the int value of the attribute. If the attribute does
     * not exist, then 0 is returned.
     *
     * @param name The attribute name.
     * @return int
     * @throws NumberFormatException if the attribute value cannot be converted.
     */
    protected final int getIntAttributeValue(String name) throws NumberFormatException
    {
        return getIntAttributeValue(name, 0);
    }

    /**
     * Returns the int value of the attribute. If the attribute does
     * not exist, then the default is returned.
     *
     * @param name The attribute name.
     * @param def The default.
     * @return int
     * @throws NumberFormatException if the attribute value cannot be converted.
     */
    protected final int getIntAttributeValue(String name, int def) throws NumberFormatException
    {
        String val = getAttributeValue(name);
        if (val != null) return Integer.parseInt(val);
        else return def;
    }

    /**
     * Returns the raw attributes.
     *
     * @return AttributeList
     */
    public AttributeList getAttributes()
    {
        if (_attributes == null) _attributes = new AttributeList();
        return _attributes;
    }

    /**
     * Adds a comment to the body and returns the comment tag.
     *
     * @param comment The comment.
     * @return CommentTag
     */
    public CommentTag addComment(String comment)
    {
        return new CommentTag(this, comment);
    }

    /**
     * Adds content to the MarkupBody at the specified index and returns it and configures the
     * children if this is on its own line or it is embedded.
     *
     * @param index The index at which to add the content.
     * @param content The content to add.
     * @return MarkupContent
     * @throws IllegalArgumentException if the content is null.
     * @throws IllegalStateException if this tag does not have an end tag.
     */
    public MarkupContent addBodyContentAt(int index, MarkupContent content)
    throws IllegalArgumentException, IllegalStateException
    {
        if ( !hasEndTag() ) throw new IllegalStateException("End tag is required to add body content.");

        super.addBodyContentAt(index, content);

        if (_isOnOwnLine || _isEmbedded)
        {
            if (content instanceof MarkupTag)
            {
                ( (MarkupTag)content ).setIsEmbeddedInLine(true);
            }
            else
            {
                content.setHasPadding(false);
                content.setHasNewline(false);
            }
        }

        return content;
    }

    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        _startTag = getStartTag(type);
        StringBuffer ret = new StringBuffer(_startTag);
        MarkupBody body = getBody();
        if (body != null) ret.append( body.format(type) );
        _endTag = getEndTag(type);
        ret.append(_endTag);
        return ret.toString();
    }

    /**
     * Writes out the opening form tag based on the properties set.
     *
     * @param type The format type of this start tag.
     * @return String
     */
    public String getStartTag(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            AttributeList attributes = getAttributes();
            attributes.setForceSingleQuotes( (_forceSingleQuotes || (_foundSingleQuotes && keepSingleQuotes())) );
            StringBuffer ret = new StringBuffer( (!preserveWhiteSpace() ? getPadding() : "") )
                .append("<").append( getTagName() ).append( attributes.toString(type) )
                .append( (_addClosingSlashInStart ? " /" : "") ).append(">");
            //New line only if we are not on our own line and we are not embedded and
            //we are not the last item in a tag that is marked as hasEndTagOnSameLine
            ret.append( getStartTagNewline() );
            _startTag = ret.toString();
        }
        return _startTag;
    }

    /**
     * Returns a newline character to print if we are supposed to.
     *
     * @return String
     */
    public String getStartTagNewline()
    {
        String ret = "";
        if (!preserveWhiteSpace() && !_isOnOwnLine && !_isEmbedded)
        {
            MarkupContent parent = getParent();
            if (_hasEndTag)
            {
                if (getBodySize() > 0) ret = Newline.CHARACTER;
            }
            else if ( !(parent instanceof MarkupTag) || !((MarkupTag)parent).hasEndTagOnSameLine() || parent.indexOfInBody(this) != parent.getBodySize()-1 )
            {
                //ret.append(Newline.CHARACTER);
            }
        }
        return ret;
    }

    /**
     * Writes out the end tag of this tag.
     *
     * @param type The type of format.
     * @return String
     * @throws UnsupportedFormatException
     */
    public String getEndTag(FormatType type) throws UnsupportedFormatException
    {
        if ( hasChanged() )
        {
            boolean preserve = preserveWhiteSpace();
            StringBuffer ret = new StringBuffer();
            MarkupContent parent = getParent();
            boolean noNewline = (parent instanceof MarkupTag && ((MarkupTag)parent).hasEndTagOnSameLine() && parent.indexOfInBody(this) == parent.getBodySize()-1);
            if (_hasEndTag)
            {
                if (!preserve && !_isOnOwnLine && !_hasEndTagOnSameLine) ret.append( getPadding() );
                ret.append("</").append( getTagName() ).append(">");
            }
            if (!preserve && parent != null && hasNewline() && !noNewline) ret.append(Newline.CHARACTER);
            Vector breaks = getLineBreaks();
            int size = breaks.size();
            for (int i=0; i<size; i++)
            {
                ret.append( ((MarkupContent)breaks.get(i)).format(type) );
            }
            if (!preserve&& size > 0 && !_isEmbedded && !noNewline) ret.append(Newline.CHARACTER);
            _endTag = ret.toString();
        }
        return _endTag;
    }

    /**
     * Returns the tag formatted to text.
     *
     * @return String
     * @throws UnsupportedFormatException if the body does not support text format.
     */
    protected String toText() throws UnsupportedFormatException
    {
        AttributeList attributes = getAttributes();
        StringBuffer ret = new StringBuffer( getTagName() ).append(": ")
            .append(attributes);
        MarkupBody body = getBody();
        if (body != null) ret.append( body.format(FormatType.TEXT) );
        return ret.toString();
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;

        //Clean leading spaces
        removeLeadingSpaces(text);

        //The leading char should be a < now. If not, then err
        if (text.charAt(0) != '<') throw new IllegalMarkupException("Invalid text: "+text);

        //Remove that
        text.deleteCharAt(0);

        //Only validate if not comment tag
        validateTagName(text);

        //Set the attributes in the start tag
        setAttributes(text, type);

        //Parse the body if we have an end tag
        if ( hasEndTag() ) getBody().parseText(text, type);

        //See if there is an end tag here (check hasEndTag later cause sometimes people put them there anyway)
        if (text.length() > 1 && text.charAt(0) == '<' && text.charAt(1) == '/')
        {
            text.delete(0, 2);
            char c = (char)0;
            String name = TextUtils.getTextUpTo(text, '>');
            //Delete the >
            text.deleteCharAt(0);
            if ( !getTagName().equalsIgnoreCase(name) )
            {
                //Gotta stick the </tagname> back on
                text.insert(0, "</"+name+">");
            }
        }
    }

    /**
     * Validates to make sure that the parsed tag name is valid.
     *
     * @param text The content.
     * @throws IllegalMarkupException if the tag is invalid.
     */
    protected void validateTagName(StringBuffer text)
    {
        String tag = TextUtils.getTextUpTo(text, new char[]{' ','>','<','\r','\n'});
        String tagName = getTagName();
        //Now we should be at the tag name. Make sure it is right
        if ( tagName != null && !tagName.equalsIgnoreCase(tag) ) throw new IllegalMarkupException
        (
            "Expected tag <"+tagName+">, but found <"+tag+TextUtils.trunc(text.toString(), 25)
        );
    }

    /**
     * Returns whether this tag should keep single quotes on tag attributes when originally parsed. If
     * the tag is created fresh and keepSingleQuotes() and forceSingleQuotes() both return true, it will
     * also format out single quotes. Otherwise, double quotes are always returned.
     *
     * @return boolean
     */
    public boolean keepSingleQuotes()
    {
        return false;
    }

    /**
     * Forces the use of single quotes when formatting.
     *
     * @param flag Whether to force formatting of single quotes.
     */
    public void setForceSingleQuotes(boolean flag)
    {
        _forceSingleQuotes = flag;
    }

    /**
     * Returns whether or not we are forcing single quotes or not when formatting tag attributes.
     *
     * @return boolean
     */
    public boolean forceSingleQuotes()
    {
        return _forceSingleQuotes;
    }

    /**
     * Sets whether or not to add a closing slash in the start tag. Ex: <tag />
     *
     * @param flag The flag.
     */
    public void setAddClosingSlashInStart(boolean flag)
    {
        _addClosingSlashInStart = flag;
    }

    /**
     * Returns whether or not to add a closing slash in the start tag.
     *
     * @return boolean
     */
    public boolean addClosingSlashInStart()
    {
        return _addClosingSlashInStart;
    }
}