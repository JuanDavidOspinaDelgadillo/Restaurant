package com.group5.Restaurant.commons.domains.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class ProductDTO implements Serializable {
    private UUID productUuid;
    private String nameFantasy;
    private category category;
    private String description;
    private Float price;
    private Boolean available;

    public enum category{
        hamburguerAndHotDogs,Chiken,fish,meats,desserts,veganFood,kidsMeals;
    }
}
