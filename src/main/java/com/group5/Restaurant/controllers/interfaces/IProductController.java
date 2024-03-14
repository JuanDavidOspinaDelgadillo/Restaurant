package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IProductController {
    @PostMapping
    ResponseEntity<ObjectResponseDTO> createProduct(ProductDTO productDTO) throws BadRequestException;
    @GetMapping
    ResponseEntity<ObjectResponseDTO> readProduct(String productUUID) throws BadRequestException;
    @GetMapping
    ResponseEntity<ObjectResponseDTO> readAllProducts();
    @PostMapping
    ResponseEntity<ObjectResponseDTO> updateProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO) throws BadRequestException;
    @DeleteMapping
    ResponseEntity<ObjectResponseDTO> deleteProduct(@PathVariable String productUUID) throws BadRequestException;
    @GetMapping
    ResponseEntity<ObjectResponseDTO> getAllProductsByFantasyName(@PathVariable String productFantasyName) throws BadRequestException;
}