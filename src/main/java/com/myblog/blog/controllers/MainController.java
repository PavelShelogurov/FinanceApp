package com.myblog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller //анотоция показывающая, что этот клас отвечает за переходы на все страницы сайта
public class MainController {



    @GetMapping("/home") //эта анатация указывает какой URL адрес мы обрабатываем
    public String home(Model model) { //Model model обязательный параметр, передающийся в метод, он принимается всегда
        model.addAttribute("title","Личный блог");
        return "home";
    }

    @GetMapping("/about") //эта анатация указывает какой URL адрес мы обрабатываем
    public String about(Model model) { //Model model обязательный параметр, передающийся в метод, он принимается всегда
        model.addAttribute("title","Инфо о проекте");
        return "about";
    }



}