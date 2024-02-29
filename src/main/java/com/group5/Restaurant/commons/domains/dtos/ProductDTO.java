package com.group5.Restaurant.commons.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductDTO implements Serializable {
    private String nombre;
    private categoria categoriaProducto;
    private String descripcion;
    private Float precio;
    private Boolean disponible;

    public enum categoria{
        hamburguerAndHotDogs,Chiken,fish,meats,desserts,veganFood,kidsMeals;
    }
}
