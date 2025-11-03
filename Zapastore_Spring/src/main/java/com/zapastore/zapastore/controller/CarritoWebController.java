package com.zapastore.zapastore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarritoWebController {

    @GetMapping("/carrito")
    public String mostrarCarrito() {
        return "carrito"; // JSP en /WEB-INF/jsp/carrito.jsp
    }
}
