package com.group5.Restaurant.errors.validations;

import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderDTOValidation {
    public void validateOrderDTO(OrderDTO orderDTO) throws BadRequestException {
        if((orderDTO.getOrderQuantity() == null || orderDTO.getOrderQuantity() <= 0) ||
        (orderDTO.getOrderAdditionalInformation() != null && orderDTO.getOrderAdditionalInformation().trim().isEmpty()) ||
        (orderDTO.getProductUUID() == null || orderDTO.getProductUUID().trim().isEmpty()) ||
        (orderDTO.getClientDocument() == null || orderDTO.getClientDocument() <= 0))
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
    }

    public void validateOrderID(Long orderID) throws BadRequestException {
        if(orderID <= 0)
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
    }
}