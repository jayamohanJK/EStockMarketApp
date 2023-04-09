package com.cognizant.stockMarket.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.stockMarket.Exception.CompanyNotFoundException;
import com.cognizant.stockMarket.Handler.ResponseHandler;
import com.cognizant.stockMarket.Model.Company;
import com.cognizant.stockMarket.Model.StockDetail;
import com.cognizant.stockMarket.Service.StockService;


@RestController
@RequestMapping("api/v1.0/market")
@CrossOrigin
public class StockDetailController {

	@Autowired
	private StockService stockservice;
	
	@PostMapping("/stock/add/{compcode}")
	public ResponseEntity<?> addCompanyStockPrice(@PathVariable int compcode,@RequestBody StockDetail stockdetail) throws CompanyNotFoundException
	{
		if(stockdetail!= null&&compcode>0)
		{
			stockdetail.setCompCode(compcode);
			Optional<StockDetail> stDetail = Optional.ofNullable(stockservice.addStockPrice(stockdetail));
			if(stDetail.isPresent())
			{
				return ResponseHandler.genereteResponse("Stock Price has been added Successfully", HttpStatus.CREATED, stDetail.get());
			}
		}
		return ResponseHandler.genereteResponse("Unable to add stock price", HttpStatus.INTERNAL_SERVER_ERROR, stockdetail);
	}
	
	@PutMapping("/stock/put/{compcode}")
	public ResponseEntity<?> updateCompanyStockPrice(@PathVariable int compcode,@RequestBody StockDetail stockdetail) throws CompanyNotFoundException
	{
		if(stockdetail!= null&&compcode>0)
		{
			stockdetail.setCompCode(compcode);
			Optional<StockDetail> stDetail = Optional.ofNullable(stockservice.addStockPrice(stockdetail));
			if(stDetail.isPresent())
			{
				return ResponseHandler.genereteResponse("Stock Price has been added Successfully", HttpStatus.CREATED, stDetail.get());
			}
		}
		return ResponseHandler.genereteResponse("Unable to add stock price", HttpStatus.INTERNAL_SERVER_ERROR, stockdetail);
	}
	
	@PostMapping("/stock/get")
	public  ResponseEntity<?> getStockPrices( @RequestBody StockDetail stockDetail) throws CompanyNotFoundException
	{
		Optional<List<StockDetail>> stDetail = Optional.ofNullable(stockservice.getAllStockPriceOfCompany(stockDetail));
		if(stDetail.isPresent())
		{
			return ResponseHandler.genereteResponse("Stock Prices retreived", HttpStatus.OK, stDetail.get());
		}
		return ResponseHandler.genereteResponse("Unable to add stock price", HttpStatus.NOT_FOUND, null);
	}
}
