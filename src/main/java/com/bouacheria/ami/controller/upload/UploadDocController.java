package com.bouacheria.ami.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bouacheria.ami.domain.uploads.Uploads;
import com.bouacheria.ami.service.uploads.UploadsService;

@Controller
public class UploadDocController {

	@Resource(name = "amiProperties")
	private Properties amiProperties;
	
	@Autowired
	private UploadsService uploadsService;

	@ModelAttribute("uploadDocItem")
	public UploadDocItem createUploadItem() 
	{
		UploadDocItem uploadItem = new UploadDocItem();
		return uploadItem;
	}
	
	@RequestMapping(value = "/uploadedDocuments",method = RequestMethod.GET)
	public String getUploadDoc(Model model, @ModelAttribute UploadDocItem uploadItem,@RequestParam long svcReqId) {
		
		List<Uploads> uploadDocs = uploadsService.findByRequestId(svcReqId);
		
		uploadItem.setRequestId(svcReqId);
		model.addAttribute(uploadItem);
		model.addAttribute("uploadDocs", uploadDocs);
		return "uploadedDocuments";
	}
	
	 @RequestMapping(value = "/uploadedDocuments", method = RequestMethod.GET, params={"openDoc"})
	 public void handleFileDownload(HttpServletResponse res,Model model,  @RequestParam long openDoc) {
	        
		 Uploads anUpload = uploadsService.findById(openDoc);
		 
		 try 
		 {
	            //String fn = "/Test.xls";
	            //URL url = getClass().getResource(fn);
//	            File f = new File(url.toURI());
			 File f = new File(anUpload.getFilePath());
	            System.out.println("Loading file "+anUpload.getFilePath()+"("+f.getAbsolutePath()+")");
	            
	            if (f.exists()) 
	            {
	                res.setContentType(anUpload.getContentType());
	                res.setContentLength(new Long(f.length()).intValue());
	                res.setHeader("Content-Disposition", "attachment; filename="+anUpload.getFileName());
	                FileCopyUtils.copy(new FileInputStream(f), res.getOutputStream());
	            } 
	            else 
	            {
	                System.out.println("File"+anUpload.getFilePath()+"("+f.getAbsolutePath()+") does not exist");
	            }
	        } catch (Exception e) 
	        {
	        	e.printStackTrace();
	        }
	    }

}
