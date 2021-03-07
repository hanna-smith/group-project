package com.group.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

       @Autowired 
	private UserDetailsService userDetailsService;
       @Autowired
       AuthenticationSuccessHandler successHandler;
	    
	  @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.
	            authorizeRequests()
	                .antMatchers("/static/**", "/","/registration", "/css/**", "/js/**", "/img/**", "/search").permitAll()
	                .antMatchers("/business/**").access("hasRole('ROLE_BUSINESS')")  
	                .antMatchers("/userDashboard").access("hasRole('ROLE_USER')")
	                .anyRequest().authenticated()
	                .and()
	                .csrf().disable()
	            .formLogin()
	                .loginPage("/login")
	                .loginProcessingUrl("/login")
	                .successHandler(successHandler)
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll();
	    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }  
}