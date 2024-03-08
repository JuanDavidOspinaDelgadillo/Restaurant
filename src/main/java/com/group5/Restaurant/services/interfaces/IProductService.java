package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import org.springframework.http.ResponseEntity;

/**
 * Contract of the service ProductServiceImpl
 */
public interface IProductService {
    ResponseEntity<ObjectResponseDTO> createProduct(ProductDTO productDTO);
    ResponseEntity<ObjectResponseDTO> readProduct(String productUUID);
    ResponseEntity<ObjectResponseDTO> updateProduct(ProductDTO productDTO);
    ResponseEntity<ObjectResponseDTO> deleteProduct(ProductDTO productDTO);
}