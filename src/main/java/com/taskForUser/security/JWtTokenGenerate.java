package com.taskForUser.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.taskForUser.exceptionpack.ApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWtTokenGenerate {
	public String generateToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, email);
	}

	private String createToken(Map<String, Object> claims, String email) {
		Date currentdate = new Date(System.currentTimeMillis());
		Date expiredate = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
		String token = Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(currentdate)
				.setExpiration(expiredate).signWith(secKey(), SignatureAlgorithm.HS256).compact();
		return token;
	}

	private Key secKey() {
		byte[] keybytes = Decoders.BASE64.decode("KQtsrJCtPVNYlWEGwPV6nr5PK3LjW5gnRZoDlVSAFIRDe7yURGbDMKkEfGin5HPHDKfl"
				+ "+2yoXbcSPMOoDH8B58f7jOZEuAzcwN3fZ6Qf7WfG/wEsJmpB8CM5ttHQ5dmxvSv37zoGltAXidqins6jUPYfuMlVLSooqcmwMbRhRZGB68F1nU55tIBOPkok/0etLWOecd5rFMDiRYSYUAFpr8uzWpZjPQEiTp9dWQVkvMmbaVf+"
				+ "5TGoE2Gmt+40/cEW8vBXwbffxB8aUss1gvW9+GxxpuIP6ICt06d7PlNXZWx+yUL4oB22R+LazJLh6m5kj9AkiZJC45cVuAqkNle7YshqQ+c/2JQmigSDDCj4lRM=");
		return Keys.hmacShaKeyFor(keybytes);
	}


	public String getEmailFromToken(String token) {

		@SuppressWarnings("deprecation")
		Claims claims = Jwts.parser().setSigningKey(secKey()).parseClaimsJws(token).getBody();
		return claims.getSubject();
		 
	}

	
	
	@SuppressWarnings("deprecation")
	public boolean validateToken(String token) {
		try {
			 Jwts.parser().setSigningKey(secKey()).parseClaimsJws(token);
			 return true;
		} catch (Exception e) {
			throw new ApiException("Toke issue : " + e.getMessage());
		}
	}
}
