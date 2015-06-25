package com.zitego.markup.xml;

import com.zitego.markup.MarkupConverter;
import org.w3c.dom.Element;

/**
 * This interface defines how to build objects from an XML string.
 *
 * @author John Glorioso
 * @version $Id: XmlConverter.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public interface XmlConverter extends MarkupConverter
{
    /**
     * Builds this object from the given xml content String.
     *
     * @param Element The xml content.
     */
    public void buildFromXml(Element root);

    /**
     * Adds a child xml component.
     *
     * @param Element The xml content.
     */
    public void addChild(Element child);
}