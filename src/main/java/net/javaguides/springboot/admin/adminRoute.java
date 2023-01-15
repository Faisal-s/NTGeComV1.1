package net.javaguides.springboot.admin;


import net.javaguides.springboot.admin.products.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class adminRoute {



//    @GetMapping("/products/create")
//    public String showNewProductForm(Model model) {
//        // create model attribute to bind form data
//        Product product = new Product();
//        model.addAttribute("product", product);
//        return "products/create";
//    }

    @GetMapping("/")
    public String viewCustomersPage(Model model) {
        return "index";
    }
}
