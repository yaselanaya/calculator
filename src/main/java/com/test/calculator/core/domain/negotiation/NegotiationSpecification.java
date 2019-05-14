package com.test.calculator.core.domain.negotiation;

import com.test.calculator.core.common.EntitySpecification;
import com.test.calculator.core.common.util.SearchCriteria;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class NegotiationSpecification extends EntitySpecification<Negotiation> {

    public NegotiationSpecification(List<SearchCriteria> filters) {
        super(filters, Strings.EMPTY);
    }

    @Override
    @SuppressWarnings("unchecked assignment")
    protected Specification buildSpecification(SearchCriteria criteria) {
        return super.buildSpecification(criteria);
    }
}
