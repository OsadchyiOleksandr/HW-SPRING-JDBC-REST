package org.example.app.controller;

import org.example.app.dto.customer.*;
import org.example.app.entity.customer.Customer;
import org.example.app.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDtoCreateResponse> createCustomer(
            @RequestBody CustomerDtoRequest request) {
        Customer customer = customerService.create(request);
        return (customer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoCreateResponse.of(true,
                                customer)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<CustomerDtoListResponse> fetchAllCustomers() {
        List<Customer> list = customerService.fetchAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoListResponse.of(true,
                            Collections.emptyList()));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoListResponse.of(false,
                            list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoGetByIdResponse> fetchCustomerById(
            @PathVariable("id") Long id) {
        Customer customer = customerService.fetchById(id);
        if (customer != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetByIdResponse.of(id, true,
                            customer));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetByIdResponse.of(id, false,
                            null));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoUpdateResponse> updateCustomerById(
            @PathVariable("id") Long id,
            @RequestBody CustomerDtoRequest request) {
        if (customerService.fetchById(id) != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoUpdateResponse.of(id, true,
                            customerService.updateById(id, request)));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoUpdateResponse.of(id, false,
                            null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDtoDeleteResponse> deleteCustomerById(
            @PathVariable("id") Long id) {
        if (customerService.deleteById(id))
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoDeleteResponse.of(id, true));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoDeleteResponse.of(id, false));
    }

    @GetMapping("/last-entity")
    public ResponseEntity<CustomerDtoGetLastEntityResponse> getLastEntity() {
        Customer customer = customerService.getLastEntity();
        if (customer != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetLastEntityResponse.of(true,
                            customer));

        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetLastEntityResponse.of(false,
                            null));
    }

    // ---- Query Params ----------------------

    @GetMapping("/query-by-name")
    public ResponseEntity<CustomerDtoListResponse> fetchByFirstName(@RequestParam("name") final String name) {
        List<Customer> list = customerService.fetchByName(name);
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(false,
                                list));
    }

    @GetMapping("/query-by-phone")
    public ResponseEntity<CustomerDtoListResponse> fetchByPhone(@RequestParam("phone") final String phone) {
        List<Customer> list = customerService.fetchByPhone(phone);
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(false,
                                list));
    }

    @GetMapping("/query-by-address")
    public ResponseEntity<CustomerDtoListResponse> fetchByAddress(@RequestParam("address") final String address) {
        List<Customer> list = customerService.fetchByAddress(address);
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(false,
                                list));
    }

    @GetMapping("/query-order-by")
    public ResponseEntity<CustomerDtoListResponse> fetchAllOrderBy(@RequestParam("orderBy") final String orderBy) {
        List<Customer> list = customerService.fetchAllOrderBy(orderBy);
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(false,
                                list));
    }
}
