package com.parent.child.rest.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootWithParentChildProjectApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootWithParentChildProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
     
    }

}
