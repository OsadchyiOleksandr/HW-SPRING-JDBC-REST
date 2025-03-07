package org.example.app.service.user;

import org.example.app.dao.customer.CustomerDao;
import org.example.app.dto.user.CustomerDtoRequest;
import org.example.app.entity.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao userDao;

    @Override
    public Customer create(CustomerDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        userDao.create(request);
        return userDao.getLastEntity()
                .orElse(null);
    }

    @Override
    public List<Customer> fetchAll() {
        return userDao.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public Customer fetchById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return userDao.fetchById(id)
                .orElse(null);
    }

    @Override
    public Customer updateById(Long id, CustomerDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        if (userDao.fetchById(id).isPresent()) {
            userDao.updateById(id, request);
        }
        return userDao.fetchById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (userDao.fetchById(id).isPresent()) {
            userDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Customer getLastEntity() {
        return userDao.getLastEntity()
                .orElse(null);
    }

    // ---- Query Params ----------------------

    public List<Customer> fetchByFirstName(String firstName) {
        return userDao.fetchByFirstName(firstName)
                .orElse(Collections.emptyList());
    }

    public List<Customer> fetchByLastName(String lastName) {
        return userDao.fetchByLastName(lastName)
                .orElse(Collections.emptyList());
    }

    public List<Customer> fetchAllOrderBy(String orderBy) {
        return userDao.fetchAllOrderBy(orderBy)
                .orElse(Collections.emptyList());
    }

}
