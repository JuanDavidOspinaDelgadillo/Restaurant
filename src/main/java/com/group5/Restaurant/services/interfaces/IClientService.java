package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import org.springframework.http.ResponseEntity;

/**
 * Contract of the service ClientServiceImpl
 */
public interface IClientService {
    ResponseEntity<ObjectResponseDTO> createClient(ClientDTO clientDTO);
    ResponseEntity<ObjectResponseDTO> readClient(String clientDocument);
    ResponseEntity<ObjectResponseDTO> deleteClient(String clientDocument);
}