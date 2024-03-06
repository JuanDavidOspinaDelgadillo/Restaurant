package com.group5.Restaurant.controllers.interfaces;

import com.group5.Restaurant.commons.responsesObjectDTO.CorrectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface IProductController {
    @PostMapping
    ResponseEntity<CorrectResponseDTO> createProduct(ProductDTO productDTO);
}
