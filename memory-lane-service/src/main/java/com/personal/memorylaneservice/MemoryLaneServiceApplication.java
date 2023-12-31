package com.personal.memorylaneservice;

import com.personal.memorylaneservice.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.personal.memorylaneservice.*"})
@Import({AppConfig.class})
public class MemoryLaneServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoryLaneServiceApplication.class, args);
	}

}
