package com.group5.Restaurant.domains.dtos;

import lombok.*;

import java.io.Serializable;
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
    private String productName;
    private String productCategory;
    private String productDescription;
    private Double productPrice;
    private Boolean isProductAvailable;
}
