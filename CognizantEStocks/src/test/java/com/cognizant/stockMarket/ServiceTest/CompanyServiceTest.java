package com.cognizant.stockMarket.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.stockMarket.Controller.CompanyController;
import com.cognizant.stockMarket.Exception.CompanyNotFoundException;
import com.cognizant.stockMarket.Model.Company;
import com.cognizant.stockMarket.Repository.CompanyRepository;
import com.cognizant.stockMarket.Repository.StockDetailRepository;
import com.cognizant.stockMarket.Service.CompanyService;
import com.cognizant.stockMarket.Service.impl.CompanyServiceimpl;
import com.cognizant.stockMarket.Service.impl.SeqGenerator;
import com.cognizant.stockMarket.Util.CommonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyServiceTest {


	
	@InjectMocks
	private CompanyServiceimpl companyService;
	
	@Mock
	private CompanyRepository companyRepo;
	
	@Mock
	private StockDetailRepository stockDetailRepo;
	
	@Mock
	private Company company;
	
	@Mock
	private SeqGenerator seqGenerator;
	
	@Autowired
	private MockMvc mockmvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(companyService).build();
	}
	
	@Test
	public void saveCompanySuccess() 
	{
		Company comp = new Company(0,"Google","pichai",1000000000L,"Google.com", "BSE",null);
		when(companyRepo.save(any())).thenReturn(comp);
		when(seqGenerator.generateSequence("")).thenReturn(0);
		doNothing().when(company).setCompCode(0);
		doNothing().when(company).setCreatedDate(CommonUtil.getDate());
		Company cm= companyService.addCompany(comp);
		assertEquals(0,cm.getCompCode());
	}
	
	@Test
	public void saveCompanyfailure() 
	{
		Company comp = null;
		when(companyRepo.save(any())).thenReturn(null);
		Company cm=companyService.addCompany(comp);
		assertNull(cm);
	}
	
	@Test
	public void updateCompanySuccess() throws CompanyNotFoundException
	{
		Company comp = new Company(0,"Google","pichai",1000000000L,"Google.com", "BSE",null);
		when(companyRepo.existsById(0)).thenReturn(true);
		when(companyRepo.save(any())).thenReturn(comp);
		boolean res=companyService.updateCompany(comp);
		assertTrue(res);

	}
	
	@Test
	public void updateCompanyfailure() throws Exception
	{
		Company comp = new Company();
		List<Company> com= new ArrayList<>();
		when(companyRepo.findById(114)).thenReturn(null);
		CompanyNotFoundException thrown = assertThrows(CompanyNotFoundException.class,
				()->companyService.updateCompany(comp),"Company error");
		assertNotNull(thrown);
		assertTrue(thrown.getMessage().contains("Company doesn't exist"));
	}
	
	@Test
	public void getCompanySuccess() throws Exception
	{
		Company comp = new Company(112,"Google","pichai",1000000000L,"Google.com", "BSE",null);
		when(stockDetailRepo.existsByCompCode(anyInt())).thenReturn(true);
		when(companyRepo.findByCompCode(anyInt())).thenReturn(comp);
		Company cm=companyService.getCompany(112);
		assertEquals(112,cm.getCompCode());
		assertEquals("Google",cm.getCompName());
	}
	
	@Test
	public void getCompanyfailure() throws Exception
	{
		Company comp = new Company();
		List<Company> com= new ArrayList<>();
		when(companyRepo.findById(114)).thenReturn(Optional.ofNullable(null));
		CompanyNotFoundException thrown = assertThrows(CompanyNotFoundException.class,
				()->companyService.getCompany(114),"Company error");
		assertNotNull(thrown);
		assertTrue(thrown.getMessage().contains("Company doesn't exist"));
	}
	
	@Test
	public void getAllCompaiesSuccess() throws Exception
	{
		Company comp = new Company(112,"Google","pichai",1000000000L,"Google.com", "BSE", null);
		List<Company> com= new ArrayList<>();
		com.add(comp);
		when(companyRepo.findAll()).thenReturn(com);
		
		List<Company> licom=companyService.getAllcompanies();
		assertEquals(1,licom.size());
		
	}
	
	@Test
	public void getAllCompaiesFailure() throws Exception
	{
		List<Company> comLi= new ArrayList<Company>();
		when(companyRepo.findAll()).thenReturn(comLi);
		
		List<Company> com=companyService.getAllcompanies();
		assertEquals(0,comLi.size());
		
	}
	
	@Test
	public void deleteCompanySuccess() throws Exception
	{
		
		when(companyRepo.existsById(anyInt())).thenReturn(true);
		
		doNothing().when(companyRepo).deleteById(112);
		when(stockDetailRepo.deleteByCompCode(112)).thenReturn(1);
		
		boolean res=companyService.deleteCompany(112);
		
		verify(companyRepo).existsById(112);
		verify(companyRepo).deleteById(112);
		assertTrue(res);
		
	}
	
	
	@Test 
	public void deleteCompanyfailure() throws Exception 
	  { 
		Company comp = new Company();
		List<Company> com= new ArrayList<>();
		when(companyRepo.findById(114)).thenReturn(Optional.ofNullable(null));
		CompanyNotFoundException thrown = assertThrows(CompanyNotFoundException.class,
				()->companyService.deleteCompany(114),"Company error");
		assertNotNull(thrown);
		assertTrue(thrown.getMessage().contains("Company doesn't exist"));
	  }
	 
	

}
