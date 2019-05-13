package com.test.calculator.core.common.validation;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.message.Messages;
import com.test.calculator.core.common.exception.CalculatorValidationException;
import com.test.calculator.core.common.exception.ErrorCode;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Validated
public class EntityValidationService<Entity> {

    private final Validator validator;

    @Autowired
    private Messages messages;

    public EntityValidationService(Validator validator) {
        this.validator = validator;
    }

    /**
     * Method to validate entity for create operation
     *
     * @param entity entity to validate
     * @throws CalculatorValidationException validation exception
     */
    public void validateForCreate(Entity entity) throws CalculatorValidationException {
        validateStructure(entity, OnCreate.class, Common.class);
        List<Map<String, Object>> errors = validateCommonBusinessConstraints(entity);
        errors.addAll(validateCreateBusinessConstraints(entity));

        if (!errors.isEmpty())
            throw new CalculatorValidationException(HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }

    /**
     * Method to validate entity for update operation
     *
     * @param entity entity to validate
     * @throws CalculatorValidationException validation exception
     */
    public void validateForUpdate(Entity entity) throws CalculatorValidationException {
        validateStructure(entity, OnUpdate.class, Common.class);
        List<Map<String, Object>> errors = validateCommonBusinessConstraints(entity);
        errors.addAll(validateUpdateBusinessConstraints(entity));

        if (!errors.isEmpty())
            throw new CalculatorValidationException(HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }

    /**
     * Method to validate entity's structure
     *
     * @param entity entity to validate
     * @param groups groups to validate the entity
     * @throws CalculatorValidationException validation exception
     */
    private void validateStructure(Entity entity, Class... groups)
            throws CalculatorValidationException {
        List<Map<String, Object>> errors = new ArrayList<>();
        Set<ConstraintViolation<Entity>> validate = validator.validate(entity, groups);
        if (!validate.isEmpty()) {
            validate.forEach(
                    error -> errors.add(createError(error.getPropertyPath().toString(),
                            ErrorCode.INVALID_VALUE, error.getMessage(), Strings.EMPTY)));

            throw new CalculatorValidationException(HttpStatus.BAD_REQUEST, errors);
        }
    }

    /**
     * Method to validate common business
     *
     * @param entity entity to validate
     * @return array with errors
     */
    protected List<Map<String, Object>> validateCommonBusinessConstraints(Entity entity) {
        return Lists.newArrayList();
    }

    /**
     * Method to validate business on create operations
     *
     * @param entity entity to validate
     * @return array with errors
     */
    protected List<Map<String, Object>> validateCreateBusinessConstraints(Entity entity) {
        return Lists.newArrayList();
    }

    /**
     * Method to validate business on update operations
     *
     * @param entity entity to validate
     * @return array with errors
     */
    protected List<Map<String, Object>> validateUpdateBusinessConstraints(Entity entity) {
        return Lists.newArrayList();
    }

    /**
     * Method to transform the error map
     *
     * @param field field in domain
     * @param code http status code
     * @param messageKey message with the description of the error
     * @param value value of the field
     * @return map with the error transformed
     */
    protected Map<String, Object> createError(String field, ErrorCode code, String messageKey, Object value) {
        Map<String, Object> errorMap = Maps.newHashMap();
        errorMap.put(CommonConstants.UPDATE_ERROR_MAP_KEY_FIELD, field);
        errorMap.put(CommonConstants.UPDATE_ERROR_MAP_KEY_CODE, code);
        errorMap.put(CommonConstants.UPDATE_ERROR_MAP_KEY_MESSAGE, messages.getMessage(messageKey));
        errorMap.put(CommonConstants.UPDATE_ERROR_MAP_KEY_VALUE, value);
        return errorMap;
    }
}
