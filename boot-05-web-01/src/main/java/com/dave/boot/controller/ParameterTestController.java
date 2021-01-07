package com.dave.boot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xu Rui
 * @date 2021/1/6 22:15
 */
@RestController
public class ParameterTestController {
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,   //获取路径参数
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv, //获取全部参数
                                      @RequestHeader("User-Agent") String userAgent,    //获取请求头中的信息
                                      @RequestHeader Map<String, String> header,    //获取请求头全部信息
                                      @RequestParam("age") Integer age, //获取请求参数(?age=)
                                      @RequestParam("interests") List<String> interests,    //获取请求参数（多个值）
                                      @RequestParam Map<String, String> params, //获取全部请求参数
                                      @CookieValue("io") String io, //获取cookie
                                      @CookieValue("io") Cookie cookie//获取cookie对象
                                      ){
        Map<String, Object> map = new HashMap<>();
//        map.put("id", id);
//        map.put("name", name);
//        map.put("pv", pv);
//        map.put("userAgent", userAgent);
//        map.put("header", header);
        map.put("age", age);
        map.put("interests", interests);
        map.put("params", params);
        map.put("ga", io);
        System.out.println(cookie);
        map.put("cookie",cookie);
        return map;
    }

    @PostMapping("/save")
    public Map PostMethod(@RequestBody String content){
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    //1. 语法 /cars/sell;low=34;brand=byd,audi,yd
    //2. SpringBoot默认禁用矩阵变量
    //3. 矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path",path);
        return map;
    }

    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }
}
