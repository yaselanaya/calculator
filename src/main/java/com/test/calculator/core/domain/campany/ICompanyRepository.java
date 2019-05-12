package com.test.calculator.core.domain.campany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company>{

    Company findByName(String name);
}
