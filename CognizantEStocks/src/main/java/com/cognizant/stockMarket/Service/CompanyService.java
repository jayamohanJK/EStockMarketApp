package com.cognizant.stockMarket.Service;

import java.util.List;
import java.util.Optional;

import com.cognizant.stockMarket.Exception.CompanyNotFoundException;
import com.cognizant.stockMarket.Model.Company;

public interface CompanyService {

	public List<Company> getAllcompanies();
	
	public Company addCompany(Company company);
	
	public boolean deleteCompany(int companycode) throws CompanyNotFoundException;
	
	public boolean updateCompany(Company company) throws CompanyNotFoundException;
	
	public Company getCompany(int compCode) throws CompanyNotFoundException;
}
