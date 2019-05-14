package com.test.calculator.core.domain.negotiation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface INegotiationRepository extends JpaRepository<Negotiation, Long>, JpaSpecificationExecutor<Negotiation> {

    Negotiation findByName(String name);

}
