package com.personal.memorylaneservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CloudinaryConfig.class})
public class AppConfig {
}
