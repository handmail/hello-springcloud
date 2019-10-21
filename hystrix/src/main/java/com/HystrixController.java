package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yinjianquan on 19-10-17.
 */
@RestController
public class HystrixController {
    @Autowired
    HystrixService hystrixService;
    @RequestMapping(value = "/hello", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String hello(@RequestParam String message) {
        return hystrixService.hello(message);
    }
}
