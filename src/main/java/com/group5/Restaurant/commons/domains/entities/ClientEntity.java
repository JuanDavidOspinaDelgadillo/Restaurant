package com.group5.Restaurant.commons.domains.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity to map the object ClientEntity to a table in the DB with its columns
 */
@Entity(name = "client_tbl")
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_document", length = 20)
    private String clientDocument;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_phone_number", length = 10)
    private Short clientPhoneNumber;

    @Column(name = "client_address", length = 500)
    private String clientAddress;
}