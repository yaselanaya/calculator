package com.test.calculator.core.infraestructure.negotiation;

import com.google.common.collect.Lists;
import com.test.calculator.core.common.exception.ErrorCode;
import com.test.calculator.core.common.validation.EntityValidationService;
import com.test.calculator.core.domain.negotiation.INegotiationRepository;
import com.test.calculator.core.domain.negotiation.Negotiation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        if(Objects.nonNull(negotiationRepository.findByName(negotiation.getName())))
            errors.add(createError("name", ErrorCode.DUPLICITY,
                    "validation.error.negotiation.duplicity.name", Strings.EMPTY));

        if(negotiation.getOriginCompany().equals(negotiation.getDestinationCompany()))
            errors.add(createError("companies", ErrorCode.DUPLICITY,
                    "validation.error.negotiation.duplicity.company", Strings.EMPTY));

        return errors;
    }
}
