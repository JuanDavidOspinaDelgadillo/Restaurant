package com.group5.Restaurant.services.impl;

import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.WrongResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.domains.entities.ProductEntity;
import com.group5.Restaurant.domains.maps.mappers.ProductMapper;
import com.group5.Restaurant.repositories.IProductRepository;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceImplTest {
    @Mock
    private IProductRepository repository;
    @Mock
    private ProductMapper mapper;
    @InjectMocks
    private ProductServiceImpl service;

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // CREATE PRODUCT TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testCreateProduct_whenProductDoesntExist() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        when(this.repository.findByProductUUID(productDTO.getProductUUID())).thenReturn(Optional.empty());
        ProductEntity productEntity = new ProductEntity();
        when(this.mapper.convertProductDTOToProductEntity(productDTO)).thenReturn(productEntity);
        ObjectResponseDTO objectResponseDTO = this.service.createProduct(productDTO);
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testCreateProduct_whenProductExists() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        when(this.repository.findByProductUUID(productDTO.getProductUUID())).thenReturn(Optional.of(new ProductEntity()));
        ObjectResponseDTO objectResponseDTO = this.service.createProduct(productDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.ALREADY_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.NOT_FOUND, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testCreateProduct_whenExceptionThrown() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        when(this.repository.findByProductUUID(productDTO.getProductUUID())).thenThrow(new RuntimeException());
        ObjectResponseDTO objectResponseDTO = this.service.createProduct(productDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // READ PRODUCT TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    void testReadProduct_whenProductExists() {
        String productUUID = "1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa";
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductUUID(productUUID);
        when(this.repository.findByProductUUID(productUUID)).thenReturn(Optional.of(productEntity));
        ObjectResponseDTO objectResponseDTO = this.service.readProduct(productUUID);
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testReadProduct_whenProductDoesNotExist() {
        String productUUID = "1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa";
        when(this.repository.findByProductUUID(productUUID)).thenReturn(Optional.empty());
        ObjectResponseDTO objectResponseDTO = this.service.readProduct(productUUID);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.DOESNT_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.NOT_FOUND, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testReadProduct_whenExceptionThrown() {
        String productUUID = "1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa";
        when(this.repository.findByProductUUID(productUUID)).thenThrow(new RuntimeException());
        ObjectResponseDTO objectResponseDTO = this.service.readProduct(productUUID);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // READ ALL PRODUCTS TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    void testReadAllProducts_whenProductsExist() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(new ProductEntity());
        productEntityList.add(new ProductEntity());
        when(this.repository.findAll()).thenReturn(productEntityList);
        ObjectResponseDTO objectResponseDTO = this.service.readAllProducts();
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testReadAllProducts_whenNoProductsFound() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        when(this.repository.findAll()).thenReturn(productEntityList);
        ObjectResponseDTO objectResponseDTO = this.service.readAllProducts();
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.NO_DATA_IN_TABLE, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.NOT_FOUND, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testReadAllProducts_whenExceptionThrown() {
        when(this.repository.findAll()).thenThrow(new RuntimeException());
        ObjectResponseDTO objectResponseDTO = this.service.readAllProducts();
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // UPDATE PRODUCT TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    void testUpdateProduct_whenProductExists() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setProductUUID(productDTO.getProductUUID());
        when(this.repository.findByProductUUID(productDTO.getProductUUID())).thenReturn(Optional.of(existingProductEntity));
        when(this.repository.save(any(ProductEntity.class))).thenReturn(existingProductEntity);
        ObjectResponseDTO objectResponseDTO = this.service.updateProduct(productDTO);
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testUpdateProduct_whenProductExistsButNoChanges() {
        String productUUID = "1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa";
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductUUID(productUUID);
        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setProductUUID(productUUID);
        when(this.repository.findByProductUUID(productDTO.getProductUUID())).thenReturn(Optional.of(existingProductEntity));
        when(this.mapper.convertProductDTOToProductEntity(productDTO)).thenReturn(existingProductEntity);
        ObjectResponseDTO objectResponseDTO = this.service.updateProduct(productDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.CONFLICT.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.DOESNT_HAVE_CHANGES, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.CONFLICT, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testUpdateProduct_whenProductDoesntExist() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        when(this.repository.findByProductUUID(productDTO.getProductUUID())).thenReturn(Optional.empty());
        ObjectResponseDTO objectResponseDTO = this.service.updateProduct(productDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.DOESNT_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.NOT_FOUND, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testUpdateProduct_whenExceptionThrown() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductUUID("1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa");
        when(this.repository.findByProductUUID(productDTO.getProductUUID())).thenThrow(new RuntimeException());
        ObjectResponseDTO objectResponseDTO = this.service.updateProduct(productDTO);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // DELETE PRODUCT TESTS
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    void testDeleteProduct_whenProductExists() {
        String productUUID = "1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa";
        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setProductUUID(productUUID);
        when(this.repository.findById(productUUID)).thenReturn(Optional.of(existingProductEntity));
        ProductDTO productDTO = new ProductDTO();
        when(mapper.converterProductEntityToProductDTO(existingProductEntity)).thenReturn(productDTO);
        ObjectResponseDTO objectResponseDTO = this.service.deleteProduct(productUUID);
        assertTrue(objectResponseDTO instanceof CorrectResponseDTO);
        assertEquals(HttpStatus.OK.value(), ((CorrectResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.OK, ((CorrectResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.OK, ((CorrectResponseDTO) objectResponseDTO).getDescription());
        assertEquals(productDTO, ((CorrectResponseDTO) objectResponseDTO).getObject());
    }

    @Test
    void testDeleteProduct_whenProductDoesNotExist() {
        String productUUID = "nonexistent-uuid";
        when(this.repository.findById(productUUID)).thenReturn(Optional.empty());
        ObjectResponseDTO objectResponseDTO = this.service.deleteProduct(productUUID);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.NOT_FOUND.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.DOESNT_EXIST, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.NOT_FOUND, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }

    @Test
    void testDeleteProduct_whenErrorOccurs() {
        String productUUID = "1a11a1a1-1a1a-1a1a-a1a1-1a1aaa1111aa";
        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setProductUUID(productUUID);
        when(this.repository.findById(productUUID)).thenReturn(Optional.of(existingProductEntity));
        doThrow(new RuntimeException("Error deleting product")).when(this.repository).deleteById(productUUID);
        ObjectResponseDTO objectResponseDTO = this.service.deleteProduct(productUUID);
        assertTrue(objectResponseDTO instanceof WrongResponseDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), ((WrongResponseDTO) objectResponseDTO).getHttpStatusCode());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getCode());
        assertEquals(Responses.INTERNAL_SERVER_ERROR, ((WrongResponseDTO) objectResponseDTO).getDescription());
    }
}
