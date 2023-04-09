package com.cognizant.stockMarket.Model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Document(collection ="Company")
public class Company {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int compCode;
	
	@NotNull
	@NotBlank
	//@Column(nullable = false)
	private String compName;
	
	@NotNull
	@NotBlank
	//@Column(nullable = false)
	private String compCeo;
	
	//@Column(nullable = false)
	private long compTurnover;
	
	@NotNull
	@NotBlank
	//@Column(nullable = false)
	private String compWebsite;
	
	@NotNull
	@NotBlank
	private String stockExchange;
	
	@Transient
	private StockDetail stockDetail;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedDate;

	public Company() {
		super();
	}

	public Company(int compCode, String compName, String compCeo, long compTurnover, String compWebsite,
			String stockExchange, StockDetail stockDetail, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		//StockDetail li = new ArrayStockDetail();
		//li.add(stockDetail);
		this.compCode = compCode;
		this.compName = compName;
		this.compCeo = compCeo;
		this.compTurnover = compTurnover;
		this.compWebsite = compWebsite;
		this.stockExchange = stockExchange;
		this.stockDetail = stockDetail;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Company(int compCode, @NotNull @NotBlank String compName, @NotNull @NotBlank String compCeo,
			long compTurnover, @NotNull @NotBlank String compWebsite, @NotNull @NotBlank String stockExchange,
			StockDetail stockDetail) {
		super();
		this.compCode = compCode;
		this.compName = compName;
		this.compCeo = compCeo;
		this.compTurnover = compTurnover;
		this.compWebsite = compWebsite;
		this.stockExchange = stockExchange;
		this.stockDetail = stockDetail;

	}

	public int getCompCode() {
		return compCode;
	}

	public void setCompCode(int compCode) {
		this.compCode = compCode;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompCeo() {
		return compCeo;
	}

	public void setCompCeo(String compCeo) {
		this.compCeo = compCeo;
	}

	public long getCompTurnover() {
		return compTurnover;
	}

	public void setCompTurnover(long compTurnover) {
		this.compTurnover = compTurnover;
	}

	public String getCompWebsite() {
		return compWebsite;
	}

	public void setCompWebsite(String compWebsite) {
		this.compWebsite = compWebsite;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public StockDetail getStockDetail() {
		return stockDetail;
	}

	public void setStockDetail(StockDetail stockDetail) {
		this.stockDetail = stockDetail;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
