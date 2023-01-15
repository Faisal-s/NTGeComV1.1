package net.javaguides.springboot.admin.customers.service;

import net.javaguides.springboot.admin.customers.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    Customer getCustomerById(long id);
    void deleteCustomerById(long id);
    Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
