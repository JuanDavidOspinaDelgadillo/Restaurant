package com.group5.Restaurant.services.impl;


import com.group5.Restaurant.commons.constants.responses.Responses;
import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.commons.domains.entities.ProductEntity;
import com.group5.Restaurant.commons.domains.maps.mappers.ProductMapper;
import com.group5.Restaurant.repositories.IProductRepository;

import com.group5.Restaurant.services.interfaces.IProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.group5.Restaurant.commons.constants.responses.ConstantsResponses;

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
    public ResponseEntity<ObjectResponseDTO> updateProduct(ProductDTO productDTO) {
            try{
                Optional<ProductEntity> find = this.iProductRepository.findById(productDTO.getProductUuid());
                System.out.println(find.isPresent());
                if (find.isPresent()){
                    ProductEntity productEntity = this.mapper.convertProductDTOToProductEntity(productDTO);
                    this.iProductRepository.save(productEntity);
                    return ConstantsResponses.OK;
                }else {
                    return ConstantsResponses.BAD_REQUEST;
                }

            }catch (Exception e){
                log.error(Responses.INTERNAL_SERVER_ERROR + e);
                return ConstantsResponses.INTERNAL_SERVER_ERROR;
            }
    }

    @Override
    public ResponseEntity<ObjectResponseDTO> deleteProduct(ProductDTO productDTO) {
        try {
            Optional<ProductEntity> find = this.iProductRepository.findById(productDTO.getProductUuid());
            System.out.println(find.isPresent());
            if (find.isPresent()){
                ProductEntity productEntity= this.mapper.convertProductDTOToProductEntity(productDTO);
                this.iProductRepository.delete(productEntity);
                return ConstantsResponses.OK;
            }else{
                return ConstantsResponses.BAD_REQUEST;
            }

        }catch (Exception e){
            return ConstantsResponses.INTERNAL_SERVER_ERROR;
        }

    }
}
