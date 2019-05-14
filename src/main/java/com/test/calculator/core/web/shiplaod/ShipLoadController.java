package com.test.calculator.core.web.shiplaod;

import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.mapper.CustomParamPageFilter;
import com.test.calculator.core.domain.shiplaod.IShipLoadService;
import com.test.calculator.core.domain.shiplaod.ShipLoadSpecification;
import com.test.calculator.core.infraestructure.ship.ShipConstants;
import com.test.calculator.core.infraestructure.shiplaod.ShipLoadConstants;
import com.test.calculator.core.infraestructure.shiplaod.ShipLoadService;
import com.test.calculator.core.web.shiplaod.dto.ShipLoadDTO;
import com.test.calculator.core.web.shiplaod.dto.ShipLoadResource;
import com.test.calculator.core.web.shiplaod.dto.ShipLoadResourceAssembler;
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
@RequestMapping(path = ShipLoadConstants.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = ShipLoadConstants.API_DOC)
public class ShipLoadController {

    private final PagedResourcesAssembler pagedResourcesAssembler;

    private final ShipLoadResourceAssembler shipLoadResourceAssembler;

    private final IShipLoadService shipLoadService;

    @Autowired
    public ShipLoadController(PagedResourcesAssembler pagedResourcesAssembler,
                              ShipLoadResourceAssembler shipLoadResourceAssembler, ShipLoadService shipLoadService) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.shipLoadResourceAssembler = shipLoadResourceAssembler;
        this.shipLoadService = shipLoadService;
    }

    /***
     * Method to find a ship load by id
     *
     * @param id id of the ship load
     * @return ship load object
     */
    @GetMapping(value = CommonConstants.MAPPING_GET_BY_ID)
    @ApiOperation(value = "Retrieve a ship load by id", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true)
    })
    public ResponseEntity<ShipLoadResource> findById(@PathVariable Long id) {
        return shipLoadService.findById(id).map(shipLoadResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /***
     * Method to find all ships loads
     *
     * @return collection of ship objects
     */
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL)
    @ApiOperation(value = "Find all ship loads", response = ResponseEntity.class)
    public ResponseEntity<Collection<ShipLoadResource>> findAll(){
        return ResponseEntity.ok(shipLoadResourceAssembler.toResources(shipLoadService.findAll()));
    }

    /***
     * Method to find all ships loads by page and filter
     *
     * @param request object to get filters and page from view
     * @return Page of Ship Load Resource
     * @throws CalculatorException exception when mapper object from request
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL_PAGE)
    @ApiOperation(value = "Find all ship loads by page and filter", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", paramType = "query", value = CommonConstants.VALUE_DATA_PAGE_FILTER)
    })
    public ResponseEntity<PagedResources<Collection<ShipLoadResource>>> findAllPage(
            HttpServletRequest request
    ) throws CalculatorException{

        CustomParamPageFilter data = CustomParamPageFilter.mapRequestData(request);

        ShipLoadSpecification shipLoadSpecification = new ShipLoadSpecification(data.getFilters());
        return ResponseEntity.ok(pagedResourcesAssembler.toResource(
                shipLoadService.findAll(
                        shipLoadSpecification.toSpecification(),
                        data.getPageable()), shipLoadResourceAssembler
        ));
    }

    /***
     * Method to save a ship load
     *
     * @param shipLoadDTO dto with data to the new ship load
     * @return new ship load object
     * @throws CalculatorException validation exception
     */
    @PostMapping(path = CommonConstants.MAPPING_POST_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new ship load", response = ResponseEntity.class)
    public ResponseEntity<ShipLoadResource> save(
            @RequestBody ShipLoadDTO shipLoadDTO
    ) throws CalculatorException {
        return ResponseEntity.ok(shipLoadResourceAssembler.toResource(shipLoadService.save(shipLoadDTO)));
    }


}
