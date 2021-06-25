package com.valorant.api.security.jwt;

import com.valorant.api.security.model.UserDetail;
import com.valorant.api.security.model.dto.JwtDto;
import com.valorant.api.security.util.Constants;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    public String generateToken(Authentication authentication){
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Constants.EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, Constants.SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String getUsernameFromtoken(String token){
        return Jwts.parser()
                .setSigningKey(Constants.SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(Constants.SECRET.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException m){
            System.out.println("Token mal formado");
        }catch (UnsupportedJwtException u){
            System.out.println("Token No Soportado");
        }catch (ExpiredJwtException e){
            System.out.println("Token Expirado");
        }catch (IllegalArgumentException i){
            System.out.println("Token Vacío");
        }catch (SignatureException s){
            System.out.println("Fallo en la Firma");
        }
        return false;
    }

    public String refreshToken(JwtDto jwtDto){
        String username = this.getUsernameFromtoken(jwtDto.getToken());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Constants.EXPIRATION * 2))
                .signWith(SignatureAlgorithm.HS256, Constants.SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
}
