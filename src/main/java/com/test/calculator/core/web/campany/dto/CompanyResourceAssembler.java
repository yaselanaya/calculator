package com.test.calculator.core.web.campany.dto;

import com.test.calculator.core.domain.campany.Company;
import com.test.calculator.core.domain.campany.CompanyFactory;
import com.test.calculator.core.web.campany.CompanyController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CompanyResourceAssembler extends ResourceAssemblerSupport<Company, CompanyResource> {

    private final CompanyFactory companyFactory;

    public CompanyResourceAssembler(CompanyFactory companyFactory) {
        super(CompanyController.class, CompanyResource.class);
        this.companyFactory = companyFactory;
    }

    @Override
    public CompanyResource toResource(Company company) {
        CompanyResource resource = companyFactory.from(company);
        Link selfLink = linkTo(ControllerLinkBuilder.methodOn(CompanyController.class)
                .findById(company.getId())).withSelfRel();
        resource.add(selfLink);
        return resource;
    }

    public Collection<CompanyResource> toResources(Collection<Company> orders) {
        return orders.stream().map(this::toResource).collect(Collectors.toList());
    }
}
