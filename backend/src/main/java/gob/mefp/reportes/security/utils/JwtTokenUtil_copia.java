package gob.mefp.reportes.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.mefp.reportes.security.jwt.JwtTokenContext;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtTokenUtil_copia {
    static final String TOKEN_PREFIX = "Bearer ";
    static final String AUTHORIZATION = "Authorization";

    public static UsernamePasswordAuthenticationToken getAuthentication(String token, PublicKey publicKey) {
        final Claims claims = parseToken(token, publicKey).getBody();
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), token, new ArrayList<>());
    }

    public static boolean isValidToken(String token, PublicKey publicKey) {
        try {
            Claims claims = parseToken(token, publicKey).getBody();
            String context = claims.get("context").toString();


            context = context.replace(", ","\",\"");
            context = context.replace("{","{\"");
            context = context.replace("}","\"}");
            context = context.replace("=","\":\"");

            ObjectMapper objectMapper = new ObjectMapper();
            JwtTokenContext vJwtTokenContext = objectMapper.readValue(context, JwtTokenContext.class);
            //System.out.println(claims.get("context"));

            return true;
        } catch (ExpiredJwtException ex) {
            //println "  "
            System.out.println(">>> Token Expirado <<<");
            //ex.printStackTrace();
        } catch (IllegalArgumentException | MalformedJwtException | ClassCastException e) {
            // println "No se puede analizar el token de autenticación, msg: ${ e.message }";
            //e.printStackTrace();
            System.out.println("No se puede analizar el token de autenticación, msg: " + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("No se puede Mapear el token de autenticación, msg: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("No se puede Procesar el token de autenticación, msg: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static String getAuthorizationToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        if(authHeader == null) return null;
        //System.out.println(authHeader);
        return authHeader.startsWith(TOKEN_PREFIX) ? authHeader.replace(TOKEN_PREFIX, "").trim() : null;
    }



    public static JwtTokenContext getJwtTokenContext(String token,PublicKey publicKey) {
        JwtTokenContext vJwtTokenContext;
        Claims claims = parseToken(token, publicKey).getBody();
        String context = claims.get("context").toString();
        context = context.replace(", ", "\",\"");
        context = context.replace("{", "{\"");
        context = context.replace("}", "\"}");
        context = context.replace("=", "\":\"");
        //System.out.println(context);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            vJwtTokenContext = objectMapper.readValue(context, JwtTokenContext.class);
            return vJwtTokenContext;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // check if the token has expired
    static private Boolean isTokenExpired(String token, PublicKey publicKey) {
        final Date expiration = getExpirationDateFromToken(token, publicKey);

        return expiration.before(new Date());
    }

    private static Jws<Claims> parseToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    static Date getExpirationDateFromToken(String token, PublicKey publicKey) {
        final Claims claims = getAllClaimsFromToken(token, publicKey);

        return claims.getExpiration();
    }

    private static Claims getAllClaimsFromToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    }
}
