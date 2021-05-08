package com.currency.microservices.currencyexchangeservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

@Autowired	
private Environment envoironment;

@Autowired
private CurrencyExchangeRespository CurrencyExchangeRespository;

private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

@GetMapping("/currency-exchange/from/{from}/to/{to}")
public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to)
{
	logger.info("GET currency exchange from {} to {}",from,to);
	
	CurrencyExchange currencyExchange = CurrencyExchangeRespository.findByFromAndTo(from, to);

	if(currencyExchange == null)
	{
		throw new RuntimeException("not able to find data");
	}
	String port = envoironment.getProperty("local.server.port");

	currencyExchange.setEnvoirnmnent(port);  // setting the cuurent port value to the response.
	
	return currencyExchange;   
}

}
