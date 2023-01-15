package net.javaguides.springboot.admin.customers.controller;

import net.javaguides.springboot.admin.customers.model.Customer;
import net.javaguides.springboot.admin.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // display list of customers

    @GetMapping("/customers")
    public String customersPage(Model model){
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/customer/create")
    public String showNewCustomerForm(Model model) {
        // create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "admin/customers/create";
    }

//    @GetMapping("/register")
//    public String registerForm(Model model) {
//        // create model attribute to bind form data
//        Customer customer = new Customer();
//        model.addAttribute("customer", customer);
//        return "create";
//    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        // save customer to database
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

//    @PostMapping("/saveCustomer1")
//    public String saveCustomer1(@ModelAttribute("customer") Customer customer) {
//        // save customer to database
//        customerService.saveCustomer(customer);
//        return "redirect:/customers";
//    }

    @GetMapping("/customers/edit/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get customer from the service
        Customer customer = customerService.getCustomerById(id);

        // set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "admin/customers/edit";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable (value = "id") long id) {

        // call delete customer method
        this.customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }

    @GetMapping("/customers/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Customer> page = customerService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Customer> customer = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("customers", customer);
        return "admin/customers/index";
    }

//    @GetMapping("/login")
//    public String showLoginForm(Model model) {
//
//        Customer customer = new Customer();
//        model.addAttribute("customer", customer);
//        return "login";
//    }

//    @PostMapping("/CustomerLogin")
//    public String LoginData(Model model ,  @ModelAttribute("customer") Customer customers) {
//        List<Customer> AllAdmin = customerService.getAllCustomers();
//        for (Customer admin:
//                AllAdmin) {
//            if(admin.getEmail().equals(customers.getEmail()) && admin.getPassword().equals(customers.getPassword()) ){
//                return "redirect:/dashboard";
//            }
//        }
//        Customer customer = new Customer();
//        model.addAttribute("customer", customer);
//        return "admin/customers/create";
//    }
}
