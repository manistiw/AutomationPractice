package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:${environment:stage}.properties")}//this will get precedence.
)
@ComponentScan(basePackages = {"uicomponent","apicomponent","pages"})
public class ApplicationConfigContext {
    private final Logger log = LoggerFactory.getLogger(ApplicationConfigContext.class);

    @Autowired
    public Environment environment;

    public static void main(String args[]){
        ApplicationContext context=new AnnotationConfigApplicationContext(ApplicationConfigContext.class);
        System.out.println("Starting appl");
    }

    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @PostConstruct
    public void log() {
        log.info("Application config loaded!");  // Displays as expected
        log.info("env: {}", environment);                // env: null
    }


}
