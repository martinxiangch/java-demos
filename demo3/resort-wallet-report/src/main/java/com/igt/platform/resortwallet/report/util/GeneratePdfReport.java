package com.igt.platform.resortwallet.report.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReport {
    
    private static Font maintitleFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private static Font secondtitleFont =  new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
    private static Font thirdtitleFont =  new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
    
    
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
    public static ByteArrayInputStream getPdfFileStream() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.setPageSize(PageSize.A4.rotate());
            writer.setPageEvent(new HeaderFooterPageEvent());
            document.setMargins(30, 30, 110, 10);
            document.open();
            document.newPage();
            createTable(document);
            document.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    private static void createTable(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Index"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Employee Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Login Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1");
        table.addCell("Martin Xiang");
        table.addCell("Xiangc");
        table.addCell("2");
        table.addCell("Wen Shunji");
        table.addCell("Wens");
        table.addCell("3");
        table.addCell("Dong Hujun");
        table.addCell("dongh");

        document.add(table);
    }

}
