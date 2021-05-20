package com.valorant.api.security.jwt;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.valorant.api.security.model.UserDetail;
import com.valorant.api.security.model.dto.JwtDto;
import com.valorant.api.security.util.Constants;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    public String generateToken(Authentication authentication){
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        List<String> authorities = userDetail
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        List<String> roles = new ArrayList<>();
        for (String role : authorities){
            String rol = role.substring(5).toLowerCase(Locale.ROOT);
            roles.add(rol);
        }
        String roleAll = String.join(", ", roles);

        return Jwts.builder()
                .setSubject(userDetail.getUsername())
                .claim("role", roleAll)
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

    public String refreshToken(JwtDto jwtDto) throws ParseException {
        JWT jwt = JWTParser.parse(jwtDto.getToken());
        JWTClaimsSet claims = jwt.getJWTClaimsSet();
        String username = claims.getSubject();
        return Jwts.builder()
                .setSubject(username)
                .claim("role", claims.getClaim("role"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Constants.EXPIRATION * 2))
                .signWith(SignatureAlgorithm.HS256, Constants.SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
}
