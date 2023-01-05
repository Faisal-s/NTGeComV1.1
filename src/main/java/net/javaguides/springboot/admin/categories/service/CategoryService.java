package net.javaguides.springboot.admin.categories.service;

import net.javaguides.springboot.admin.categories.model.Category;
import net.javaguides.springboot.admin.products.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void saveCategory(Category category);

    Category getCategoryById(long id);

    void deleteCategoryById(long id);

    Page<Category> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
