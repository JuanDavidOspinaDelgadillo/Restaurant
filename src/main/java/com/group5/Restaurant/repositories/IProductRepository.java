package com.group5.Restaurant.repositories;

import com.group5.Restaurant.domains.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Connection to the DB through spring stereotype @Repository
 * Connecting with product_tbl
 */
@Repository
public interface IProductRepository extends JpaRepository<ProductEntity,String> {
    Optional<ProductEntity> findByProductUUID(String productUUID);//Find a product by its UUID
}