package com.bouacheria.ami.service.cases;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.cases.Case;
import com.bouacheria.ami.repository.config.ConfigUtil;
import com.bouacheria.ami.service.emails.EmailService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfGenerator {

	
	 private static Font catFont = new Font(Font.TIMES_ROMAN, 18,
		      Font.BOLD);
	 
//	  private static Font redFont = new Font(Font.TIMES_ROMAN, 12,
//	      Font.NORMAL, Color.RED);
//	  
//	  private static Font titleFont = new Font(Font.TIMES_ROMAN, 12,
//			  Font.NORMAL, Color.BLACK);
	  
	  private static Font titleFontBold = new Font(Font.TIMES_ROMAN, 12,
			  Font.BOLD, Color.BLACK);
	  
	  private static Font smallFont9 = new Font(Font.TIMES_ROMAN, 9,
			  Font.NORMAL, Color.BLACK);
	  
	  private static Font smallFont7 = new Font(Font.TIMES_ROMAN, 7,
			  Font.NORMAL, Color.BLACK);
	  
	  private static Font smallFont7Bold = new Font(Font.TIMES_ROMAN, 7,
			  Font.BOLD, Color.BLACK);
	  
	  
//	  private static Font subFont = new Font(Font.TIMES_ROMAN, 16,
//	      Font.BOLD);
//	  private static Font smallBold = new Font(Font.TIMES_ROMAN, 12,
//	      Font.BOLD);
	  
	  private static Font smallUnderline = new Font(Font.TIMES_ROMAN, 12,
			  Font.UNDERLINE);
	  
		  
	@Autowired
	private EmailService emailService;

	@Autowired
	private ConfigUtil config;

	
	  
	  public String getPdfFileNameOnly(String caseNumber)
	  {
		  return  "AMI_Report_Request_" + caseNumber +".pdf";
	  }
	  public String getPdfFileWithPath(String caseNumber)
	  {
		  return config.getPdfFilesPath() + File.separatorChar + getPdfFileNameOnly(caseNumber);
	  }
	  
	  
	  
	  public FileSystemResource getPdf(Case aCase) throws FileNotFoundException, DocumentException
	  {		
		  
		  final String pdfFile = getPdfFileWithPath(aCase.getRequestNumber()+ System.currentTimeMillis());
	  	  Document document = new Document();
	      PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
	      document.open();
	      
	      addMetaData(document,aCase);
	      addContent(document,aCase);
	      document.close();
	      
	      FileSystemResource inputStreamSrc = new FileSystemResource(pdfFile);
	      
	      //saveToDisk(inputStreamSrc);
	      return inputStreamSrc;
	  }
	private void saveToDisk(FileSystemResource inputStreamSrc) {
		try {
	    	  
	    	  FileInputStream in = new FileInputStream(inputStreamSrc.getFile());
	 	      FileOutputStream fos = new FileOutputStream(inputStreamSrc.getFilename() );
	 	      int c;

	           while ((c = in.read()) != -1) {
	        	   fos.write(c);
	           }
	 	      
			
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	  public void emailPdf(Case aCase, String from, String to, String subject, String msg)
	  {		
		  
		  final String pdfFileNameOnly = getPdfFileNameOnly(aCase.getRequestNumber());
		  
		  try 
		  {
			  	FileSystemResource inputStreamSrc = getPdf(aCase);
			  	emailService.sendMailWithAttachement(from, to, subject, msg, inputStreamSrc, pdfFileNameOnly);
		  } 
		  catch (Exception e) 
		  {
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

	  
	  private static PdfPCell getPdfPCellPara(String text, Font font, int alignment)
	  {
		  PdfPCell cell = new PdfPCell(new Paragraph(text,  font));
		  cell.setBorder(Rectangle.NO_BORDER); 
		  cell.setHorizontalAlignment(alignment);
		  return cell;
	  }
	  
	  private static PdfPCell getPdfPCellPhrase(String text, Font font, int alignment)
	  {
		  PdfPCell cell = new PdfPCell(new Phrase(text,  font));
		  cell.setBorder(Rectangle.NO_BORDER); 
		  cell.setHorizontalAlignment(alignment);
		  return cell;
	  }
	  private static void addContent(Document document, Case aCase)
	      throws DocumentException {
		 
		Paragraph paragraph1Lines = new Paragraph();
		Paragraph paragraph2Lines = new Paragraph();
		addEmptyLine(paragraph1Lines, 1);
		addEmptyLine(paragraph2Lines, 2);
		
	    // --- AMI Title ----
        document.add(getAmiTable());
        
        document.add(paragraph1Lines);

        // ---- Vet Table -----
        document.add(getVetTable(aCase));
        document.add(paragraph1Lines);
        
        
       // ---- Client Table -----
        document.add( getClientTable(aCase));
        document.add(paragraph1Lines);
	    
        
        //document.add(getPatientDescTable());
        
        // ---- Patient Table -----
        document.add(getPatientTable(aCase));
        document.add(paragraph2Lines);
        
        // ------ exam ------------
        addReportDetail(document,"Exam", aCase.getConsultation() , paragraph1Lines, paragraph2Lines);
	    
	    // ------ tentative diagnosis ------------
        addReportDetail(document,"Tentative Diagnosis", aCase.getTentativeDiagnosis() , paragraph1Lines, paragraph2Lines);
        
        // ------ Radiographic Interpretation ------------
	    if( ! aCase.isUnderContract())
	    {
	    	addReportDetail(document,"Radiographic Interpretation", aCase.getRadioInterpretation() , paragraph1Lines, paragraph2Lines);
	    }

	    // ------ Radiographic Impression ------------
	    addReportDetail(document,"Radiographic Impression", aCase.getRadioImpression() , paragraph1Lines, paragraph2Lines);

	    // ------ Recommendation ------------
	    addReportDetail(document,"Recommendation", aCase.getRecommendations() , paragraph1Lines, paragraph2Lines);
	  }
	private static PdfPTable getAmiTable() {
		PdfPTable amiTitleTable = new PdfPTable(1); 
        amiTitleTable.addCell(getPdfPCellPara("RADIOGRAPHIC INTERPRETATION REPORT ",  catFont, Element.ALIGN_CENTER));
        amiTitleTable.addCell( getPdfPCellPara("Animal Medical Imaging", titleFontBold, Element.ALIGN_CENTER));
        amiTitleTable.addCell(getPdfPCellPara("First Place West Bothell, WA  98021",smallFont7,Element.ALIGN_CENTER));
        amiTitleTable.addCell(getPdfPCellPara("Phone: 425-419-4220 or 800-888-0197 Fax: 425-949-8143",smallFont7,Element.ALIGN_CENTER));
		return amiTitleTable;
	}
	private static PdfPTable getPatientTable(Case aCase)
			throws DocumentException {
		float[] columnWidths6 = {10, 10, 40 ,40,10,30};
        PdfPTable patientTable = new PdfPTable(6); 
        patientTable.addCell(getPdfPCellPhrase("Species: " ,  smallFont7,Element.ALIGN_LEFT)); 
        patientTable.addCell(getPdfPCellPhrase(aCase.getPatientSpecies(),  smallFont7Bold,Element.ALIGN_LEFT));
        patientTable.addCell(getPdfPCellPhrase("Breed: ",  smallFont7,Element.ALIGN_RIGHT));
        patientTable.addCell(getPdfPCellPhrase(aCase.getPatientBreed(),  smallFont7Bold,Element.ALIGN_LEFT));
        patientTable.addCell(getPdfPCellPhrase("Age",  smallFont7,Element.ALIGN_RIGHT));
        patientTable.addCell(getPdfPCellPhrase(aCase.getPatientAge(),  smallFont7Bold,Element.ALIGN_LEFT));
        patientTable.setWidths(columnWidths6);
        
        patientTable.addCell(getPdfPCellPhrase("Sex: " ,  smallFont7,Element.ALIGN_LEFT)); 
        patientTable.addCell(getPdfPCellPhrase(aCase.getPatientSex(),  smallFont7Bold,Element.ALIGN_LEFT));
        patientTable.addCell(getPdfPCellPhrase("Name: ",  smallFont7,Element.ALIGN_RIGHT));
        patientTable.addCell(getPdfPCellPhrase(aCase.getPatientName(),  smallFont7Bold,Element.ALIGN_LEFT));
        patientTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_LEFT));
        patientTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_LEFT));
        patientTable.setWidths(columnWidths6);
		return patientTable;
	}
	  
	  
	private static PdfPTable getPatientDescTable() throws DocumentException {
		PdfPTable patientDescTable = new PdfPTable(6); 
	    //row 1
        patientDescTable.addCell(getPdfPCellPhrase("Patient Description: " ,  smallFont7Bold,Element.ALIGN_LEFT)); 
        patientDescTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_LEFT));
        patientDescTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_RIGHT));
        patientDescTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_RIGHT));
        patientDescTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_RIGHT));
        patientDescTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_RIGHT));

        float[] columnWidths6a = {40, 10, 10 ,40,10,30};
        patientDescTable.setWidths(columnWidths6a);
        
        return patientDescTable;
	}
	private static PdfPTable getClientTable(Case aCase)
			throws DocumentException {
		float[] columnWidths = {35, 50, 35,50};
        
        PdfPTable clientTable = new PdfPTable(4); 
	    //row 1
        clientTable.addCell(getPdfPCellPhrase("Client's name: " ,  smallFont7,Element.ALIGN_LEFT)); 
        clientTable.addCell(getPdfPCellPhrase(aCase.getClientFirstName() + " "+aCase.getClientLastName(),  smallFont7Bold,Element.ALIGN_LEFT));
        clientTable.addCell(getPdfPCellPhrase("ID#: ",  smallFont7,Element.ALIGN_RIGHT));
        clientTable.addCell(getPdfPCellPhrase(aCase.getClientId(),  smallFont7Bold,Element.ALIGN_RIGHT));
        clientTable.setWidths(columnWidths);
        
        clientTable.addCell(getPdfPCellPhrase("Address: " ,  smallFont7,Element.ALIGN_LEFT)); 
        clientTable.addCell(getPdfPCellPhrase(aCase.getClientAttribute().getAddress().getFormattedAddress(),  smallFont7Bold,Element.ALIGN_LEFT));
        clientTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_LEFT));
        clientTable.addCell(getPdfPCellPhrase(aCase.getClientId(),  smallFont7Bold,Element.ALIGN_LEFT));
        clientTable.setWidths(columnWidths);
        
        clientTable.addCell(getPdfPCellPhrase("Home Telephone: " ,  smallFont7,Element.ALIGN_LEFT)); 
        clientTable.addCell(getPdfPCellPhrase(aCase.getClientAttribute().getContactNotNull().getOffice(),  smallFont7Bold,Element.ALIGN_LEFT));
        clientTable.addCell(getPdfPCellPhrase("",  smallFont7,Element.ALIGN_RIGHT));
        clientTable.addCell(getPdfPCellPhrase("",  smallFont7Bold,Element.ALIGN_RIGHT));
        clientTable.setWidths(columnWidths);
		return clientTable;
	}
	private static PdfPTable getVetTable(Case aCase)
			throws DocumentException {
		
		PdfPTable vetTable = new PdfPTable(4); 
		//row 1
        vetTable.addCell(getPdfPCellPhrase("Date Of Request: " ,  smallFont7,Element.ALIGN_LEFT)); 
        vetTable.addCell(getPdfPCellPhrase(aCase.format(aCase.getRequestDate()),  smallFont7Bold,Element.ALIGN_LEFT));
        vetTable.addCell(getPdfPCellPhrase("Date Of Report: ",  smallFont7,Element.ALIGN_RIGHT));
        vetTable.addCell(getPdfPCellPhrase( aCase.format(aCase.getTranscriptionCompleteDate()),  smallFont7Bold,Element.ALIGN_LEFT));
        //row 2
        vetTable.addCell(getPdfPCellPhrase("Requesting Veterinarian: " ,  smallFont7,Element.ALIGN_LEFT)); 
        vetTable.addCell(getPdfPCellPhrase(aCase.getVeterinarian(),  smallFont7Bold,Element.ALIGN_LEFT));
        vetTable.addCell(getPdfPCellPhrase(" ",  smallFont7Bold,Element.ALIGN_RIGHT));
        vetTable.addCell(getPdfPCellPhrase(" ",  smallFont7Bold,Element.ALIGN_RIGHT));
        //row 2
        vetTable.addCell(getPdfPCellPhrase("Hospital: " ,  smallFont7,Element.ALIGN_LEFT)); 
        vetTable.addCell(getPdfPCellPhrase(aCase.getHospitalName(),  smallFont7Bold,Element.ALIGN_LEFT));
        vetTable.addCell(getPdfPCellPhrase(" ",  smallFont7Bold,Element.ALIGN_RIGHT));
        vetTable.addCell(getPdfPCellPhrase(" ",  smallFont7Bold,Element.ALIGN_RIGHT));
        //row 2
        vetTable.addCell(getPdfPCellPhrase("Address: " ,  smallFont7,Element.ALIGN_LEFT)); 
        vetTable.addCell(getPdfPCellPhrase(aCase.getHospitalAttribute().getAddress().getFormattedAddress(),  smallFont7Bold,Element.ALIGN_LEFT));
        vetTable.addCell(getPdfPCellPhrase(" ",  smallFont7Bold,Element.ALIGN_RIGHT));
        vetTable.addCell(getPdfPCellPhrase(" ",  smallFont7Bold,Element.ALIGN_RIGHT));
        //row 2
        vetTable.addCell(getPdfPCellPhrase("Telephone: " ,  smallFont7,Element.ALIGN_LEFT)); 
        vetTable.addCell(getPdfPCellPhrase(aCase.getHospitalAttribute().getContact().getOffice(),  smallFont7Bold,Element.ALIGN_LEFT));
        vetTable.addCell(getPdfPCellPhrase(" ",  smallFont7Bold,Element.ALIGN_RIGHT));
        vetTable.addCell(getPdfPCellPhrase(" ",  smallFont7Bold,Element.ALIGN_RIGHT));
        
        float[] columnWidths = {35, 50, 35,50};
        vetTable.setWidths(columnWidths);
		return vetTable;
	}
	  
	  
	private static void addReportDetail(Document document, String title, String description,
			Paragraph paragraph1Lines, Paragraph paragraph2Lines)
			throws DocumentException {
		document.add(new Paragraph(title,  smallUnderline));
//	    document.add(paragraph1Lines);
	    document.add(new Paragraph(description,  smallFont9));
	    document.add(paragraph2Lines);
	}


	 private static void addEmptyLine(Paragraph paragraph, int number) 
	 {
	    for (int i = 0; i < number; i++) 
	    {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
}
