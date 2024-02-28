package com.group5.Restaurant.commons.domains.maps;

import org.modelmapper.ModelMapper;

/**
 * Object to use the ModelMapper dependency
 */
public class Mapper {
    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
