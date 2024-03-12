package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.commons.responsesObjectDTO.CorrectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import com.group5.Restaurant.commons.responsesObjectDTO.ResponseObjectDTO;
import org.springframework.http.ResponseEntity;

/**
 * Contract of the service ClientServiceImpl
 */
public interface IClientService {
    ResponseEntity<ResponseObjectDTO> createClient(ClientDTO clientDTO);
    ResponseEntity<ResponseObjectDTO> updateClient(ClientDTO clientDTO);
}
