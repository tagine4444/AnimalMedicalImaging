package com.bouacheria.ami.service.cases;

import java.awt.Color;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.cases.Case;
import com.bouacheria.ami.service.emails.EmailService;
import com.lowagie.text.Anchor;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfGenerator {

	
	@Autowired
	private EmailService emailService;
	
	private static String FILE = "c:/temp/FirstPdf.pdf";
	  private static Font catFont = new Font(Font.TIMES_ROMAN, 18,
	      Font.BOLD);
	  private static Font redFont = new Font(Font.TIMES_ROMAN, 12,
	      Font.NORMAL, Color.RED);
	  private static Font smallFont = new Font(Font.TIMES_ROMAN, 12,
			  Font.NORMAL, Color.BLACK);
	  private static Font subFont = new Font(Font.TIMES_ROMAN, 16,
	      Font.BOLD);
	  private static Font smallBold = new Font(Font.TIMES_ROMAN, 12,
	      Font.BOLD);
	  
	  private static Font smallUnderline = new Font(Font.TIMES_ROMAN, 12,
			  Font.UNDERLINE);

	  public static void main(String[] args) {
	    
	  }
	  
	  public void emailPdf(Case aCase, String from, String to, String subject, String msg)
	  {
		  try {
		      Document document = new Document();
		      PdfWriter.getInstance(document, new FileOutputStream(FILE));
		      document.open();
		      addMetaData(document,aCase);
		      addTitlePage(document,aCase);
		      //addContent(document);
		      document.close();
		      
		      
		      FileSystemResource inputStreamSrc = new FileSystemResource(FILE);
		      emailService.sendMailWithAttachement(from, to, subject, msg, inputStreamSrc, "AMI Report Request_" + aCase.getRequestNumber() +".pdf");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	  }

	  // iText allows to add metadata to the PDF which can be viewed in your Adobe
	  // Reader
	  // under File -> Properties
	  private static void addMetaData(Document document, Case aCase) {
	    document.addTitle("Animal Medical Imaging Report");
	    document.addSubject(aCase.getPatientName());
	  }

	  private static void addTitlePage(Document document, Case aCase)
	      throws DocumentException {
	    Paragraph preface = new Paragraph();
	    // We add one empty line
	    addEmptyLine(preface, 1);
	    // Lets write a big header
	    preface.add(new Paragraph("Animal Medical Imaging", catFont));
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("First Place West Bothell, WA  98021",smallFont));
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("Phone: 425-419-4220 or 800-888-0197 Fax: 425-949-8143",smallFont));
	    
	    

	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("RADIOGRAPHIC INTERPRETATION REPORT ",  redFont));
	    
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("Requesting Veterinarian: " ,  smallBold));
	    preface.add(new Paragraph(aCase.getVeterinarian(),  smallFont));
	    
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("Patient: " ,  smallBold));
	    preface.add(new Paragraph(aCase.getPatientName() + " " + aCase.getPatientBreed() + " "+aCase.getPatientSpecies(),  smallFont));

	    
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("Exam",  smallUnderline));
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph(aCase.getConsultation(),  smallFont));
	    
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("Tentative Diagnosis",  smallUnderline));
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph(aCase.getTentativeDiagnosis(),  smallFont));
	    
	    
	    if( ! aCase.isUnderContract())
	    {
	    	addEmptyLine(preface, 1);
	 	    preface.add(new Paragraph("Radiographic Interpreatation",  smallUnderline));
	 	    addEmptyLine(preface, 1);
	 	    preface.add(new Paragraph(aCase.getRadioInterpretation(),  smallFont));
	    }
	    
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("Radiographic Impression",  smallUnderline));
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph(aCase.getRadioImpression(),  smallFont));
	    
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph("Recommendation",  smallUnderline));
	    addEmptyLine(preface, 1);
	    preface.add(new Paragraph(aCase.getRecommendations(),  smallFont));
	    
	    document.add(preface);
	    // Start a new page
	    //document.newPage();
	  }

	  private static void addContent(Document document) throws DocumentException {
	    Anchor anchor = new Anchor("First Chapter", catFont);
	    anchor.setName("First Chapter");

	    // Second parameter is the number of the chapter
	    Chapter catPart = new Chapter(new Paragraph(anchor), 1);

	    Paragraph subPara = new Paragraph("Subcategory 1", subFont);
	    Section subCatPart = catPart.addSection(subPara);
	    subCatPart.add(new Paragraph("Hello"));

	    subPara = new Paragraph("Subcategory 2", subFont);
	    subCatPart = catPart.addSection(subPara);
	    subCatPart.add(new Paragraph("Paragraph 1"));
	    subCatPart.add(new Paragraph("Paragraph 2"));
	    subCatPart.add(new Paragraph("Paragraph 3"));

	    // Add a list
	    createList(subCatPart);
	    Paragraph paragraph = new Paragraph();
	    addEmptyLine(paragraph, 5);
	    subCatPart.add(paragraph);

	    // Add a table
	    createTable(subCatPart);

	    // Now add all this to the document
	    document.add(catPart);

	    // Next section
	    anchor = new Anchor("Second Chapter", catFont);
	    anchor.setName("Second Chapter");

	    // Second parameter is the number of the chapter
	    catPart = new Chapter(new Paragraph(anchor), 1);

	    subPara = new Paragraph("Subcategory", subFont);
	    subCatPart = catPart.addSection(subPara);
	    subCatPart.add(new Paragraph("This is a very important message"));

	    // Now add all this to the document
	    document.add(catPart);

	  }

	  private static void createTable(Section subCatPart)
	      throws BadElementException {
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

	    subCatPart.add(table);

	  }

	  private static void createList(Section subCatPart) {
		  List list = new List(true, false, 10);
		    list.add(new ListItem("First point"));
		    list.add(new ListItem("Second point"));
		    list.add(new ListItem("Third point"));
		    subCatPart.add(list);
	    subCatPart.add(list);
	  }

	  private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
}
