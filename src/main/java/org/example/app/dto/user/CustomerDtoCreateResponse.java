package org.example.app.dto.user;

import org.example.app.entity.user.Customer;
import org.springframework.http.HttpStatus;

public record CustomerDtoCreateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Customer user) {

    public static final String SUCCESS_MESSAGE = "Customer has been created successfully.";
    public static final String FAILURE_MESSAGE = "Customer has not been created!";

//    public static CustomerDtoCreateResponse of(boolean isUserCreated, Customer user) {
//        if (isUserCreated)
//            return new CustomerDtoCreateResponse(
//                    HttpStatus.OK.value(),
//                    HttpStatus.OK.getReasonPhrase(),
//                    true, SUCCESS_MESSAGE, user);
//        else
//            return new CustomerDtoCreateResponse(
//                    HttpStatus.NO_CONTENT.value(),
//                    HttpStatus.NO_CONTENT.getReasonPhrase(),
//                    false, FAILURE_MESSAGE, null);
//    }

    public static CustomerDtoCreateResponse of(boolean isUserCreated, Customer user) {
        // ternary operator usage
        return (isUserCreated) ?
                new CustomerDtoCreateResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE, user) :
                new CustomerDtoCreateResponse(
                        HttpStatus.NO_CONTENT.value(),
                        HttpStatus.NO_CONTENT.getReasonPhrase(),
                        false, FAILURE_MESSAGE, null);
    }
}
