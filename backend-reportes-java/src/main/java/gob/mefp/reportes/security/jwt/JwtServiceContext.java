/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "JwtServiceContext.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 10/8/2023 17:00
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.security.jwt;

import gob.ypfb.nova.security.properties.RSAKeyConfigProperties;
import gob.ypfb.nova.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JwtServiceContext {
    private JwtTokenContext jwtTokenContextAplicacion;
    @Autowired
    RSAKeyConfigProperties rsaKeyConfigProperties;

    @Autowired
    private HttpServletRequest request;

    @Value("${ypfb.nova.login}")
    private String usuarioJano;

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
            jwtTokenContextAplicacion = JwtTokenUtil.getJwtTokenContext(token, rsaKeyConfigProperties.getPublicKey());
            this.token= token;
            this.ip ="local";
            if (jwtTokenContextAplicacion != null && !jwtTokenContextAplicacion.getUsername().equals(usuarioJano)) {
                this.ip = request.getRemoteAddr();
            }
        }
        return jwtTokenContextAplicacion;
    }

    public void setValorDefecto(){
        jwtTokenContextAplicacion = new JwtTokenContext();
        jwtTokenContextAplicacion.setUsername("nova");

    }
    public void setearTokenEnContexto(String token) {
        // Crear un objeto Authentication con el token
        Authentication authentication = new UsernamePasswordAuthenticationToken(null, token);

        // Configurar el objeto Authentication en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Ahora SecurityContextHolder.getContext().getAuthentication() contendrá la información del token
    }

    public String getIp(){
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            System.out.println(headerName + ": " + headerValue);
//        }
        getJwtTokenContextAplicacion();
        return this.ip;
    }

    public String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
            // Obtener el token
            String token = (String) authToken.getCredentials();
            jwtTokenContextAplicacion = JwtTokenUtil.getJwtTokenContext(token, rsaKeyConfigProperties.getPublicKey());
            this.token= token;
            this.ip ="local";

            if (jwtTokenContextAplicacion != null && !jwtTokenContextAplicacion.getUsername().equals(usuarioJano)) {
                this.ip = request.getRemoteAddr();
            }
        }
        return this.token;
    }
}
