package com.group5.Restaurant.commons.domains.validators;

import com.group5.Restaurant.commons.constants.responses.Responses;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class ClientValidator {
    Predicate<String> validateString = str -> str != null && !str.isEmpty();
    Predicate<Short> validatePositiveNumber = num -> num > 0;
    public void validateClient(ClientDTO clientDTO) {
        validateString(clientDTO.getClientName());
        validateString(clientDTO.getClientEmail());
        validateString(clientDTO.getClientAddress());
        validatePositiveNumber(clientDTO.getClientPhoneNumber());
    }

    private void validateString(String value) {
        if (!validateString.test(value)) {
            throw new IllegalArgumentException(Responses.ILLEGAL_ARGUMENTS);
        }
    }

    private void validatePositiveNumber(Short value) {
        if (!validatePositiveNumber.test(value)) {
            throw new IllegalArgumentException(Responses.ILLEGAL_ARGUMENTS);
        }
    }
}