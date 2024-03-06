package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.commons.responsesObjectDTO.CorrectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import com.group5.Restaurant.commons.responsesObjectDTO.ResponseObjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Contract of the controller ClientControllerImpl
 */
public interface IClientController {
    @PostMapping
    ResponseEntity<ResponseObjectDTO> createClient(ClientDTO clientDTO);
}