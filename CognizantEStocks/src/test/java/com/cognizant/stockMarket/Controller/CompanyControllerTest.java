package com.cognizant.stockMarket.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.cognizant.stockMarket.Model.Company;
import com.cognizant.stockMarket.Service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

	
	@InjectMocks
	private CompanyController companyController;
	
	@Mock
	private CompanyService companyService;
	
	@Autowired
	private MockMvc mockmvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(companyController).build();
	}
	
	@Test
	public void saveCompanySuccess() throws Exception
	{
		
		Company comp = new Company(112,"Google","pichai",1000000000L,"Google.com", "BSE",null);
		List<Company> com= new ArrayList<>();
		when(companyService.addCompany(any())).thenReturn(comp);
		com.add(comp);
		assertEquals(112,comp.getCompCode());
		assertEquals(1,com.size());
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/addCompany").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comp)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void saveCompanyfailure() throws Exception
	{
		Company comp = new Company();
		List<Company> com= new ArrayList<>();
		when(companyService.addCompany(any())).thenReturn(null);
		

		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/addCompany").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comp)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void updateCompanySuccess() throws Exception
	{
		Company comp = new Company(112,"Google","S pichai",1000000000L,"Google.com","BSE",null);
		List<Company> com= new ArrayList<>();
		when(companyService.updateCompany(any())).thenReturn(true);
		com.add(comp);
		assertEquals(1,com.size());
		mockmvc.perform(MockMvcRequestBuilders.put("/api/v1.0/market/updateCompany").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comp)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void updateCompanyfailure() throws Exception
	{
		Company comp = new Company();
		List<Company> com= new ArrayList<>();
		when(companyService.updateCompany(any())).thenReturn(false);
		
		//System.out.println(comp.getCompCode());
		assertEquals(0,com.size());
		mockmvc.perform(MockMvcRequestBuilders.put("/api/v1.0/market/updateCompany").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comp)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void getCompanySuccess() throws Exception
	{
		Company comp = new Company(112,"Google","pichai",1000000000L,"Google.com", "BSE",null);
		List<Company> com= new ArrayList<>();
		com.add(comp);
		when(companyService.getCompany(anyInt())).thenReturn(comp);

		//System.out.println(comp.getCompCode());
		assertEquals(1,com.size());
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/getCompany/{compCode}",comp.getCompCode()).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comp)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getCompanyfailure() throws Exception
	{
		Company comp = new Company(112,"Google","pichai",1000000000L,"Google.com","BSE",null);
		List<Company> com= new ArrayList<>();
		when(companyService.getCompany(anyInt())).thenReturn(null);

		//System.out.println(comp.getCompCode());
		assertEquals(0,com.size());
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/getCompany/{compCode}",33).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comp)))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
	@Test
	public void getAllCompaiesSuccess() throws Exception
	{
		Company comp = new Company(112,"Google","pichai",1000000000L,"Google.com", "BSE",null);
		List<Company> com= new ArrayList<>();
		when(companyService.getAllcompanies()).thenReturn(com);
		com.add(comp);
		//System.out.println(comp.getCompCode());
		assertEquals(1,com.size());
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/getAllCompanies").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comp)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getAllCompaiesFailure() throws Exception
	{
		Company comp = new Company();
		List<Company> com= new ArrayList<>();
		when(companyService.getAllcompanies()).thenReturn(null);
		com.add(comp);
		//System.out.println(comp.getCompCode());
		assertEquals(1,com.size());
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/getAllCompanies").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comp)))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	public void deleteCompanySuccess() throws Exception
	{
		
		when(companyService.deleteCompany(anyInt())).thenReturn(true);

		//System.out.println(comp.getCompCode());
		//assertEquals(true,true);
		mockmvc.perform(MockMvcRequestBuilders.delete("/api/v1.0/market/deleteCompany/{compCode}",112).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteCompanyfailure() throws Exception
	{
		when(companyService.deleteCompany(anyInt())).thenReturn(false);
		
		//System.out.println(comp.getCompCode());
		//assertEquals(true,true);
		mockmvc.perform(MockMvcRequestBuilders.delete("/api/v1.0/market/deleteCompany/{compCode}",112).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
	
}
