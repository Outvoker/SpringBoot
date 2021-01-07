package com.dave.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xu Rui
 * @date 2021/1/7 10:25
 */
@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg", "成功！");
        request.setAttribute("code", 200);
        return "forward:/success";  //转发到 /success请求
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        Object msg1 = request.getAttribute("msg");
        map.put("reqMethod_msg", msg1);
        map.put("annotation_msg", msg);
        return map;
    }
}
