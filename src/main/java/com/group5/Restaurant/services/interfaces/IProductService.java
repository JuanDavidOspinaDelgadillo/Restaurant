package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IProductService {
    ResponseEntity<ObjectResponseDTO> updateProduct(ProductDTO productDTO);
    ResponseEntity<ObjectResponseDTO> deleteProduct(UUID uuid);
}
