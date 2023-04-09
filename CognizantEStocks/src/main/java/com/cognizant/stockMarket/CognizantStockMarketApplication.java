package com.cognizant.stockMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cognizant.stockMarket.Filter.JWTFilter;


@SpringBootApplication
@EnableMongoAuditing
public class CognizantStockMarketApplication {

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean frb = new FilterRegistrationBean<>();
		frb.setFilter(new JWTFilter());
		frb.addUrlPatterns("/api/v1.0/market/*");//redirecting 
		return frb;
	}
	public static void main(String[] args) {
		SpringApplication.run(CognizantStockMarketApplication.class, args);
	}

}
