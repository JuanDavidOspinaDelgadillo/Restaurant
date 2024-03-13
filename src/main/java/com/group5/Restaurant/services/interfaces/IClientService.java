package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import org.springframework.http.ResponseEntity;

/**
 * Contract of the service ClientServiceImpl
 */
public interface IClientService {
    ObjectResponseDTO createClient(ClientDTO clientDTO);
    ObjectResponseDTO readClient(Long clientDocument);
    ObjectResponseDTO readAllClients();
    ObjectResponseDTO updateClient(Long clientDocument, ClientDTO clientDTO);
    ObjectResponseDTO deleteClient(Long clientDocument);
}