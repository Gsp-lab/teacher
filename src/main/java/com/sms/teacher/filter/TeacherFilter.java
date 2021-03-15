package com.sms.teacher.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sms.teacher.service.TeacherServiceImpl;

@Component
public class TeacherFilter extends OncePerRequestFilter {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TeacherServiceImpl teacherService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", authorizationHeader);
	
			HttpEntity req = new HttpEntity(headers);
			String token = null;
			String userName = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			String UserNameUrl = "http://localhost:9090/user/getusernamebytoken";
			ResponseEntity<String> user = restTemplate.exchange(UserNameUrl , HttpMethod.GET,req, String.class);
			userName = user.getBody();

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = teacherService.loadUserByUsername(userName);
				String validateUrl = "http://localhost:9090/user/validate";
				ResponseEntity<Boolean> validFlag = restTemplate.exchange(validateUrl, HttpMethod.GET,req, Boolean.class);

				if (validFlag.getBody()) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
							userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}

		}
		filterChain.doFilter(request, response);
	}

}
