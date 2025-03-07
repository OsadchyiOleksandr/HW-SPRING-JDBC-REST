package org.example.app.dto.customer;

import org.example.app.entity.customer.Customer;
import org.springframework.http.HttpStatus;

public record CustomerDtoGetByIdResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Customer user) {

    public static final String SUCCESS_MESSAGE = "Customer with id %s has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Customer with id %s has not been found!";

    public static CustomerDtoGetByIdResponse of(Long id, boolean isUserFound, Customer user) {
        if (isUserFound)
            return new CustomerDtoGetByIdResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), user);
        else
            return new CustomerDtoGetByIdResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
