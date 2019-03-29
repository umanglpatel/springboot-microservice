package com.demo.api.oauth2.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.demo.api.oauth2.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// https://stackoverflow.com/questions/44171633/spring-boot-oauth2-access-is-denied-user-is-anonymous-redirecting-to-authen?rq=1
	// https://stackoverflow.com/questions/42822875/springboot-1-5-x-security-oauth2
	// check more on filter orders

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	// @Bean
	// public CorsFilter corsFilter() {
	// final UrlBasedCorsConfigurationSource source = new
	// UrlBasedCorsConfigurationSource();
	// final CorsConfiguration config = new CorsConfiguration();
	// config.setAllowCredentials(true);
	// config.addAllowedOrigin("*");
	// config.addAllowedHeader("*");
	// config.addAllowedMethod("OPTIONS");
	// config.addAllowedMethod("HEAD");
	// config.addAllowedMethod("GET");
	// config.addAllowedMethod("PUT");
	// config.addAllowedMethod("POST");
	// config.addAllowedMethod("DELETE");
	// config.addAllowedMethod("PATCH");
	// source.registerCorsConfiguration("/**", config);
	// System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
	// CorsFilter corsFilter = new CorsFilter(source);
	// corsFilter.setBeanName("corsFilter");
	// return corsFilter;
	// }

	// @Bean
	// public FilterRegistrationBean corsFilter() {
	// UrlBasedCorsConfigurationSource source = new
	// UrlBasedCorsConfigurationSource();
	// CorsConfiguration config = new CorsConfiguration();
	// config.setAllowCredentials(true);
	// config.addAllowedOrigin("*");
	// config.addAllowedHeader("*");
	// config.addAllowedMethod("*");
	// source.registerCorsConfiguration("/**", config);
	// FilterRegistrationBean bean = new FilterRegistrationBean(new
	// CorsFilter(source));
	// bean.setOrder(0);
	// return bean;
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO : users/ should be accessible without security. Not working right now
		// may be some filter issue
		http.authorizeRequests().antMatchers("/users/**").permitAll().anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(entryPoint()).and().csrf().disable();
	}

	private AuthenticationEntryPoint entryPoint() {
		return new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				authException.printStackTrace();
				System.out.println(authException.getCause());
			}
		};
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
