package com.taskForUser.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtauthenticationFilter extends OncePerRequestFilter {
	@Autowired
    private JWtTokenGenerate jWtTokenGenerate;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
        if(StringUtils.hasText(token) && jWtTokenGenerate.validateToken(token)) {
        	String email = jWtTokenGenerate.getEmailFromToken(token);
        	UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        	UsernamePasswordAuthenticationToken authentication=
        			new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        	SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			return header.substring(7, header.length());
		}
		return null;
	}
}
