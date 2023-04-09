package com.cognizant.stockMarket.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.cognizant.stockMarket.Model.Company;

public class CompanyTest {

	
	@Test
	public void test01()
	{
		Company company= Mockito.mock(Company.class);
		assertEquals(null, company.getCompCeo());
		assertEquals(0, company.getCompCode());
		assertEquals(null, company.getCompName());
		assertEquals(0, company.getCompTurnover());
		assertEquals(null, company.getCompWebsite());
		assertEquals(null, company.getStockExchange());
		
		
		when(company.getCompCeo()).thenReturn("pichai");
		when(company.getCompCode()).thenReturn(112);
		when(company.getCompName()).thenReturn("Google");
		when(company.getCompTurnover()).thenReturn(100000000000L);
		when(company.getCompWebsite()).thenReturn("Google.com");
		when(company.getStockExchange()).thenReturn("BSE");
		
		Company cmp= new Company(112,"Google","pichai",100000000000L,"Google.com","BSE",null,null,null);
		
		assertEquals("pichai", cmp.getCompCeo());
		assertEquals(112, cmp.getCompCode());
		assertEquals("Google", cmp.getCompName());
		assertEquals(100000000000L, cmp.getCompTurnover());
		assertEquals("Google.com", cmp.getCompWebsite());
		assertEquals("BSE", cmp.getStockExchange());
	}
}
