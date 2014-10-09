import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan
@ImportResource("classpath:applicationContext.xml")
@EnableIntegration
public class MainApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApp.class, args);
    }
}
