package com.group5.Restaurant.controllers.impl;

import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import com.group5.Restaurant.errors.validations.ProductDTOValidation;
import com.group5.Restaurant.services.interfaces.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerImplTest {

    @InjectMocks
    ProductControllerImpl controller;
    @Mock
    IProductService service;
    @Mock
    ProductDTOValidation validator;

    @Test
    void testCreateProduct_when_ProductDTOIsValid() throws BadRequestException {
        ProductDTO productDTO = new ProductDTO();
        ResponseEntity<ObjectResponseDTO> expectedResponse = ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(CorrectResponseDTO.builder()
                        .httpStatusCode(HttpStatus.OK.value())
                        .code(ResponseCodes.OK)
                        .description(Responses.OK)
                        .timeStamp(LocalDateTime.now())
                        .code(ResponseCodes.OK)
                        .object(productDTO)
                        .build());
        doNothing().when(this.validator).validateProductDTO(productDTO);
        when(this.service.createProduct(productDTO)).thenReturn(expectedResponse.getBody());
        ResponseEntity<ObjectResponseDTO> responseEntity = this.controller.createProduct(productDTO);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(expectedResponse.getBody(), responseEntity.getBody());
    }
}
