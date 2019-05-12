package com.test.calculator.core.common;

import com.google.common.collect.Lists;
import com.test.calculator.core.common.util.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntitySpecification<Entity> {
    protected List<SearchCriteria> filters;

    protected List<SearchCriteria> searchFilters;

    protected String search;

    public EntitySpecification(List<SearchCriteria> filters, String search) {
        this.filters = filters;
        this.search = search;
        searchFilters = new ArrayList<>();
    }

    public EntitySpecification(List<SearchCriteria> filters) {
        this.filters = filters;
        search = Strings.EMPTY;
        searchFilters = Lists.newArrayList();
    }

    public EntitySpecification(List<SearchCriteria> filters,
                               List<SearchCriteria> searchFilters) {
        this.filters = filters;
        this.searchFilters = searchFilters;
        search = Strings.EMPTY;
    }

    @SuppressWarnings("unchecked assignment")
    public Specification<Entity> toSpecification() {

        Specification filterResult = filters.stream().map(this::buildSpecification)
                .filter(Objects::nonNull).reduce(Specification::and).orElse(null);

        return search.isEmpty() && searchFilters.isEmpty() ? filterResult
                : processSearch(filterResult);
    }

    @SuppressWarnings("unchecked call")
    private Specification processSearch(Specification filterResult) {

        populateSearchCriteria();

        if (searchFilters.isEmpty())
            return filterResult;

        Specification searchSpecification = searchFilters.stream()
                .map(this::buildSpecification).filter(Objects::nonNull)
                .reduce(Specification::or).orElse(null);

        List<Specification> validSearchSpecification = getSearchSpecification().stream()
                .filter(Objects::nonNull).collect(Collectors.toList());

        for (Specification specification : validSearchSpecification) {
            searchSpecification = searchSpecification.or(specification);
        }

        return Objects.isNull(filterResult) ? searchSpecification
                : filterResult.and(searchSpecification);
    }

    protected void populateSearchCriteria() {
    }

    protected List<Specification> getSearchSpecification() {
        return Collections.emptyList();
    }

    protected Specification buildSpecification(SearchCriteria criteria) {
        return new CustomSpecification(criteria);
    }
}
