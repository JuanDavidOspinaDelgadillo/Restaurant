package com.group5.Restaurant.services.impl;

import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.WrongResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.domains.entities.ClientEntity;
import com.group5.Restaurant.domains.entities.OrderEntity;
import com.group5.Restaurant.domains.entities.ProductEntity;
import com.group5.Restaurant.domains.maps.mappers.OrderMapper;
import com.group5.Restaurant.repositories.IClientRepository;
import com.group5.Restaurant.repositories.IOrderRepository;
import com.group5.Restaurant.repositories.IProductRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceImplTest {
    @Mock
    IOrderRepository repository;
    @Mock
    IProductRepository productRepository;
    @Mock
    IClientRepository clientRepository;
    @Mock
    OrderMapper mapper;
    @InjectMocks
    OrderServiceImpl service;

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // CREATE ORDER TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    void testCreateOrder_whenProductAndClientExist() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        orderDTO.setClientDocument(123456789L);
        ProductEntity productEntity = new ProductEntity();
        when(this.productRepository.findByProductUUID(orderDTO.getProductUUID())).thenReturn(Optional.of(productEntity));
        ClientEntity clientEntity = new ClientEntity();
        when(this.clientRepository.findById(orderDTO.getClientDocument())).thenReturn(Optional.of(clientEntity));
        OrderEntity savedOrderEntity = new OrderEntity();
        when(this.mapper.convertOrderDTOToOrderEntity(orderDTO)).thenReturn(savedOrderEntity);
        when(this.repository.save(any(OrderEntity.class))).thenReturn(savedOrderEntity);
        ObjectResponseDTO objectResponseDTO = service.createOrder(orderDTO);
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(orderDTO, ((CorrectResponseDTO) objectResponseDTO).getObject());
    }

    @Test
    void testCreateOrder_whenProductDoesNotExist() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        orderDTO.setClientDocument(123456789L);
        when(this.productRepository.findByProductUUID(orderDTO.getProductUUID())).thenReturn(Optional.empty());
        ObjectResponseDTO objectResponseDTO = this.service.createOrder(orderDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.PRODUCT_RELATED_DOESNT_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals("The product related doesnt exist", ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testCreateOrder_whenClientDoesNotExist() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        orderDTO.setClientDocument(987654321L);
        ProductEntity productEntity = new ProductEntity();
        when(this.productRepository.findByProductUUID(orderDTO.getProductUUID())).thenReturn(Optional.of(productEntity));
        when(this.clientRepository.findById(orderDTO.getClientDocument())).thenReturn(Optional.empty());
        ObjectResponseDTO objectResponseDTO = this.service.createOrder(orderDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.CLIENT_RELATED_DOESNT_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals("The client related doesnt exist", ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testCreateOrder_whenRepositoryThrowsException() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        orderDTO.setClientDocument(987654321L);
        when(this.productRepository.findByProductUUID(orderDTO.getProductUUID())).thenReturn(Optional.of(new ProductEntity()));
        when(this.clientRepository.findById(orderDTO.getClientDocument())).thenThrow(new RuntimeException("Simulated exception"));
        ObjectResponseDTO objectResponseDTO = this.service.createOrder(orderDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }
}