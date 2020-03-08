package cn.sjlm.sjlmapi.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {


    @GetMapping("/hello")
    public String hello(){

        return "hello";
    }
}
