package com.test.calculator.core.infraestructure.campany;

import com.google.common.collect.Lists;
import com.test.calculator.core.common.validation.EntityValidationService;
import com.test.calculator.core.common.exception.ErrorCode;
import com.test.calculator.core.domain.campany.Company;
import com.test.calculator.core.domain.campany.ICompanyRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Validated
public class CompanyServiceValidation extends EntityValidationService<Company> {

    private final ICompanyRepository companyRepository;

    @Autowired
    public CompanyServiceValidation(Validator validator, ICompanyRepository companyRepository) {
        super(validator);
        this.companyRepository = companyRepository;
    }

    @Override
    protected List<Map<String, Object>> validateCommonBusinessConstraints(Company company){

        /* Validate business logic specific to order */
        List<Map<String, Object>> errors = Lists.newArrayList();

        if(Objects.nonNull(companyRepository.findByName(company.getName()))) {
            errors.add(createError("name", ErrorCode.DUPLICITY,
                    "validation.error.company.duplicity.name", Strings.EMPTY));
        }

        return errors;
    }
}
