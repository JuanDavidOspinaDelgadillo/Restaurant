package com.group5.Restaurant.commons.domains.dtos;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO implements Serializable {
    private String productName;
    private String productCategory;
    private String productDescription;
    private Double productPrice;
    private Boolean isProductAvailable;
}
