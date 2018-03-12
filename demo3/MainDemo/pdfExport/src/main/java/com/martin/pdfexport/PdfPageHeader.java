package com.martin.pdfexport;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfPageHeader extends PdfPageEventHelper {
    private int _pg = 0;
    private BaseFont font;
    private  PdfTemplate template=null;
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        _pg++;
        PdfContentByte cb = writer.getDirectContent();
        cb.beginText();
        try {
            Rectangle pageSize = document.getPageSize();
            cb.setFontAndSize(font, 8);
            cb.setTextMatrix(pageSize.getLeft(40), pageSize.getBottom(15));
            String s = "Page " + _pg + "/";
            cb.showText(s);
            cb.addTemplate(template, pageSize.getLeft(40) + font.getWidthPoint(s, 8), pageSize.getBottom(15));
        } catch (Exception exc) {
          //  logger.warn("got Exception : " + exc.getMessage());
        }
        cb.endText();
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        super.onOpenDocument(writer, document);
        template = writer.getDirectContent().createTemplate(50, 50);
        try {
            font = BaseFont.createFont(BaseFont.COURIER, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        } catch (Exception exc) {
          //  logger.warn("got Exception : " + exc.getMessage());
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
          //  logger.error(ex);
        }
        template.endText();
    }
}
