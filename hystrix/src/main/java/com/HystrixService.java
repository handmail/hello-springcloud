package com;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yinjianquan on 19-10-17.
 */
@Service
public class HystrixService {
    @Auto
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(String message) {
        return restTemplate.getForObject("http://service/hello?message=" + message, String.class);
    }

    public String helloError(String message) {
        return String.format("hello %s, but request bad", message);
    }
}
