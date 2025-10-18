package com.example.demo.controller;

import java.io.File;

import com.example.demo.model.User;
import com.example.demo.model.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.ParamService;

@Controller
public class RegisterController {

    @Autowired
    ParamService paramService;

    @GetMapping("/account/register")
    public String form() {
        return "account/register";
    }

    @PostMapping("/account/register")
    public String register(Model model, @RequestParam("photo") MultipartFile file) {
        try {
            File savedFile = paramService.save(file, "/images/uploads/");
            String username = paramService.getString("username", "");
            String password = paramService.getString("password", "");

            // ✅ Lưu user mới vào "database giả lập"
            User user = new User(username, password, "/images/uploads/" + savedFile.getName());
            UserDB.addUser(user);

            model.addAttribute("message", "Đăng ký thành công cho tài khoản: " + username);
            model.addAttribute("photoPath", user.getPhotoPath());
        } catch (Exception e) {
            model.addAttribute("message", "Đăng ký thất bại: " + e.getMessage());
        }
        return "account/register";
    }

}
