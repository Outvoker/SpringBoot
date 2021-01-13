package com.dave.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Xu Rui
 * @date 2021/1/11 22:00
 */
@Controller
public class ViewTestController {

    @GetMapping("/atdave")
    public String atdave(Model model){
        model.addAttribute("msg","你好，dave");
        model.addAttribute("link", "http://www.google.com");
        return "success";
    }
}
