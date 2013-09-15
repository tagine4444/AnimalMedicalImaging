package com.bouacheria.ami.service.cases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.cases.Case;
import com.bouacheria.ami.repository.config.ConfigUtil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfBuilder
{
	
	@Autowired
	private ConfigUtil config;
	
	@Autowired
	private PdfComponentBuilder pdfComponentBuilder;

	
	
	// iText allows to add metadata to the PDF which can be viewed in your Adobe
	// Reader
	// under File -> Properties

	public FileSystemResource getPdf(Case aCase, HttpServletRequest request) throws FileNotFoundException, DocumentException
	{

		final String pdfFile = getPdfFileWithPath(aCase.getRequestNumber() + System.currentTimeMillis(), request);
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
		document.open();

		addMetaData(document, aCase);
		addContent(document, aCase);
		document.close();

		FileSystemResource inputStreamSrc = new FileSystemResource(pdfFile);

		// saveToDisk(inputStreamSrc);
		return inputStreamSrc;
	}

	public String getPdfFileWithPath(String caseNumber, HttpServletRequest request)
	{
		String pdfPath = request.getSession().getServletContext().getRealPath("/pdftmp");
		
		
		String fileName = getPdfFileNameOnly(caseNumber);
		String goodUploadPath1 = pdfPath.replace("/", File.separator);
		String fileNameAndPath = goodUploadPath1+ File.separator+fileName;
		
		return fileNameAndPath;
		//return config.getPdfFilesPath() + File.separatorChar + getPdfFileNameOnly(caseNumber);
	}

	public String getPdfFileNameOnly(String caseNumber)
	{
		return "AMI_Report_Request_" + caseNumber + ".pdf";
	}

	private void addMetaData(Document document, Case aCase)
	{
		document.addTitle("Animal Medical Imaging Report");
		document.addSubject(aCase.getPatientName());
	}

	
	private void addContent(Document document, Case aCase) throws DocumentException
	{

		Paragraph paragraph1Lines = new Paragraph();
		Paragraph paragraph2Lines = new Paragraph();
		addEmptyLine(paragraph1Lines, 1);
		addEmptyLine(paragraph2Lines, 2);

		// --- AMI Title ----
		document.add(pdfComponentBuilder.getAmiTable());

		document.add(paragraph1Lines);

		// ---- Vet Table -----
		document.add(pdfComponentBuilder.getVetTable(aCase));
		document.add(paragraph1Lines);

		// ---- Client Table -----
		document.add(pdfComponentBuilder.getClientTable(aCase));
		document.add(paragraph1Lines);

		// document.add(getPatientDescTable());

		// ---- Patient Table -----
		document.add(pdfComponentBuilder.getPatientTable(aCase));
		document.add(paragraph2Lines);

		// ------ exam ------------
		pdfComponentBuilder.addReportDetail(document, "Exam", aCase.getConsultation(), paragraph1Lines, paragraph2Lines);

		// ------ tentative diagnosis ------------
		pdfComponentBuilder.addReportDetail(document, "Tentative Diagnosis", aCase.getTentativeDiagnosis(), paragraph1Lines, paragraph2Lines);

		// ------ Radiographic Interpretation ------------
		if (!aCase.isUnderContract())
		{
			pdfComponentBuilder.addReportDetail(document, "Radiographic Interpretation", aCase.getRadioInterpretation(), paragraph1Lines, paragraph2Lines);
		}

		// ------ Radiographic Impression ------------
		pdfComponentBuilder.addReportDetail(document, "Radiographic Impression", aCase.getRadioImpression(), paragraph1Lines, paragraph2Lines);

		// ------ Recommendation ------------
		pdfComponentBuilder.addReportDetail(document, "Recommendation", aCase.getRecommendations(), paragraph1Lines, paragraph2Lines);
	}

	private void addEmptyLine(Paragraph paragraph, int number)
	{
		for (int i = 0; i < number; i++)
		{
			paragraph.add(new Paragraph(" "));
		}
	}

	
}
