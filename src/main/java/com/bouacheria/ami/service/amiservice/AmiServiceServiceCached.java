package com.bouacheria.ami.service.amiservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.amiservices.AmiFee;
import com.bouacheria.ami.domain.amiservices.AmiService;
import com.bouacheria.ami.service.datatype.AmiServiceCategory;

@Service
public class AmiServiceServiceCached {
	
	
//	@Autowired
//	private DeleteMeService deleteMeService;
	
	@Autowired
	private AmiServiceService amiService;
	
	@Autowired
	private AmiFeeService amiFeeService;
	
//	@Autowired
//	private ConfigUtil config;
	
	
	private ConcurrentHashMap<Long, AmiService> cacheId;
	private ConcurrentHashMap<AmiServiceCategory, List<AmiService>> cache;
	private List<AmiFee> cachedAmiFee;
	private AmiFee latePaymetAmiFee;
	private AmiFee milageAmiFee;
	
	private List<String> interpretationOnlyList;
	private List<String> ultrasoundSvcList;
	private List<String> radiographyFluoroscopyServicesList;
	private List<String> contrastRadiographyServicesList;
	private List<String> computedTomographyServicesList;
	private List<String> MRIServicesList;

	private List<AmiService> allServices;
	private List<AmiService> interpretationOnlyAmiServiceList;
	private List<AmiService> ultrasoundSvcAmiServiceList;
	private List<AmiService> radiographyFluoroscopyAmiServiceList;
	private List<AmiService> contrastRadiographyAmiServiceList;
	private List<AmiService> computedTomographyAmiServiceList;
	private List<AmiService> MRIAmiServiceList;
	
	private String pdfDirectory;
	private String uploadDirectory;
	
	//private AmiService miscAmiService;
	
	private List<String> serviceCategories;
	
	
	
	public AmiService findById(long id) {
		return cacheId.get(id);
	}
	
	
	public void init() 
	{
		
		initCategories();
		
		allServices = amiService.findAll();
		
		addToCacheId(allServices);
		
		this.cache = new ConcurrentHashMap<AmiServiceCategory, List<AmiService>>();
		
		this.cachedAmiFee = amiFeeService.findAll();
		this.latePaymetAmiFee = getFee(AmiServiceCategory.LATE_PAYMENT_FEE);
		this.milageAmiFee     = getFee(AmiServiceCategory.MILAGE_FEE);
		
		
		AmiServiceCategory anAmiServiceType = AmiServiceCategory.MRI;
		List<AmiService> services = filterByCategory(anAmiServiceType, allServices);
		this.cache.put(anAmiServiceType, services);
		
		anAmiServiceType = AmiServiceCategory.COMPUTEDTOMOGRAPHY;
		services = filterByCategory(anAmiServiceType, allServices);
		this.cache.put(anAmiServiceType, services );
		
		anAmiServiceType = AmiServiceCategory.CONTRASTEDRADIOGRAPHY;
		services = filterByCategory(anAmiServiceType, allServices);
		this.cache.put(anAmiServiceType, services );
		
		anAmiServiceType = AmiServiceCategory.RADIOGRAPHYFLUOROSCOPY;
		services = filterByCategory(anAmiServiceType, allServices);
		this.cache.put(anAmiServiceType, services );

		anAmiServiceType = AmiServiceCategory.INTERPRETATION_ONLY;
		services = filterByCategory(anAmiServiceType, allServices);
		this.cache.put(anAmiServiceType, services );
		
		anAmiServiceType = AmiServiceCategory.ULTRASOUND;
		services = filterByCategory(anAmiServiceType, allServices);
		this.cache.put(anAmiServiceType, services );
		
		
		anAmiServiceType = AmiServiceCategory.MISC_SERVICE;
		services = filterByCategory(anAmiServiceType, allServices);
		//this.miscAmiService = services.get(0);
		this.cache.put(anAmiServiceType, services );
		
		this.ultrasoundSvcList = getServiceNames(AmiServiceCategory.ULTRASOUND);
		this.radiographyFluoroscopyServicesList = getServiceNames(AmiServiceCategory.RADIOGRAPHYFLUOROSCOPY);
		this.contrastRadiographyServicesList = getServiceNames(AmiServiceCategory.CONTRASTEDRADIOGRAPHY);
		this.computedTomographyServicesList = getServiceNames(AmiServiceCategory.COMPUTEDTOMOGRAPHY);
		this.MRIServicesList = getServiceNames(AmiServiceCategory.MRI);
		this.interpretationOnlyList =  getServiceNames(AmiServiceCategory.INTERPRETATION_ONLY);
		
		this.ultrasoundSvcAmiServiceList = cache.get(AmiServiceCategory.ULTRASOUND);
		this.radiographyFluoroscopyAmiServiceList = cache.get(AmiServiceCategory.RADIOGRAPHYFLUOROSCOPY);
		this.contrastRadiographyAmiServiceList = cache.get(AmiServiceCategory.CONTRASTEDRADIOGRAPHY);
		this.computedTomographyAmiServiceList = cache.get(AmiServiceCategory.COMPUTEDTOMOGRAPHY);
		this.MRIAmiServiceList = cache.get(AmiServiceCategory.MRI);
		this.interpretationOnlyAmiServiceList =  cache.get(AmiServiceCategory.INTERPRETATION_ONLY);
		
		try
		{
			
			String filePath =  new ClassPathResource("pdftmp/pdfPath.txt").getURL().getPath();
			this.pdfDirectory = filePath.substring(0, filePath.length() - "pdfPath.txt".length());
			
			String uploadFilePath =  new ClassPathResource("uploads/uploadPath.txt").getURL().getPath();
			this.uploadDirectory  = uploadFilePath.substring(0, uploadFilePath.length() - "uploadPath.txt".length());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("PDF DIRECTORY: >>>>> '"+ pdfDirectory+"'");
		System.out.println("UPLOAD DIRECTORY: >>>>> '"+ uploadDirectory+"'");
		
	}
	
	
	private AmiFee getFee(AmiServiceCategory amiFeeCategory) 
	{
		for (AmiFee amiFee : cachedAmiFee) {
			if(amiFee.getCategory().equals(amiFeeCategory.getCode()))
			{
				return amiFee;
			}
			
		}
		return null;
		//throw new IllegalArgumentException("amiFeeCategory not found :"+ amiFeeCategory.getCode());
	}

	private void addToCacheId(List<AmiService> allServices)
	{
		this.cacheId = new ConcurrentHashMap<Long, AmiService>();

		for (Iterator<AmiService> iterator = allServices.iterator(); iterator.hasNext();) 
		{
			AmiService amiService = iterator.next();
			
			cacheId.put(amiService.getId(), amiService);
		}
		
	}
	
	public List<AmiService> filterByCategory(AmiServiceCategory category, List<AmiService> allServices)
	{
		List<AmiService> list = new ArrayList<AmiService>();

		for (Iterator<AmiService> iterator = allServices.iterator(); iterator.hasNext();) 
		{
			AmiService amiService = iterator.next();
			
			if( amiService.getCategory().equals(category.getCode())){
				list.add(amiService);
			}
		}
		
		return list;
	}
	
	
	
	public AmiService getService(AmiServiceCategory category, String seviceName)
	{
		List<AmiService> services = cache.get(category);
		for (AmiService aService : services) {
			if(aService.getName().equals(seviceName))
			{
				return aService;
			}
		}
		throw new IllegalArgumentException("No services called '" +seviceName+ "' exist in the category: '"+category.getCode()+"'" );
		
	}
	
	public List<AmiService> getSelectedImagingServices(Map<AmiServiceCategory, String> serviceMap)
	{
		
		List<AmiService> list = new ArrayList<AmiService>();
		
		if(serviceMap!=null)
		{
			Iterator<AmiServiceCategory> iterator = serviceMap.keySet().iterator();
			
			while( iterator.hasNext())
			{
				AmiServiceCategory category = iterator.next();
				String serviceName = serviceMap.get(category);
				AmiService anAmiService = getService(category,serviceName);
				list.add(anAmiService);
			}
		}
		
		return list;
	}

	public List<String> initCategories()
	{
		AmiServiceCategory[] values = AmiServiceCategory.values();
		serviceCategories = new ArrayList<String>(values.length + 1);
		for (int i = 0; i < values.length; i++) {
			serviceCategories.add(values[i].getCode());
		}
		return serviceCategories;
		
	}
	
	public ConcurrentHashMap<Long, AmiService> getCacheId() {
		
		if(cacheId==null)
		{
			init();
		}
		
		return cacheId;
	}

	public ConcurrentHashMap<AmiServiceCategory, List<AmiService>> getCache() {
		
		if(cache==null)
		{
			init();
		}
		return cache;
	}
	
	public List<String> getServiceNames(AmiServiceCategory category)
	{
			List<String> names = new ArrayList<String>();
			List<AmiService> list = getCache().get(category);
			if(list==null)
			{
				return names;
			}
			for (Iterator<AmiService> iterator = list.iterator(); iterator.hasNext();) 
			{
				AmiService anItem = iterator.next();
				names.add(anItem.getName());
			}
			return names;
	}

	public AmiServiceService getAmiService() {
		return amiService;
	}

	public List<String> getUltrasoundSvcList() {
		return ultrasoundSvcList;
	}

	public List<String> getRadiographyFluoroscopyServicesList() {
		return radiographyFluoroscopyServicesList;
	}

	public List<String> getContrastRadiographyServicesList() {
		return contrastRadiographyServicesList;
	}

	public List<String> getComputedTomographyServicesList() {
		return computedTomographyServicesList;
	}

	public List<String> getMRIServicesList() {
		return MRIServicesList;
	}

	public List<String> getInterpretationOnlyList() {
		return interpretationOnlyList;
	}

	public List<AmiService> getInterpretationOnlyAmiServiceList() {
		return interpretationOnlyAmiServiceList;
	}

	public List<AmiService> getUltrasoundSvcAmiServiceList() {
		return ultrasoundSvcAmiServiceList;
	}

	public List<AmiService> getRadiographyFluoroscopyAmiServiceList() {
		return radiographyFluoroscopyAmiServiceList;
	}

	public List<AmiService> getContrastRadiographyAmiServiceList() {
		return contrastRadiographyAmiServiceList;
	}

	public List<AmiService> getComputedTomographyAmiServiceList() {
		return computedTomographyAmiServiceList;
	}

	public List<AmiService> getMRIAmiServiceList() {
		return MRIAmiServiceList;
	}

	public List<AmiFee> getCachedAmiFee() {
		return cachedAmiFee;
	}

	public AmiFee getLatePaymetAmiFee() {
		return latePaymetAmiFee;
	}

	public AmiFee getMilageAmiFee() {
		return milageAmiFee.clone();
	}
	
	public AmiFee getDefaultMilage()
	{
		return milageAmiFee.clone();
	}
	public AmiFee getDefaultLateFee()
	{
		return latePaymetAmiFee.clone();
	}

//	public AmiService getMiscAmiService() {
//		return miscAmiService.clone();
//	}

	public List<String> getServiceCategories() {
		return serviceCategories;
	}

	public void setServiceCategories(List<String> serviceCategories) {
		this.serviceCategories = serviceCategories;
	}

	public List<AmiService> getAllServices() {
		return allServices;
	}

	public String getPdfDirectory()
	{
		return pdfDirectory;
	}

	public String getUploadDirectory()
	{
		return uploadDirectory;
	}
}
