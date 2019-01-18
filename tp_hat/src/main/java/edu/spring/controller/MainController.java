package edu.spring.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public String main(Locale locale, Model model) {
		logger.info("Main Start", locale);
		
		return "main";
	}
	
	@RequestMapping(value = "/user/point-charge")
	public String charge() {
		return "/user/point-charge";
	}

}
