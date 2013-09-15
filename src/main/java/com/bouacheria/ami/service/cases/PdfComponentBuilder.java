package com.bouacheria.ami.service.cases;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bouacheria.ami.domain.cases.AmiPdfHolder;
import com.bouacheria.ami.domain.cases.AmiPdfModel;
import com.bouacheria.ami.domain.cases.Case;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

@Component
public class PdfComponentBuilder
{
	
	public static Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);

	public static Font titleFontBold = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK);

	public static Font smallFont9 = new Font(Font.TIMES_ROMAN, 9, Font.NORMAL, Color.BLACK);

	public static Font smallFont7 = new Font(Font.TIMES_ROMAN, 7, Font.NORMAL, Color.BLACK);

	public static Font smallFont7Bold = new Font(Font.TIMES_ROMAN, 7, Font.BOLD, Color.BLACK);

	public static Font smallUnderline = new Font(Font.TIMES_ROMAN, 12, Font.UNDERLINE);

	
	@Autowired
	private AmiPdfHolder amiPdfHolder;

	public PdfPTable getAmiTable()
	{
		PdfPTable amiTitleTable = new PdfPTable(1);
		
		AmiPdfModel amiPdfModel = amiPdfHolder.getAmiPdfModel();
		
		amiTitleTable.addCell(getPdfPCellPara(amiPdfModel.getReportTitle(), catFont, Element.ALIGN_CENTER));
		amiTitleTable.addCell(getPdfPCellPara(amiPdfModel.getCompanyTitle(), titleFontBold, Element.ALIGN_CENTER));
		amiTitleTable.addCell(getPdfPCellPara(amiPdfModel.getAddress(), smallFont7, Element.ALIGN_CENTER));
		amiTitleTable.addCell(getPdfPCellPara(amiPdfModel.getPhone(), smallFont7, Element.ALIGN_CENTER));
		return amiTitleTable;
	}

	public PdfPTable getPatientTable(Case aCase) throws DocumentException
	{
		float[] columnWidths6 = { 10, 10, 40, 40, 10, 30 };
		PdfPTable patientTable = new PdfPTable(6);
		patientTable.addCell(getPdfPCellPhrase("Species: ", smallFont7, Element.ALIGN_LEFT));
		patientTable.addCell(getPdfPCellPhrase(aCase.getPatientSpecies(), smallFont7Bold, Element.ALIGN_LEFT));
		patientTable.addCell(getPdfPCellPhrase("Breed: ", smallFont7, Element.ALIGN_RIGHT));
		patientTable.addCell(getPdfPCellPhrase(aCase.getPatientBreed(), smallFont7Bold, Element.ALIGN_LEFT));
		patientTable.addCell(getPdfPCellPhrase("Age", smallFont7, Element.ALIGN_RIGHT));
		patientTable.addCell(getPdfPCellPhrase(aCase.getPatientAge(), smallFont7Bold, Element.ALIGN_LEFT));
		patientTable.setWidths(columnWidths6);

		patientTable.addCell(getPdfPCellPhrase("Sex: ", smallFont7, Element.ALIGN_LEFT));
		patientTable.addCell(getPdfPCellPhrase(aCase.getPatientSex(), smallFont7Bold, Element.ALIGN_LEFT));
		patientTable.addCell(getPdfPCellPhrase("Name: ", smallFont7, Element.ALIGN_RIGHT));
		patientTable.addCell(getPdfPCellPhrase(aCase.getPatientName(), smallFont7Bold, Element.ALIGN_LEFT));
		patientTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
		patientTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
		patientTable.setWidths(columnWidths6);
		return patientTable;
	}

	public PdfPTable getPatientDescTable() throws DocumentException
	{
		PdfPTable patientDescTable = new PdfPTable(6);
		// row 1
		patientDescTable.addCell(getPdfPCellPhrase("Patient Description: ", smallFont7Bold, Element.ALIGN_LEFT));
		patientDescTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
		patientDescTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_RIGHT));
		patientDescTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_RIGHT));
		patientDescTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_RIGHT));
		patientDescTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_RIGHT));

		float[] columnWidths6a = { 40, 10, 10, 40, 10, 30 };
		patientDescTable.setWidths(columnWidths6a);

		return patientDescTable;
	}

	public PdfPTable getClientTable(Case aCase) throws DocumentException
	{
		//float[] columnWidths = { 16, 75 };
		float[] columnWidths = { 35, 50, 35, 50 };

		PdfPTable clientTable = new PdfPTable(4);
		// row 1
		clientTable.addCell(getPdfPCellPhrase("Client Name: ", smallFont7, Element.ALIGN_LEFT));
		clientTable.addCell(getPdfPCellPhrase(aCase.getClientFirstName() + " " + aCase.getClientLastName(), smallFont7Bold, Element.ALIGN_LEFT));
		clientTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
		clientTable.addCell(getPdfPCellPhrase("", smallFont7Bold, Element.ALIGN_LEFT));
		
		clientTable.addCell(getPdfPCellPhrase("Client ID#: ", smallFont7, Element.ALIGN_LEFT));
		clientTable.addCell(getPdfPCellPhrase(aCase.getClientId() , smallFont7Bold, Element.ALIGN_LEFT));
		clientTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
		clientTable.addCell(getPdfPCellPhrase("", smallFont7Bold, Element.ALIGN_LEFT));

		if(!aCase.isUnderContract())
		{
			clientTable.addCell(getPdfPCellPhrase("Address: ", smallFont7, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase(aCase.getClientAttribute().getAddress().getFormattedAddress(), smallFont7Bold, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase("", smallFont7Bold, Element.ALIGN_LEFT));
	
			clientTable.addCell(getPdfPCellPhrase("Home phone: ", smallFont7, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase(aCase.getClientAttribute().getContactNotNull().getHomePhone(), smallFont7Bold, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase("", smallFont7Bold, Element.ALIGN_LEFT));
			
			clientTable.addCell(getPdfPCellPhrase("Cell phone", smallFont7, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase(aCase.getClientAttribute().getContactNotNull().getCell(), smallFont7Bold, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
			clientTable.addCell(getPdfPCellPhrase("", smallFont7Bold, Element.ALIGN_LEFT));
		}
		clientTable.setWidths(columnWidths);
		return clientTable;
	}

	public PdfPTable getVetTable(Case aCase) throws DocumentException
	{

		PdfPTable vetTable = new PdfPTable(4);
		// row 1
		vetTable.addCell(getPdfPCellPhrase("Date Of Request: ", smallFont7, Element.ALIGN_LEFT));
		vetTable.addCell(getPdfPCellPhrase(aCase.format(aCase.getRequestDate()), smallFont7Bold, Element.ALIGN_LEFT));
		vetTable.addCell(getPdfPCellPhrase("Date Of Report: ", smallFont7, Element.ALIGN_RIGHT));
		vetTable.addCell(getPdfPCellPhrase(aCase.format(aCase.getTranscriptionCompleteDate()), smallFont7Bold, Element.ALIGN_LEFT));

		// empty row
		vetTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_LEFT));
		vetTable.addCell(getPdfPCellPhrase("", smallFont7Bold, Element.ALIGN_LEFT));
		vetTable.addCell(getPdfPCellPhrase("", smallFont7, Element.ALIGN_RIGHT));
		vetTable.addCell(getPdfPCellPhrase("", smallFont7Bold, Element.ALIGN_LEFT));
		// row 2
		vetTable.addCell(getPdfPCellPhrase("Requesting Veterinarian: ", smallFont7, Element.ALIGN_LEFT));
		vetTable.addCell(getPdfPCellPhrase(aCase.getVeterinarian(), smallFont7Bold, Element.ALIGN_LEFT));
		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
		// row 2
		vetTable.addCell(getPdfPCellPhrase("Request submited by: ", smallFont7, Element.ALIGN_LEFT));
		vetTable.addCell(getPdfPCellPhrase(aCase.getRequestBy(), smallFont7Bold, Element.ALIGN_LEFT));
		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
		// row 2
//		vetTable.addCell(getPdfPCellPhrase("Hospital: ", smallFont7, Element.ALIGN_LEFT));
//		vetTable.addCell(getPdfPCellPhrase(aCase.getHospitalName(), smallFont7Bold, Element.ALIGN_LEFT));
//		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
//		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
		// row 2
//		vetTable.addCell(getPdfPCellPhrase("Address: ", smallFont7, Element.ALIGN_LEFT));
//		vetTable.addCell(getPdfPCellPhrase(aCase.getHospitalAttribute().getAddress().getFormattedAddress(), smallFont7Bold, Element.ALIGN_LEFT));
//		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
//		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
		// row 2
//		vetTable.addCell(getPdfPCellPhrase("Telephone: ", smallFont7, Element.ALIGN_LEFT));
//		vetTable.addCell(getPdfPCellPhrase(aCase.getHospitalAttribute().getContact().getOffice(), smallFont7Bold, Element.ALIGN_LEFT));
//		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));
//		vetTable.addCell(getPdfPCellPhrase(" ", smallFont7Bold, Element.ALIGN_RIGHT));

		float[] columnWidths = { 35, 50, 35, 50 };
		vetTable.setWidths(columnWidths);
		return vetTable;
	}

	
	public PdfPCell getPdfPCellPara(String text, Font font, int alignment)
	{
		PdfPCell cell = new PdfPCell(new Paragraph(text, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(alignment);
		return cell;
	}

	public PdfPCell getPdfPCellPhrase(String text, Font font, int alignment)
	{
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(alignment);
		return cell;
	}
	
	public void addReportDetail(Document document, String title, String description, Paragraph paragraph1Lines, Paragraph paragraph2Lines) throws DocumentException
	{
		document.add(new Paragraph(title, smallUnderline));
		// document.add(paragraph1Lines);
		document.add(new Paragraph(description, smallFont9));
		document.add(paragraph2Lines);
	}

}
