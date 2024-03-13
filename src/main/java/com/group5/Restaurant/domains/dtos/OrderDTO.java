package com.group5.Restaurant.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO object to send and receive information of the entity AddressesEntity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Short orderQuantity;
    private String orderAdditionalInformation;
    private Double orderSubTotal;
    private Double orderTax;
    private Double orderTotalPrice;
    private Boolean orderDelivered;
    private LocalDateTime orderDeliveredDate;
    private String productUUID;
    private Long clientDocument;
}