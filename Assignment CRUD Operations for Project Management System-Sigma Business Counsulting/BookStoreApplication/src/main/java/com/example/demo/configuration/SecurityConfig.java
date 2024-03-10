package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.entity.User;
import com.example.demo.security.CustomUserDetailService;
import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)

public class SecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	

	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
            
        	csrf()
        	.disable()
        	.authorizeHttpRequests()
        	.antMatchers(PUBLIC_URLS)
        	.permitAll()
        	.antMatchers(HttpMethod.GET)
        	.permitAll()
        	.anyRequest()
        	.authenticated()
        	.and().exceptionHandling()
        	.AuthenticationEntryPoint(this.jwtAuthenticationEntryPoint)
        	.and()
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        	http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter)
        	http.authenticationProvider(daoAuthenticationProvider());
        	DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
        	return defaultSecurityFilterChain;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration)throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	
	
	@Bean
	public FilterRegistrationBean coresFilter() {
		UrlBeanCorsConfigurationSource source=new UrlBasedCorsConfigurationSource

	CorsConfiguration corsConfiguration =new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedDriginPattern("*");
		corsConfiguration.addAllowedHeader("Authorization");
		corsConfiguration.addAllowedHeader("Content-Type");
		corsConfiguration.addAllowedHeader("Accept");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("DELETE");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("OPTIONS");
		corsConfiguration.setMaxAge(3600L);
		
		source.registrationCorsConfiguration(pattern:"/**", corsconfiguration);
		
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.builder()
                .username("user1")
                .password(encoder().encode("user1c cc"))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1);
    }
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	

}
