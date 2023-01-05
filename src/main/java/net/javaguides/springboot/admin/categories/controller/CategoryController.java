package net.javaguides.springboot.admin.categories.controller;


import net.javaguides.springboot.admin.categories.model.Category;
import net.javaguides.springboot.admin.categories.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    // display list of Categories
    @GetMapping("/admin/category")
    public String viewCategoriesPage(Model model) {
        return findPaginatedForCategorys(1, "id", "asc", model);
    }

    @GetMapping("/admin/category/create")
    public String showNewCategoryForm(Model model) {
        // create model attribute to bind form data
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/categories/create";
    }

    @PostMapping("/admin/category/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
        // save category to database


        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/showFormForUpdateCategory/{id}")
    public String showFormForUpdateCategory(@PathVariable( value = "id") long id, Model model) {

        // get category from the service
        Category category = categoryService.getCategoryById(id);

        // set category as a model attribute to pre-populate the form
        model.addAttribute("category", category);
        return "admin/categories/edit";
    }

    @GetMapping("/admin/category/deleteCategory/{id}")
    public String deleteCategory(@PathVariable (value = "id") long id) {

        // call delete category method
        this.categoryService.deleteCategoryById(id);
        return "redirect:/admin/category";
    }


    @GetMapping("/admin/category/page/{pageNo}")
    public String findPaginatedForCategorys(@PathVariable (value = "pageNo") int pageNo,
                                           @RequestParam("sortField") String sortField,
                                           @RequestParam("sortDir") String sortDir,
                                           Model model) {
        int pageSize = 5;

        Page<Category> page = categoryService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Category> category = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCategories", category);
        return "admin/categories/index";
    }
}
