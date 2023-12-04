package com.techacademy.controller;

import com.techacademy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("userlist", service.getUserList());
        return "user/list";
    }
}
