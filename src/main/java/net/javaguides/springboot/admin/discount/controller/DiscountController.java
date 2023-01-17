package net.javaguides.springboot.admin.discount.controller;

import net.javaguides.springboot.admin.customers.model.Customer;
import net.javaguides.springboot.admin.discount.model.Discount;
import net.javaguides.springboot.admin.discount.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DiscountController {

    @Autowired
    private DiscountService discountService;


    @GetMapping("/discounts")
    public String index(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/discounts/create")
    public String create(Model model) {
        Discount discount = new Discount();
        model.addAttribute("discount", discount);
        return "admin/discounts/create";
    }

    @PostMapping("/saveDiscount")
    public String saveDiscount(@ModelAttribute("discount") Discount discount) {
        // save Discount to database
        discountService.saveDiscount(discount);
        return "redirect:/discounts";
    }




    @GetMapping("/discounts/edit/{id}")
    public String edit(@PathVariable( value = "id") long id, Model model) {

        Discount discount = discountService.getDiscountById(id);

        // set customer as a model attribute to pre-populate the form
        model.addAttribute("discount", discount);
        return "admin/discounts/edit";
    }


    @GetMapping("/discounts/delete/{id}")
    public String deleteDiscount(@PathVariable (value = "id") long id) {

        // call delete customer method
        this.discountService.deleteDiscountById(id);
        return "redirect:/discounts";
    }



    @GetMapping("/discounts/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Discount> page = discountService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Discount> discount = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("discount", discount);
        return "admin/discounts/index";
    }


}
