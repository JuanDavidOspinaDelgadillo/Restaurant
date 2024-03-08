package com.group5.Restaurant.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

/**
 * Entity to map the object AddressesEntity to a table in the DB with its columns
 */
@Entity(name = "addresses_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addresses_id")
    private Long addressesId;

    @Column(name = "addresses_date_")
    private LocalDateTime addressesDateAndTime;

    @Column(name = "addresses_amount_of_product")
    private Short addressesAmount;

    @Column(name = "addresses_additional_information")
    private String addressesAdditionalInformation;

    @ManyToOne
    @JoinColumn(name = "product_uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "client-document")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClientEntity clientEntity;
}