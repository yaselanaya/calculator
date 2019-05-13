package com.test.calculator.core.infraestructure.ship;

import com.google.common.collect.Lists;
import com.test.calculator.core.common.exception.ErrorCode;
import com.test.calculator.core.common.validation.EntityValidationService;
import com.test.calculator.core.domain.ship.IShipRepository;
import com.test.calculator.core.domain.ship.Ship;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ShipServiceValidation extends EntityValidationService<Ship> {

    private final IShipRepository shipRepository;

    @Autowired
    public ShipServiceValidation(Validator validator, IShipRepository shipRepository) {
        super(validator);
        this.shipRepository = shipRepository;
    }

    @Override
    protected List<Map<String, Object>> validateCommonBusinessConstraints(Ship ship){

        /* Validate business logic specific to ship */
        List<Map<String, Object>> errors = Lists.newArrayList();

        if(Objects.nonNull(shipRepository.findByName(ship.getName()))) {
            errors.add(createError("name", ErrorCode.DUPLICITY,
                    "validation.error.destination.duplicity.name", Strings.EMPTY));
        }

        return errors;
    }
}
