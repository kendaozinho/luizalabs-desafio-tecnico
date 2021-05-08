package com.luizalabs.customer.configuration.http;

import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("test")
public class RestTemplateBuilderConfiguration {
  @Bean
  public RestTemplateBuilder restTemplateBuilder() {
    RestTemplateBuilder restTemplateBuilder = Mockito.spy(new RestTemplateBuilder());

    Mockito.doReturn(restTemplateBuilder).when(restTemplateBuilder).rootUri(Mockito.anyString());
    Mockito.doReturn(restTemplateBuilder).when(restTemplateBuilder).setReadTimeout(Mockito.any());
    Mockito.doReturn(restTemplateBuilder).when(restTemplateBuilder).setConnectTimeout(Mockito.any());

    Mockito.doReturn(Mockito.mock(RestTemplate.class)).when(restTemplateBuilder).build();

    return restTemplateBuilder;
  }
}
