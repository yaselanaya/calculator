package com.test.calculator.core.web.campany;

import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.mapper.CustomParamPageFilter;
import com.test.calculator.core.domain.campany.Company;
import com.test.calculator.core.domain.campany.CompanySpecification;
import com.test.calculator.core.domain.campany.ICompanyService;
import com.test.calculator.core.infraestructure.campany.CompanyConstants;
import com.test.calculator.core.web.campany.dto.CompanyDTO;
import com.test.calculator.core.web.campany.dto.CompanyResource;
import com.test.calculator.core.web.campany.dto.CompanyResourceAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(path = CompanyConstants.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = CompanyConstants.API_DOC)
public class CompanyController {

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
    @GetMapping(value = CommonConstants.MAPPING_GET_BY_ID)
    @ApiOperation(value = "Retrieve a company by id", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true)
    })
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
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL)
    @ApiOperation(value = "Find all companies", response = ResponseEntity.class)
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
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL_PAGE)
    @ApiOperation(value = "Find all companies by page and filter", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", paramType = "query", value = CommonConstants.VALUE_DATA_PAGE_FILTER)
    })
    public ResponseEntity<PagedResources<Collection<CompanyResource>>> findAllPage(
            HttpServletRequest request
    ) throws CalculatorException{

        CustomParamPageFilter data = CustomParamPageFilter.mapRequestData(request);

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(
                companyService.findAll(
                        new CompanySpecification(data.getFilters()).toSpecification(),
                        data.getPageable()), companyResourceAssembler
        ));
    }

    /***
     * Method to save a company
     *
     * @param companyDTO dto with data to the new company
     * @return new company object
     * @throws CalculatorException validation exception
     */
    @PostMapping(path = CommonConstants.MAPPING_POST_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new company", response = ResponseEntity.class)
    public ResponseEntity<CompanyResource> save(@RequestBody CompanyDTO companyDTO) throws CalculatorException {
        return ResponseEntity.ok(companyResourceAssembler.toResource(companyService.save(companyDTO)));
    }
}
