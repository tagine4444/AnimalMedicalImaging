package com.bouacheria.ami.controller;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public abstract class AbstractAmiController
{

	@ExceptionHandler(Throwable.class)
	public 	ModelAndView handleException(Throwable exception)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("errorPage");
		view.addObject("errorString", ExceptionUtils.getStackTrace(exception));
		return view;
	}
	@ExceptionHandler(Exception.class)
	public 	ModelAndView handleException(Exception exception)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("errorPage");
		view.addObject("errorString", ExceptionUtils.getStackTrace(exception));
		return view;
	}
	@ExceptionHandler(RuntimeException.class)
	public 	ModelAndView handleException(RuntimeException exception)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("errorPage");
		view.addObject("errorString", ExceptionUtils.getStackTrace(exception));
		return view;
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public 	ModelAndView handleException(IllegalArgumentException exception)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("errorPage");
		view.addObject("errorString", ExceptionUtils.getStackTrace(exception));
		return view;
	}
}
