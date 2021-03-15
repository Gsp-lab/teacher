package com.sms.teacher.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sms.teacher.filter.TeacherFilter;
import com.sms.teacher.service.TeacherServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private TeacherServiceImpl teacherService;

	@Autowired
	private TeacherFilter teacherFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(teacherService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().
		 * exceptionHandling().and()
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 * http.addFilterBefore(teacherFilter,
		 * UsernamePasswordAuthenticationFilter.class);
		 */
		http
	       .authorizeRequests()
	           .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	           .antMatchers("/logout").permitAll()
	           .anyRequest().fullyAuthenticated()
	           .and()
	       .httpBasic()
	           .and()
	       .sessionManagement()
	           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	           .and()
	       .csrf().disable();
		http.addFilterBefore(teacherFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
