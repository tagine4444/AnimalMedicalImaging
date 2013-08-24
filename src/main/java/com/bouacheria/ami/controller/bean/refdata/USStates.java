package com.bouacheria.ami.controller.bean.refdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class USStates {

	@Resource(name="usStates")
	private Properties usStates;
	
	private List<String> usStatesCodeList;
	private Map<String,String> usStatesMap;
	
	
	
	private void load()
	{
		this.usStatesMap = new HashMap<String, String> (51);
		this.usStatesCodeList = new ArrayList<String>(51);
				
		for (Iterator iterator = usStates.entrySet().iterator(); iterator.hasNext();) {
			Entry<Object, Object> entry = (Entry<Object, Object>) iterator.next();
			
			usStatesMap.put((String)entry.getKey(), (String)entry.getValue());
			usStatesCodeList.add((String)entry.getKey());
			
		}
		Collections.sort(usStatesCodeList);
		usStatesCodeList.add(0, "");
		
	}


	public List<String> getUsStatesCodeList() {
		if(this.usStatesCodeList==null)
		{
			load();
		}
		return usStatesCodeList;
	}


	public Map<String, String> getUsStatesMap() {
		if(this.usStatesMap==null)
		{
			load();
		}
		return usStatesMap;
	}

}
