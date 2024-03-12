package com.group5.Restaurant.services.impl;

import com.group5.Restaurant.constants.responses.constantResponses.ConstantResponses;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.entities.ClientEntity;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.domains.maps.mappers.ClientMapper;
import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.repositories.IClientRepository;
import com.group5.Restaurant.services.interfaces.IClientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service of the entity ClientEntity
 */
@Service
@AllArgsConstructor
@Log4j2
public class ClientServiceImpl implements IClientService {

    final IClientRepository repository;//Repository dependency injected

    final ClientMapper mapper;//Mapper dependency injected

    /**
     * Create a client
     * @param clientDTO Data reference object of the client
     * @return Information referred of the operation result
     */
    @Override
    public ResponseEntity<ObjectResponseDTO> createClient(ClientDTO clientDTO) {
        try {
            Optional<ClientEntity> clientExist = this.repository.findById(clientDTO.getClientDocument());
            if(clientExist.isEmpty()) {
                ClientEntity clientEntity = this.mapper.convertClientDTOToClientEntity(clientDTO);
                this.repository.save(clientEntity);
                return ConstantResponses.OK.apply("Correct operation with client" + clientDTO);
            } else {
                return ConstantResponses.BAD_REQUEST.apply("Te client " + clientDTO + "already exist", ResponseCodes.ALREADY_EXIST);
            }
        } catch (Exception e) {
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply("was an error working on the information of the client: " + clientDTO.toString(), e);
        }
    }

    @Override
    public ResponseEntity<ObjectResponseDTO> readClient(String clientDocument) {
        try {
            Optional<ClientEntity> clientExist = this.repository.findById(clientDocument);
            return clientExist.<ResponseEntity<ObjectResponseDTO>>map(clientEntity -> ResponseEntity.ok().body(CorrectResponseDTO.builder()
                            .timeStamp(LocalDateTime.now())
                            .code(ResponseCodes.OK)
                            .description("The client with document " + clientDocument + "exists!")
                            .object(clientEntity)
                            .httpStatusCode(HttpStatus.OK.value())
                            .build()))
                    .orElseGet(() -> ConstantResponses.BAD_REQUEST.apply("Client with document " + clientDocument + "doesnt exist", ResponseCodes.DOESNT_EXIST));
        } catch (Exception e) {
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply("Was an error searching the client with this information: " + clientDocument, e);
        }
    }

    @Override
    public ResponseEntity<ObjectResponseDTO> deleteClient(String clientDocument) {
        try {
            Optional<ClientEntity> clientExist = this.repository.findById(clientDocument);
            if (clientExist.isPresent()) {
                this.repository.deleteById(clientDocument);
                return ConstantResponses.OK.apply(ResponseCodes.OK);
            }
            else {
                return ConstantResponses.CONFLICT.apply(ResponseCodes.DOESNT_EXIST);
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply(ResponseCodes.INTERNAL_SERVER_ERROR, e);
        }
    }
}