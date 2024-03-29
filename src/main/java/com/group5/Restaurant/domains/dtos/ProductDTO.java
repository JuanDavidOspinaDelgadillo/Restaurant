package com.group5.Restaurant.domains.dtos;

import com.group5.Restaurant.domains.entities.OrderEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO object to send and receive information of the entity ProductEntity
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO implements Serializable {
    private String productUUID;
    private String productFantasyName;
    private String productCategory;
    private String productDescription;
    private Double productPrice;
    private Boolean isProductAvailable;
    private List<OrderEntity> orderEntityList;
}
