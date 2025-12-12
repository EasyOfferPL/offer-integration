package com.easyoffer.offer_integration.config;

import com.easyoffer.offer_client.client.OfferClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class FeignConfig {

    @Bean
    public OfferClient offerClient(@Value("${rest.offer-service.url}") String offerServiceUrl) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return Feign.builder()
                .requestInterceptor(new RestRequestInterceptor())
                .responseInterceptor(new RestResponseInterceptor())
                .decoder(new JacksonDecoder(mapper))
                .encoder(new JacksonEncoder(mapper))
                .target(OfferClient.class, offerServiceUrl);
    }

}
