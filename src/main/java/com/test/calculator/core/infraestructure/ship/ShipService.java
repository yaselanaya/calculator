package com.test.calculator.core.infraestructure.ship;

import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.domain.ship.IShipRepository;
import com.test.calculator.core.domain.ship.IShipService;
import com.test.calculator.core.domain.ship.Ship;
import com.test.calculator.core.domain.ship.ShipFactory;
import com.test.calculator.core.web.ship.dto.ShipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ShipService implements IShipService {

    private final IShipRepository shipRepository;

    private final ShipFactory shipFactory;

    private final ShipServiceValidation shipServiceValidation;


    @Autowired
    public ShipService(IShipRepository shipRepository, ShipFactory shipFactory,
                       ShipServiceValidation shipServiceValidation) {
        this.shipRepository = shipRepository;
        this.shipFactory = shipFactory;
        this.shipServiceValidation = shipServiceValidation;
    }

    @Override
    public Optional<Ship> findById(Long id) {
        return shipRepository.findById(id);
    }

    @Override
    public Collection<Ship> findAll() {
        return shipRepository.findAll();
    }

    @Override
    public Page<Ship> findAll(Pageable pageable) {
        return shipRepository.findAll(pageable);
    }

    @Override
    public Page<Ship> findAll(Specification<Ship> specification, Pageable pageable) {
        return shipRepository.findAll(specification, pageable);
    }

    /***
     * Method to save a ship
     *
     * @param shipDTO object came from view
     * @return return a new ship
     * @throws CalculatorException throw an exception if ship is not valid for save
     */
    @Override
    public Ship save(ShipDTO shipDTO) throws CalculatorException {
        // Convert dto to entity
        Ship ship = shipFactory.from(shipDTO);

        // Validate destination dto
        shipServiceValidation.validateForCreate(ship);

        return shipRepository.save(ship);
    }

    /***
     * Method to update a ship
     *
     * @param shipDTO object came from view
     * @return return ship updated
     * @throws CalculatorException throw an exception if ship is not valid for save
     */
    @Override
    public Ship update(ShipDTO shipDTO) throws CalculatorException {
        return null;
    }
}
