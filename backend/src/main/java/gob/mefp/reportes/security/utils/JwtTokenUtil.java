package gob.mefp.reportes.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.mefp.reportes.security.jwt.JwtTokenContext;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    static final String TOKEN_PREFIX = "Bearer ";
    static final String AUTHORIZATION = "Authorization";

    public static UsernamePasswordAuthenticationToken getAuthentication(String token, String secretKey) {
        final Claims claims = parseToken(token, secretKey).getBody();
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), token, new ArrayList<>());
    }

    public static boolean isValidToken(String token, String secretKey) {
        try {
            Claims claims = parseToken(token, secretKey).getBody();
            String context = claims.get("context").toString();

            context = context.replace(", ", "\",\"");
            context = context.replace("{", "{\"");
            context = context.replace("}", "\"}");
            context = context.replace("=", "\":\"");

            ObjectMapper objectMapper = new ObjectMapper();
            JwtTokenContext vJwtTokenContext = objectMapper.readValue(context, JwtTokenContext.class);

            return true;
        } catch (ExpiredJwtException ex) {
            System.out.println(">>> Token Expirado <<<");
        } catch (IllegalArgumentException | MalformedJwtException | ClassCastException e) {
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
        if (authHeader == null) return null;
        return authHeader.startsWith(TOKEN_PREFIX) ? authHeader.replace(TOKEN_PREFIX, "").trim() : null;
    }

    public static JwtTokenContext getJwtTokenContext(String token, String secretKey) {
        JwtTokenContext vJwtTokenContext;
        Claims claims = parseToken(token, secretKey).getBody();
        String context = claims.get("context").toString();
        context = context.replace(", ", "\",\"");
        context = context.replace("{", "{\"");
        context = context.replace("}", "\"}");
        context = context.replace("=", "\":\"");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            vJwtTokenContext = objectMapper.readValue(context, JwtTokenContext.class);
            return vJwtTokenContext;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Comprueba si el token ha expirado
    static private Boolean isTokenExpired(String token, String secretKey) {
        final Date expiration = getExpirationDateFromToken(token, secretKey);
        return expiration.before(new Date());
    }

    private static Jws<Claims> parseToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())) // Convierte la clave secreta a Base64
                .parseClaimsJws(token);
    }

    static Date getExpirationDateFromToken(String token, String secretKey) {
        final Claims claims = getAllClaimsFromToken(token, secretKey);
        return claims.getExpiration();
    }

    private static Claims getAllClaimsFromToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }
}
