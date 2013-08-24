package com.bouacheria.ami.controller.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.bouacheria.ami.domain.amiservices.AmiService;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;

//@Component("amiServiceFormatter")
public class AmiServiceFormatter  implements Formatter<AmiService> {

	@Autowired
	private AmiServiceServiceCached cachedSvc;
	
	@Override
	public String print(AmiService amiService, Locale locale) {
//		if(amiService!=null){
//			System.out.println("MRISvcListFormatter: "+ amiService.getId() + " "+amiService.getName() );
//		}
		return String.valueOf(amiService.getId());
	}

	@Override
	public AmiService parse(String idStr, Locale locale) throws ParseException {
		long id = Long.valueOf(idStr);
		AmiService amiService = cachedSvc.getCacheId().get(id);
		return amiService;
	}

}
