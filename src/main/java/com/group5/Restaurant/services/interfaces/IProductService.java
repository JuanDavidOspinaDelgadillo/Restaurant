package com.group5.Restaurant.services;

import com.group5.Restaurant.commons.responsesObjectDTO.CorrectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    ResponseEntity<CorrectResponseDTO> updateProduct(ProductDTO productDTO);
}
