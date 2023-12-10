package com.example.stockportfoliotracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/admin")
    @ResponseBody
    public String helloAdminController() {
        return "Admin access level";
    }
}
