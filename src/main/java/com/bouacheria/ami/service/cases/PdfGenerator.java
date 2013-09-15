package com.bouacheria.ami.service.cases;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.cases.Case;
import com.bouacheria.ami.service.emails.EmailService;

@Service
public class PdfGenerator
{

	private final static boolean SAVE_PDF_TO_DISK = Boolean.FALSE;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PdfBuilder pdfBuilder;

	public String getPdfFileNameOnly(String caseNumber) {
		return "AMI_Report_Request_" + caseNumber + ".pdf";
	}

	private void saveToDisk(FileSystemResource inputStreamSrc) {
		try
		{

			FileInputStream in = new FileInputStream(inputStreamSrc.getFile());
			FileOutputStream fos = new FileOutputStream(inputStreamSrc.getFilename());
			int c;

			while ((c = in.read()) != -1)
			{
				fos.write(c);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

//	public FileSystemResource getPdf(Case aCase) throws FileNotFoundException, DocumentException 
//	{
//
//		FileSystemResource inputStreamSrc =  pdfBuilder.getPdf(aCase);
//		
//		if (SAVE_PDF_TO_DISK)
//		{
//			saveToDisk(inputStreamSrc);
//		}
//			
//		
//		return inputStreamSrc;
//	}
	public void emailPdf(Case aCase, String from, String to, String subject, String msg, HttpServletRequest request) {
		
		final String pdfFileNameOnly = getPdfFileNameOnly(aCase.getRequestNumber());
		
		try
		{
			FileSystemResource inputStreamSrc = pdfBuilder.getPdf(aCase, request);
			
			if (SAVE_PDF_TO_DISK)
			{
				saveToDisk(inputStreamSrc);
			}
			
			emailService.sendMailWithAttachement(from, to, subject, msg, inputStreamSrc, pdfFileNameOnly);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
