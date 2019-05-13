package com.test.calculator.core.infraestructure.destination;

import com.google.common.collect.Lists;
import com.test.calculator.core.common.exception.ErrorCode;
import com.test.calculator.core.common.validation.EntityValidationService;
import com.test.calculator.core.domain.destination.Destination;
import com.test.calculator.core.domain.destination.IDestinationRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DestinationServiceValidation extends EntityValidationService<Destination> {

    private final IDestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceValidation(Validator validator, IDestinationRepository destinationRepository) {
        super(validator);
        this.destinationRepository = destinationRepository;
    }

    @Override
    protected List<Map<String, Object>> validateCommonBusinessConstraints(Destination destination){

        /* Validate business logic specific to destination */
        List<Map<String, Object>> errors = Lists.newArrayList();

        if(Objects.nonNull(destinationRepository.findByName(destination.getName()))) {
            errors.add(createError("name", ErrorCode.DUPLICITY,
                    "validation.error.destination.duplicity.name", Strings.EMPTY));
        }

        return errors;
    }
}
