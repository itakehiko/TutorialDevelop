package com.techacademy.controller;

import com.techacademy.entity.User;
import com.techacademy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
        return "user/register";
    }

    @PostMapping("/register")
    public String postRegister(@Validated User user, BindingResult res, Model model) {
        if (res.hasErrors()) {
            return getRegister(user);
        }
        service.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/update/{id}/")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        if (id != null) {
            model.addAttribute("user", service.getUser(id));
            return "user/update";
        } else {
            return "user/update";
        }
    }

    @PostMapping("/update/{id}/")
    public String postUser(@Validated User user, BindingResult res, Model model) {
        if (res.hasErrors()) {
            return getUser(null, model);
        }
        service.saveUser(user);
        return "redirect:/user/list";
    }

    @PostMapping(path = "list", params = "deleteRun")
    public String deleteRun(@RequestParam(name = "idck") Set<Integer> idck, Model model) {
        service.deleteUser(idck);
        return "redirect:/user/list";
    }
}
