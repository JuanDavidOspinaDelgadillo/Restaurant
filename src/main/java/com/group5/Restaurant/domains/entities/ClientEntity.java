package com.group5.Restaurant.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

/**
 * Entity to map the object ClientEntity to a table in the DB with its columns
 */
@Entity(name = "client_tbl")
@Data
public class ClientEntity {

    @Id
    @Column(name = "client_document", length = 20)
    private String clientDocument;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_phone_number", length = 10)
    private String clientPhoneNumber;

    @Column(name = "client_address", length = 511)
    private String clientAddress;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private List<AddressesEntity> addressesEntity;
}