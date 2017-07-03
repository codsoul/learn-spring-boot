package com.latteyan.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latteyan.dao.UserMapper;

@RestController
public class HelloWorldController {


    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

}
