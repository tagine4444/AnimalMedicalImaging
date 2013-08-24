package com.bouacheria.ami.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;
import com.bouacheria.ami.service.datatype.DataTypeService;

@Component
public class ServiceRequestAndCaseHelper {
	
	
	@Autowired
	private DataTypeService dataTypeService;
	
	@Autowired
	private AmiServiceServiceCached amiServiceServiceCached;
	

	public void addRefDataToModel(Model model) 
	{
		List<String> speciesList = dataTypeService.getSpeciesList();
		List<String> breedList = dataTypeService.getBreedList();
		Map<String, List<String>> speciesBreedMap = dataTypeService.getSpeciesBreedMap();
		List<String> labsList = dataTypeService.getLabsList();
		
		List<String> ultrasoundSvcList = amiServiceServiceCached.getUltrasoundSvcList();
		List<String> radiographyFluoroscopySvcList = amiServiceServiceCached.getRadiographyFluoroscopyServicesList();
		List<String> contrastRadiographySvcList = amiServiceServiceCached.getContrastRadiographyServicesList();
		List<String> computedTomographySvcList = amiServiceServiceCached.getComputedTomographyServicesList();
		List<String> MRISvcList = amiServiceServiceCached.getMRIServicesList();

		model.addAttribute("labsList",labsList);
		model.addAttribute("speciesList",speciesList);
		model.addAttribute("breedList",breedList);
		model.addAttribute("speciesBreedMap",speciesBreedMap);
		
		model.addAttribute("ultrasoundSvcList",ultrasoundSvcList);
		model.addAttribute("radiographyFluoroscopySvcList",radiographyFluoroscopySvcList);
		model.addAttribute("contrastRadiographySvcList",contrastRadiographySvcList);
		model.addAttribute("computedTomographySvcList",computedTomographySvcList);
		model.addAttribute("MRISvcList",MRISvcList);
	}
}
