package com.test.calculator.core.domain.negotiation;

import com.test.calculator.core.common.IBaseService;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.web.negotiation.dto.NegotiationDTO;

import java.util.Map;

public interface INegotiationService extends IBaseService<Negotiation, Long, NegotiationDTO> {

    Map calculateFleteOfNegotiation(Long negotiationId) throws CalculatorException;

}
