package com.parent.child.jpa.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ParentChildJpaProjectDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParentChildJpaProjectDemoApplication.class, args);
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
        b.failOnEmptyBeans(false);
        b.failOnUnknownProperties(false);
        return b;
    }

}
