package com.igt.platform.resortwallet.report.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.io.ClassPathResource;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent  extends PdfPageEventHelper {
    private static Font maintitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static Font secondtitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
    private static Font thirdtitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

    private static Font footborderFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLDITALIC);
    private static Font footnormalFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
    private static PdfTemplate template = null;
    private static BaseFont font;
    private static String fontfilePath="/fonts/arial.ttf";

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        super.onOpenDocument(writer, document);
        template = writer.getDirectContent().createTemplate(50, 50);
        try {
            initFont();
            ClassPathResource imgFile = new ClassPathResource("static/font/arial.ttf");
            fontfilePath = imgFile.getPath();
            font = BaseFont.createFont(fontfilePath, BaseFont.WINANSI, BaseFont.EMBEDDED);

        } catch (Exception exc) {
            // logger.warn("got Exception : " + exc.getMessage());
        }
    }


    private void initFont() {
        try {
            Font font = FontFactory.getFont(fontfilePath, BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED, 0.8f, Font.NORMAL, BaseColor.BLACK);
            BaseFont basefont = font.getBaseFont();

            maintitleFont = new Font(basefont, 12, Font.BOLD);
            secondtitleFont = new Font(basefont, 10, Font.NORMAL);
            thirdtitleFont = new Font(basefont, 8, Font.NORMAL);
            footborderFont = new Font(basefont, 9, Font.ITALIC);
            footnormalFont = new Font(basefont, 8, Font.NORMAL);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        try {

            addTemplate(writer, document, writer.getPageNumber());
            PdfPTable table = addRWtitleTableMain();
            table.setTotalWidth(Math.abs(document.left() - document.right()));
            table.setLockedWidth(true);
            table.writeSelectedRows(0, -1, document.left(),
                    document.top() + ((document.topMargin() + table.getTotalHeight()) / 2 - 10),
                    writer.getDirectContent());


            PdfPTable footer = addFooter();
            footer.setTotalWidth(Math.abs(document.left() - document.right()));
            footer.setLockedWidth(true);
            footer.writeSelectedRows(0, -1, document.left(), document.bottom() + 50,
                    writer.getDirectContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        super.onCloseDocument(writer, document);
        template.beginText();
        try {
            template.setFontAndSize(font, 8);
            template.setTextMatrix(0f, 0f);
            template.showText("" + (writer.getPageNumber()));
        } catch (Exception ex) {

        }
        template.endText();
    }


    static SimpleDateFormat dateformate =
            new SimpleDateFormat("EEEEEEEEE, MMMMMMM dd, yyyy hh:mm:ss a");

    // begin to init table
    private static PdfPTable addRWtitleTable() {
        final PdfPTable table1 = new PdfPTable(1);
        table1.getDefaultCell().setBorder(1);
        table1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.setWidthPercentage(100);
        // contents
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(0);

        cell.addElement(
                new Chunk("Run Date: " + dateformate.format(new Date()), thirdtitleFont));
        cell.addElement(
                new Chunk("Ver# 2.00                            ID# 123", thirdtitleFont));
        cell.addElement(new Chunk(String.format(""), thirdtitleFont));
        table1.addCell(cell);
        return table1;
    }


    private static void addTemplate(PdfWriter writer, Document document, int pageindex) {
        PdfContentByte cb = writer.getDirectContent();
        cb.beginText();
        try {
            Rectangle pageSize = document.getPageSize();
            cb.setFontAndSize(font, 8);
            cb.setTextMatrix(pageSize.getRight(182), pageSize.getTop(85));
            String s = String.format("Page: %d of ", pageindex);
            cb.showText(s);
            cb.addTemplate(template, pageSize.getRight(182) + font.getWidthPoint(s, 8),
                    pageSize.getTop(85));
        } catch (Exception exc) {

        }
        cb.endText();

    }

    private static PdfPTable addRWtitleTableMain() throws Exception {
        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(0);
        table.setWidths(new float[] {4, 1});

        PdfPTable titletb = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(0);
        cell.addElement(new Chunk("PA EZPAY 9.2 S2S+MAGCARD+G2S 64BIT", maintitleFont));
        cell.addElement(
                new Chunk("Mag Card Patron Account Activity Report - Detail", maintitleFont));
        cell.addElement(new Chunk(
                "Reporting Period: Tuesday, October 18, 2016 03:00:00 PM to Thursday, October 20, 2016 02:59:59 PM",
                secondtitleFont));
        titletb.addCell(cell);
        table.addCell(titletb);

        PdfPCell rightCell = new PdfPCell();
        rightCell.addElement(addRWtitleTable());
        table.addCell(rightCell);


        PdfPCell lineCell = new PdfPCell();
        lineCell.setFixedHeight(5);
        lineCell.setColspan(2);
        lineCell.setBorder(Rectangle.BOTTOM);
        lineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(lineCell);

        return table;
    }


    private static PdfPTable addFooter() {
        PdfPTable footerTb = new PdfPTable(3);
        try {
            footerTb.setWidths(new float[] {5, 5, 2});
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PdfPCell cell1 = new PdfPCell();
        cell1.setRowspan(3);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell1.setBorder(Rectangle.TOP);
        try {
            ClassPathResource imgFile = new ClassPathResource("static/images/igtLogoBlack.png");
            Image image = Image.getInstance(imgFile.getURL());
            cell1.setImage(image);
            footerTb.addCell(cell1);
        } catch (BadElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(Rectangle.TOP);
        cell2.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        // cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.addElement(new Chunk("***CONFIDENTIAL***", footborderFont));
        cell2.addElement(new Chunk(""));
        cell2.addElement(new Chunk(""));
        footerTb.addCell(cell2);

        PdfPCell cell3 = new PdfPCell();
        cell3.setBorder(Rectangle.TOP);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.addElement(new Chunk("Version: EZ Pay 9.2.2698.0400", footnormalFont));
        cell3.addElement(new Chunk("Copyright 2016 IGT Systems", footnormalFont));
        cell3.addElement(new Chunk(""));
        footerTb.addCell(cell3);

        return footerTb;
    }
}