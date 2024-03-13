package com.group5.Restaurant.errors.validations;

import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.domains.entities.ProductEntity;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductDTOValidation {
    public void validateProductDTO(ProductDTO productDTO) throws BadRequestException {
        Set<String> foodCategories = new HashSet<>();
        foodCategories.add("HAMBURGERS_AND_HOT_DOGS");
        foodCategories.add("CHICKEN");
        foodCategories.add("FISH");
        foodCategories.add("MEATS");
        foodCategories.add("DESSERTS");
        foodCategories.add("VEGAN_FOOD");
        foodCategories.add("KIDS_MEAL");

        if ((productDTO.getProductUUID() == null || productDTO.getProductUUID().isEmpty()) ||
        (productDTO.getProductFantasyName() == null || productDTO.getProductFantasyName().isEmpty()) ||
        (productDTO.getProductCategory() == null || !foodCategories.contains(productDTO.getProductCategory())) ||
        (productDTO.getProductDescription() == null || productDTO.getProductDescription().isEmpty()) ||
        (productDTO.getProductPrice() == null || productDTO.getProductPrice() < 0.0) ||
        (productDTO.getIsProductAvailable() == null)) {
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
        }
    }

    public void validateProductUUID(String productUUID) throws BadRequestException {
        if(productUUID == null || productUUID.trim().isEmpty())
            throw new BadRequestException(ResponseCodes.NULL_OR_INVALID_DATA);
    }
}
