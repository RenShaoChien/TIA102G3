package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName： PageController package：com.controllers Description：
 *
 * @Author 任少騫
 * @Create 2024/8/2 上午12:17
 * @Version 1.0
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/trainers")
    public String trainers(Model model) {
        return "trainers";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        return "menu";
    }

}
