package com.zitego.markup.xml;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This is a class that will convert a standard xml document into a properties object. The items in the document
 * will be keyed in the properties object by way of their tags. For example, consider the following document:<br>
 * <pre>
 * &lt;customer&gt;
 *  &lt;name&gt;
 *   &lt;first&gt;Johnny&lt;/first&gt;
 *   &lt;last&gt;G&lt;/last&gt;
 *  &lt;/name&gt;
 *  &lt;address&gt;
 *   &lt;address1&gt;1234 Fake Street&lt;/address1&gt;
 *   &lt;address2&gt;&lt;/address2&gt;
 *   &lt;city&gt;Springfield&lt;/city&gt;
 *   &lt;state&gt;Ohio&lt;/state&gt;
 *   &lt;zipcode&gt;12345&lt;/zipcode&gt;
 *  &lt;address&gt;
 *  &lt;ss-num&gt;123-12-1234&lt;/ss-num&gt;
 * &lt;/customer&gt;
 * </pre>
 *
 * @author John Glorioso
 * @version $Id: XmlProperties.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class XmlProperties extends Properties
{
    private StringBuffer _buffer = new StringBuffer();

    public static void main(String[] args) throws Exception
    {
        XmlProperties p = new XmlProperties();
        p.setProperty("delivery.type", "1");
        p.setProperty("delivery.subject", "test subject");
        p.setProperty("delivery.to", "me@here.com");
        System.out.println( "\n"+p.getAsString() );
    }

    /**
     * Creates a new xml properties object.
     */
    public XmlProperties() { }

    /**
     * Loads the properties from a file.
     *
     * @param file The file to load.
     * @throws IOException if an error occurs reading the file.
     */
    public void load(File file) throws IOException
    {
        load( new BufferedInputStream(new FileInputStream(file)) );
    }

    /**
     * Loads the properties from an xml string.
     *
     * @param xml The xml to load.
     * @throws IOException if an error occurs reading the xml.
     */
    public void load(String xml) throws IOException
    {
        load( new ByteArrayInputStream(xml.getBytes()) );
    }

    /**
     * Loads the properties from an input stream.
     *
     * @param in The input stream to load from.
     * @throws IOException if an error occurs reading the stream.
     */
    public void load(InputStream in) throws IOException
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);

            Document document = factory.newDocumentBuilder().parse(in);

            loadElements( document.getChildNodes() );
        }
        catch (Exception e)
        {
            throw new IOException( e.getMessage() );
        }
    }

    /**
     * Returns the properties as an xml string.
     *
     * @throws IOException if an error occurs writing the xml out.
     */
    public synchronized String getAsString() throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        store(out);
        return out.toString().trim();
    }

    /**
     * Stores the properties to an output stream.
     *
     * @param out The output stream to write to.
     * @throws IOException if an error occurs writing to the stream.
     */
    public synchronized void store(OutputStream out) throws IOException
    {
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(out) );

        List keyList = new LinkedList( keySet() );
        Collections.sort(keyList);
        Hashtable store = new Hashtable();
        for (Iterator i = keyList.iterator(); i.hasNext();)
        {
            String key = (String)i.next();
            String val = (String)get(key);
            storeIn(store, key, val);
        }
        writeTo(writer, store, 0);
        writer.flush();
    }

    private void storeIn(Hashtable store, String name, String val)
    {
        int index = name.indexOf(".");
        if (index > -1)
        {
            String key = name.substring(0, index);
            name = name.substring(index+1);
            Hashtable store2 = (Hashtable)store.get(key);
            if (store2 == null) store2 = new Hashtable();
            store.put(key, store2);
            storeIn(store2, name, val);
        }
        else
        {
            store.put(name, val);
        }
    }

    private void writeTo(BufferedWriter writer, Hashtable store, int depth) throws IOException
    {
        StringBuffer depthString = new StringBuffer();
        for (int i=0; i<depth; i++)
        {
            depthString.append(" ");
        }
        for (Enumeration e=store.keys(); e.hasMoreElements();)
        {
            String key = (String)e.nextElement();
            writer.write(depthString+"<"+key+">");
            Object val = store.get(key);
            if (val instanceof Hashtable)
            {
                writer.newLine();
                writeTo( writer, (Hashtable)val, depth+1 );
                writer.write( depthString.toString() );
            }
            else
            {
                writer.write( (String)val );
            }
            writer.write("</"+key+">");
            writer.newLine();
        }
    }

    private void addElement(String name)
    {
        if (_buffer.length() > 0) _buffer.append(".");
        _buffer.append(name);
    }

    private void loadAttributes(NamedNodeMap attributes)
    {
        int len = attributes.getLength();
        for (int i=0; i<len; i++)
        {
            Attr attribute = (Attr)attributes.item(i);
            if (attribute != null)
            {
                addElement( attribute.getName() );
                setProperty( _buffer.toString(), attribute.getValue() );
                removeLastElement();
            }
        }
    }

    private void loadElements(NodeList elements)
    {
        for (int i=0; i<elements.getLength(); i++)
        {
            Node current = elements.item(i);

            // ignore text nodes that are whitespace
            if ( !(current.getNodeType() == Node.TEXT_NODE && "".equals(current.getNodeValue().trim())) )
            {
                String name =  current.getNodeName();
                // add name of current element to key buffer
                if (current.getNodeType() == Node.ELEMENT_NODE) addElement(name);
                if ( current.hasAttributes() ) loadAttributes( current.getAttributes() );

                String value = current.getNodeValue();
                if ( value == null || "".equals(value.trim()) )
                {
                    if ( current.hasChildNodes() ) loadElements( current.getChildNodes() );
                }
                else
                {
                    // add value to the properties
                    setProperty(_buffer.toString(), value);
                }
                if (current.getNodeType() != Node.TEXT_NODE)  removeLastElement();
            }
        }
    }

    private void removeLastElement()
    {
        int index = _buffer.lastIndexOf(".");
        int start = (index != -1 ? index : 0);
        _buffer.delete( start, _buffer.length() );
    }
}