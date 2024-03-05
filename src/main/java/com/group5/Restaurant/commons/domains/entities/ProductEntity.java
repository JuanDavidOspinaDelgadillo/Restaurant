package com.group5.Restaurant.commons.domains.entities;

import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "product_tbl")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_uuid")
    private UUID productUuid;

    @Column(name = "product_name", length = 30)
    private String nameFantasy;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_category", length = 20)
    private ProductDTO.category category;

    @Column(name = "product_description", length = 30)
    private String description;
    @Column(name = "product_price")
    private Float price;
    @Column(name = "product_available")
    private Boolean available;
}
