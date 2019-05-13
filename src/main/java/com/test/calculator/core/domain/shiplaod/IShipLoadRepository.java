package com.test.calculator.core.domain.shiplaod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IShipLoadRepository extends JpaRepository<ShipLoad, Long>, JpaSpecificationExecutor<ShipLoad> {}
