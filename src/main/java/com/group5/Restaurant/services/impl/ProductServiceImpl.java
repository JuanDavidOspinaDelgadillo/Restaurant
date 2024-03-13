package com.group5.Restaurant.services.impl;


import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.WrongResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.domains.entities.ProductEntity;
import com.group5.Restaurant.domains.maps.mappers.ProductMapper;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.errors.exceptions.ConflictException;
import com.group5.Restaurant.errors.exceptions.NotFoundException;
import com.group5.Restaurant.repositories.IProductRepository;

import com.group5.Restaurant.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service of the entity ProductEntity
 */
@Service
@Log4j2
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {


    final IProductRepository repository;//Repository dependency injected
    final ProductMapper mapper;//Mapper dependency injected

    /**
     * Create a product
     * @param productDTO Data reference object of the product
     * @return Information referred of the operation result
     */
    @Override
    public ObjectResponseDTO createProduct(ProductDTO productDTO) {
        try {
            Optional<ProductEntity> productExist = this.repository.findByProductUUID(productDTO.getProductUUID());
            if(productExist.isEmpty()) {
                ProductEntity productEntity = this.mapper.convertProductDTOToProductEntity(productDTO);
                productEntity.setAddresesList(null);
                this.repository.save(productEntity);
                return CorrectResponseDTO.builder()
                        .code(ResponseCodes.OK)
                        .description("The product was created succesful")
                        .timeStamp(LocalDateTime.now())
                        .httpStatusCode(HttpStatus.OK.value())
                        .object(productDTO)
                        .build();
            } else {
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .timeStamp(LocalDateTime.now())
                        .description("The product already exist")
                        .code(ResponseCodes.ALREADY_EXIST)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR + e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    /**
     * Consult a product
     * @param productUUID ID reference of the client
     * @return Information referred of the operation result
     */
    @Override
    public ObjectResponseDTO readProduct(String productUUID) {
        try {
            Optional<ProductEntity> productExist = this.repository.findByProductUUID(productUUID);
            if(productExist.isPresent()) {
                ProductDTO productDTO = this.mapper.converterProductEntityToProductDTO(productExist.get());
                return CorrectResponseDTO.builder()
                        .code(ResponseCodes.OK)
                        .description("The product exists")
                        .timeStamp(LocalDateTime.now())
                        .httpStatusCode(HttpStatus.OK.value())
                        .object(productDTO)
                        .build();
            } else {
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .timeStamp(LocalDateTime.now())
                        .description("The product doesnt exist")
                        .code(ResponseCodes.DOESNT_EXIST)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    /**
     * Get all the products registered
     * @return Information referred of the operation result
     */
    @Override
    public ObjectResponseDTO readAllProducts() {
        try {
            List<ProductEntity> productEntityList = this.repository.findAll();
            if(!productEntityList.isEmpty()) {
                List<ProductDTO> productDTOList = productEntityList.stream()
                        .map(this.mapper::converterProductEntityToProductDTO)
                        .toList();
                return CorrectResponseDTO.builder()
                        .code(ResponseCodes.OK)
                        .description("There are products registered")
                        .timeStamp(LocalDateTime.now())
                        .httpStatusCode(HttpStatus.OK.value())
                        .object(productDTOList)
                        .build();
            } else {
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .timeStamp(LocalDateTime.now())
                        .description("There are not products registered")
                        .code(ResponseCodes.NO_DATA_IN_TABLE)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    /**
     * Update a product
     * @param productDTO Data reference object of the client
     * @return Information referred of the operation result
     */
    @Override
    public ObjectResponseDTO updateProduct(ProductDTO productDTO) {
            try{
                Optional<ProductEntity> find = this.repository.findByProductUUID(productDTO.getProductUUID());
                if (find.isPresent()){
                    ProductEntity productEntity = this.mapper.convertProductDTOToProductEntity(productDTO);
                    if(find.get().equals(productEntity)){
                        return WrongResponseDTO.builder()
                                .exception(new ConflictException())
                                .code(ResponseCodes.DOESNT_HAVE_CHANGES)
                                .description("The objects are the same")
                                .httpStatusCode(HttpStatus.CONFLICT.value())
                                .timeStamp(LocalDateTime.now())
                                .build();
                    }
                    this.repository.save(productEntity);
                    return CorrectResponseDTO.builder()
                            .code(ResponseCodes.OK)
                            .description("Product updated")
                            .timeStamp(LocalDateTime.now())
                            .httpStatusCode(HttpStatus.OK.value())
                            .object(productDTO)
                            .build();
                }else {
                    return WrongResponseDTO.builder()
                            .exception(new NotFoundException())
                            .timeStamp(LocalDateTime.now())
                            .description("The product doesnt exist")
                            .code(ResponseCodes.DOESNT_EXIST)
                            .httpStatusCode(HttpStatus.NOT_FOUND.value())
                            .build();
                }
            }catch (Exception e){
                log.error(Responses.INTERNAL_SERVER_ERROR + e);
                return WrongResponseDTO.builder()
                        .exception(e)
                        .timeStamp(LocalDateTime.now())
                        .description(Responses.INTERNAL_SERVER_ERROR)
                        .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                        .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build();
            }
    }

    /**
     * Delete a client
     * @param productUUID ID reference object of the client
     * @return Information referred of the operation result
     */
    @Override
    public ObjectResponseDTO deleteProduct(String productUUID) {
        try {
            Optional<ProductEntity> find = this.repository.findById(productUUID);
            if (find.isPresent()){
                this.repository.deleteById(productUUID);
                ProductDTO productDTO = this.mapper.converterProductEntityToProductDTO(find.get());
                return CorrectResponseDTO.builder()
                        .code(ResponseCodes.OK)
                        .description("Product deleted")
                        .timeStamp(LocalDateTime.now())
                        .httpStatusCode(HttpStatus.OK.value())
                        .object(productDTO)
                        .build();
            }else{
                return WrongResponseDTO.builder()
                        .exception(new NotFoundException())
                        .timeStamp(LocalDateTime.now())
                        .description("The product doesnt exist")
                        .code(ResponseCodes.DOESNT_EXIST)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            }
        }catch (Exception e){
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }
}