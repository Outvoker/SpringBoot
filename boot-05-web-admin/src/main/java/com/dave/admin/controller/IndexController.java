package com.dave.admin.controller;

import com.dave.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @author Xu Rui
 * @date 2021/1/11 22:27
 */
@Controller
public class IndexController {

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
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null) return "main";
        else{
            model.addAttribute("msg", "未登录！");
            return "login";
        }
    }
}
