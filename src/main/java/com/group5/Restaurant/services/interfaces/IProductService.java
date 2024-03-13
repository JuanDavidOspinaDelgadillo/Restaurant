package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;

/**
 * Contract of the service ProductServiceImpl
 */
public interface IProductService {
    ObjectResponseDTO createProduct(ProductDTO productDTO);
    ObjectResponseDTO readProduct(String productUUID);
    ObjectResponseDTO readAllProducts();
    ObjectResponseDTO updateProduct(ProductDTO productDTO);
    ObjectResponseDTO deleteProduct(String productUUID);
}