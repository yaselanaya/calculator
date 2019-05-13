package com.test.calculator.core.domain.destination;

import com.test.calculator.core.common.EntitySpecification;
import com.test.calculator.core.common.util.SearchCriteria;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class DestinationSpecification extends EntitySpecification<Destination> {

    public DestinationSpecification(List<SearchCriteria> filters) {
        super(filters, Strings.EMPTY);
    }

    public DestinationSpecification(List<SearchCriteria> filters, List<SearchCriteria> searchFilters, String search) {
        super(filters, searchFilters, search);
    }

    public DestinationSpecification(List<SearchCriteria> filters, List<SearchCriteria> searchFilters) {
        super(filters, searchFilters, Strings.EMPTY);
    }

    @Override
    @SuppressWarnings("unchecked assignment")
    protected Specification buildSpecification(SearchCriteria criteria) {
        return super.buildSpecification(criteria);
    }
}
