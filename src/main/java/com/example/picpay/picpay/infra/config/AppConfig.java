package com.example.picpay.picpay.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class AppConfig {
  /**
   * Bean for RestTemplate to be used in the application.
   * 
   * @return a new instance of RestTemplate
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
