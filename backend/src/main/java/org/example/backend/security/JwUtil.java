package org.example.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwUtil {
    public String generateToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuer("org.example")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))//30 minut normal
                .signWith(getKey())
                .claim("roles",roles)
                .compact();
    }
    public String generateRefreshToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuer("org.example")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))//aslida 1 hafta qoyiladi
                .signWith(getKey())
                .claim("roles",roles)
                .compact();
    }
    private SecretKey getKey(){
       byte[] bytes= Decoders.BASE64.decode("yUYFFQVcIx8kS29qk0J1cBVPjXAS6GTlxDBPmlMsmAtze7YG1imFuEZbGppxljF7");
        return Keys.hmacShaKeyFor(bytes);
    }
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String getUsername(String token) {
         return getClaims(token).getSubject();
    }
    public List<GrantedAuthority> getRoles(String token) {
        String roles = getClaims(token).get("roles", String.class);
        return Arrays.stream(roles.split(",")).map(item->new SimpleGrantedAuthority(item)).collect(Collectors.toList());
        }

}
