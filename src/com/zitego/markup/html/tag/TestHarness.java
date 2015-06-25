package com.zitego.markup.html.tag;

import com.zitego.markup.TextContent;
import com.zitego.format.FormatType;
import com.zitego.markup.tag.SpecialChar;
import com.zitego.markup.html.StyleDeclaration;
import com.zitego.markup.html.tag.table.*;
import com.zitego.markup.html.tag.textEffect.*;
import com.zitego.markup.html.tag.block.*;

public class TestHarness
{
    public static void main(String[] args) throws Exception
    {
        //System.setProperty("debug", "1");
        Html page = new Html();
        Head head = page.getHead();
        head.setTitle("penWrights.com Newsletter - Volume 2 Issue 9 - September 2003");
        Style style = head.getStyleTag();
        StyleDeclaration tdStyle = style.createStyleDeclaration("td", "header");
        tdStyle.setProperty("background-color", "#D4D0C8");
        tdStyle.setProperty("border-style", "solid");
        tdStyle.setProperty("border-width", "1px");
        tdStyle.setProperty("border-color", "#808080");
        tdStyle.setProperty("border-left-color", "#FFFFFF");
        tdStyle.setProperty("border-top-color", "#FFFFFF");

        Body body = page.getBodyTag();
        body.setAttributes
        (
            new String[] { "marginheight=0", "marginwidth=0", "style=font-family:Verdana", "bgcolor=#000000" }
        );

        Table table = body.createTable();
        table.setAttributes( new String[] { "cellpadding=3", "cellspacing=3", "border=0", "align=center" } );
        Tr row = table.createRow();

        Td cell = row.createCell();
        cell.setIsOnOwnLine(true);
        Img image = cell.createImage("http://penWrights.com/images/pwlogo.gif");

        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        image = cell.createImage("http://penWrights.com/images/blank.gif");
        image.setWidth("100");

        cell = row.createCell();
        cell.setAlign("center");
        TextEffect bold = cell.createTextEffect(TextEffectType.BOLD);
        Font f = (Font)bold.createTextEffect(TextEffectType.FONT);
        f.setColor("#ffffff");
        Font f2 = (Font)f.createTextEffect(TextEffectType.FONT);
        f2.setIsOnOwnLine(true);
        f2.setSize("+1");
        f2.addText("Monthly Newsletter");
        f2.addLineBreak();
        TextContent c = f.createTextContent("Volume 2, Issue 9");
        c.setHasNewline(false);
        c.addLineBreak();
        f2 = (Font)f.createTextEffect(TextEffectType.FONT);
        f2.setIsOnOwnLine(true);
        f2.setSize("-1");
        f2.addText("September 2003");

        Div div = (Div)body.createBlockFormat(BlockFormatType.DIV);
        div.setAlign("center");
        f = (Font)div.createTextEffect(TextEffectType.FONT);
        f.setSize("-1"); f.setColor("#ffffff");
        Anchor a = f.createLink("#word");
        a.setIsOnOwnLine(true);
        a.setHasNewline(false);
        f2 = (Font)a.createTextEffect(TextEffectType.FONT);
        f2.setColor("#ffffff");
        f2.addText("Word From Zuul");
        c = f.createTextContent(SpecialChar.NBSP+"|");
        c.setHasPadding(false);
        a = f.createLink("#column");
        a.setIsOnOwnLine(true);
        a.setHasNewline(false);
        f2 = (Font)a.createTextEffect(TextEffectType.FONT);
        f2.setColor("#ffffff");
        f2.addText("Member Column");
        c = f.createTextContent(SpecialChar.NBSP+"|");
        c.setHasPadding(false);
        a = f.createLink("#spotlight");
        a.setIsOnOwnLine(true);
        a.setHasNewline(false);
        f2 = (Font)a.createTextEffect(TextEffectType.FONT);
        f2.setColor("#ffffff");
        f2.addText("Member Spotlight");
        c = f.createTextContent(SpecialChar.NBSP+"|");
        c.setHasPadding(false);
        a = f.createLink("#poem");
        a.setIsOnOwnLine(true);
        a.setHasNewline(false);
        f2 = (Font)a.createTextEffect(TextEffectType.FONT);
        f2.setColor("#ffffff");
        f2.addText("Random Poem of Month");
        c = f.createTextContent(SpecialChar.NBSP+"|");
        c.setHasPadding(false);
        a = f.createLink("#members");
        a.setIsOnOwnLine(true);
        a.setHasNewline(false);
        f2 = (Font)a.createTextEffect(TextEffectType.FONT);
        f2.setColor("#ffffff");
        f2.addText("New Members");
        c = f.createTextContent(SpecialChar.NBSP+"|");
        c.setHasPadding(false);
        a = f.createLink("#tip");
        a.setIsOnOwnLine(true);
        a.setHasNewline(false);
        f2 = (Font)a.createTextEffect(TextEffectType.FONT);
        f2.setColor("#ffffff");
        f2.addText("Monthly Tip");

        HR hr = body.createHR();
        hr.setWidth("95%");

        table = body.createTable();
        table.setAttributes( new String[] { "cellpadding=0", "cellspacing=0", "border=0", "align=center", "width=95%" } );

        row = table.createRow();
        row.setIsOnOwnLine(true);
        cell = row.createCell();
        cell.setColspan(4);
        image = cell.createImage("http://penWrights.com/images/blank.gif");
        image.setHeight(3);

        row = table.createRow();
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        cell.setBgColor("#000000");
        image = cell.createImage("http://penWrights.com/images/blank.gif");
        image.setWidth(5);
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        image = cell.createImage("http://penWrights.com/images/blank.gif");
        image.setWidth(3);
        cell = row.createCell();
        Table t2 = cell.createTable();
        t2.setAttributes( new String[] { "border=0", "cellspacing=0", "cellpadding=0", "align=center", "bgcolor=#edefef", "width=100%" } );
        Tr r2 = t2.createRow();
        Td c2 = r2.createCell();
        c2.setIsOnOwnLine(true);
        Img i2 = c2.createImage("http://penWrights.com/images/top_left_body_corner.gif");
        i2.setWidth(5);
        i2.setHeight(5);
        c2 = r2.createCell();
        c2.setIsOnOwnLine(true);
        c2.setColspan(3);
        c2.setBgColor("#edefef");
        i2 = c2.createImage("http://penWrights.com/images/blank.gif");
        i2.setHeight(5);
        c2 = r2.createCell();
        c2.setAlign("right");
        c2.setIsOnOwnLine(true);
        i2 = c2.createImage("http://penWrights.com/images/top_right_body_corner.gif");
        i2.setWidth(5);
        i2.setHeight(5);
        r2 = t2.createRow();
        c2 = r2.createCell();
        c2.setBgColor("#edefef");
        c2.setIsOnOwnLine(true);
        i2 = c2.createImage("http://penWrights.com/images/blank.gif");
        i2.setWidth(5);
        c2 = r2.createCell();
        c2.setColspan(3);
        a = c2.createAnchor("#word");
        bold = (Bold)a.createTextEffect(TextEffectType.BOLD);
        bold.setIsOnOwnLine(true);
        bold.createTextEffect(TextEffectType.UNDERLINE).addText("A Word From Zuul:");
        f = (Font)c2.createTextEffect(TextEffectType.FONT);
        f.setSize("-1");
        Paragraph p = (Paragraph)f.createBlockFormat(BlockFormatType.PARAGRAPH);
        p.addText("Well, its September already. The days are getting shorter and colder and soon the leaves"+
                  "will be falling (for some of us at least). Kids are back in school thankfully and I am"+
                  "now home from work for a while. I am only three days into it now, but I am getting a lot"+
                  "done and I really love it. I can't imagine going back to work, but eventually I will need"+
                  "to earn some money again. Sigh. For now though, I am enjoying every minute of it.");
        p = (Paragraph)f.createBlockFormat(BlockFormatType.PARAGRAPH);
        p.addText("I don't really have a lot to say this month. As you have noticed, the server has been a");
        p.addText("real jerk lately. Unfortunately, I am having some problems figuring out what is going "+
                  "wrong, so you will have to bear with me until I figure it out. In the short term, I am"+
                  "going to put something into place that will monitor how long it takes to retrieve a page"+
                  "and if it takes long, I will have it email me and automatically restart. This will cause"+
                  "you to see the \"site is down\" message off and on, but at least there will not be long periods"+
                  "of time where all you can do is chat.");
        c2 = r2.createCell();
        c2.setIsOnOwnLine(true);
        c2.setBgColor("#edefef");
        i2 = c2.createImage("http://penWrights.com/images/blank.gif");
        i2.setWidth(5);
        r2 = t2.createRow();
        c2 = r2.createCell();
        c2.setIsOnOwnLine(true);
        i2 = c2.createImage("http://penWrights.com/images/bottom_left_body_corner.gif");
        i2.setWidth(5);
        i2.setHeight(5);
        c2 = r2.createCell();
        c2.setColspan(3);
        c2.setBgColor("#edefef");
        c2.setIsOnOwnLine(true);
        i2 = c2.createImage("http://penWrights.com/images/blank.gif");
        i2.setHeight(5);
        c2 = r2.createCell();
        c2.setAlign("right");
        c2.setIsOnOwnLine(true);
        i2 = c2.createImage("http://penWrights.com/images/bottom_right_body_corner.gif");
        i2.setWidth(5);
        i2.setHeight(5);
        cell = row.createCell();
        cell.setIsOnOwnLine(true);
        image = cell.createImage("http://penWrights.com/images/blank.gif");
        image.setWidth(5);

        row = table.createRow();
        cell = row.createCell();
        cell.setColspan(4);
        cell.setIsOnOwnLine(true);
        image = cell.createImage("http://penWrights.com/images/blank.gif");
        image.setHeight(3);

        row = table.createRow();
        cell = row.createCell();
        cell.setColspan(4);
        cell.setBgColor("#000000");
        cell.setIsOnOwnLine(true);
        image = cell.createImage("http://penWrights.com/images/blank.gif");
        image.setHeight(5);

        System.out.println("First pass:");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println( page.format(FormatType.HTML) );
        System.out.println("--------------------------------------------------------------------------------");
        /*System.out.println("\n\nSecond pass:");
        body.setBgColor("#ffffff");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println( page.format(FormatType.HTML) );
        System.out.println("--------------------------------------------------------------------------------");*/
    }
}