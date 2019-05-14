package com.test.calculator.core.infraestructure.negotiation;

import com.google.common.collect.Lists;
import com.test.calculator.core.common.exception.ErrorCode;
import com.test.calculator.core.common.validation.EntityValidationService;
import com.test.calculator.core.domain.negotiation.INegotiationRepository;
import com.test.calculator.core.domain.negotiation.Negotiation;
import com.test.calculator.core.domain.ship.Ship;
import com.test.calculator.core.domain.shiplaod.ShipLoad;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class NegotiationServiceValidation extends EntityValidationService<Negotiation> {

    private final INegotiationRepository negotiationRepository;

    @Autowired
    public NegotiationServiceValidation(Validator validator, INegotiationRepository negotiationRepository) {
        super(validator);
        this.negotiationRepository = negotiationRepository;
    }

    @Override
    protected List<Map<String, Object>> validateCommonBusinessConstraints(Negotiation negotiation){

        /* Validate business logic specific to negotiation */
        List<Map<String, Object>> errors = Lists.newArrayList();

        /* Validate that the name don't exist */
        if(Objects.nonNull(negotiationRepository.findByName(negotiation.getName())))
            errors.add(createError("name", ErrorCode.DUPLICITY,
                    "validation.error.negotiation.duplicity.name", Strings.EMPTY));

        /* Validate that the Origin company and Destination company not be equals */
        if(negotiation.getOriginCompany().equals(negotiation.getDestinationCompany()))
            errors.add(createError("companies", ErrorCode.DUPLICITY,
                    "validation.error.negotiation.duplicity.company", Strings.EMPTY));

        /* Validate if there are two ship equal in ship loads list */
        List<Ship> ships = Lists.newArrayList();

        List<ShipLoad> shipLoads = new ArrayList<>(negotiation.getShipLoads());
        for (int i = 0; i < negotiation.getShipLoads().size(); i++) {
            if(ships.contains(shipLoads.get(i).getShip())) {
                errors.add(createError("ships", ErrorCode.DUPLICITY,
                        "validation.error.negotiation.duplicity.ship", Strings.EMPTY));
                break;
            }
            ships.add(shipLoads.get(i).getShip());
        }

        return errors;
    }
}
