package com.cognizant.stockMarket.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpReq= (HttpServletRequest)request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		String authHeader  = httpReq.getHeader("Authorization");
		httpRes.setHeader("Access-Control-Allow-Origin", "*");
		httpRes.setHeader("Access-Control-Allow-Credentials", "true");
		httpRes.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE,PUT");
		httpRes.setHeader("Access-Control-Max-Age", "3600");
		httpRes.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept,Authorization, X-Requested-With, remember-me");
		if (CorsUtils.isPreFlightRequest(httpReq)) {
			httpRes.setStatus(HttpServletResponse.SC_OK);

	    }else
	    {
			if(authHeader ==null || !authHeader.startsWith("Bearer"))
			{
				throw new ServletException("Missing or invalid Authentication Header");
			}
			String jwtToken =authHeader.substring(7);
			Claims claims =Jwts.parser().setSigningKey("super key").parseClaimsJws(jwtToken).getBody();
			
			httpReq.setAttribute(authHeader, jwtToken);
			
			chain.doFilter(request, response);
	    }
	
	}

}