package com.zitego.markup.xml;

import com.zitego.markup.TextContent;
import com.zitego.format.*;
import com.zitego.util.TextUtils;
import java.util.Vector;

/**
 * This is a class to represent a plain text xml text component. All this does
 * is escape the text to be displayed in an xml document.
 *
 * @author John Glorioso
 * @version $Id: XmlTextContent.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class XmlTextContent extends TextContent
{
    /**
     * Creates new text content with a an xml parent and some text.
     *
     * @param XmlTag The parent content.
     * @param String The text.
     */
    public XmlTextContent(XmlTag parent, String text)
    {
        super(parent, text);
    }

    /**
     * Unescapes any xml formatting before appending it.
     *
     * @param String The text.
     */
    public void appendText(String txt)
    {
        if (txt != null) super.appendText( XmlFormatter.unescape(txt) );
    }

    /**
     * Returns the text escaped to appear in an xml document.
     *
     * @return String
     */
    public String getText()
    {
        return XmlFormatter.escape( super.getText() );
    }

    /**
     * Returns the text unescaped.
     *
     * @return String
     */
    public String getUnescapedText()
    {
        return super.getText();
    }
}