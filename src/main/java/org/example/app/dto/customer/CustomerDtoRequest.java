package org.example.app.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerDtoRequest(Long id, String name,
                                 String phone, String address) {
}
