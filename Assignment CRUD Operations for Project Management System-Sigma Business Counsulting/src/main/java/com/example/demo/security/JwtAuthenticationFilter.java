package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get token
		String requestToken = request.getHeader("Authorization");
		
		System.out.println(requestToken);
		String token=null;
		
		if(requestToken!=null &&requestToken.startsWith("Bearer")) {
			token= requestToken.substring(7);
			
			try {
			
			username=this.jwtTokenHelper.getUsernameFromToken(token);
		}
			catch(IllegalArgumentException e)
			{
				System.out.println("unable to get jwt token");
			}
		
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails =this.userDetailService.loadUserByUsername(username);
				
				if(this.jwtTokenHelper.validateToken(token, userDetails)) 
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				else {
					System.out.println("Invalid Jwt Token");
				}
			}
				else {
					System.out.println("Usename is null or context is null");
				}
			filterChain.doFilter(request, response);
			
			}
	
	}

}
