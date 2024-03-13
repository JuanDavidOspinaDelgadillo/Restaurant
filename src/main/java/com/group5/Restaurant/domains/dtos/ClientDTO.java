package com.group5.Restaurant.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO object to send and receive information of the entity ClientEntity
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDTO implements Serializable {
    private Long clientDocument;
    private String clientDocumentType;
    private String clientName;
    private String clientEmail;
    private String clientPhoneNumber;
    private String clientAddress;
}
