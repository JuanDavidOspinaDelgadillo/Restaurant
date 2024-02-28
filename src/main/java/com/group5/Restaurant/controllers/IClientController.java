package com.group5.Restaurant.controllers;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Contract of the controller ClientControllerImpl
 */
public interface IClientController {
    @PostMapping
    ResponseEntity<ObjectResponseDTO> createClient(ClientDTO clientDTO);
}
