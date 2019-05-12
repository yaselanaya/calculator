package com.test.calculator.core.web.campany;

import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.mapper.CustomParamPageFilter;
import com.test.calculator.core.domain.campany.Company;
import com.test.calculator.core.domain.campany.ICompanyService;
import com.test.calculator.core.infraestructure.campany.CompanyConstants;
import com.test.calculator.core.web.campany.dto.CompanyDTO;
import com.test.calculator.core.web.campany.dto.CompanyResource;
import com.test.calculator.core.web.campany.dto.CompanyResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping(path = CompanyController.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    static final String ENTITY_URI = "/company";

    private final CompanyResourceAssembler companyResourceAssembler;

    private final PagedResourcesAssembler pagedResourcesAssembler;

    private final ICompanyService companyService;

    @Autowired
    public CompanyController(CompanyResourceAssembler companyResourceAssembler, PagedResourcesAssembler pagedResourcesAssembler, ICompanyService companyService) {
        this.companyResourceAssembler = companyResourceAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.companyService = companyService;
    }

    /***
     * Method to find a company by id
     *
     * @param id id of the company
     * @return company object
     */
    @GetMapping(value = CompanyConstants.MAPPING_GET_BY_ID)
    public ResponseEntity<CompanyResource> findById(@PathVariable Long id) {
        return companyService.findById(id).map(companyResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /***
     * Method to find all companies
     *
     * @return collection of company objects
     */
    @GetMapping(value = CompanyConstants.MAPPING_FIND_ALL)
    public ResponseEntity<Collection<Company>> findAll(){
        return ResponseEntity.ok(companyService.findAll());
    }

    /***
     * Method to find all companies by page and filter
     *
     * @param request object to get filters and page from view
     * @return Page of Company Resource
     * @throws CalculatorException exception when mapper object from request
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value = CompanyConstants.MAPPING_FIND_ALL_PAGE)
    public ResponseEntity<PagedResources<Collection<CompanyResource>>> findAllPage(
            HttpServletRequest request
    ) throws CalculatorException{

        CustomParamPageFilter data = CustomParamPageFilter.mapRequestData(request);

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(
                companyService.findAll(data.getPageable()), companyResourceAssembler
        ));
    }

    /***
     * Method to save a company
     *
     * @param companyDTO dto with data to the new company
     * @return new company object
     * @throws CalculatorException validation exception
     */
    @PostMapping(path = CompanyConstants.MAPPING_POST_SAVE_ORDER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyResource> save(@RequestBody CompanyDTO companyDTO) throws CalculatorException {
        return ResponseEntity.ok(companyResourceAssembler.toResource(companyService.save(companyDTO)));
    }
}
