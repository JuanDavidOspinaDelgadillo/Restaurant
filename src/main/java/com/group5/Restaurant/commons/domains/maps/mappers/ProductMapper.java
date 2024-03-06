package com.group5.Restaurant.commons.domains.maps.mappers;
import com.group5.Restaurant.commons.constants.responses.Responses;
import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.commons.domains.entities.ProductEntity;
import com.group5.Restaurant.commons.domains.maps.Mapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ProductMapper {

    public ProductEntity convertProductDTOToProductEntity(ProductDTO productDto){
        ProductEntity productEntity = new ProductEntity();
        try {
            productEntity = Mapper.modelMapper().map(productDto, ProductEntity.class);
        }catch (Exception e){
            log.error(Responses.INTERNAL_SERVER_ERROR + e);
        }
        return productEntity;
    }
}
