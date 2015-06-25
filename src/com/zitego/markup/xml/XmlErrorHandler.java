package com.zitego.markup.xml;

import org.xml.sax.*;

/**
 * A generic class to handle parsing xml documents.
 *
 * @author John Glorioso
 * @version $Id: XmlErrorHandler.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class XmlErrorHandler implements ErrorHandler
{
    /**
     * Creates a new error handler.
     */
    public XmlErrorHandler()
    {
        super();
    }

    /**
     * Throws a SAXException with the line number and message of the error.
     *
     * @param SAXParseException The error.
     * @throws SAXException
     */
    public void fatalError(SAXParseException err) throws SAXException
    {
        throw new SAXException( "Fatal Error at line " + err.getLineNumber() + ": " + err.getMessage() );
    }

    /**
     * Throws a SAXException with the line number and message of the error.
     *
     * @param SAXParseException The error.
     * @throws SAXException
     */
    public void error(SAXParseException err) throws SAXException
    {
        throw new SAXException("Error at line " + err.getLineNumber() + ": " + err.getMessage() );
    }

    /**
     * Throws a SAXException with the line number and message of the warning.
     *
     * @param SAXParseException The error.
     * @throws SAXException
     */
    public void warning(SAXParseException err) throws SAXException
    {
        throw new SAXException("Warning at line " + err.getLineNumber() + ": " + err.getMessage() );
    }
}