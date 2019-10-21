package com;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yinjianquan on 19-10-17.
 */
@RestController
public class HystrixController {
    @RequestMapping(value = "/hello", produces = "application/json;charset=utf-8")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(@RequestParam Long id) {
        if (id == 1) {
            throw new RuntimeException("error!!");
        }
        return "hello";
    }

    public String helloError(Long id) {
        return "exception id=" + id;
    }
}
