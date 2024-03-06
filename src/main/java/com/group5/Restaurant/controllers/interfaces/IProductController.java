package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.commons.responsesObjectDTO.CorrectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.commons.responsesObjectDTO.ResponseObjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface IProductController {
    @PostMapping
    ResponseEntity<ResponseObjectDTO> createProduct(ProductDTO productDTO);
    @PostMapping
    ResponseEntity<ResponseObjectDTO> updateProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO);
    @PostMapping
    ResponseEntity<ResponseObjectDTO> deleteProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO);
}