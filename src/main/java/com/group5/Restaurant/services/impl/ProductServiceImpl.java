package com.group5.Restaurant.services.impl;


import com.group5.Restaurant.commons.constants.responses.ConstantResponses;
import com.group5.Restaurant.commons.constants.responses.Responses;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.commons.domains.entities.ProductEntity;
import com.group5.Restaurant.commons.domains.maps.mappers.ProductMapper;
import com.group5.Restaurant.commons.responsesObjectDTO.ResponseObjectDTO;
import com.group5.Restaurant.repositories.IProductRepository;

import com.group5.Restaurant.services.interfaces.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements IProductService {


    final IProductRepository iProductRepository;
    final ProductMapper mapper;

    public ProductServiceImpl(IProductRepository repository, ProductMapper mapper) {
        this.iProductRepository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ResponseObjectDTO> createProduct(ProductDTO productDTO) {
        try {
            Optional<ProductEntity> productExist = this.iProductRepository.findByProductUUID(productDTO.getProductUUID());
            if(productExist.isEmpty()) {
                ProductEntity productEntity = this.mapper.convertProductDTOToProductEntity(productDTO);
                this.iProductRepository.save(productEntity);
                return ConstantResponses.OK.get();
            } else {
                return ConstantResponses.BAD_REQUEST.get();
            }
        } catch (RuntimeException e) {
            log.error(Responses.INTERNAL_SERVER_ERROR + e);
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply(e);
        }
    }

    @Override
    public ResponseEntity<ResponseObjectDTO> updateProduct(ProductDTO productDTO) {
            try{
                Optional<ProductEntity> find = this.iProductRepository.findByProductUUID(productDTO.getProductUUID());
                System.out.println(find.isPresent());
                if (find.isPresent()){
                    ProductEntity productEntity = this.mapper.convertProductDTOToProductEntity(productDTO);
                    this.iProductRepository.save(productEntity);
                    return ConstantResponses.OK.get();
                }else {
                    return ConstantResponses.BAD_REQUEST.get();
                }
            }catch (RuntimeException e){
                log.error(Responses.INTERNAL_SERVER_ERROR + e);
                return ConstantResponses.INTERNAL_SERVER_ERROR.apply(e);
            }
    }

    @Override
    public ResponseEntity<ResponseObjectDTO> deleteProduct(ProductDTO productDTO) {
        try {
            Optional<ProductEntity> find = this.iProductRepository.findById(productDTO.getProductUUID());
            System.out.println(find.isPresent());
            if (find.isPresent()){
                ProductEntity productEntity= this.mapper.convertProductDTOToProductEntity(productDTO);
                this.iProductRepository.delete(productEntity);
                return ConstantResponses.OK.get();
            }else{
                return ConstantResponses.BAD_REQUEST.get();
            }
        }catch (RuntimeException e){
            return ConstantResponses.INTERNAL_SERVER_ERROR.apply(e);
        }
    }
}