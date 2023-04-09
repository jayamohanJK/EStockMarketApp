package com.cognizant.stockMarket.Service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.stockMarket.Exception.CompanyNotFoundException;
import com.cognizant.stockMarket.Model.Company;
import com.cognizant.stockMarket.Model.StockDetail;
import com.cognizant.stockMarket.Repository.CompanyRepository;
import com.cognizant.stockMarket.Repository.StockDetailRepository;
import com.cognizant.stockMarket.Service.CompanyService;
import com.cognizant.stockMarket.Util.CommonUtil;

@Service
public class CompanyServiceimpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private SeqGenerator seqGenerator;
	
	@Autowired
	private StockDetailRepository stockDetailRepo;
	
	@Override
	public List<Company> getAllcompanies() {
		List<Company> comli = companyRepo.findAll();
		for(int i=0;i<comli.size();i++)
		{
			Company comp= comli.get(i);
			List<StockDetail> stli= new ArrayList<>();
			comp.setStockDetail(stockDetailRepo.findFirstByCompCodeOrderBySpCreatedatDesc(comp.getCompCode()));
		}
		return comli;
	}

	@Override
	public Company addCompany(Company company) {
		
		if(company!=null)
		{
			company.setCompCode(seqGenerator.generateSequence("company_seq"));
			company.setCreatedDate(CommonUtil.getDate());
			return companyRepo.save(company);
		}
		return company;
	}

	@Override
	public boolean deleteCompany(int companycode) throws CompanyNotFoundException {
		if(companyRepo.existsById(companycode))
		{
			stockDetailRepo.deleteByCompCode(companycode);
			companyRepo.deleteById(companycode);
			return true;
		}else
		{
			throw new CompanyNotFoundException("Company doesn't exist");
		}
	}

	@Override
	public boolean updateCompany(Company company) throws CompanyNotFoundException{
		boolean iscmpyAvailable= companyRepo.existsById(company.getCompCode());
		if(iscmpyAvailable)
		{
			company.setUpdatedDate(CommonUtil.getDate());
			 companyRepo.save(company);
			 return true;
		}
		throw new CompanyNotFoundException("Company doesn't exist");
	}
	
	@Override
	public Company getCompany(int compCode) throws CompanyNotFoundException
	{
		Optional<Company> comp=stockDetailRepo.existsByCompCode(compCode)?
				Optional.ofNullable(companyRepo.findByCompCode(compCode)):companyRepo.findById(compCode);
		
		if(comp.isPresent())
		{
			Company com= comp.get();
			com.setStockDetail(stockDetailRepo.findFirstByCompCodeOrderBySpCreatedatDesc(com.getCompCode()));
			return com;
		}
		
		throw new CompanyNotFoundException("Company doesn't exist");
	}
	
	
}
