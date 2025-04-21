package gob.mefp.reportes.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import gob.ypfb.nova.commons.dto.ResponseRest;
import gob.ypfb.nova.security.properties.RSAKeyConfigProperties;
import gob.ypfb.nova.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    RSAKeyConfigProperties rsaKeyConfigProperties;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String token = JwtTokenUtil.getAuthorizationToken(request);
            if(StringUtils.isEmpty(token)) {
                filterChain.doFilter(request , response);
                return;
            }

            if(JwtTokenUtil.isValidToken(token, rsaKeyConfigProperties.getPublicKey())) {
                UsernamePasswordAuthenticationToken authentication = JwtTokenUtil.getAuthentication(token, rsaKeyConfigProperties.getPublicKey());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            }
            filterChain.doFilter(request , response);
        }catch (Exception e){
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity vResponseEntity =  ResponseRest.evaluarExcepcionGeneral(e);
            String vJsonExcepcion = objectMapper.writeValueAsString(vResponseEntity.getBody());
            log.error("Excepcion generica",e);
            response.setStatus(vResponseEntity.getStatusCode().value());
            response.getWriter().write(vJsonExcepcion);
        }

    }




}
