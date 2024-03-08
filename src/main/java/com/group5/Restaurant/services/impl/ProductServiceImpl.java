package com.group5.Restaurant.services.impl;


import com.group5.Restaurant.constants.responses.constantResponses.ConstantResponses;
import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.domains.entities.ProductEntity;
import com.group5.Restaurant.domains.maps.mappers.ProductMapper;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.repositories.IProductRepository;

import com.group5.Restaurant.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service of the entity ProductEntity
 */
@Service
@Log4j2
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {


    final IProductRepository iProductRepository;//Repository dependency injected
    final ProductMapper mapper;//Mapper dependency injected

    /**
     * Create a product
     * @param productDTO Data reference object of the product
     * @return Information referred of the operation result
     */
    @Override
    public ResponseEntity<ObjectResponseDTO> createProduct(ProductDTO productDTO) {
        try {
            Optional<ProductEntity> productExist = this.iProductRepository.findByProductUUID(productDTO.getProductUUID());
            if(productExist.isEmpty()) {
                ProductEntity productEntity = this.mapper.convertProductDTOToProductEntity(productDTO);
                this.iProductRepository.save(productEntity);
                return ConstantResponses.OK.apply(productDTO.toString());
            } else {
                return ConstantResponses.BAD_REQUEST.apply("The product " + productDTO + " already exist in DataBase ", ResponseCodes.ALREADY_EXIST);
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR + e);
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply(productDTO.toString(), e);
        }
    }

    /**
     * Consult a product
     * @param productUUID ID reference of the client
     * @return Information referred of the operation result
     */
    @Override
    public ResponseEntity<ObjectResponseDTO> readProduct(String productUUID) {
        try {
            Optional<ProductEntity> productExist = this.iProductRepository.findByProductUUID(productUUID);
            return productExist.<ResponseEntity<ObjectResponseDTO>>map(clientEntity -> ResponseEntity.ok().body(CorrectResponseDTO.builder()
                            .timeStamp(LocalDateTime.now())
                            .code(ResponseCodes.OK)
                            .description("The product with document " + productUUID + "exists!")
                            .object(clientEntity)
                            .httpStatusCode(HttpStatus.OK.value())
                            .build()))
                    .orElseGet(() -> ConstantResponses.BAD_REQUEST.apply("Product with document " + productUUID + "doesnt exist", ResponseCodes.DOESNT_EXIST));
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply(productUUID, e);
        }
    }

    /**
     * Update a product
     * @param productDTO Data reference object of the client
     * @return Information referred of the operation result
     */
    @Override
    public ResponseEntity<ObjectResponseDTO> updateProduct(ProductDTO productDTO) {
            try{
                Optional<ProductEntity> find = this.iProductRepository.findByProductUUID(productDTO.getProductUUID());
                System.out.println(find.isPresent());
                if (find.isPresent()){
                    ProductEntity productEntity = this.mapper.convertProductDTOToProductEntity(productDTO);
                    this.iProductRepository.save(productEntity);
                    return ConstantResponses.OK.apply(productDTO.toString());
                }else {
                    return ConstantResponses.BAD_REQUEST.apply("The product " + productDTO + " doesnt exist in DataBase ", ResponseCodes.DOESNT_EXIST);
                }
            }catch (Exception e){
                log.error(Responses.INTERNAL_SERVER_ERROR + e);
                return ConstantResponses.INTERNAL_SERVER_ERROR.apply(productDTO.toString(), e);
            }
    }

    /**
     * Delete a client
     * @param productDTO Data reference object of the client
     * @return Information referred of the operation result
     */
    @Override
    public ResponseEntity<ObjectResponseDTO> deleteProduct(ProductDTO productDTO) {
        try {
            Optional<ProductEntity> find = this.iProductRepository.findById(productDTO.getProductUUID());
            System.out.println(find.isPresent());
            if (find.isPresent()){
                ProductEntity productEntity= this.mapper.convertProductDTOToProductEntity(productDTO);
                this.iProductRepository.delete(productEntity);
                return ConstantResponses.OK.apply(productDTO.toString());
            }else{
                return ConstantResponses.BAD_REQUEST.apply("The product " + productDTO + " doesnt exist in DataBase", ResponseCodes.DOESNT_EXIST);
            }
        }catch (Exception e){
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply(productDTO.toString(), e);
        }
    }
}