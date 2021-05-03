package com.currency.microservices.currencyexchangeservice;


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

@GetMapping("/currency-exchange/from/{from}/to/{to}")
public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to)
{
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
