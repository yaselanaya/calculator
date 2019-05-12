package com.test.calculator.core.infraestructure.campany;

import com.test.calculator.core.common.CalculatorException;
import com.test.calculator.core.domain.campany.Company;
import com.test.calculator.core.domain.campany.CompanyFactory;
import com.test.calculator.core.domain.campany.ICompanyRepository;
import com.test.calculator.core.domain.campany.ICompanyService;
import com.test.calculator.core.web.campany.dto.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {

    private final ICompanyRepository companyRepository;

    private final CompanyFactory companyFactory;

    private final CompanyServiceValidation companyServiceValidation;

    @Autowired
    public CompanyService(ICompanyRepository companyRepository, CompanyFactory companyFactory,
                          CompanyServiceValidation companyServiceValidation) {
        this.companyRepository = companyRepository;
        this.companyFactory = companyFactory;
        this.companyServiceValidation = companyServiceValidation;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Collection<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public Page<Company> findAll(Specification<Company> specification, Pageable pageable) {
        return companyRepository.findAll(specification, pageable);
    }

    /***
     * Method to save a company
     *
     * @param companyDTO object came from view
     * @return return a new company
     * @throws CalculatorException throw an exception if company is not valid for save
     */
    @Override
    public Company save(CompanyDTO companyDTO) throws CalculatorException {
        // Convert dto to entity
        Company company = companyFactory.from(companyDTO);

        // Validate company dto
        companyServiceValidation.validateForCreate(company);

        return companyRepository.save(company);
    }

    @Override
    public Company update(CompanyDTO companyDTO) throws CalculatorException {
        return companyRepository.save(companyFactory.from(companyDTO));
    }
}
