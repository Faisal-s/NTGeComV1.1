package net.javaguides.springboot.admin.dashboard.controller;


import net.javaguides.springboot.admin.customers.model.Customer;
import net.javaguides.springboot.admin.customers.service.CustomerService;
import net.javaguides.springboot.admin.users.model.User;
import net.javaguides.springboot.admin.products.model.Product;
import net.javaguides.springboot.admin.products.service.ProductService;
import net.javaguides.springboot.admin.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class Dashboard {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/dashboard")
    public String showDashBoard(ModelMap model) {

        List<Product> products = productService.getAllProducts();

        double totalProductsValue = products.stream().filter(Objects::nonNull).mapToDouble(Product::getPrice).sum();

        List<Customer> customer = customerService.getAllCustomers();

        model.addAttribute("product", products);
        model.addAttribute("customer", customer);
        model.addAttribute("totalValue", totalProductsValue);


        return "admin/dashboard/dashboard";
    }
}
