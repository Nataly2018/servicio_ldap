package gob.mefp.reportes.security;

import gob.ypfb.nova.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthenticationFilter vJwtAuthenticationFilter;
    @Override
    public void configure(WebSecurity security){
        System.out.println(" >>> WebSecurity (Cargado) <<<< ");
        security.ignoring().antMatchers("/swagger-ui/**","/v3/api-docs/**","/test/v1/status/**");
    }
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> loggingFilter(){
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(vJwtAuthenticationFilter);
        registrationBean.addUrlPatterns("/nova/api/v1/*");
        return registrationBean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
        addFilterAfter(loggingFilter().getFilter(), UsernamePasswordAuthenticationFilter.class).
        authorizeRequests()
        .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
        .antMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
        .antMatchers(HttpMethod.GET,"/test/v1/status/**").permitAll()
        .anyRequest().authenticated();
//        http.headers().addHeaderWriter((request, response) -> {
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN, Token");
//            response.setHeader("Access-Control-Expose-Headers", "xsrf-token");
//            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//                response.setStatus(HttpServletResponse.SC_OK);
//            } else {
//                response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
//                response.setHeader("Pragma", "no-cache");
//                response.setHeader("Expires", "0");
//            }
//        });
    }

}
