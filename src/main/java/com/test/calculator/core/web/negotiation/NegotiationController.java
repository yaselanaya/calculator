package com.test.calculator.core.web.negotiation;

import com.google.common.collect.Maps;
import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.mapper.CustomParamPageFilter;
import com.test.calculator.core.domain.negotiation.INegotiationService;
import com.test.calculator.core.domain.negotiation.NegotiationSpecification;
import com.test.calculator.core.infraestructure.negotiation.NegotiationConstants;
import com.test.calculator.core.web.negotiation.dto.NegotiationDTO;
import com.test.calculator.core.web.negotiation.dto.NegotiationResource;
import com.test.calculator.core.web.negotiation.dto.NegotiationResourceAssembler;
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
import java.util.Map;

@RestController
@RequestMapping(path = NegotiationConstants.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = NegotiationConstants.API_DOC)
public class NegotiationController {

    private final PagedResourcesAssembler pagedResourcesAssembler;

    private final NegotiationResourceAssembler negotiationResourceAssembler;

    private final INegotiationService negotiationService;

    @Autowired
    public NegotiationController(PagedResourcesAssembler pagedResourcesAssembler,
                                 NegotiationResourceAssembler negotiationResourceAssembler,
                                 INegotiationService negotiationService) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.negotiationResourceAssembler = negotiationResourceAssembler;
        this.negotiationService = negotiationService;
    }

    /***
     * Method to find a negotiation by id
     *
     * @param id id of the negotiation
     * @return negotiation object
     */
    @GetMapping(value = CommonConstants.MAPPING_GET_BY_ID)
    @ApiOperation(value = "Retrieve a negotiation by id", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true)
    })
    public ResponseEntity<NegotiationResource> findById(@PathVariable Long id) {
        return negotiationService.findById(id).map(negotiationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /***
     * Method to find all negotiations
     *
     * @return collection of negotiation objects
     */
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL)
    @ApiOperation(value = "Find all negotiations", response = ResponseEntity.class)
    public ResponseEntity<Collection<NegotiationResource>> findAll(){
        return ResponseEntity.ok(negotiationResourceAssembler.toResources(negotiationService.findAll()));
    }

    /***
     * Method to find all negotiations by page and filter
     *
     * @param request object to get filters and page from view
     * @return Page of Negotiation Resource
     * @throws CalculatorException exception when mapper object from request
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL_PAGE)
    @ApiOperation(value = "Find all negotiations by page and filter", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", paramType = "query", value = CommonConstants.VALUE_DATA_PAGE_FILTER)
    })
    public ResponseEntity<PagedResources<Collection<NegotiationResource>>> findAllPage(
            HttpServletRequest request
    ) throws CalculatorException{

        CustomParamPageFilter data = CustomParamPageFilter.mapRequestData(request);

        NegotiationSpecification shipLoadSpecification = new NegotiationSpecification(data.getFilters());
        return ResponseEntity.ok(pagedResourcesAssembler.toResource(
                negotiationService.findAll(
                        shipLoadSpecification.toSpecification(),
                        data.getPageable()), negotiationResourceAssembler
        ));
    }

    /***
     * Method to save a negotiation
     *
     * @param negotiationDTO dto with data to the new ship load
     * @return new ship load object
     * @throws CalculatorException validation exception
     */
    @PostMapping(path = CommonConstants.MAPPING_POST_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new negotiation", response = ResponseEntity.class)
    public ResponseEntity<NegotiationResource> save(
            @RequestBody NegotiationDTO negotiationDTO
    ) throws CalculatorException {
        return ResponseEntity.ok(negotiationResourceAssembler.toResource(negotiationService.save(negotiationDTO)));
    }

    @GetMapping(path = NegotiationConstants.MAPPING_POST_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Calculate flete of a negotiation", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "negotiationId", paramType = "query", value = "Negotiation ID")
    })
    public ResponseEntity<Map> calculateFleteOfNegotiation(
            @RequestParam("negotiationId") Long negotiationId
    ) throws CalculatorException {
        return ResponseEntity.ok(negotiationService.calculateFleteOfNegotiation(negotiationId));
    }

}
