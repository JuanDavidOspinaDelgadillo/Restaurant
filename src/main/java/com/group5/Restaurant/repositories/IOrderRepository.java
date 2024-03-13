package com.group5.Restaurant.repositories;

import com.group5.Restaurant.domains.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(value = "SELECT * FROM product_tbl WHERE product_uuid = :productUUID LIMIT 1", nativeQuery = true)
    Optional<OrderEntity> findByProductUUID(@Param("productUUID") String productUUID);
}
