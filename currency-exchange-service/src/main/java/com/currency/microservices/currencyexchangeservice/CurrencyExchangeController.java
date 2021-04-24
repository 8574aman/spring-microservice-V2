package com.currency.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

@Autowired	
private Environment envoironment;
	
@GetMapping("/currency-exchange/from/{from}/to/{to}")
public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to)
{
	
	CurrencyExchange currencyExchange = new CurrencyExchange(100L,from,to,BigDecimal.valueOf(65));

	String port = envoironment.getProperty("local.server.port");

	currencyExchange.setEnvoirnmnent(port);  // setting the cuurent port value to the response.
	return currencyExchange;
}

}
