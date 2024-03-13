package com.group5.Restaurant.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity to map the object AddressesEntity to a table in the DB with its columns
 */
@Entity(name = "order_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date_")
    private LocalDateTime orderCreationDateAndTime;

    @Column(name = "addresses_quantity_of_product")
    private Short orderQuantity;

    @Column(name = "order_additional_information")
    private String orderAdditionalInformation;

    @ManyToOne
    @JoinColumn(name = "product_uuid")
    @JsonIgnore
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "client_document")
    @JsonIgnore
    private ClientEntity clientEntity;
}