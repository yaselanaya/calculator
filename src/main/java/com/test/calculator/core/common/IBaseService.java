package com.test.calculator.core.common;

import com.test.calculator.core.common.exception.CalculatorException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.Optional;

public interface IBaseService<T, Y, Z> {
    Optional<T> findById(Y id);

    Collection<T> findAll();

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(Specification<T> specification, Pageable pageable);

    T save(Z entity) throws CalculatorException;

    T update(Z entity) throws CalculatorException;
}
