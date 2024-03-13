package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Contract of the controller ClientControllerImpl
 */
public interface IClientController {
    @PostMapping
    ResponseEntity<ObjectResponseDTO> createClient(ClientDTO clientDTO) throws BadRequestException;
    @GetMapping
    ResponseEntity<ObjectResponseDTO> readClient(Long clientDocument) throws BadRequestException;
    @GetMapping
    ResponseEntity<ObjectResponseDTO> readAllClients();
    @PutMapping
    ResponseEntity<ObjectResponseDTO> updateClient(Long clientDocument, ClientDTO clientDTO) throws BadRequestException;
    @DeleteMapping
    ResponseEntity<ObjectResponseDTO> deleteClient(Long clientDocument) throws BadRequestException;
}