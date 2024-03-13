package com.group5.Restaurant.controllers.impl;

import com.group5.Restaurant.errors.validations.ClientDTOValidation;
import com.group5.Restaurant.services.interfaces.IClientService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
@ExtendWith(MockitoExtension.class)
class ClientControllerImplTest {

    @InjectMocks
    ClientControllerImpl clientController;
    @Mock
    IClientService service;
    @Mock
    ClientDTOValidation clientDTOValidation;
    @BeforeEach
    void setUp() {
        //this.clientController = new ClientControllerImpl(service, clientDTOValidation);
    }
}