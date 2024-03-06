package com.group5.Restaurant.services.impl;

import com.group5.Restaurant.commons.constants.responses.ConstantResponses;
import com.group5.Restaurant.commons.domains.entities.ClientEntity;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import com.group5.Restaurant.commons.domains.maps.mappers.ClientMapper;
import com.group5.Restaurant.commons.responsesObjectDTO.ResponseObjectDTO;
import com.group5.Restaurant.repositories.IClientRepository;
import com.group5.Restaurant.services.interfaces.IClientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<ResponseObjectDTO> createClient(ClientDTO clientDTO) {
        try {
            Optional<ClientEntity> clientExist = this.repository.findById(clientDTO.getClientId());
            if(clientExist.isEmpty()) {
                ClientEntity clientEntity = this.mapper.convertClientDTOToClientEntity(clientDTO);
                this.repository.save(clientEntity);
                return ConstantResponses.OK.get();
            } else {
                return ConstantResponses.BAD_REQUEST.get();
            }
        } catch (RuntimeException e) {
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply(e);
        }
    }
}