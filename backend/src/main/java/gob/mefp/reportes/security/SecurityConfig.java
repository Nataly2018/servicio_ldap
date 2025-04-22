package gob.mefp.reportes.security;

import gob.mefp.reportes.security.jwt.JwtAuthenticationFilter;
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
        //security.ignoring().antMatchers("/swagger-ui/**","/v3/api-docs/**","/test/v1/status/**","/nova/api/v1/*");
    }
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> loggingFilter(){
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(vJwtAuthenticationFilter);
        registrationBean.addUrlPatterns("/reportes/api/*");
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
        .antMatchers("/reportes/api/dominio"
                ,"/reportes/api/dominio_reporte"
                ,"/reportes/api/dominio_reporte_v2"
                ,"/reportes/api/dominio_reporte_v3").permitAll()
        .anyRequest().authenticated();
    }

}
