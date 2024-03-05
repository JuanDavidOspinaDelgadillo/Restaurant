package com.group5.Restaurant.repositories;

import com.group5.Restaurant.commons.domains.dtos.ProductDTO;
import com.group5.Restaurant.commons.domains.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository <ProductEntity, UUID> {
}
