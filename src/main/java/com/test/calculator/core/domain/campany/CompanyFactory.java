package com.test.calculator.core.domain.campany;

import com.test.calculator.core.web.campany.dto.CompanyDTO;
import com.test.calculator.core.web.campany.dto.CompanyResource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CompanyFactory {

    public Company from(CompanyDTO companyDTO){
        return Company.builder()
                .id(Objects.nonNull(companyDTO.getId()) ? companyDTO.getId() : null)
                .name(Objects.nonNull(companyDTO.getName()) ? companyDTO.getName() : null)
                .description(Objects.nonNull(companyDTO.getDescription()) ? companyDTO.getDescription() : null)
                .build();
    }

    public CompanyResource from(Company company){
        return CompanyResource.builder()
                .companyId(Objects.nonNull(company.getId()) ? company.getId() : null)
                .name(Objects.nonNull(company.getName()) ? company.getName() : null)
                .description(Objects.nonNull(company.getDescription()) ? company.getDescription() : null)
                .build();
    }

}
