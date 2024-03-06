package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.commons.responsesObjectDTO.ResponseObjectDTO;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    ResponseEntity<ResponseObjectDTO> createProduct(ProductDTO productDTO);
    ResponseEntity<ResponseObjectDTO> updateProduct(ProductDTO productDTO);
    ResponseEntity<ResponseObjectDTO> deleteProduct(ProductDTO productDTO);
}