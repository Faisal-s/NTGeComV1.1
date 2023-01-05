package net.javaguides.springboot.dashboard.controller;


import net.javaguides.springboot.model.User;
import net.javaguides.springboot.products.model.Product;
import net.javaguides.springboot.products.service.ProductService;
import net.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class Dashboard {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String showDashBoard(ModelMap model) {

        List<Product> products = productService.getAllProducts();

        double totalProductsValue = products.stream().filter(Objects::nonNull).mapToDouble(Product::getPrice).sum();

        List<User> users = userService.getAllUsers();

        model.addAttribute("product", products);
        model.addAttribute("user", users);
        model.addAttribute("totalValue", totalProductsValue);


        return "dashboard/dashboard";
    }
}
