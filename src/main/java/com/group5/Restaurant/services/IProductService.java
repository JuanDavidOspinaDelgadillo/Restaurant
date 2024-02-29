package com.group5.Restaurant.services;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ClientDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    ResponseEntity<ObjectResponseDTO> createProduct(ProductDTO productDTO);
}