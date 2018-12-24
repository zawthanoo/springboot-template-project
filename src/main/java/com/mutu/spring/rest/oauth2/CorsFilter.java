package com.mutu.spring.rest.oauth2;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
@Configuration
public class CorsFilter extends OncePerRequestFilter {

	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");// * or origin as u prefer
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "application/x-www-form-urlencoded, x-requested-with, Content-Type, origin, Authorization, accept, client-security-token, Content-Disposition");
		response.setHeader("Access-Control-Expose-Headers", "Authorization, Content-Disposition");

		if (request.getMethod().equals("OPTIONS"))
			response.setStatus(HttpServletResponse.SC_OK);
		else
			filterChain.doFilter(request, response);

	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		doFilter(request, response, filterChain);
		
	}
}	