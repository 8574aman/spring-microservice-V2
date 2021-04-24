package com.currency.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class CurrencyExchange {

@Id
private Long id;

@Column(name="currency_from") // since "from" is a keyword in SQL so it will throw a syntax error while creating a table 
private String from;
@Column(name="currency_to")
private String to;
private BigDecimal conversionMultiple;
@Transient
private String envoirnmnent;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFrom() {
	return from;
}
public CurrencyExchange()
{}
public String getEnvoirnmnent() {
	return envoirnmnent;
}
public void setEnvoirnmnent(String envoirnmnent) {
	this.envoirnmnent = envoirnmnent;
}
public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
	super();
	this.id = id;
	this.from = from;
	this.to = to;
	this.conversionMultiple = conversionMultiple;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
public BigDecimal getConversionMultiple() {
	return conversionMultiple;
}
public void setConversionMultiple(BigDecimal conversionMultiple) {
	this.conversionMultiple = conversionMultiple;
}

}
