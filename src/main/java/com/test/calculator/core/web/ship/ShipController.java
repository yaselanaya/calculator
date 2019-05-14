package com.test.calculator.core.web.ship;

import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.mapper.CustomParamPageFilter;
import com.test.calculator.core.domain.ship.Ship;
import com.test.calculator.core.domain.ship.ShipSpecification;
import com.test.calculator.core.infraestructure.ship.ShipConstants;
import com.test.calculator.core.infraestructure.ship.ShipService;
import com.test.calculator.core.web.ship.dto.ShipDTO;
import com.test.calculator.core.web.ship.dto.ShipResource;
import com.test.calculator.core.web.ship.dto.ShipResourceAssembler;
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
@RequestMapping(path = ShipConstants.ENTITY_URI ,produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = ShipConstants.API_DOC)
public class ShipController {

    private final PagedResourcesAssembler pagedResourcesAssembler;

    private final ShipResourceAssembler shipResourceAssembler;

    private final ShipService shipService;

    @Autowired
    public ShipController(PagedResourcesAssembler pagedResourcesAssembler,
                          ShipResourceAssembler shipResourceAssembler, ShipService shipService) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.shipResourceAssembler = shipResourceAssembler;
        this.shipService = shipService;
    }

    /***
     * Method to find a ship by id
     *
     * @param id id of the ship
     * @return ship object
     */
    @GetMapping(value = CommonConstants.MAPPING_GET_BY_ID)
    @ApiOperation(value = "Retrieve a ship by id", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true)
    })
    public ResponseEntity<ShipResource> findById(@PathVariable Long id) {
        return shipService.findById(id).map(shipResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /***
     * Method to find all ships
     *
     * @return collection of ship objects
     */
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL)
    @ApiOperation(value = "Find all ships", response = ResponseEntity.class)
    public ResponseEntity<Collection<Ship>> findAll(){
        return ResponseEntity.ok(shipService.findAll());
    }

    /***
     * Method to find all ships by page and filter
     *
     * @param request object to get filters and page from view
     * @return Page of Ship Resource
     * @throws CalculatorException exception when mapper object from request
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value = CommonConstants.MAPPING_FIND_ALL_PAGE)
    @ApiOperation(value = "Find all ships by page and filter", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", paramType = "query", value = CommonConstants.VALUE_DATA_PAGE_FILTER)
    })
    public ResponseEntity<PagedResources<Collection<ShipResource>>> findAllPage(
            HttpServletRequest request
    ) throws CalculatorException{

        CustomParamPageFilter data = CustomParamPageFilter.mapRequestData(request);

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(
                shipService.findAll(
                        new ShipSpecification(data.getFilters()).toSpecification(),
                        data.getPageable()), shipResourceAssembler
        ));
    }

    /***
     * Method to save a ship
     *
     * @param shipDTO dto with data to the new ship
     * @return new ship object
     * @throws CalculatorException validation exception
     */
    @PostMapping(path = CommonConstants.MAPPING_POST_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new ship", response = ResponseEntity.class)
    public ResponseEntity<ShipResource> save(
            @RequestBody ShipDTO shipDTO
            ) throws CalculatorException {
        return ResponseEntity.ok(shipResourceAssembler.toResource(shipService.save(shipDTO)));
    }
}
