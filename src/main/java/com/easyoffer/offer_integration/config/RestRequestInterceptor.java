package com.easyoffer.offer_integration.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
public class RestRequestInterceptor implements RequestInterceptor {

    private static final String SPACE = " ";
    private static final String NULL = "null";
    private static final String REQUEST = "Request";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var httpMethod = requestTemplate.method();
        var path = requestTemplate.feignTarget().name() + requestTemplate.path();
        var body = Arrays.toString(requestTemplate.body());
        var logString = REQUEST + SPACE + httpMethod + SPACE + path;

        if (Strings.isNotBlank(body) && !NULL.equalsIgnoreCase(body)) {
            log.info("{}\n{}", logString, body);
        } else {
            log.info(logString);
        }
    }
}
