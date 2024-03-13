package com.group5.Restaurant.domains.maps.mappers;

import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.domains.entities.ClientEntity;
import com.group5.Restaurant.domains.maps.Mapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Use ModelMapper already created to map the entity ClientEntity
 */
@Component
@Log4j2
public class ClientMapper {
    public ClientEntity convertClientDTOToClientEntity(ClientDTO clientDTO){
        ClientEntity clientEntity = new ClientEntity();
        try {
            clientEntity = Mapper.modelMapper().map(clientDTO, ClientEntity.class);
        }catch (Exception e){
            log.error(Responses.INTERNAL_SERVER_ERROR + e);
        }
        return clientEntity;
    }

    public ClientDTO convertClientEntityToClientDTO(ClientEntity clientEntity) {
        ClientDTO clientDTO = new ClientDTO();
        try {
            clientDTO = Mapper.modelMapper().map(clientEntity, ClientDTO.class);
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
        }
        return clientDTO;
    }
}