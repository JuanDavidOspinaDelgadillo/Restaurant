package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;

/**
 * Contract of the service ClientServiceImpl
 */
public interface IClientService {
    ResponseEntity<ObjectResponseDTO> createClient(ClientDTO clientDTO);
}
