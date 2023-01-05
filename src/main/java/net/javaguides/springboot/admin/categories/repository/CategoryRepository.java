package net.javaguides.springboot.admin.categories.repository;

import net.javaguides.springboot.admin.categories.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
