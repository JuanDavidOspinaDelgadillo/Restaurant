package com.group5.Restaurant.controllers;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface IProductController {

    ResponseEntity<ObjectResponseDTO> updateProduct(@PathVariable UUID uuid, @RequestBody ProductDTO productDTO);
    ResponseEntity<ObjectResponseDTO> deleteProduct(@PathVariable UUID uuid, @RequestBody ProductDTO productDTO);
}
