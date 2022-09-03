package com.money.config.security;


import com.money.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenService
{
	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication){
		User userLogged = (User) authentication.getPrincipal();
		Date todayDate = new Date();
		Date expirationDate = new Date(todayDate.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
			.setIssuer("Money S/A")
			.setSubject(userLogged.getId().toString())
			.setIssuedAt(todayDate)
			.setExpiration(expirationDate)
			.signWith(SignatureAlgorithm.HS256,secret)
			.compact();

	}

	public boolean isTokenValid(String token)
	{
		try
		{
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}
		catch (Exception e ){
			return false;
		}
	}

	public Long getUserId(String token){
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
