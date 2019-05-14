package com.test.calculator.core.infraestructure.negotiation;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.domain.negotiation.INegotiationRepository;
import com.test.calculator.core.domain.negotiation.INegotiationService;
import com.test.calculator.core.domain.negotiation.Negotiation;
import com.test.calculator.core.domain.negotiation.NegotiationFactory;
import com.test.calculator.core.domain.shiplaod.IShipLoadRepository;
import com.test.calculator.core.domain.shiplaod.ShipLoad;
import com.test.calculator.core.web.negotiation.dto.FleteResource;
import com.test.calculator.core.web.negotiation.dto.NegotiationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NegotiationService implements INegotiationService {

    private final INegotiationRepository negotiationRepository;

    private final NegotiationFactory negotiationFactory;

    private final NegotiationServiceValidation negotiationServiceValidation;

    private final IShipLoadRepository shipLoadRepository;

    @Autowired
    public NegotiationService(INegotiationRepository negotiationRepository,
                              NegotiationFactory negotiationFactory,
                              NegotiationServiceValidation negotiationServiceValidation,
                              IShipLoadRepository shipLoadRepository) {
        this.negotiationRepository = negotiationRepository;
        this.negotiationFactory = negotiationFactory;
        this.negotiationServiceValidation = negotiationServiceValidation;
        this.shipLoadRepository = shipLoadRepository;
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

        negotiation = negotiationRepository.save(negotiation);

        // Set destination to all ship loads
        for (ShipLoad shipLoad : negotiation.getShipLoads()) {
            shipLoad.setNegotiation(negotiation);
            shipLoadRepository.save(shipLoad);
        }

        return negotiation;
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

    /***
     * Method to calculate flete of a negotiation
     *
     * @param negotiationId id of the negotiation
     * @return return map with all fletes by ships belong to the negotiation
     * @throws CalculatorException throw an exception if negotiation does not exist
     */
    @Override
    public Map calculateFleteOfNegotiation(Long negotiationId) throws CalculatorException {

        Optional<Negotiation> optionalNegotiation = this.findById(negotiationId);

        if (!optionalNegotiation.isPresent())
            throw new CalculatorException(HttpStatus.NOT_FOUND, "validation.error.negotiation.not.found");

        Negotiation negotiation = optionalNegotiation.get();
        Map<String, Object> fletes = Maps.newLinkedHashMap();
        Float total = 0f;
        fletes.put(NegotiationConstants.RESULT_KEY_ID, negotiationId);
        fletes.put(NegotiationConstants.RESULT_KEY_NAME, negotiation.getName());
        fletes.put(NegotiationConstants.RESULT_KEY_FLETE_PER_SHIP, Lists.newArrayList());

        for (ShipLoad shipLoad : negotiation.getShipLoads()) {
            FleteResource fleteResource = FleteResource.builder()
                    .shipLoadId(shipLoad.getId())
                    .shipName(shipLoad.getShip().getName())
                    .flete(negotiation.getDestination().getSurcharge() * shipLoad.getLoad())
                    .build();
            total+= fleteResource.getFlete();
            ((List) fletes.get(NegotiationConstants.RESULT_KEY_FLETE_PER_SHIP)).add(fleteResource);
        }

        fletes.put(NegotiationConstants.RESULT_KEY_TOTAL, total);

        return fletes;
    }
}
