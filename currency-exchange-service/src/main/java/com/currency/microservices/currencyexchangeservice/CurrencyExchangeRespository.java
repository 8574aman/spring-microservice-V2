package com.currency.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRespository extends JpaRepository<CurrencyExchange, Long> {

	public CurrencyExchange findByFromAndTo(String From,String To);
}
