package com.group5.Restaurant.commons.domains.maps.mappers;

import com.group5.Restaurant.commons.constants.responses.Responses;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import com.group5.Restaurant.commons.domains.entities.ClientEntity;
import com.group5.Restaurant.commons.domains.maps.Mapper;
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
}