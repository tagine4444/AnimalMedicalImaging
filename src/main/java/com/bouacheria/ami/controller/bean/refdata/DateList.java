package com.bouacheria.ami.controller.bean.refdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DateList {

	public List<Integer> getDaysList()
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);

		
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		list.add(17);
		list.add(18);
		list.add(19);
		
		list.add(20);
		list.add(21);
		list.add(22);
		list.add(23);
		list.add(24);
		list.add(25);
		list.add(26);
		list.add(27);
		list.add(28);
		list.add(29);
		
		list.add(30);
		list.add(31);
		return list;
	}
	public List<Integer> getMonthsList()
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		
		
		list.add(10);
		list.add(11);
		list.add(12);
		
		return list;
	}
}
