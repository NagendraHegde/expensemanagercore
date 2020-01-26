package expensemanager.core.rest.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@SpringBootApplication(scanBasePackages = {"expensemanager.core"})
@Configuration
@ComponentScan(basePackages="expensemanager.core")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages="expensemanager.core")
@EntityScan(basePackages="expensemanager.core")
//@ComponentScan({"expensemanager.core.service"})
//@EntityScan("expensemanager.core.domain")
//@EnableJpaRepositories("expensemanager.core.domain")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
