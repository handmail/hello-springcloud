package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yinjianquan on 19-10-17.
 */
@RestController
public class ServiceController {
    @GetMapping("/hello")
    public String hello() {
        return "hello2";
    }
}
