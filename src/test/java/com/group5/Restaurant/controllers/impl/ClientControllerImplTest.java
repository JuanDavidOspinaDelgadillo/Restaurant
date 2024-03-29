package com.group5.Restaurant.controllers.impl;

import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import com.group5.Restaurant.errors.validations.ClientDTOValidation;
import com.group5.Restaurant.services.interfaces.IClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerImplTest {

    @InjectMocks
    ClientControllerImpl controller;
    @Mock
    IClientService service;
    @Mock
    ClientDTOValidation validator;

    @Test
    void testCreateClient_whenClientDTOIsValid() throws BadRequestException {
        ClientDTO clientDTO = new ClientDTO();
        ResponseEntity<ObjectResponseDTO> expectedResponse = ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(CorrectResponseDTO.builder()
                        .httpStatusCode(HttpStatus.OK.value())
                        .code(ResponseCodes.OK)
                        .description(Responses.OK)
                        .timeStamp(LocalDateTime.now())
                        .code(ResponseCodes.OK)
                        .object(clientDTO)
                        .build());
        doNothing().when(this.validator).validateClientDTO(clientDTO);
        when(service.createClient(clientDTO)).thenReturn(expectedResponse.getBody());
        ResponseEntity<ObjectResponseDTO> responseEntity = this.controller.createClient(clientDTO);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(expectedResponse.getBody(), responseEntity.getBody());
    }
}
