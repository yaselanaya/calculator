package com.test.calculator.core.infraestructure.shiplaod;

import com.google.common.collect.Lists;
import com.test.calculator.core.common.exception.ErrorCode;
import com.test.calculator.core.common.validation.EntityValidationService;
import com.test.calculator.core.domain.shiplaod.ShipLoad;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;

@Service
public class ShipLoadServiceValidation extends EntityValidationService<ShipLoad> {

    @Autowired
    public ShipLoadServiceValidation(Validator validator) {
        super(validator);
    }

    @Override
    protected List<Map<String, Object>> validateCommonBusinessConstraints(ShipLoad shipLoad){

        /* Validate business logic specific to ship */
        List<Map<String, Object>> errors = Lists.newArrayList();

        if(shipLoad.getShip().getCapacity() < shipLoad.getLoad()) {
            errors.add(createError("load", ErrorCode.INVALID_VALUE,
                    "validation.error.ship.load.capacity.ship", Strings.EMPTY));
        }

        return errors;
    }
}
