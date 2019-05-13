package com.test.calculator.core.domain.ship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IShipRepository extends JpaRepository<Ship, Long>, JpaSpecificationExecutor<Ship> {

    Ship findByName(String name);

}
