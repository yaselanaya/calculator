package com.test.calculator.core.web.campany;

import com.test.calculator.core.common.CalculatorException;
import com.test.calculator.core.domain.campany.ICompanyService;
import com.test.calculator.core.infraestructure.campany.CompanyConstants;
import com.test.calculator.core.web.campany.dto.CompanyDTO;
import com.test.calculator.core.web.campany.dto.CompanyResource;
import com.test.calculator.core.web.campany.dto.CompanyResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = CompanyController.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    static final String ENTITY_URI = "/company";

    private final CompanyResourceAssembler companyResourceAssembler;

    private final ICompanyService companyService;

    @Autowired
    public CompanyController(CompanyResourceAssembler companyResourceAssembler, ICompanyService companyService) {
        this.companyResourceAssembler = companyResourceAssembler;
        this.companyService = companyService;
    }

    @GetMapping(value = CompanyConstants.MAPPING_GET_BY_ID)
    public ResponseEntity<CompanyResource> findById(@PathVariable Long id) {
        return companyService.findById(id).map(companyResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(path = CompanyConstants.MAPPING_POST_SAVE_ORDER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyResource> save(@RequestBody CompanyDTO companyDTO) throws CalculatorException {
        return ResponseEntity.ok(companyResourceAssembler.toResource(companyService.save(companyDTO)));
    }
}
