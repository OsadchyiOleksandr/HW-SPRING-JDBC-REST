package org.example.app.controller;

import org.example.app.dto.user.*;
import org.example.app.entity.user.Customer;
import org.example.app.service.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

// Вхідна точка (REST-контроллер)
@RestController
@RequestMapping("api/v1/users")
public class CustomerController {

    @Autowired
    CustomerService userService;

//    @PostMapping
//    public ResponseEntity<CustomerDtoCreateResponse> createUser(
//            @RequestBody CustomerDtoRequest request) {
//        Customer user = userService.create(request);
//        // NO ternary operator
//        if (user != null)
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(CustomerDtoCreateResponse.of(true,
//                            user));
//        else
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(CustomerDtoCreateResponse.of(false,
//                            null));
//    }

    @PostMapping
    public ResponseEntity<CustomerDtoCreateResponse> createUser(
            @RequestBody CustomerDtoRequest request) {
        Customer user = userService.create(request);
        // ternary operator usage
        return (user != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoCreateResponse.of(true,
                                user)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<CustomerDtoListResponse> fetchAllUsers() {
        List<Customer> list = userService.fetchAll();
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
    public ResponseEntity<CustomerDtoGetByIdResponse> fetchUserById(
            @PathVariable("id") Long id) {
        Customer user = userService.fetchById(id);
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetByIdResponse.of(id, true,
                            user));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetByIdResponse.of(id, false,
                            null));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CustomerDtoUpdateResponse> updateUserById(
//            @PathVariable("id") Long id,
//            @RequestBody CustomerDtoRequest request) {
//        Customer userToUpdate = userService.fetchById(id);
//        if (userToUpdate != null) {
//            Customer userUpdated = userService.updateById(id, request);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(CustomerDtoUpdateResponse.of(id, true,
//                    userUpdated));
//        } else {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(CustomerDtoUpdateResponse.of(id, false,
//                    null));
//        }
//    }

    // Refactored method updateUserById()
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoUpdateResponse> updateUserById(
            @PathVariable("id") Long id,
            @RequestBody CustomerDtoRequest request) {
        if (userService.fetchById(id) != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoUpdateResponse.of(id, true,
                            userService.updateById(id, request)));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoUpdateResponse.of(id, false,
                            null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDtoDeleteResponse> deleteUserById(
            @PathVariable("id") Long id) {
        if (userService.deleteById(id))
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoDeleteResponse.of(id, true));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoDeleteResponse.of(id, false));
    }

    @GetMapping("/last-entity")
    public ResponseEntity<CustomerDtoGetLastEntityResponse> getLastEntity() {
        Customer user = userService.getLastEntity();
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetLastEntityResponse.of(true,
                            user));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetLastEntityResponse.of(false,
                            null));
    }

    // ---- Query Params ----------------------

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-firstname?firstName=Tom
        If firstName does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-firstname?firstName=Tomas
    */
    @GetMapping("/query-by-firstname")
    public ResponseEntity<CustomerDtoListResponse> fetchByFirstName(@RequestParam("firstName") final String firstName) {
        List<Customer> list = userService.fetchByFirstName(firstName);
        // ternary operator usage
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(false,
                                list));
    }

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Bright
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Terra
        If lastName does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Mars
    */
    @GetMapping("/query-by-lastname")
    public ResponseEntity<CustomerDtoListResponse> fetchByLastName(@RequestParam("lastName") final String lastName) {
        List<Customer> list = userService.fetchByLastName(lastName);
        // ternary operator usage
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(false,
                                list));
    }

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-order-by?orderBy=first_name
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-order-by?orderBy=last_name
    */
    @GetMapping("/query-order-by")
    public ResponseEntity<CustomerDtoListResponse> fetchAllOrderBy(@RequestParam("orderBy") final String orderBy) {
        List<Customer> list = userService.fetchAllOrderBy(orderBy);
        // ternary operator usage
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoListResponse.of(false,
                                list));
    }

}
