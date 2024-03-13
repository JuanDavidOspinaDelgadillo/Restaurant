package com.group5.Restaurant.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

/**
 * Entity to map the object ProductEntity to a table in the DB with its columns
 */
@Entity(name = "product_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "product_uuid")
    private String productUUID;

    @Column(name = "product_fantasy_name")
    private String productFantasyName;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "product_description", length = 511)
    private String productDescription;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_available")
    private Boolean isProductAvailable;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private List<OrderEntity> addresesList;

    public enum Category{
        HAMBURGERS_AND_HOT_DOGS,
        CHICKEN,
        FISH,
        MEATS,
        DESSERTS,
        VEGAN_FOOD,
        KIDS_MEAL
    }
}