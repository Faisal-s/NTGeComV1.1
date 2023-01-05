package net.javaguides.springboot.admin.products.controller;


import net.javaguides.springboot.admin.categories.model.Category;
import net.javaguides.springboot.admin.categories.service.CategoryService;
import net.javaguides.springboot.admin.products.model.Product;
import net.javaguides.springboot.admin.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

//	@Autowired
//	private CategoryService categoryService;
	
	// display list of products
	@GetMapping("/products")
	public String viewProductsPage(Model model) {
		return findPaginatedForProducts(1, "id", "asc", model);
	}
	
	@GetMapping("/products/create")
	public String showNewProductForm(ModelMap model) {
		// create model attribute to bind form data
		Product product = new Product();
		model.addAttribute("product", product);


//		List<Category> category = categoryService.getAllCategories();
//		model.addAttribute("category", category);
		return "admin/products/create";
	}
	
	@PostMapping("/products/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product) {
		// save product to database


		productService.saveProduct(product);
		return "redirect:/products";
	}
	
	@GetMapping("/products/showFormForUpdateProduct/{id}")
	public String showFormForUpdateProduct(@PathVariable ( value = "id") long id, Model model) {
		
		// get product from the service
		Product product = productService.getProductById(id);
		
		// set product as a model attribute to pre-populate the form
		model.addAttribute("product", product);
		return "admin/products/edit";
	}
	
	@GetMapping("/products/deleteProduct/{id}")
	public String deleteProduct(@PathVariable (value = "id") long id) {
		
		// call delete product method
		this.productService.deleteProductById(id);
		return "redirect:/products";
	}
	
	
	@GetMapping("/products/page/{pageNo}")
	public String findPaginatedForProducts(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Product> product = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listProducts", product);
		return "admin/products/index";
	}
}
