package com.currency.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.LoggingEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	Logger logger = LoggerFactory.getLogger(LoggingEvent.class);
	int counter = 0;
	
	@GetMapping("/sample-api")
	@Retry(name = "sample-api" //config named for "sample-api" set in application.properties = "default" for 3 
		   ,fallbackMethod = "hardCodedFallBackMethod") // fallbackmethod will be called in case when we dont get response from the API.
	public String sampleApi()
	{
		logger.info("Try hitting dummy-url {}",++counter);
		ResponseEntity<String> res = new RestTemplate().getForEntity("http://localhost:8222/dummy-url", String.class);
		return res.getBody();
	}

	@GetMapping("/sample-api-circuitbreaker")
	@CircuitBreaker(name = "default"  
		   ,fallbackMethod = "hardCodedFallBackMethod") // fallbackmethod will be called in case when we dont get response from the API.
	public String sampleApiCircuit()
	{
		logger.info("Try hitting dummy-url {}",++counter);
		ResponseEntity<String> res = new RestTemplate().getForEntity("http://localhost:8222/dummy-url", String.class);
		return res.getBody();
	}

	
	private String hardCodedFallBackMethod(Exception ex)
	{
		return "FallbackResponse";
	}
}
