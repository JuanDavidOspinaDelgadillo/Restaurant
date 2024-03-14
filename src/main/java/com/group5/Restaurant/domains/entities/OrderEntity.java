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

    @Column(name = "order_creation_date_and_time")
    private LocalDateTime orderCreationDateAndTime;

    @Column(name = "addresses_quantity_of_product")
    private Short orderQuantity;

    @Column(name = "order_additional_information")
    private String orderAdditionalInformation;

    @Column(name = "order_sub_total")
    private Double orderSubTotal;

    @Column(name = "order_tax")
    private Double orderTax;

    @Column(name = "order_total_price")
    private Double orderTotalPrice;

    @Column(name = "order_delivered")
    private Boolean orderDelivered;

    @Column(name = "order_delivered_date")
    private LocalDateTime orderDeliveredDate;

    @ManyToOne
    @JoinColumn(name = "product_uuid")
    @JsonIgnore
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "client_document")
    @JsonIgnore
    private ClientEntity clientEntity;
}