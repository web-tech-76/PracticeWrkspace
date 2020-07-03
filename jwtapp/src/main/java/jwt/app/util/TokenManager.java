package jwt.app.util;

import java.io.Serializable;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
public class TokenManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS512);
	private static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
	
	public String getUserFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpiryDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public Boolean isTokenExpired(String token) {
		Date expDate = getExpiryDateFromToken(token);
		return expDate.before(new Date());
	}
	
	
	public Boolean validateToken(String token , UserDetails userDetails) {
	
		String username = getUserFromToken(token);
		Boolean isexpired= isTokenExpired(token);
		
		if(username.equalsIgnoreCase(userDetails.getUsername())  && !isexpired ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		
		Claims claims = getClaimsFromToken(token);
		return claimResolver.apply(claims);
		
	}

	
	public Claims getClaimsFromToken(String token ) {
		
		Claims claims =null;
		try {
				
			claims =
				Jwts.parser()
				.setSigningKey(base64SecretBytes)
				.parseClaimsJws(token)
				.getBody();
				
				System.out.println("sub ********************" + claims.getSubject());
				
				
				
		}
		catch(IllegalArgumentException 
				| UnsupportedJwtException  
				| SignatureException 
				| MalformedJwtException 
				| ExpiredJwtException se) {
			
			//System.out.println("Signature Exception " + se);
			
		}
		
		return claims;

	}

	
	public JwtBuilder buildClaim(UserDetails userDetails) 
	{
		try {
			
			  SecureRandom secureRandom= SecureRandom.getInstance("SHA1PRNG", "SUN");
			  secureRandom.setSeed(253967); 
			  Map<String, Object> claims = new HashMap<>();

			  //Date now = Date.from(Instant.ofEpochSecond(Instant.now().getEpochSecond()));
		      //Date exp = Date.from(Instant.ofEpochSecond(Instant.now().getEpochSecond() + (1000 * 30)));
		        		
			/*
			 * Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
			 * claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
			 */			
			JwtBuilder builder= Jwts.builder()
					.setClaims(claims)
					.setSubject(userDetails.getUsername())
					//.setId(Long.toString(secureRandom.nextLong()))
					
					.setIssuedAt(new Date(System.currentTimeMillis()))
					//.setIssuer("http://microservice.in")
					.setExpiration(new Date(System.currentTimeMillis() + (5 * 60 * 60 * 1000) ));
			
			
			return builder;
			
		}
		
		catch(Exception e) {
			System.out.println("Exception in build claim " + e.getMessage());
			return null;
		}
		
	}

	public String generateToken(UserDetails userDetails) {
				
		String token = createClaimJwtToken(userDetails);
		return token;
		
	}
	
	
	public String createClaimJwtToken(
				UserDetails userDetails) {
		return 	buildClaim(userDetails)
				.signWith(SignatureAlgorithm.HS512, 
						base64SecretBytes)
				.compact();

	}
	
}
