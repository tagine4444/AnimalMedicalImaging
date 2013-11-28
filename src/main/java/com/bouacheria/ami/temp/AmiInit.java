package com.bouacheria.ami.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bouacheria.ami.repository.config.ConfigUtil;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;

@Component
public class AmiInit {

	private boolean isAlreadyInit = false;
	
	@Autowired
	private AmiServiceServiceCached cache;
	
	@Autowired
	private ConfigUtil config;
	
	@Autowired
	private DeleteMeService deleteMeService;
	
	public void init()
	{
		if(isAlreadyInit)
		{
			System.out.println("It is already initialized");
			return;
		}
		
		loadData();
		cache.init();
		
		isAlreadyInit = true;
	}

	public boolean isAlreadyInit()
	{
		return isAlreadyInit;
	}
	
	public void loadData()
	{
		if(config.isLoadData())
		{
			deleteMeService.load();
		}
		//serviceRequestNotificationCache.cachEmail();
	}
}
