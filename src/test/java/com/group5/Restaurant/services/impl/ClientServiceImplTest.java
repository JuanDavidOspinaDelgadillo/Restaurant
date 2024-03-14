package com.group5.Restaurant.services.impl;

import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.WrongResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.domains.entities.ClientEntity;
import com.group5.Restaurant.domains.maps.mappers.ClientMapper;
import com.group5.Restaurant.repositories.IClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceImplTest {
    @Mock
    private IClientRepository repository;

    @Mock
    private ClientMapper mapper;

    @InjectMocks
    private ClientServiceImpl service;

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // CREATE CLIENT TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testCreateClient_whenClientDoesNotExist() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientDocument(123456789L);
        when(this.repository.findById(clientDTO.getClientDocument())).thenReturn(Optional.empty());
        ClientEntity clientEntity = new ClientEntity();
        when(this.mapper.convertClientDTOToClientEntity(clientDTO)).thenReturn(clientEntity);
        ObjectResponseDTO objectResponseDTO = this.service.createClient(clientDTO);
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    public void testCreateClient_whenClientExists() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientDocument(123456789L);
        ClientEntity existingClient = new ClientEntity();
        existingClient.setClientDocument(clientDTO.getClientDocument());
        when(this.repository.findById(clientDTO.getClientDocument())).thenReturn(Optional.of(existingClient));
        ObjectResponseDTO objectResponseDTO = this.service.createClient(clientDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.ALREADY_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals("The client already exist", ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    public void testCreateClient_whenExceptionThrown() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientDocument(123456789L);
        when(this.repository.findById(clientDTO.getClientDocument())).thenThrow(new RuntimeException("Simulated exception"));
        ObjectResponseDTO objectResponseDTO = this.service.createClient(clientDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // READ CLIENT TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testReadClient_whenClientExists() {
        Long clientDocument = 123456789L;
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientDocument(clientDocument);
        when(this.repository.findById(clientDocument)).thenReturn(Optional.of(clientEntity));
        ClientDTO clientDTO = new ClientDTO();
        when(this.mapper.convertClientEntityToClientDTO(clientEntity)).thenReturn(clientDTO);
        ObjectResponseDTO objectResponseDTO = service.readClient(clientDocument);
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
        assertEquals(clientDTO, ((CorrectResponseDTO) objectResponseDTO).getObject());
    }

    @Test
    public void testReadClient_whenClientDoesNotExist() {
        Long clientDocument = 123456789L;
        when(this.repository.findById(clientDocument)).thenReturn(Optional.empty());
        ObjectResponseDTO objectResponseDTO = this.service.readClient(clientDocument);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.DOESNT_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals("The client doesnt exist", ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    public void testReadClient_whenExceptionThrown() {
        Long clientDocument = 123456789L;
        when(this.repository.findById(clientDocument)).thenThrow(new RuntimeException("Simulated exception"));
        ObjectResponseDTO objectResponseDTO = this.service.readClient(clientDocument);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // READ ALL CLIENTS TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void testReadAllClients_whenClientTableIsEmpty(){
        List<ClientEntity> clientEntityList = new ArrayList<>();
        when(this.repository.findAll()).thenReturn(clientEntityList);
        ObjectResponseDTO objectResponseDTO = this.service.readAllClients();
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.NO_DATA_IN_TABLE, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals("There are no data in table", ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    public void testReadAllClients_whenDataExist() {
        List<ClientEntity> clientEntityList = new ArrayList<>();
        clientEntityList.add(new ClientEntity());
        clientEntityList.add(new ClientEntity());
        when(this.repository.findAll()).thenReturn(clientEntityList);
        List<ClientDTO> clientDTOList = new ArrayList<>();
        clientDTOList.add(new ClientDTO());
        clientDTOList.add(new ClientDTO());
        when(this.mapper.convertClientEntityToClientDTO(any(ClientEntity.class))).thenReturn(new ClientDTO());
        ObjectResponseDTO objectResponseDTO = this.service.readAllClients();
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
        assertEquals(clientDTOList, ((CorrectResponseDTO) objectResponseDTO).getObject());
    }

    @Test
    public void testReadAllClients_whenExceptionThrown() {
        when(this.repository.findAll()).thenThrow(new RuntimeException("Simulated exception"));
        ObjectResponseDTO objectResponseDTO = this.service.readAllClients();
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // UPDATE CLIENT TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testUpdateClient_whenClientExistsAndHasChanges() {
        ClientEntity existingClientEntity = new ClientEntity();
        existingClientEntity.setClientName("Name");
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientName("New Name");
        ClientEntity clientDTOMapped = new ClientEntity();
        clientDTOMapped.setClientName(clientDTO.getClientName());
        when(this.repository.findByClientDocument(anyLong())).thenReturn(Optional.of(existingClientEntity));
        when(this.mapper.convertClientDTOToClientEntity(clientDTO)).thenReturn(clientDTOMapped);
        ObjectResponseDTO objectResponseDTO = this.service.updateClient(123456789L, clientDTO);
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
        assertEquals(clientDTO, ((CorrectResponseDTO) objectResponseDTO).getObject());
    }

    @Test
    public void testUpdateClient_whenClientExistsAndHasNoChanges() {
        ClientEntity existingClientEntity = new ClientEntity();
        existingClientEntity.setClientDocument(123456789L);
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientDocument(123456789L);
        when(this.repository.findByClientDocument(anyLong())).thenReturn(Optional.of(existingClientEntity));
        when(this.mapper.convertClientDTOToClientEntity(clientDTO)).thenReturn(existingClientEntity);
        ObjectResponseDTO objectResponseDTO = service.updateClient(123456789L, clientDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.CONFLICT.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.DOESNT_HAVE_CHANGES, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals("The objects are the same", ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    public void testUpdateClient_whenClientDoesntExist() {
        when(this.repository.findByClientDocument(anyLong())).thenReturn(Optional.empty());
        ObjectResponseDTO objectResponseDTO = this.service.updateClient(123456789L, new ClientDTO());
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.DOESNT_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals("The client with document: 123456789 doesnt exist", ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    public void testUpdateClient_whenExceptionThrown() {
        when(this.repository.findByClientDocument(anyLong())).thenThrow(new RuntimeException("Simulated exception"));
        ObjectResponseDTO objectResponseDTO = this.service.updateClient(123456789L, new ClientDTO());
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // UPDATE CLIENT TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    void testDeleteClient_whenClientExists() {
        Long clientDocument = 123456789L;
        ClientEntity clientEntity = new ClientEntity();
        when(this.repository.findById(clientDocument)).thenReturn(Optional.of(clientEntity));
        ObjectResponseDTO responseDTO = this.service.deleteClient(clientDocument);
        assertTrue(responseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) responseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) responseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) responseDTO).getDescription());
    }

    @Test
    void testDeleteClient_whenClientDoesNotExist() {
        Long clientDocument = 123456789L;
        when(this.repository.findById(clientDocument)).thenReturn(Optional.empty());
        ObjectResponseDTO responseDTO = this.service.deleteClient(clientDocument);
        assertTrue(responseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) responseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.DOESNT_EXIST, ((WrongResponseDTO) responseDTO).getCode());
        assertEquals("The client with document: " + clientDocument + " doesnt exist", ((WrongResponseDTO) responseDTO).getDescription());
    }

    @Test
    void testDeleteClient_whenExceptionThrown() {
        Long clientDocument = 123456789L;
        when(this.repository.findById(clientDocument)).thenThrow(new RuntimeException("Test exception"));
        ObjectResponseDTO responseDTO = this.service.deleteClient(clientDocument);
        assertTrue(responseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) responseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) responseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) responseDTO).getDescription());
    }
}
