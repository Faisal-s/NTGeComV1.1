package net.javaguides.springboot.admin.discount.repository;


 import net.javaguides.springboot.admin.discount.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository <Discount, Long> {
}
