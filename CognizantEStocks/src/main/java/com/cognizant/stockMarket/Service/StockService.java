package com.cognizant.stockMarket.Service;

import java.util.List;

import com.cognizant.stockMarket.Exception.CompanyNotFoundException;
import com.cognizant.stockMarket.Model.StockDetail;

public interface StockService {
	
	public StockDetail addStockPrice(StockDetail stockdetail) throws CompanyNotFoundException;
	
	public List<StockDetail> getAllStockPriceOfCompany(StockDetail stockdetail) throws CompanyNotFoundException;
}
