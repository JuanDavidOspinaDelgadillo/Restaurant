package com.group5.Restaurant.commons.domains.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

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
