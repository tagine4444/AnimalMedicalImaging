package com.bouacheria.ami.service.datatype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.datatype.Breed;
import com.bouacheria.ami.domain.datatype.Labs;
import com.bouacheria.ami.domain.datatype.Species;

@Service
public class DataTypeService {
	
	@Autowired
	private SpeciesService speciesService;
	private List<String> species;
	
	@Autowired
	private BreedService breedService;
	private List<String> breedList;
	private Map<String, List<String>> speciesBreedMap;
	
	@Autowired
	private LabsService labsService;
	private List<String> labsList;
	
	
	/**
	 * @return
	 * 
	 * This is a way to cache the species.
	 */
	public List<String> getSpeciesList()
	{
		if(species==null){
			species = new ArrayList<String>();
			List<Species> dbSpecies = speciesService.findAll();
			if(dbSpecies!=null)
			{
				for (Iterator<Species> iterator = dbSpecies.iterator(); iterator.hasNext();) 
				{
					Species aSpecies =  iterator.next();
					species.add(aSpecies.getName());
				}
				
			}
		}
		return species;
	}
	
	public List<String> getBreedList()
	{
		if(breedList==null)
		{
			breedList = new ArrayList<String>();
			List<Breed> dbBreeds = breedService.findAll();
			if(dbBreeds!=null)
			{
				for (Iterator<Breed> iterator = dbBreeds.iterator(); iterator.hasNext();) 
				{
					Breed aBreed =  iterator.next();
					breedList.add(aBreed.getName());
				}
				
			}
		}
		return breedList;
	}
	
	public Map<String, List<String>> getSpeciesBreedMap()
	{
		if(speciesBreedMap==null)
		{
			speciesBreedMap = new HashMap<String, List<String>>();
			List<Breed> dbBreeds = breedService.findAll();
			if(dbBreeds!=null)
			{
				for (Iterator<Breed> iterator = dbBreeds.iterator(); iterator.hasNext();) 
				{
					Breed aBreed =  iterator.next();
					List<String> breedList = speciesBreedMap.get(aBreed.getSpecies());
					if (breedList == null) 
					{
						breedList = new ArrayList<String>();
						speciesBreedMap.put(aBreed.getSpecies(), breedList);
					}
					breedList.add(aBreed.getName());
				}
				
			}
		}
		return speciesBreedMap;
	}
	
	public List<String> getLabsList()
	{
		if(labsList==null){
			labsList = new ArrayList<String>();
			List<Labs> list = labsService.findAll();
			if(list!=null)
			{
				for (Iterator<Labs> iterator = list.iterator(); iterator.hasNext();) 
				{
					Labs aLab = iterator.next();
					labsList.add(aLab.getName());
				}
			}
		}
		return labsList;
	}
	
	
}
