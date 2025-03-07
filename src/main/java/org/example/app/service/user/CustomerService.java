package org.example.app.service.user;

import org.example.app.dto.user.CustomerDtoRequest;
import org.example.app.entity.user.Customer;
import org.example.app.service.BaseService;

import java.util.List;

public interface CustomerService extends BaseService<Customer, CustomerDtoRequest> {
    Customer create(CustomerDtoRequest request);
    List<Customer> fetchAll();
    Customer fetchById(Long id);
    Customer updateById(Long id, CustomerDtoRequest request);
    boolean deleteById(Long id);
    Customer getLastEntity();

    // ---- Query Params ----------------------
    List<Customer> fetchByFirstName(String firstName);
    List<Customer> fetchByLastName(String lastName);
    List<Customer> fetchAllOrderBy(String orderBy);
}
