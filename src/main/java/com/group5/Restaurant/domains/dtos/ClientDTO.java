package com.group5.Restaurant.domains.dtos;

import com.group5.Restaurant.domains.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
    private List<OrderEntity> orderEntityList;
}
