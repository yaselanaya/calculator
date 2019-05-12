package com.test.calculator.core.infraestructure.destination;

import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.domain.destination.Destination;
import com.test.calculator.core.domain.destination.DestinationFactory;
import com.test.calculator.core.domain.destination.IDestinationRepository;
import com.test.calculator.core.domain.destination.IDestinationService;
import com.test.calculator.core.web.destination.dto.DestinationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DestinationService implements IDestinationService {

    private final IDestinationRepository destinationRepository;

    private final DestinationFactory destinationFactory;

    private final DestinationServiceValidation destinationServiceValidation;


    @Autowired
    public DestinationService(IDestinationRepository destinationRepository, DestinationFactory destinationFactory,
                              DestinationServiceValidation destinationServiceValidation) {
        this.destinationRepository = destinationRepository;
        this.destinationFactory = destinationFactory;
        this.destinationServiceValidation = destinationServiceValidation;
    }

    @Override
    public Optional<Destination> findById(Long id) {
        return destinationRepository.findById(id);
    }

    @Override
    public Collection<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public Page<Destination> findAll(Pageable pageable) {
        return destinationRepository.findAll(pageable);
    }

    @Override
    public Page<Destination> findAll(Specification<Destination> specification, Pageable pageable) {
        return destinationRepository.findAll(specification, pageable);
    }

    /***
     * Method to save a destination
     *
     * @param destinationDTO object came from view
     * @return return a new destination
     * @throws CalculatorException throw an exception if destination is not valid for save
     */
    @Override
    public Destination save(DestinationDTO destinationDTO) throws CalculatorException {
        // Convert dto to entity
        Destination destination = destinationFactory.from(destinationDTO);

        // Validate destination dto
        destinationServiceValidation.validateForCreate(destination);

        return destinationRepository.save(destination);
    }

    @Override
    public Destination update(DestinationDTO destinationDTO) throws CalculatorException {
        return destinationRepository.save(destinationFactory.from(destinationDTO));
    }
}
