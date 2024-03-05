package com.group5.Restaurant.controllers;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface IProductController {
    @PostMapping
    ResponseEntity<ObjectResponseDTO> createProduct(ProductDTO productDTO);
}
