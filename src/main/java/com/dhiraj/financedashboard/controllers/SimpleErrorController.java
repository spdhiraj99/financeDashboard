package com.dhiraj.financedashboard.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class SimpleErrorController implements ErrorController {

	@RequestMapping
	public String errorPage() {
		return "<h1>404 not Found!!, Please check the URL</h1>";
	}
	
}
