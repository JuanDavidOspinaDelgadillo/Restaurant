package com.group5.Restaurant.services;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Contract of the service ClientServiceImpl
 */
public interface IClientService {
    ResponseEntity<ObjectResponseDTO> createClient(ClientDTO clientDTO);
}
