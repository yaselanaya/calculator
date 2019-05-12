package com.test.calculator.core.web.destination;

import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.domain.destination.IDestinationService;
import com.test.calculator.core.web.destination.dto.DestinationDTO;
import com.test.calculator.core.web.destination.dto.DestinationResource;
import com.test.calculator.core.web.destination.dto.DestinationResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = DestinationController.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
public class DestinationController {

    static final String ENTITY_URI = "/destination";

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
    public ResponseEntity<DestinationResource> findById(@PathVariable Long id) {
        return destinationService.findById(id).map(destinationResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /***
     * Method to save a destination
     *
     * @param destinationDTO dto with data to the new destination
     * @return new destination object
     * @throws CalculatorException validation exception
     */
    @PostMapping(path = CommonConstants.MAPPING_POST_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DestinationResource> save(@RequestBody DestinationDTO destinationDTO) throws CalculatorException {
        return ResponseEntity.ok(destinationResourceAssembler.toResource(destinationService.save(destinationDTO)));
    }
}
