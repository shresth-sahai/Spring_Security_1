package com.example.demo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import io.jsonwebtoken.*;
@Component
public class JwtUtil {
    private static String SECRET_KEY="mysecret";
    public static String generateToken(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }
    public String extractUsername(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody().getSubject();

    }
    public boolean validateToken(String token,String userName){
        return extractUsername(token).equals(userName) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody().getExpiration().before(new Date());
    }
}
