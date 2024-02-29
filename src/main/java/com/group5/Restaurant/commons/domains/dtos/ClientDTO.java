package com.group5.Restaurant.commons.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * DTO object to send and receive information of the entity ClientEntity
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO implements Serializable {
    private Long clientId;
    private String clientName;
    private String clientEmail;
    private Short clientPhoneNumber;
    private String clientAddress;
}