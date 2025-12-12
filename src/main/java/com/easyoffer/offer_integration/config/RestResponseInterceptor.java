package com.easyoffer.offer_integration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.InvocationContext;
import feign.Response;
import feign.ResponseInterceptor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestResponseInterceptor implements ResponseInterceptor {

    private static final String SPACE = " ";
    private static final String RESPONSE = "Response";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        var response = invocationContext.response();
        var request = response.request();
        var httpMethod = request.httpMethod();
        var url = request.url();
        var httpStatus = response.status();
        var logString = RESPONSE + SPACE + httpMethod + SPACE + url + SPACE + httpStatus;
//
//        var is = response.body().asInputStream();
//        var bytes = objectMapper.writeValueAsBytes(response.body());
//        var obj = objectMapper.writeValueAsString(bytes);
        log.info("{}\n", logString);

        //todo body print

        return invocationContext.proceed();
    }

    @SneakyThrows
    private byte[] cloneBody(Response.Body body) {
        var out = new ByteArrayOutputStream();
        copy(body.asInputStream(), out);
        return out.toByteArray();
    }

    private static long copy(InputStream from, OutputStream to) throws IOException {
        byte[] buf = new byte[2048];
        long total = 0L;

        while(true) {
            int r = from.read(buf);
            if (r == -1) {
                return total;
            }

            to.write(buf, 0, r);
            total += (long)r;
        }
    }

}
