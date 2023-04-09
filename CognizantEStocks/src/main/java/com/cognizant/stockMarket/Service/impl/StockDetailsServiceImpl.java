package com.cognizant.stockMarket.Service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.stockMarket.Exception.CompanyNotFoundException;
import com.cognizant.stockMarket.Model.StockDetail;
import com.cognizant.stockMarket.Repository.CompanyRepository;
import com.cognizant.stockMarket.Repository.StockDetailRepository;
import com.cognizant.stockMarket.Service.StockService;
import com.cognizant.stockMarket.Util.CommonUtil;

@Service
public class StockDetailsServiceImpl implements StockService {

	@Autowired
	private StockDetailRepository sdrepo;

	@Autowired
	private CompanyRepository compRepo;
	
	@Autowired
	private SeqGenerator seqGenerator;
	
	@Override
	public StockDetail addStockPrice(StockDetail stockdetail) throws CompanyNotFoundException {
		boolean flag =compRepo.existsById(stockdetail.getCompCode());
		StockDetail stdet= null;
		if(flag)
		{
			stockdetail.setSpCreatedat(CommonUtil.getDate());
			stockdetail.setId(seqGenerator.generateSequence("stock_seq"));
			stdet= sdrepo.save(stockdetail);
		}else
		{
			throw new CompanyNotFoundException("Company Doesn't exist, please recheck ");
		}
		return stdet;
	}

	@Override
	public List<StockDetail> getAllStockPriceOfCompany(StockDetail stockdetail) throws CompanyNotFoundException {
		boolean flag =compRepo.existsById(stockdetail.getCompCode());
		List<StockDetail> stDetailsList= null;
		if(flag)
		{
			stDetailsList = sdrepo.findByCompCodeAndSpCreatedatBetween(stockdetail.getCompCode(),stockdetail.getSpCreatedat(),stockdetail.getSpCreatedat());
		}else
		{
			throw new CompanyNotFoundException("Company Doesn't exist, please recheck ");
		}
		return stDetailsList;
	}

	
	
	

}
