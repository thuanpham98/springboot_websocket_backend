package com.thuannek.util;

import java.util.Date;

import com.thuannek.commons.JwtUtilKey;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtUtil {
    private String skey = JwtUtilKey.SECRET_KEY.getSecretKey();
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public String generateToken(String userKey){
        Date now = new Date() ;
        return Jwts.builder().setSubject(userKey)
                             .setIssuedAt(now)
                             .setExpiration(new Date(now.getTime() + 3600000L))
                             .signWith(SignatureAlgorithm.HS512, skey)
                             .compact();
    }

    public String getUserKey(String token){
        return Jwts.parser().setSigningKey(skey).parseClaimsJws(token).getBody().getSubject();
    }

    public int  validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(skey).parseClaimsJws(authToken);
            return 0;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            return 1;
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return 2;
    }
}
