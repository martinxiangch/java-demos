package com.martin.pdfexport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
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

    /*
     * public static ByteArrayInputStream citiesReport(List<City> cities) {
     * 
     * Document document = new Document(); ByteArrayOutputStream out = new ByteArrayOutputStream();
     * 
     * try {
     * 
     * PdfPTable table = new PdfPTable(3); table.setWidthPercentage(60); table.setWidths(new
     * int[]{1, 3, 3});
     * 
     * Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
     * 
     * PdfPCell hcell; hcell = new PdfPCell(new Phrase("Id", headFont));
     * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
     * 
     * hcell = new PdfPCell(new Phrase("Name", headFont));
     * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
     * 
     * hcell = new PdfPCell(new Phrase("Population", headFont));
     * hcell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(hcell);
     * 
     * for (City city : cities) {
     * 
     * PdfPCell cell;
     * 
     * cell = new PdfPCell(new Phrase(city.getId().toString()));
     * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     * cell.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(cell);
     * 
     * cell = new PdfPCell(new Phrase(city.getName())); cell.setPaddingLeft(5);
     * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     * cell.setHorizontalAlignment(Element.ALIGN_LEFT); table.addCell(cell);
     * 
     * cell = new PdfPCell(new Phrase(String.valueOf(city.getPopulation())));
     * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
     * cell.setHorizontalAlignment(Element.ALIGN_RIGHT); cell.setPaddingRight(5);
     * table.addCell(cell); }
     * 
     * PdfWriter.getInstance(document, out); document.open(); document.add(table);
     * 
     * document.close();
     * 
     * } catch (DocumentException ex) {
     * 
     * Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex); }
     * 
     * return new ByteArrayInputStream(out.toByteArray()); }
     */


    public static ByteArrayInputStream WriteUsingiText() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            Paragraph p2 = new Paragraph();
            p2.add("This is my paragraph 2"); // no alignment
            document.add(p2);
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);
            document.add(new Paragraph("This is my paragraph 3", f));
            // close
            document.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return new ByteArrayInputStream(out.toByteArray());
    }


    public static ByteArrayInputStream getPdfmode2() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter writer=  PdfWriter.getInstance(document, out);
//            HeaderFooterPageEvent event=  new HeaderFooterPageEvent();
//            writer.setPageEvent(event);
            
           // writer.createXmpMetadata();
         //   writer.setTagged();
            writer.setPageEvent(new PdfPageHeader());
            
            document.setMargins(30, 30, 80, 30);
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);

            document.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    public static ByteArrayInputStream getPdfmode3() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter writer=  PdfWriter.getInstance(document, out);
            document.setPageSize(PageSize.A4);
            writer.createXmpMetadata();
            writer.setTagged();
            writer.setPageEvent(new PdfHeader());
            document.setMargins(30, 30, 80, 10);
           // document.setMargins(36, 36, 36, 36);
            
            document.open();
          
         // Left
            Paragraph paragraph = new Paragraph("This is right aligned text");
            paragraph.setAlignment(Element.ALIGN_RIGHT);
        
            document.add(paragraph);
            // Centered
            paragraph = new Paragraph("This is centered text");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            
            document.newPage();
            // Left
            paragraph = new Paragraph("This is left aligned text");
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);
            // Left with indentation
            paragraph = new Paragraph(
                    "This is left aligned text with indentation");
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(50);
            document.add(paragraph);
            document.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static void addMetaData(Document document) {
        document.addTitle("Martin demo");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Martin X");
        document.addCreator("Martin X");
    }

    private static void addTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Title of the document", maintitleFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph("This document describes something which is very important ",
                smallBold));

        addEmptyLine(preface, 8);

        preface.add(new Paragraph(
                "This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
                secondtitleFont));

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", secondtitleFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph( "Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", maintitleFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);

    }

    private static void createTable(Section subCatPart) throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");
        table.addCell("content 1");
        table.addCell("content 2");
        table.addCell("content 3");

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    // begin to init table
    private static PdfPTable addRWtitleTable() {
//        PdfPTable table=new PdfPTable(1);
//        PdfPCell cell1=new PdfPCell(new Paragraph("Run Date: Wednesday, October 19 2016 05:23:54 PM"));
//        cell1.setBorder(0);
//        table.addCell(cell1);
//        PdfPCell cell2=new PdfPCell(new Paragraph("Ver# 2.00      ID# 123"));
//        cell2.setBorder(0);
//        
//        table.addCell(cell2); 
//        PdfPCell cell3=new PdfPCell(new Paragraph("Page: 1 of 2"));
//        cell3.setBorder(0);
//        table.addCell(cell3); 
//        return table;
        
        final PdfPTable table1 = new PdfPTable(1);
        table1.getDefaultCell().setBorder(1);
        table1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.setWidthPercentage(100);
     // contents
        PdfPCell cell = new PdfPCell();
       // cell.setBorderColor(BaseColor.BLACK);
        cell.setBorderWidth(0);
        cell.addElement(new Chunk("Run Date: Wednesday, October 19 2016 05:23:54 PM"));
        cell.addElement(new Chunk("Ver# 2.00      ID# 123"));
        cell.addElement(new Chunk("Page: 1 of 2"));
        table1.addCell(cell);
        return table1;
    }
    
    private static PdfPTable addRWtitleTableMain() throws Exception {
        PdfPTable table=new PdfPTable(2);
        table.getDefaultCell().setBorder(0);
        table.setWidths(new float[] { 4, 1 });
        
        PdfPTable titletb=new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(0);
        cell.addElement(new Chunk("PA EZPAY 9.2 S2S+MAGCARD+G2S 64BIT"));
        cell.addElement(new Chunk("Mag Card Patron Account Activity Report - Detail"));
        cell.addElement(new Chunk("Reporting Period: Tuesday, October 18, 2016 03:00:00 PM to Thursday, October 20, 2016 02:59:59 PM"));
        titletb.addCell(cell);
//        PdfPCell cell1=new PdfPCell(new Paragraph("PA EZPAY 9.2 S2S+MAGCARD+G2S 64BIT"));
//        cell1.setBorder(0);
//        titletb.addCell(cell1);
//        PdfPCell cell2=new PdfPCell(new Paragraph("Mag Card Patron Account Activity Report - Detail"));
//        cell2.setBorder(0);
//        titletb.addCell(cell2); 
//        PdfPCell cell3=new PdfPCell(new Paragraph("Reporting Period: Tuesday, October 18, 2016 03:00:00 PM to Thursday, October 20, 2016 02:59:59 PM"));
//        cell3.setBorder(0);
//        titletb.addCell(cell3);  
        
        table.addCell(titletb);
        
        PdfPCell rightCell = new PdfPCell();
     //   rightCell.setBorderWidth(1);
        rightCell.addElement(addRWtitleTable());
        table.addCell(rightCell);
        return table;
    }
    
    
    public static ByteArrayInputStream getPdfmode4() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter writer=  PdfWriter.getInstance(document, out);
            document.setPageSize(PageSize.A4.rotate());
            writer.setPageEvent(new HeaderFooterPageEvent());
            document.setMargins(30, 30, 110, 10);
            document.open();
        
            document.add(addRWtitleTableMain()); 

            document.newPage();
            document.add(new Paragraph("Title2", secondtitleFont));
            ////Demo
            Anchor anchor = new Anchor("First Chapter", secondtitleFont);
            anchor.setName("First Chapter");
            Chapter catPart = new Chapter(new Paragraph(anchor), 1);
            Paragraph subPara = new Paragraph( "Subcategory 1", subFont);
            Section subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("Hello"));
            subPara = new Paragraph("Subcategory 2", subFont);
            subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("Paragraph 1"));
            subCatPart.add(new Paragraph("Paragraph 2"));
            subCatPart.add(new Paragraph("Paragraph 3"));
            // add a list
            
            document.newPage();
            document.add(new Paragraph("Title3", secondtitleFont));
            createList(subCatPart);
            Paragraph paragraph = new Paragraph();
            addEmptyLine(paragraph, 5);
            subCatPart.add(paragraph);
            // add a table
            
            document.newPage();
            createTable(subCatPart);
            // now add all this to the document
            document.add(catPart);
            ///end demo
            
            document.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    
}
