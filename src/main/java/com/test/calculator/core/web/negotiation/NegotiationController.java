package com.test.calculator.core.web.negotiation;

import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.mapper.CustomParamPageFilter;
import com.test.calculator.core.domain.destination.Destination;
import com.test.calculator.core.domain.negotiation.INegotiationService;
import com.test.calculator.core.domain.negotiation.Negotiation;
import com.test.calculator.core.domain.negotiation.NegotiationSpecification;
import com.test.calculator.core.web.negotiation.dto.NegotiationDTO;
import com.test.calculator.core.web.negotiation.dto.NegotiationResource;
import com.test.calculator.core.web.negotiation.dto.NegotiationResourceAssembler;
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
@RequestMapping(path = NegotiationController.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
public class NegotiationController {

    static final String ENTITY_URI = "/negotiation";

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
    public ResponseEntity<NegotiationResource> save(
            @RequestBody NegotiationDTO negotiationDTO
    ) throws CalculatorException {
        return ResponseEntity.ok(negotiationResourceAssembler.toResource(negotiationService.save(negotiationDTO)));
    }

}