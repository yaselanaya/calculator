package com.test.calculator.core.web.destination;

import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.mapper.CustomParamPageFilter;
import com.test.calculator.core.domain.destination.Destination;
import com.test.calculator.core.domain.destination.DestinationSpecification;
import com.test.calculator.core.domain.destination.IDestinationService;
import com.test.calculator.core.infraestructure.destination.DestinationConstants;
import com.test.calculator.core.web.destination.dto.DestinationDTO;
import com.test.calculator.core.web.destination.dto.DestinationResource;
import com.test.calculator.core.web.destination.dto.DestinationResourceAssembler;
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
@RequestMapping(path = DestinationConstants.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = DestinationConstants.API_DOC)
public class DestinationController {

    private final PagedResourcesAssembler pagedResourcesAssembler;

    private final DestinationResourceAssembler destinationResourceAssembler;

    private final IDestinationService destinationService;

    @Autowired
    public DestinationController(PagedResourcesAssembler pagedResourcesAssembler,
                                 DestinationResourceAssembler destinationResourceAssembler,
                                 IDestinationService destinationService) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.destinationResourceAssembler = destinationResourceAssembler;
        this.destinationService = destinationService;
    }

    /***
     * Method to find a destination by id
     *
     * @param id id of the destination
     * @return destination object
     */
    @GetMapping(value = CommonConstants.MAPPING_GET_BY_ID)
    @ApiOperation(value = "Retrieve a destination by id", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true)
    })
    public ResponseEntity<DestinationResource> findById(@PathVariable Long id) {
        return destinationService.findById(id).map(destinationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /***
     * Method to find all destinations
     *
     * @return collection of destination objects
     */
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL)
    @ApiOperation(value = "Find all destination", response = ResponseEntity.class)
    public ResponseEntity<Collection<Destination>> findAll(){
        return ResponseEntity.ok(destinationService.findAll());
    }

    /***
     * Method to find all destinations by page and filter
     *
     * @param request object to get filters and page from view
     * @return Page of Destination Resource
     * @throws CalculatorException exception when mapper object from request
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL_PAGE)
    @ApiOperation(value = "Find all destinations by page and filter", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", paramType = "query", value = CommonConstants.VALUE_DATA_PAGE_FILTER)
    })
    public ResponseEntity<PagedResources<Collection<DestinationResource>>> findAllPage(
            HttpServletRequest request
    ) throws CalculatorException{

        CustomParamPageFilter data = CustomParamPageFilter.mapRequestData(request);

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(
                destinationService.findAll(
                        new DestinationSpecification(data.getFilters()).toSpecification(),
                        data.getPageable()), destinationResourceAssembler
        ));
    }

    /***
     * Method to save a destination
     *
     * @param destinationDTO dto with data to the new destination
     * @return new destination object
     * @throws CalculatorException validation exception
     */
    @PostMapping(path = CommonConstants.MAPPING_POST_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new destination", response = ResponseEntity.class)
    public ResponseEntity<DestinationResource> save(
            @RequestBody DestinationDTO destinationDTO
    ) throws CalculatorException {
        return ResponseEntity.ok(destinationResourceAssembler.toResource(destinationService.save(destinationDTO)));
    }
}
