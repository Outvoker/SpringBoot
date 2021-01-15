package com.dave.admin.controller;

import com.dave.admin.bean.Alg;
import com.dave.admin.bean.City;
import com.dave.admin.bean.User;
import com.dave.admin.service.AlgService;
import com.dave.admin.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @author Xu Rui
 * @date 2021/1/11 22:27
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    AlgService algService;

    @Autowired
    CityService cityService;

    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") Long id){
        return cityService.getById(id);
    }

    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city){
        cityService.saveCity(city);
        return city;
    }

    @ResponseBody
    @GetMapping("/alg")
    public Alg getById(@RequestParam("id") Long id){
        log.info("getById");
        return algService.getAlgById(id);
    }

    @GetMapping(value = {"/", "/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){

        if(!StringUtils.isEmpty(user.getUserName()) && "123456".equals(user.getPassword())){
            //把登陆成功的用户保存起来
            session.setAttribute("loginUser", user);
            //登录成功重定向到main.html防止表单重复提交
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg", "账号密码错误");
            return "login";
        }

    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model){
        log.info("当前方法是：{}", "mainPage");
//        Object loginUser = session.getAttribute("loginUser");
//        if(loginUser != null) return "main";
//        else{
//            model.addAttribute("msg", "未登录！");
//            return "login";
//        }

        return "main";
    }
}
