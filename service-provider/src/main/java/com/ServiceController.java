package com;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yinjianquan on 19-10-17.
 */
@RestController
public class ServiceController {
    @RequestMapping(value = "/hello", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String hello() {
        System.out.println("request hello..");
        return new JSONObject().toString();
    }
}
