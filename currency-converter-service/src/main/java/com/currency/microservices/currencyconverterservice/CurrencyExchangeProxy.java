package com.currency.microservices.currencyconverterservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// this interface creates a FeignClient which will talk to the currency Exchnage service.
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	
	// we have to provide definition of the method to call from the Currency-Exchange service
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConveter getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
