package com.zitego.markup.xml;

import com.zitego.markup.TextContent;
import com.zitego.markup.MarkupContent;
import com.zitego.markup.MarkupBody;
import com.zitego.markup.IllegalMarkupException;
import com.zitego.markup.tag.MarkupTag;
import com.zitego.format.FormatType;
import com.zitego.format.UnsupportedFormatException;
import com.zitego.util.TextUtils;
import java.util.Vector;
import org.xml.sax.SAXException;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.NamedNodeMap;

/**
 * This is just a non abstract tag that can be extended or used directly.
 *
 * @author John Glorioso
 */
public class XmlTag extends MarkupTag implements XmlConverter
{
    private String _rootLine = null;
    private boolean _validateXml = false;

    /**
     * Creates an empty XmlTag as the parent
     */
    public XmlTag()
    {
        super();
    }

    /**
     * Creates a new XmlTag with a tag name as the parent.
     *
     * @param tag The tag name.
     */
    public XmlTag(String tag)
    {
        super(tag);
    }

    /**
     * Creates a new xml tag with a parent.
     *
     * @param parent The parent tag.
     */
    protected XmlTag(XmlTag parent)
    {
        super(parent);
    }

    /**
     * Creates a new XmlTag with a tag name and a parent.
     *
     * @param tag The tag name.
     * @param parent The parent.
     */
    public XmlTag(String tag, XmlTag parent)
    {
        super(tag, parent);
    }

    /**
     * This is a typo. Use setRootLine instead.
     *
     * @param line The root line.
     * @deprecated Use setRootLine instead.
     */
    public void setRootLin(String line)
    {
        setRootLine(line);
    }

    /**
     * Sets the root line of the xml document (if this is the root tag).
     *
     * @param line The root line.
     */
    public void setRootLine(String line)
    {
        _rootLine = line;
    }

    /**
     * Sets whether to validate xml that is parsed to the provided dtd if any.
     *
     * @param flag The boolean flag.
     */
    public void setValidateXml(boolean flag)
    {
        _validateXml = flag;
    }

    /**
     * Returns whether or not to validate xml when parsed.
     *
     * @return boolean
     */
    public boolean getValidateXml()
    {
        return _validateXml;
    }

    public void parseText(StringBuffer text, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (text == null) return;
        if (type != FormatType.XML) throw new UnsupportedFormatException("Illegal format type: "+type);
        try
        {
            parse(XmlUtils.parseXml(text.toString(), _validateXml).getDocumentElement(), FormatType.XML);
        }
        catch (Exception e)
        {
            throw new UnsupportedFormatException("Could not parse the xml", e);
        }
    }

    public void parse(Object objToParse, FormatType type) throws IllegalMarkupException, UnsupportedFormatException
    {
        if (objToParse instanceof Element && type == FormatType.XML) buildFromXml( (Element)objToParse );
        else super.parse(objToParse, type);
    }

    public void buildFromXml(Element root)
    {
        setTagName( root.getTagName() );

        NamedNodeMap attributes = root.getAttributes();
        int size = (attributes != null ? attributes.getLength() : 0);
        for (int i=0; i<size; i++)
        {
            Node attr = attributes.item(i);
            if (attr instanceof Attr) setAttribute( ((Attr)attr).getName(), ((Attr)attr).getValue() );
        }

        NodeList nodes = root.getChildNodes();
        size = nodes.getLength();
        for (int i=0; i<size; i++)
        {
            Node n = nodes.item(i);
            if (n instanceof Element)
            {
                addChild( (Element)n );
            }
            else if (n instanceof Text)
            {
                if (n instanceof CDATASection)
                {
                    CData data = new CData(this);
                    try
                    {
                        data.parse( (CDATASection)n, FormatType.XML );
                    }
                    //CData supports this...
                    catch (UnsupportedFormatException ufe)
                    {
                        throw new RuntimeException("Could not parse: "+n, ufe);
                    }
                }
                else
                {
                    String txt = TextUtils.trim( n.getNodeValue() );
                    if ( txt != null && !"".equals(txt) ) addBodyContent(txt);
                }
            }
            else if (n instanceof org.w3c.dom.Comment)
            {
                addComment( XmlFormatter.unescape(TextUtils.trim(n.getNodeValue())) );
            }
        }
    }

    public void addChild(Element child)
    {
        XmlTag tag = new XmlTag(this);
        try
        {
            tag.parse(child, FormatType.XML);
        }
        //XmlTag supports this...
        catch (UnsupportedFormatException ufe)
        {
            throw new RuntimeException("Could not parse: "+child, ufe);
        }
    }

    /**
     * Returns the first occurrence of an XmlTag by traversing the document
     * from top down.
     *
     * @param tag The tag name.
     * @return XmlTag
     */
    public XmlTag getFirstOccurrenceOf(String tag)
    {
        if ( getTagName().equalsIgnoreCase(tag) ) return this;
        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent child = getBodyContent(i);
            if (child instanceof XmlTag)
            {
                XmlTag ret = ( (XmlTag)child ).getFirstOccurrenceOf(tag);
                if (ret != null) return ret;
            }
        }
        return null;
    }

    /**
     * Returns the children of an XmlTag with the given tag name. This will look
     * only at the children of this tag.
     *
     * @param tag The tag name.
     * @return Vector
     */
    public Vector getChildrenWithName(String tag)
    {
        Vector ret = new Vector();
        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent child = getBodyContent(i);
            if (child instanceof XmlTag)
            {
                if ( ((XmlTag)child).getTagName().equalsIgnoreCase(tag) ) ret.add(child);
            }
        }
        return ret;
    }

    /**
     * Returns the children of an XmlTag with the given tag name. This will look
     * only at the children of this tag.
     *
     * @param tag The tag name.
     * @return Vector
     */
    public Vector<XmlTag> getChildren()
    {
        Vector<XmlTag> ret = new Vector<XmlTag>();
        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent child = getBodyContent(i);
            if (child instanceof XmlTag) ret.add((XmlTag)child);
        }
        return ret;
    }

    /**
     * Returns the children of an XmlTag with a tag name like the given tag name. This will
     * look only at the children of this tag.
     *
     * @param tag The tag name.
     * @return Vector
     */
    public Vector getChildrenWithNameLike(String tag)
    {
        Vector ret = new Vector();
        String tag2 = tag.toLowerCase();
        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent child = getBodyContent(i);
            if (child instanceof XmlTag)
            {
                String tag3 = ( (XmlTag)child ).getTagName().toLowerCase();
                if (tag3.indexOf(tag2) > -1) ret.add(child);
            }
        }
        return ret;
    }

    /**
     * Returns the value of the given child name.
     *
     * @param tag The child tag name.
     * @return String The value of the tag.
     */
    public String getChildValue(String tag)
    {
        XmlTag child = getFirstOccurrenceOf(tag);
        if (child != null) return child.getValue();
        else return null;
    }

    /**
     * Sets the value of the given child by name. If the child is not found, it
     * is added then set.
     *
     * @param tag The child tag name.
     * @param value The value of the tag.
     */
    public void setChildValue(String tag, String value)
    {
        XmlTag child = getFirstOccurrenceOf(tag);
        if (child == null) child = new XmlTag(tag, this);
        child.setValue(value);
    }

    /**
     * Returns the value of the contents of this tag as a String. This will only return
     * the TextContent in the markup body.
     *
     * @return String
     */
    public String getValue()
    {
        StringBuffer ret = new StringBuffer();
        int size = getBodySize();
        for (int i=0; i<size; i++)
        {
            MarkupContent child = getBodyContent(i);
            if (child instanceof CData)
            {
                ret.append( ((CData)child).getText() );
            }
            else if (child instanceof TextContent)
            {
                ret.append( child.toString() );
            }
        }
        return ret.toString().trim();
    }

    /**
     * Returns an attribute by name.
     *
     * @param name The name of the attribute.
     * @return String
     */
    public String getTagAttribute(String name)
    {
        return super.getAttributeValue(name);
    }

    /**
     * Sets the body of this markup content to be text. This will clear any
     * other markup content out that has been added, then set the body as
     * just the text. Use AddBodyContent if you want to keep what is in
     * there. If the text is null, then the body is cleared and nothing is
     * added.
     *
     * @param val The text to set.
     */
    public void setValue(String val)
    {
        clearContent();
        if (val != null) addBodyContent(val);
    }

    /**
     * Prepends the xml tag and any dtd if this is the root xml tag.
     *
     * @param type The format type.
     * @throws UnsupportedFormatException if the format is not xml.
     */
    protected String generateContent(FormatType type) throws UnsupportedFormatException
    {
        StringBuffer ret = new StringBuffer( super.generateContent(type) );
        if ( isRoot() )
        {
            if (_rootLine != null) ret.insert(0, _rootLine+"\r\n");
            else ret.insert(0, "<?xml version=\"1.0\"?>\r\n");
        }
        return ret.toString();
    }

    public String getStartTag(FormatType type) throws UnsupportedFormatException
    {
        StringBuffer ret = new StringBuffer();
        if ( hasChanged() )
        {
            if (getBodySize() == 0)
            {
                setIsOnOwnLine(true);
                ret.append( super.getStartTag(type) );
                //Insert a " /" before the last >
                ret.insert(ret.length()-1, " /");
            }
            else
            {
                if (getBodySize() == 1 && getFirstBodyContent() instanceof XmlTextContent) setIsOnOwnLine(true);
                ret.append( super.getStartTag(type) );
            }
        }
        else
        {
            ret.append( super.getStartTag(type) );
        }
        return ret.toString();
    }

    public String getEndTag(FormatType type) throws UnsupportedFormatException
    {
        String ret = null;
        if (hasChanged() && getBodySize() == 0)
        {
            boolean hasEndTag = hasEndTag();
            setHasEndTag(false);
            ret = super.getEndTag(type);
            setHasEndTag(hasEndTag);
        }
        else
        {
            ret = super.getEndTag(type);
        }
        return ret;
    }

    protected MarkupBody createMarkupBody()
    {
        return new XmlMarkupBody(this);
    }

    public TextContent createTextContent(String txt)
    {
        return new XmlTextContent(this, txt);
    }
}
