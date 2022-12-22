package net.javaguides.springboot.products.repository;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
