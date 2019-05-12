package com.test.calculator.core.domain.campany;

import com.test.calculator.core.common.EntitySpecification;
import com.test.calculator.core.common.util.SearchCriteria;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CompanySpecification extends EntitySpecification<Company> {

    public CompanySpecification(List<SearchCriteria> filters) {
        super(filters, Strings.EMPTY);
    }

    public CompanySpecification(List<SearchCriteria> filters, List<SearchCriteria> searchFilters, String search) {
        super(filters, searchFilters, search);
    }

    public CompanySpecification(List<SearchCriteria> filters, List<SearchCriteria> searchFilters) {
        super(filters, searchFilters, Strings.EMPTY);
    }

    @Override
    @SuppressWarnings("unchecked assignment")
    protected Specification buildSpecification(SearchCriteria criteria) {
        return super.buildSpecification(criteria);
    }
}
