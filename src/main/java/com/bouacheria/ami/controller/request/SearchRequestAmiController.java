package com.bouacheria.ami.controller.request;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.controller.AbstractAmiController;
import com.bouacheria.ami.domain.request.SearchRequest;
import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.request.ServiceRequestService;

@Controller
public class SearchRequestAmiController extends AbstractAmiController{
	

	@Autowired
	private ServiceRequestService serviceRequestService;

	@Autowired
	private SecurityUtil securityUtil;
	
	
	@ModelAttribute("searchRequestAmi")
	public SearchRequest createSearchRequest() 
	{
		SearchRequest request = new SearchRequest();
		return request;
	}
	
	
	@RequestMapping(value = "/searchRequestAmi", method = RequestMethod.GET)
	public String goToSeachPage(Model model, @ModelAttribute SearchRequest request)
	{
		List<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest>();
		model.addAttribute("serviceRequests", serviceRequests);
		return "searchRequestAmi";
	}
	
	@RequestMapping(value = "/searchRequestAmi", method = RequestMethod.GET, params={"last60"})
	public String searchLas60Request(Model model, @ModelAttribute SearchRequest request)
	{
		List<ServiceRequest> serviceRequests =serviceRequestService.findLas60Request();
		if(serviceRequests ==null)
		{
			serviceRequests = new ArrayList<ServiceRequest>();
		}
		model.addAttribute("serviceRequests", serviceRequests);
		
		return "searchRequestAmi";
	}
	
	@RequestMapping(value = "/searchRequestAmi", method = RequestMethod.GET, params={"last60Stat"})
	public String searchLast30STATRequest(Model model, @ModelAttribute SearchRequest request)
	{
		List<ServiceRequest> serviceRequests =serviceRequestService.findLas60STATRequest();
		if(serviceRequests ==null)
		{
			serviceRequests = new ArrayList<ServiceRequest>();
		}
		model.addAttribute("serviceRequests", serviceRequests);
		
		return "searchRequestAmi";
	}
	
	
	@RequestMapping(value = "/searchRequestAmi", method = RequestMethod.GET, params={"notInQuickbook"})
	public String searchNotInQuickbook(Model model, @ModelAttribute SearchRequest request)
	{
		List<ServiceRequest> serviceRequests =serviceRequestService.findNotInQuickbook();
		if(serviceRequests ==null)
		{
			serviceRequests = new ArrayList<ServiceRequest>();
		}
		model.addAttribute("serviceRequests", serviceRequests);
		
		return "searchRequestAmi";
	}
	
	
	@RequestMapping(value = "/searchRequestAmi", method = RequestMethod.GET, params={"searchRequestNumber"})
	public String searchByRequest(Model model, @ModelAttribute SearchRequest request)
	{
		List<ServiceRequest> serviceRequests = new ArrayList<ServiceRequest>();
		
		if(StringUtils.isNotEmpty(request.getRequestNumber()))
		{
			ServiceRequest serviceRequest = serviceRequestService.findByRequestNumber(request.getRequestNumber());
			serviceRequests.add(serviceRequest);
			model.addAttribute("serviceRequests", serviceRequests);
			return "searchRequestAmi";
		}
		
		dateSearch(model, request, serviceRequests);
		
		
		return "searchRequestAmi";
	}


	private void dateSearch(Model model, SearchRequest request,
			List<ServiceRequest> serviceRequests) 
	{
		DateTime begDate = request.getBegDate();
		DateTime endDate = request.getEndDate();
		
		boolean hasOnlyBegDate = begDate!=null && endDate==null;
		boolean hasOnlyEndDate = begDate==null && endDate!=null;
	
		if(begDate==null && endDate==null)
		{
			model.addAttribute("searchMsg", "You must specify a date range or a Request Number");
			return;
		}
		
		if(hasOnlyBegDate)
		{
			setBedAndAndDate(request, begDate);
			
			serviceRequests = serviceRequestService.findRequestForDateRange( request.getBegDate(), request.getEndDate());
			model.addAttribute("searchMsg", "Because you only specified the begining date, the search defaulted for that date.");
			model.addAttribute("serviceRequests", serviceRequests);
			return;
		}
		
		if(hasOnlyEndDate)
		{
			setBedAndAndDate(request, begDate);
			
			serviceRequests = serviceRequestService.findRequestForDateRange( request.getBegDate(), request.getEndDate());
			model.addAttribute("searchMsg", "Because you only specified the end date, the search defaulted for that date.");
			model.addAttribute("serviceRequests", serviceRequests);
			return;
		}
		
		setBedAndEndDate(request, request.getBegDate(), request.getEndDate()) ;
		
		serviceRequests = serviceRequestService.findRequestForDateRange( request.getBegDate(), request.getEndDate());
		model.addAttribute("serviceRequests", serviceRequests);
	}


	/**
	 * @param request
	 * @param date
	 * 
	 * zeros out the beg date and makes the end date 23:59 so that 
	 * looking up requests works
	 */
	private void setBedAndAndDate(SearchRequest request, DateTime date) 
	{
		Calendar calBeg = Calendar.getInstance();
		calBeg.set(date.getYear(), date.getMonthOfYear()-1, date.getDayOfMonth(), 0,0,0);
		DateTime midnightBegDate = new DateTime(calBeg.getTimeInMillis());
		request.setBegDate(midnightBegDate);


		Calendar calEnd = Calendar.getInstance();
		calEnd.set(date.getYear(), date.getMonthOfYear()-1, date.getDayOfMonth(), 23,59,59);
		DateTime midnightEndDate = new DateTime(calEnd.getTimeInMillis());
		request.setEndDate(midnightEndDate);
	}

	/**
	 * @param request
	 * @param begDate
	 * @param endDate
	 * 
	 * 
	 */
	private void setBedAndEndDate(SearchRequest request, DateTime begDate, DateTime endDate) 
	{
		Calendar calBeg = Calendar.getInstance();
		calBeg.set(begDate.getYear(), begDate.getMonthOfYear()-1, begDate.getDayOfMonth(), 0,0,0);
		DateTime midnightBegDate = new DateTime(calBeg.getTimeInMillis());
		request.setBegDate(midnightBegDate);


		Calendar calEnd = Calendar.getInstance();
		calEnd.set(endDate.getYear(), endDate.getMonthOfYear()-1, endDate.getDayOfMonth(), 23,59,59);
		DateTime midnightEndDate = new DateTime(calEnd.getTimeInMillis());
		request.setEndDate(midnightEndDate);
	}
}
