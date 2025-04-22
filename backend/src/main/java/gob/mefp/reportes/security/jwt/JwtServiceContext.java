/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/
package gob.mefp.reportes.security.jwt;

import gob.mefp.reportes.security.properties.HMACKeyConfigProperties;
import gob.mefp.reportes.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JwtServiceContext {
    private JwtTokenContext jwtTokenContextAplicacion;

    @Autowired
    HMACKeyConfigProperties hmacKeyConfigProperties;

    @Autowired
    private HttpServletRequest request;

    private String ip;
    private String token;

    public String obtenerValorCabecera(String prop){
        return request.getHeader(prop);
    }


    public JwtTokenContext getJwtTokenContextAplicacion() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
            // Obtener el token
            String token = (String) authToken.getCredentials();
            jwtTokenContextAplicacion = JwtTokenUtil.getJwtTokenContext(token, hmacKeyConfigProperties.getSecretKey());
            this.token= token;
            this.ip ="local";
            if (jwtTokenContextAplicacion != null ) {
                this.ip = request.getRemoteAddr();
            }
        }
        return jwtTokenContextAplicacion;
    }

    public void setValorDefecto(){
        jwtTokenContextAplicacion = new JwtTokenContext();
        jwtTokenContextAplicacion.setUsername("reportes");

    }
    public void setearTokenEnContexto(String token) {
        // Crear un objeto Authentication con el token
        Authentication authentication = new UsernamePasswordAuthenticationToken(null, token);

        // Configurar el objeto Authentication en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Ahora SecurityContextHolder.getContext().getAuthentication() contendrá la información del token
    }

    public String getIp(){
        getJwtTokenContextAplicacion();
        return this.ip;
    }

    public String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
            // Obtener el token
            String token = (String) authToken.getCredentials();
            jwtTokenContextAplicacion = JwtTokenUtil.getJwtTokenContext(token, hmacKeyConfigProperties.getSecretKey());
            this.token= token;
            this.ip ="local";

            if (jwtTokenContextAplicacion != null ) {
                this.ip = request.getRemoteAddr();
            }
        }
        return this.token;
    }
}
