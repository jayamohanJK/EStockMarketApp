package com.cognizant.stockMarket.Model;


import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection ="StockDetail")
public class StockDetail {
	
	@Id
	@JsonIgnore
	private int id;
	
	private int compCode;
	
	private double stockPrice;
	
	private LocalDateTime spCreatedat;


	public int getCompCode() {
		return compCode;
	}

	public void setCompCode(int compCode) {
		this.compCode = compCode;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getSpCreatedat() {
		return spCreatedat;
	}

	public void setSpCreatedat(LocalDateTime spCreatedat) {
		this.spCreatedat = spCreatedat;
	}

	

	public StockDetail(int id, int compCode, double stockPrice, LocalDateTime spCreatedat) {
		super();
		this.id = id;
		this.compCode = compCode;
		this.stockPrice = stockPrice;
		this.spCreatedat = spCreatedat;
	}

	public StockDetail() {
		super();
	}

	
	
	

}
