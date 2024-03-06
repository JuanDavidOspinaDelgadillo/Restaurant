package com.group5.Restaurant.controllers.impl;


import com.group5.Restaurant.commons.constants.endpoints.IProductEndpints;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.commons.responsesObjectDTO.ResponseObjectDTO;
import com.group5.Restaurant.controllers.interfaces.IProductController;
import com.group5.Restaurant.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(IProductEndpints.PRODUCT_END_POINT)
@AllArgsConstructor
public class ProductControllerImpl implements IProductController {


    final IProductService service;
    @Override
    @PostMapping(IProductEndpints.PRODUCT_CREATE)
    public ResponseEntity<ResponseObjectDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return this.service.createProduct(productDTO);
    }

    @Override
    @PutMapping(IProductEndpints.PRODUCT_UPDATE)
    public ResponseEntity<ResponseObjectDTO> updateProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO) {
        return this.service.updateProduct(productDTO);
    }
    @Override
    @DeleteMapping(IProductEndpints.PRODUCT_DELETE)
    public ResponseEntity<ResponseObjectDTO> deleteProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO){
        return this.service.deleteProduct(productDTO);
    }
}