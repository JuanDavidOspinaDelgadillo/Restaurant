package com.group5.Restaurant.repositories;

import com.group5.Restaurant.domains.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Connection to the DB through spring stereotype @Repository
 * Connecting with client_tbl
 */
@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByClientDocument(Long clientDocument);
}