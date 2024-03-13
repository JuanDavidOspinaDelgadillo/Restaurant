package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface IOrderController {
    @PostMapping
    ResponseEntity<ObjectResponseDTO> createOrder(OrderDTO orderDTO) throws BadRequestException;
}
