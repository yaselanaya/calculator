package com.test.calculator.core.domain.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IDestinationRepository extends JpaRepository<Destination, Long>, JpaSpecificationExecutor<Destination> {

    Destination findByName(String name);

}
