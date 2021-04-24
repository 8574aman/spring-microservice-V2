package com.currency.microservices.currencyconverterservice;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConverterController {
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConveter getConvertedCurrency(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity)
	{
		return new CurrencyConveter(1001L, from, to, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, "8000");
	}

}
