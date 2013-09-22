package com.bouacheria.ami.controller.upload;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bouacheria.ami.controller.AbstractAmiController;
import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.domain.uploads.Uploads;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;
import com.bouacheria.ami.service.request.ServiceRequestService;
import com.bouacheria.ami.service.uploads.UploadsService;

@Controller
public class UploadController extends AbstractAmiController {

//	@Resource(name = "amiProperties")
//	private Properties amiProperties;
	
	@Autowired
	private ServiceRequestService serviceRequestService;

	@Autowired
	private UploadsService uploadsService;

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private AmiServiceServiceCached cache;
	
	@ModelAttribute("uploadItem")
	public UploadItem createUploadItem() 
	{
		UploadItem uploadItem = new UploadItem();
		return uploadItem;
	}
	
	@RequestMapping(value = "/upload",method = RequestMethod.GET,params = { "svcReqId","requestNumber" })
	public String getUploadForm(Model model, @ModelAttribute UploadItem uploadItem,@RequestParam long svcReqId, @RequestParam String requestNumber) {
		
		uploadItem.setRequestNumber(requestNumber);
		uploadItem.setSvcReqId(svcReqId);
		model.addAttribute(uploadItem);
		
		List<Uploads> uploadDocs = uploadsService.findByRequestId(svcReqId);
		model.addAttribute("uploadDocs", uploadDocs);
		
		return "uploadForm";
	}

	@RequestMapping(value = "/upload",method = RequestMethod.POST )
	public String create(Model model,UploadItem uploadItem, BindingResult result, HttpServletRequest request) 
	{
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) 
			{
				System.err.println("Error: " + error.getCode() + " - "+ error.getDefaultMessage());
			}
			return "uploadForm";
		}
		
	
		long svcReqId = uploadItem.getSvcReqId();
		CommonsMultipartFile fileData = uploadItem.getFileData();
		String contentType = fileData.getContentType();
		List<Uploads> uploadDocs = null;
		try 
		{
			String originalFilename = uploadItem.getFileData().getOriginalFilename();
				
			if(StringUtils.isEmpty(originalFilename))
			{
				model.addAttribute("error", "You must choos a file before uploading");
				
				
				uploadDocs = uploadsService.findByRequestId(svcReqId);
				model.addAttribute("uploadDocs", uploadDocs);
				
				return "uploadForm";
			}
			
			//String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
			String uploadPath = cache.getUploadDirectory();
			
			
			String fileName = uploadItem.getRequestNumber() + originalFilename;
			//String goodUploadPath1 = uploadPath.replace("/", File.separator);
			String fileNameAndPath = uploadPath +fileName;
			fileData.transferTo(new File(fileNameAndPath));
			
			
			String hospitalName = securityUtil.getHospitalForLoggedinUser().getName();
			Uploads uploads = new Uploads();
			uploads.setRequestId(uploadItem.getSvcReqId());
			uploads.setRequestNumber(uploadItem.getRequestNumber());
			uploads.setFilePath(fileNameAndPath);
			uploads.setHospitalName(hospitalName);
			uploads.setContentType(contentType);
			uploads.setFileName(fileName);
			uploadsService.save(uploads);
			
			model.addAttribute("successMsg", originalFilename + " succesfully uploaded");
			
			ServiceRequest serviceRequest = serviceRequestService.findById(uploadItem.getSvcReqId());
			serviceRequest.setHasDocuments(true);
			serviceRequestService.save(serviceRequest);
			
			
		} 
		catch (Exception e) 
		{
			model.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		finally{
			uploadDocs = uploadsService.findByRequestId(svcReqId);
			model.addAttribute("uploadDocs", uploadDocs);
		}

//		// Some type of file processing...
//		System.err.println("-------------------------------------------");
//		System.err.println("Test upload: " + uploadItem.getName());
//		System.err.println("Test upload: "
//				+ uploadItem.getFileData().getOriginalFilename());
//		System.err.println("-------------------------------------------");

		return "uploadForm";
	}
	
//	@ExceptionHandler(Throwable.class)
//	public 	ModelAndView handleException(Throwable exception)
//	{
//		ModelAndView view = new ModelAndView();
//		view.setViewName("errorPage");
//		view.addObject("errorString", ExceptionUtils.getStackTrace(exception));
//		return view;
//	}
}
