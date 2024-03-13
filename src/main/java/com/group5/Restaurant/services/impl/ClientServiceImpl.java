package com.group5.Restaurant.services.impl;

import com.group5.Restaurant.constants.responses.objectResponseDTO.WrongResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.entities.ClientEntity;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.domains.maps.mappers.ClientMapper;
import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.errors.exceptions.ConflictException;
import com.group5.Restaurant.errors.exceptions.NotFoundException;
import com.group5.Restaurant.repositories.IClientRepository;
import com.group5.Restaurant.services.interfaces.IClientService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public ObjectResponseDTO createClient(ClientDTO clientDTO) {
        try {

            Optional<ClientEntity> clientExist = this.repository.findById(clientDTO.getClientDocument());
            if(clientExist.isEmpty()) {
                ClientEntity clientEntity = this.mapper.convertClientDTOToClientEntity(clientDTO);
                clientEntity.setAddressesEntity(null);
                this.repository.save(clientEntity);
                return CorrectResponseDTO.builder()
                        .httpStatusCode(HttpStatus.OK.value())
                        .code(ResponseCodes.OK)
                        .description(Responses.OK)
                        .timeStamp(LocalDateTime.now())
                        .code(ResponseCodes.OK)
                        .object(clientDTO)
                        .build();
            } else {
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .code(ResponseCodes.ALREADY_EXIST)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .description("The client already exist")
                        .timeStamp(LocalDateTime.now())
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    /**
     * Read a client
     * @param clientDocument ID reference of the document of the client
     * @return Information referred of the operation result
     */
    @Override
    public ObjectResponseDTO readClient(Long clientDocument) {
        try {
            Optional<ClientEntity> clientExist = this.repository.findById(clientDocument);
            if(clientExist.isPresent()) {
                ClientDTO clientDTO = this.mapper.convertClientEntityToClientDTO(clientExist.get());
                return CorrectResponseDTO.builder()
                        .httpStatusCode(HttpStatus.OK.value())
                        .code(ResponseCodes.OK)
                        .description(Responses.OK)
                        .timeStamp(LocalDateTime.now())
                        .code(ResponseCodes.OK)
                        .object(clientDTO)
                        .build();
            } else {
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .code(ResponseCodes.DOESNT_EXIST)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .description("The client doesnt exist")
                        .timeStamp(LocalDateTime.now())
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    /**
     * Read all the clients registered
     * @return Information referred of the operation result
     */
    @Override
    public ObjectResponseDTO readAllClients() {
        try {
            List<ClientEntity> clientEntityList = this.repository.findAll();
            if(!clientEntityList.isEmpty()) {
                List<ClientDTO> clientDTOList = clientEntityList.stream()
                        .map(this.mapper::convertClientEntityToClientDTO)
                        .toList();
                return CorrectResponseDTO.builder()
                        .httpStatusCode(HttpStatus.OK.value())
                        .code(ResponseCodes.OK)
                        .description(Responses.OK)
                        .timeStamp(LocalDateTime.now())
                        .code(ResponseCodes.OK)
                        .object(clientDTOList)
                        .build();
            } else {
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .code(ResponseCodes.NO_DATA_IN_TABLE)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .description("There are no data in table")
                        .timeStamp(LocalDateTime.now())
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR + e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    /**
     * Update a client
     * @param clientDocument New data to update the client
     * @param clientDTO ID reference to the client in the table
     * @return Information referred of the operation result
     */
    @Override
    public ObjectResponseDTO updateClient(Long clientDocument, ClientDTO clientDTO) {
        try {
            Optional<ClientEntity> clientExist = this.repository.findByClientDocument(clientDocument);
            if(clientExist.isPresent()) {
                ClientEntity clientEntity = this.mapper.convertClientDTOToClientEntity(clientDTO);
                if(clientExist.get().equals(clientEntity)) {
                    return WrongResponseDTO.builder()
                            .exception(new ConflictException())
                            .timeStamp(LocalDateTime.now())
                            .httpStatusCode(HttpStatus.CONFLICT.value())
                            .description("The objects are the same")
                            .code(ResponseCodes.DOESNT_HAVE_CHANGES)
                            .build();
                }
                this.repository.save(clientEntity);
                return CorrectResponseDTO.builder()
                        .httpStatusCode(HttpStatus.OK.value())
                        .code(ResponseCodes.OK)
                        .description(Responses.OK)
                        .timeStamp(LocalDateTime.now())
                        .code(ResponseCodes.OK)
                        .object(clientDTO)
                        .build();
            } else {
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .code(ResponseCodes.DOESNT_EXIST)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .description("The client with document: " + clientDocument + " doesnt exist")
                        .timeStamp(LocalDateTime.now())
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    @Override
    public ObjectResponseDTO deleteClient(Long clientDocument) {
        try {
            Optional<ClientEntity> clientExist = this.repository.findById(clientDocument);
            if(clientExist.isPresent()) {
                ClientDTO clientDTO = this.mapper.convertClientEntityToClientDTO(clientExist.get());
                this.repository.deleteById(clientDocument);
                return CorrectResponseDTO.builder()
                        .httpStatusCode(HttpStatus.OK.value())
                        .code(ResponseCodes.OK)
                        .description(Responses.OK)
                        .timeStamp(LocalDateTime.now())
                        .code(ResponseCodes.OK)
                        .object(clientDTO)
                        .build();
            } else {
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .code(ResponseCodes.DOESNT_EXIST)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .description("The client with document: " + clientDocument + " doesnt exist")
                        .timeStamp(LocalDateTime.now())
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }
}