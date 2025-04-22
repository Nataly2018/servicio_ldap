package gob.mefp.reportes.app;
import gob.mefp.reportes.config.AppProperties;
import gob.mefp.reportes.exception.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@ComponentScan(basePackages = {"gob.mefp.reportes.commons","gob.mefp.reportes.app","gob.mefp.reportes.security"})
@EnableConfigurationProperties(AppProperties.class)
@OpenAPIDefinition(info = @Info(title = "Nombre - Sistema ...", version = "1.0", description = "Endpoints del Sistema "))
@Import(value= {GlobalExceptionHandler.class})
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
