package com.group5.Restaurant.controllers.impl;


import com.group5.Restaurant.commons.constants.endpoints.IProductEndpints;
import com.group5.Restaurant.commons.responsesObjectDTO.CorrectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.controllers.interfaces.IProductController;
import com.group5.Restaurant.services.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(IProductEndpints.PRODUCT_END_POINT)
@AllArgsConstructor
public class ProductControllerImpl implements IProductController {


    final ProductServiceImpl service;
    @Override
    @GetMapping(IProductEndpints.PRODUCT_CREATE)
    public ResponseEntity<CorrectResponseDTO> createProduct(ProductDTO productDTO) {
        return this.service.createProduct(productDTO);
    }
}
