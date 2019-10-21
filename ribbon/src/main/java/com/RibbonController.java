package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yinjianquan on 19-10-16.
 */
@RestController
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hello", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String hello() {
        System.out.println("ribbon hello");
        return restTemplate.getForObject("http://service-provider/hello", String.class);
    }
}
