package net.javaguides.springboot.admin.discount.service;

 import net.javaguides.springboot.admin.discount.model.Discount;
import net.javaguides.springboot.admin.discount.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public void saveDiscount(Discount discount) {
        this.discountRepository.save(discount);
    }

    @Override
    public Discount getDiscountById(long id) {
        Optional<Discount> optional = discountRepository.findById(id);
        Discount discount = null;
        if (optional.isPresent()) {
            discount = optional.get();
        } else {
            throw new RuntimeException(" Discount not found for id :: " + id);
        }
        return discount;
    }

    @Override
    public void deleteDiscountById(long id) {
        this.discountRepository.deleteById(id);
    }

    @Override
    public Page<Discount> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.discountRepository.findAll(pageable);

    }
}
