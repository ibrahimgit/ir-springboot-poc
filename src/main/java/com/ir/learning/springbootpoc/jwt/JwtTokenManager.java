package com.ir.learning.springbootpoc.jwt;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenManager {
	
	@Value("${secret.token}")
	private String secretToken;
	
	private static final String ISSUER = "POC";
	private static final String AUTHORITY = "authorities";
	
	public String createJWTToken(String userName, List<String> roles) {
		Calendar validity = Calendar.getInstance();
		validity.add(Calendar.MINUTE, 30);
		
		return Jwts.builder()
			.setSubject(userName)
			.setIssuer(ISSUER)
			.setId(UUID.randomUUID().toString())
			.setIssuedAt(new Date())
			.setExpiration(validity.getTime())
			.claim(AUTHORITY, roles)
			.signWith(SignatureAlgorithm.HS256, secretToken)
			.compact();
	}

	public boolean validateJwtToken(String jwtToken) {
		Jwts.parser().setSigningKey(secretToken).parseClaimsJws(jwtToken);
		return true;
		
	}

	public Authentication getAuthentication(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(secretToken).parseClaimsJws(jwt).getBody();
		String username = claims.getSubject();
		List<String> roles = claims.get(AUTHORITY, List.class);
		List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		User user = new User(username, "", authorities);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, "", authorities);
		return authentication;
	}

}
