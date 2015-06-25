package com.zitego.markup.html.tag;

/**
 * A class that prints out a blank image tag. The only required parameter is the src.
 * A servlet can be used to print out a blank 1x1 pixel by creating a printwriter to
 * the response output stream and sending the bytes defined by PIXEL.
 *
 * @author John Glorioso
 * @version $Id: BlankImg.java,v 1.1.1.1 2008/02/20 15:01:12 jglorioso Exp $
 */
public class BlankImg extends Img
{
    /** This is the byte array to print out a blank 1x1 pixel. */
    public static final byte[] PIXEL =
    {
        (byte)0x47, (byte)0x49, (byte)0x46, (byte)0x38, (byte)0x39,
        (byte)0x61, (byte)0x01, (byte)0x00, (byte)0x01, (byte)0x00,
        (byte)0x91, (byte)0xFF, (byte)0x00, (byte)0xFF, (byte)0xFF,
        (byte)0xFF, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xC0,
        (byte)0xC0, (byte)0xC0, (byte)0x00, (byte)0x00, (byte)0x00,
        (byte)0x21, (byte)0xF9, (byte)0x04, (byte)0x01, (byte)0x00,
        (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x2C, (byte)0x00,
        (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00,
        (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x02,
        (byte)0x54, (byte)0x01, (byte)0x00, (byte)0x3B
    };

    /**
     * Creates a new BlankImg tag with no parent and a src.
     *
     * @param src The src.
     */
    public BlankImg(String src)
    {
        super();
        super.setSrc(src);
    }

    /**
     * Creates a new BlankImg tag with an HtmlMarkupTag parent and a src.
     *
     * @param parent The parent HtmlMarkupTag.
     * @param src The src.
     */
    public BlankImg(HtmlMarkupTag parent, String src)
    {
        super(parent);
        super.setSrc(src);
    }
}