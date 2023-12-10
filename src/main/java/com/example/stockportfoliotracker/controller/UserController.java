package com.example.stockportfoliotracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/user")
    @ResponseBody
    public String helloUserController() {
        return "User access level";
    }
}
