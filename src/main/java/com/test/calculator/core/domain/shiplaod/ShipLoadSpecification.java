package com.test.calculator.core.domain.shiplaod;

import com.test.calculator.core.common.EntitySpecification;
import com.test.calculator.core.common.util.SearchCriteria;
import com.test.calculator.core.domain.ship.Ship;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.List;

public class ShipLoadSpecification extends EntitySpecification<ShipLoad> {
    
    public ShipLoadSpecification(List<SearchCriteria> filters) {
        super(filters, Strings.EMPTY);
    }

    @Override
    @SuppressWarnings("unchecked assignment")
    protected Specification buildSpecification(SearchCriteria criteria) {

        String field = criteria.getField();
        Object value = criteria.getValue();

        if (SHIP_LOAD_FILTER.SHIP.getName().equals(field)) {
            return byShip(value.toString());
        }

        return super.buildSpecification(criteria);
    }

    private static Specification<ShipLoad> byShip(String shipName) {
        return (root, query, builder) -> {
            final Join<ShipLoad, Ship> ship = root.join("ship");
            return builder.like(builder.upper(ship.get("name")),
                    "%" + shipName.toUpperCase() + "%");
        };
    }



}
