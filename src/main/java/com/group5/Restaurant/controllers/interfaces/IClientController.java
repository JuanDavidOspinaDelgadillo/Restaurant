package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Contract of the controller ClientControllerImpl
 */
public interface IClientController {
    @PostMapping
    ResponseEntity<ObjectResponseDTO> createClient(ClientDTO clientDTO);
    @GetMapping
    ResponseEntity<ObjectResponseDTO> readClient(String clientDocument);

    @DeleteMapping
    ResponseEntity<ObjectResponseDTO> deleteClient(String clientDocument);
}