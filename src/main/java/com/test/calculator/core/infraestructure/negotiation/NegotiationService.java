package com.test.calculator.core.infraestructure.negotiation;

import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.domain.negotiation.INegotiationRepository;
import com.test.calculator.core.domain.negotiation.INegotiationService;
import com.test.calculator.core.domain.negotiation.Negotiation;
import com.test.calculator.core.domain.negotiation.NegotiationFactory;
import com.test.calculator.core.web.negotiation.dto.NegotiationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NegotiationService implements INegotiationService {

    private final INegotiationRepository negotiationRepository;

    private final NegotiationFactory negotiationFactory;

    private final NegotiationServiceValidation negotiationServiceValidation;

    @Autowired
    public NegotiationService(INegotiationRepository negotiationRepository,
                              NegotiationFactory negotiationFactory,
                              NegotiationServiceValidation negotiationServiceValidation) {
        this.negotiationRepository = negotiationRepository;
        this.negotiationFactory = negotiationFactory;
        this.negotiationServiceValidation = negotiationServiceValidation;
    }

    @Override
    public Optional<Negotiation> findById(Long id) {
        return negotiationRepository.findById(id);
    }

    @Override
    public Collection<Negotiation> findAll() {
        return negotiationRepository.findAll();
    }

    @Override
    public Page<Negotiation> findAll(Pageable pageable) {
        return negotiationRepository.findAll(pageable);
    }

    @Override
    public Page<Negotiation> findAll(Specification<Negotiation> specification, Pageable pageable) {
        return negotiationRepository.findAll(specification, pageable);
    }

    /***
     * Method to save a negotiation
     *
     * @param negotiationDTO object came from view
     * @return return a new negotiation
     * @throws CalculatorException throw an exception if negotiation is not valid for save
     */
    @Override
    public Negotiation save(NegotiationDTO negotiationDTO) throws CalculatorException {

        // Convert dto to entity
        Negotiation negotiation = negotiationFactory.from(negotiationDTO);

        // Validate negotiation dto
        negotiationServiceValidation.validateForCreate(negotiation);

        return negotiationRepository.save(negotiation);
    }

    /***
     * Method to update a negotiation
     *
     * @param negotiationDTO object came from view
     * @return return negotiation update
     * @throws CalculatorException throw an exception if negotiation is not valid for save
     */
    @Override
    public Negotiation update(NegotiationDTO negotiationDTO) throws CalculatorException {
        return negotiationRepository.save(negotiationFactory.from(negotiationDTO));
    }
}
