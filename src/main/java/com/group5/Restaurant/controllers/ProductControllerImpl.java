package com.group5.Restaurant.controllers;


import com.group5.Restaurant.commons.constants.endpoints.IProductEndpints;
import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.services.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(IProductEndpints.PRODUCT_END_POINT)
@AllArgsConstructor
public class ProductControllerImpl implements IProductController {


    final ProductServiceImpl service;
    @Override
    @PutMapping(IProductEndpints.PRODUCT_UPDATE)
    public ResponseEntity<ObjectResponseDTO> updateProduct(@PathVariable UUID uuid, @RequestBody ProductDTO productDTO) {
        return this.service.updateProduct(productDTO);
    }
    @DeleteMapping(IProductEndpints.PRODUCT_DELETE)
    public  ResponseEntity<ObjectResponseDTO> deleteProduct(@PathVariable UUID uuid,    @RequestBody ProductDTO productDTO){
        return  this.service.deleteProduct(productDTO);
    }
}
