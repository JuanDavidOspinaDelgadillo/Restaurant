package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IProductController {
    @PostMapping
    ResponseEntity<ObjectResponseDTO> createProduct(ProductDTO productDTO);
    @PostMapping
    ResponseEntity<ObjectResponseDTO> updateProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO);
    @PostMapping
    ResponseEntity<ObjectResponseDTO> deleteProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO);
}