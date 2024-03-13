package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;

/**
 * Contract of the order controller
 */
public interface IOrderController {
    @PostMapping
    ResponseEntity<ObjectResponseDTO> createOrder(OrderDTO orderDTO) throws BadRequestException;
    @PutMapping
    ResponseEntity<ObjectResponseDTO> updateOrderToDelivered(String productUUID, LocalDateTime deliveredDate) throws BadRequestException;
}
