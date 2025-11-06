package com.zapastore.zapastore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        // Aquí puedes agregar datos que quieras mostrar en el dashboard
        // por ejemplo, total de productos, ventas, usuarios, etc.
        return "admin/usuarioLista"; // JSP que crearemos en el siguiente paso
    }
}
