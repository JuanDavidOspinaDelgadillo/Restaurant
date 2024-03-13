package com.group5.Restaurant.errors.validations;

import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Filter of validation of Order API
 */
@Component
public class OrderDTOValidation {

    /**
     * Validator of an object OrderDTO
     * @param orderDTO Object to validate
     * @throws BadRequestException When the data is not valid
     */
    public void validateOrderDTO(OrderDTO orderDTO) throws BadRequestException {
        if(orderDTO.getOrderQuantity() == null || orderDTO.getOrderQuantity() <= 0 ||
        orderDTO.getOrderAdditionalInformation() == null ||
        orderDTO.getOrderAdditionalInformation().trim().isEmpty() ||
        orderDTO.getOrderSubTotal() == null ||
        orderDTO.getOrderSubTotal() <= 0 ||
        orderDTO.getOrderTax() == null ||
        orderDTO.getOrderTax() < 0 ||
        orderDTO.getOrderTotalPrice() == null ||
        orderDTO.getOrderTotalPrice() <= 0 ||
        orderDTO.getOrderDelivered() == null ||
        orderDTO.getOrderDelivered() ||
        orderDTO.getOrderDeliveredDate() == null ||
        orderDTO.getProductUUID() == null ||
        orderDTO.getProductUUID().trim().isEmpty() ||
        orderDTO.getClientDocument() == null ||
        orderDTO.getClientDocument() <= 0)
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
    }

    /**
     * Validator of a productUUID String and a deliveredDate date
     * @param productUUID The ID reference of the product
     * @param deliveredDate The date when the order was delivered
     * @throws BadRequestException When the data is not valid
     */
    public void validateProductUUIDAndDeliveredDate(String productUUID, LocalDateTime deliveredDate) throws BadRequestException {
        if(productUUID == null || productUUID.trim().isEmpty() || deliveredDate == null)
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
    }
}