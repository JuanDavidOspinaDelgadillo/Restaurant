package com.group5.Restaurant.domains.maps.mappers;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.domains.entities.ProductEntity;
import com.group5.Restaurant.domains.maps.Mapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Use ModelMapper already created to map the entity ProductEntity
 */
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

    public ProductDTO converterProductEntityToProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        try {
            productDTO = Mapper.modelMapper().map(productEntity, ProductDTO.class);
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR);
        }
        return productDTO;
    }
}
