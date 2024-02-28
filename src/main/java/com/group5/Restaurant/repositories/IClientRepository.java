package com.group5.Restaurant.repositories;

import com.group5.Restaurant.commons.domains.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Connection to the DB through spring stereotype @Repository
 */
@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {}