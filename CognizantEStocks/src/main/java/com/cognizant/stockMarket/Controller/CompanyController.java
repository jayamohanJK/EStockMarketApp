package com.cognizant.stockMarket.Controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.stockMarket.Exception.CompanyNotFoundException;
import com.cognizant.stockMarket.Exception.TurnOverValidationException;
import com.cognizant.stockMarket.Handler.ResponseHandler;
import com.cognizant.stockMarket.Model.Company;
import com.cognizant.stockMarket.Service.CompanyService;


@RestController
@RequestMapping("api/v1.0/market")
@Validated
@CrossOrigin("*")
public class CompanyController {
	
	@Autowired
	private CompanyService  cmpyService;
	
	@Value("${stockmarket.turnover.amount}")
	private int turnOver;
	
	@PostMapping("/addCompany")
	public ResponseEntity<?> saveCompany( @RequestBody @Valid Company company) throws TurnOverValidationException
	{
		if(company.getCompTurnover()<turnOver)
		{
			throw new TurnOverValidationException();
		}
		Optional<Company> comp = Optional.ofNullable(cmpyService.addCompany(company));
		if(comp.isPresent())
		{
			return ResponseHandler.genereteResponse("Company has been added Successfully", HttpStatus.CREATED, comp.get());
		}
		return ResponseHandler.genereteResponse("Company is already exist", HttpStatus.CONFLICT, company);
	}
	
	@GetMapping("/getAllCompanies")
	public ResponseEntity<?> getAllCompanies()
	{
		List<Company> complst = cmpyService.getAllcompanies();
		if(complst!=null&&!complst.isEmpty())
		{
			return ResponseHandler.genereteResponse("Companies data retreived Successfully",HttpStatus.OK,complst);
		}
		return ResponseHandler.genereteResponse("No Data Found",HttpStatus.NO_CONTENT,null);
	}
	
	@PutMapping("/updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody @Valid Company company) throws CompanyNotFoundException, TurnOverValidationException
	{
		if(company.getCompTurnover()<turnOver)
		{
			throw new TurnOverValidationException();
		}
		boolean isUpdated = cmpyService.updateCompany(company);
		if(isUpdated) {
			return ResponseHandler.genereteResponse("Company has been updated", HttpStatus.OK, null);
		}
		return ResponseHandler.genereteResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, null);
	}
	
	@GetMapping("/getCompany/{compCode}")
	public ResponseEntity<?> getCompany(@PathVariable int compCode) throws CompanyNotFoundException
	{
		Optional<Company> comp=Optional.ofNullable(cmpyService.getCompany(compCode));
		if(comp.isPresent())
		{
			return ResponseHandler.genereteResponse("Company retreived successfully ",HttpStatus.OK,comp.get());
		}
		return ResponseHandler.genereteResponse("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR,null);
	}
	
	@DeleteMapping("/deleteCompany/{compCode}")
	public ResponseEntity<?> deteleCompany(@PathVariable int compCode) throws CompanyNotFoundException
	{
		if(cmpyService.deleteCompany(compCode))
		{
			return ResponseHandler.genereteResponse("Record has been deleted successfully ",HttpStatus.OK,null);
		}
		return ResponseHandler.genereteResponse("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR,null);
	}
}
