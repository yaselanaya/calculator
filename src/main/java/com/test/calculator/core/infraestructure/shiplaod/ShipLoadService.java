package com.test.calculator.core.infraestructure.shiplaod;

import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.domain.shiplaod.IShipLoadRepository;
import com.test.calculator.core.domain.shiplaod.IShipLoadService;
import com.test.calculator.core.domain.shiplaod.ShipLoad;
import com.test.calculator.core.domain.shiplaod.ShipLoadFactory;
import com.test.calculator.core.web.shiplaod.dto.ShipLoadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ShipLoadService implements IShipLoadService {

    private final IShipLoadRepository shipLoadRepository;

    private final ShipLoadFactory shipLoadFactory;

    private final ShipLoadServiceValidation shipLoadServiceValidation;

    @Autowired
    public ShipLoadService(IShipLoadRepository shipLoadRepository, ShipLoadFactory shipLoadFactory,
                           ShipLoadServiceValidation shipLoadServiceValidation) {
        this.shipLoadRepository = shipLoadRepository;
        this.shipLoadFactory = shipLoadFactory;
        this.shipLoadServiceValidation = shipLoadServiceValidation;
    }

    @Override
    public Optional<ShipLoad> findById(Long id) {
        return shipLoadRepository.findById(id);
    }

    @Override
    public Collection<ShipLoad> findAll() {
        return shipLoadRepository.findAll();
    }

    @Override
    public Page<ShipLoad> findAll(Pageable pageable) {
        return shipLoadRepository.findAll(pageable);
    }

    @Override
    public Page<ShipLoad> findAll(Specification<ShipLoad> specification, Pageable pageable) {
        return shipLoadRepository.findAll(specification, pageable);
    }


    /***
     * Method to save a ship load
     *
     * @param shipLoadDTO object came from view
     * @return return a new ship load
     * @throws CalculatorException throw an exception if ship is not valid for save
     */
    @Override
    public ShipLoad save(ShipLoadDTO shipLoadDTO) throws CalculatorException {

        // Convert dto to entity
        ShipLoad shipLoad = shipLoadFactory.from(shipLoadDTO);

        // Validate ship load dto
        shipLoadServiceValidation.validateForCreate(shipLoad);

        return shipLoadRepository.save(shipLoad);
    }

    /***
     * Method to update a ship load
     *
     * @param shipLoadDTO object came from view
     * @return return ship load updated
     * @throws CalculatorException throw an exception if ship is not valid for save
     */
    @Override
    public ShipLoad update(ShipLoadDTO shipLoadDTO) throws CalculatorException {
        return shipLoadRepository.save(shipLoadFactory.from(shipLoadDTO));
    }
}
