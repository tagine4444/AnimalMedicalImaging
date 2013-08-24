package com.bouacheria.ami.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;

@Component
public class AmiInit {

	private boolean isAlreadyInit = false;
	
	@Autowired
	private AmiServiceServiceCached cache;
	
	public void init()
	{
		if(isAlreadyInit)
		{
			System.out.println("It is already initialized");
			return;
		}
		cache.init();
		
		isAlreadyInit = true;
	}
}
