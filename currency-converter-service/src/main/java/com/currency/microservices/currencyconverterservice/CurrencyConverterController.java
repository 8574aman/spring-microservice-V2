package com.currency.microservices.currencyconverterservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConverterController {
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConveter getConvertedCurrency(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity)
	{
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		// making a call to another microservice :  currencyExchange
		
		ResponseEntity<CurrencyConveter> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConveter.class, uriVariables);
		
		CurrencyConveter response = responseEntity.getBody();
		
		return new CurrencyConveter(response.getId(), from, to, quantity, response.getConversionMultiple(), quantity.multiply(response.getConversionMultiple()), response.getEnvoirnmnent());
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConveter getConvertedCurrencyFeign(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity)
	{
		CurrencyConveter response =  currencyExchangeProxy.getExchangeValue(from, to);

		return new CurrencyConveter(response.getId(), from, to, quantity, response.getConversionMultiple(), quantity.multiply(response.getConversionMultiple()), response.getEnvoirnmnent()+"Feign");

	}

}
