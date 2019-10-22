package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yinjianquan on 19-10-22.
 */
@RestController
public class ClientController {
    @Value("${from}")
    private String from;

    @RequestMapping(value = "/hello", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String hello() {
        return from;
    }
}
