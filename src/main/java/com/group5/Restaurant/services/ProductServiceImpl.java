package com.group5.Restaurant.services;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService{

    @Override
    public ResponseEntity<ObjectResponseDTO> createProduct(ProductDTO productDTO) {
        return null;
    }
}
