package com.zitego.markup.xml;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * This class is a utility class for converting xml text to java objects
 * and vice versa.
 *
 * @author John Glorioso
 * @version $Id: XmlUtils.java,v 1.2 2012/10/16 02:12:10 jglorioso Exp $
 */
public class XmlUtils
{
    /**
     * Converts the given string to a Document object. If the String is null
     * then null is returned. No validation is performed on the xml.
     *
     * @param String The xml.
     * @return Document
     * @throws SaxException when an error occurs in the xml content.
     * @throws IOException when an error occurs reading the content.
     * @throws ParserConfigurationException when an error occurs with the parser.
     */
    public static Document parseXml(String xml) throws SAXException, IOException, ParserConfigurationException
    {
        return parseXml(xml, false);
    }

    /**
     * Converts the given string to a Document object. If the String is null
     * then null is returned. If the validate flag is set to true, then it
     * validates the xml.
     *
     * @param String The xml.
     * @param boolean Whether or not to validate.
     * @return Document
     * @throws SaxException when an error occurs in the xml content.
     * @throws IOException when an error occurs reading the content.
     * @throws ParserConfigurationException when an error occurs with the parser.
     */
    public static Document parseXml(String xml, boolean validate) throws SAXException, IOException, ParserConfigurationException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(validate);
        if (!validate)
        {
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setFeature("http://xml.org/sax/features/validation", false);
        }
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource src = new InputSource( new StringReader(xml) );
        builder.setErrorHandler( new XmlErrorHandler() );
        return builder.parse(src);
    }

    /**
     * Returns the text value of the named node in the given Element. If the Element has no value,
     * then it returns null.
     *
     * @param String The name of the node value to return.
     * @param Element The element to get the named value from.
     * @return String
     */
    public static String getNodeValue(String nodeName, Element e)
    {
        String ret = null;
        if (e != null && nodeName != null)
        {
            NodeList nodes = e.getElementsByTagName(nodeName);
            if (nodes.getLength() > 0)
            {
                Node n = nodes.item(0).getFirstChild();
                if (n != null) ret = n.getNodeValue();
            }
        }
        return ret;
    }
}
