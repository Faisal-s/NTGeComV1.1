package net.javaguides.springboot.admin.discount.service;

 import net.javaguides.springboot.admin.discount.model.Discount;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DiscountService {

    List<Discount> getAllDiscounts();
    void saveDiscount(Discount customer);
    Discount getDiscountById(long id);
    void deleteDiscountById(long id);
    Page<Discount> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
