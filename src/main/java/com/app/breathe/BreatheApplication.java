package com.app.breathe;

import com.google.cloud.spring.data.firestore.repository.config.EnableReactiveFirestoreRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableReactiveFirestoreRepositories
@SpringBootApplication
public class BreatheApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreatheApplication.class, args);
    }
}
