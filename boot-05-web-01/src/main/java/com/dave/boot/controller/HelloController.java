package com.dave.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xu Rui
 * @date 2021/1/3 17:54
 */
@RestController
public class HelloController {
    @RequestMapping("/kdy.jpg")
    public String hello(){
        return "aaaa";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(){
        return "GET-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveUser(){
        return "POST-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String putUser(){
        return "PUT-张三";
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String deleteUser(){
        return "DELETE-张三";
    }
}
