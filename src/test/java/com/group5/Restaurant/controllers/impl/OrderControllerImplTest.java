package com.group5.Restaurant.controllers.impl;

import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import com.group5.Restaurant.errors.validations.OrderDTOValidation;
import com.group5.Restaurant.services.interfaces.IOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerImplTest {
    @InjectMocks
    OrderControllerImpl controller;

    @Mock
    IOrderService service;

    @Mock
    OrderDTOValidation validator;

    @Test
    void testCreateOrder_whenOrderDTOIsValid() throws BadRequestException {
        OrderDTO orderDTO = new OrderDTO();
        ResponseEntity<ObjectResponseDTO> expectedResponse = ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(CorrectResponseDTO.builder()
                        .httpStatusCode(HttpStatus.OK.value())
                        .code(ResponseCodes.OK)
                        .description(Responses.OK)
                        .timeStamp(LocalDateTime.now())
                        .code(ResponseCodes.OK)
                        .object(orderDTO)
                        .build());
        doNothing().when(this.validator).validateOrderDTO(orderDTO);
        when(this.service.createOrder(orderDTO)).thenReturn(expectedResponse.getBody());
        ResponseEntity<ObjectResponseDTO> responseEntity = this.controller.createOrder(orderDTO);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(expectedResponse.getBody(), responseEntity.getBody());
    }
}
