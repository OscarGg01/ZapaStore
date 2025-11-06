package com.zapastore.zapastore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/metricas")
    public String showDashboard(Model model) {    
        return "admin/metricas"; 
    }
}
