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
public class AddressesDTO implements Serializable {
    private LocalDateTime addressesDateAndTime;
    private Short addressesAmount;
    private String addressesAdditionalInformation;
    private String productUUID;
    private String clientDocument;
}