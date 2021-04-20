package com.currency.microservices.limits_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currency.microservices.limits_service.beans.Limits;
import com.currency.microservices.limits_service.configuration.Configuration;

@RestController
public class LimitsController {
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	private Limits getLimits()
	{
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
	}
}

