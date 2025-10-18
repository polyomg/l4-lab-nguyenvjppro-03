package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.model.UserDB;
import com.example.demo.service.ParamService;
import com.example.demo.service.SessionService;

@Controller
public class AccountController {

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String showLoginForm() {
        return "account/login";
    }

    @PostMapping("/account/login")
    public String login(Model model) {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");

        User user = UserDB.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // ✅ Lưu thông tin user vào session
            sessionService.set("user", user);

            // ✅ Chuyển hướng đến trang tiếp theo (ví dụ item list)
            return "redirect:/item/index";
        } else {
            model.addAttribute("message", "Sai tài khoản hoặc mật khẩu!");
            return "account/login";
        }
    }

    @GetMapping("/account/logout")
    public String logout() {
        sessionService.remove("user");
        return "redirect:/account/login";
    }
}
