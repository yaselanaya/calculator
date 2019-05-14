package com.test.calculator.core.domain.negotiation;

import com.google.common.collect.Lists;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.message.Messages;
import com.test.calculator.core.domain.campany.CompanyFactory;
import com.test.calculator.core.domain.campany.ICompanyService;
import com.test.calculator.core.domain.destination.DestinationFactory;
import com.test.calculator.core.domain.destination.IDestinationService;
import com.test.calculator.core.domain.shiplaod.IShipLoadService;
import com.test.calculator.core.domain.shiplaod.ShipLoad;
import com.test.calculator.core.domain.shiplaod.ShipLoadFactory;
import com.test.calculator.core.web.negotiation.dto.NegotiationDTO;
import com.test.calculator.core.web.negotiation.dto.NegotiationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class NegotiationFactory {

    private final IShipLoadService shipLoadService;

    private final ShipLoadFactory shipLoadFactory;

    private final IDestinationService destinationService;

    private final DestinationFactory destinationFactory;

    private final ICompanyService companyService;

    private final CompanyFactory companyFactory;

    private final Messages messages;

    @Autowired
    public NegotiationFactory(IShipLoadService shipLoadService, ShipLoadFactory shipLoadFactory,
                              IDestinationService destinationService, DestinationFactory destinationFactory,
                              ICompanyService companyService, CompanyFactory companyFactory, Messages messages) {
        this.shipLoadService = shipLoadService;
        this.shipLoadFactory = shipLoadFactory;
        this.destinationService = destinationService;
        this.destinationFactory = destinationFactory;
        this.companyService = companyService;
        this.companyFactory = companyFactory;
        this.messages = messages;
    }

    public Negotiation from(NegotiationDTO negotiationDTO) throws CalculatorException{

        Collection<ShipLoad> shipLoads = Lists.newArrayList();

        if(Objects.isNull(negotiationDTO.getShipLoadIds()) || negotiationDTO.getShipLoadIds().isEmpty()){
            throw new CalculatorException(
                    HttpStatus.NOT_FOUND, messages.getMessage("validation.error.destination.not.found")
            );
        }

        negotiationDTO.getShipLoadIds().forEach(shipLoadId -> {
            shipLoadService.findById(shipLoadId).ifPresent(shipLoads::add);
        });

        return Negotiation.builder()
                .id(Objects.nonNull(negotiationDTO.getId()) ? negotiationDTO.getId() : null)
                .name(Objects.nonNull(negotiationDTO.getName()) ? negotiationDTO.getName() : null)
                .description(Objects.nonNull(negotiationDTO.getDescription()) ? negotiationDTO.getDescription() : null)
                .originCompany(Objects.nonNull(negotiationDTO.getOriginCompanyId()) ?
                        companyService.findById(negotiationDTO.getOriginCompanyId())
                        .orElseThrow(() -> new CalculatorException(
                                HttpStatus.NOT_FOUND, messages.getMessage("validation.error.company.not.found")
                        )) : null)
                .destinationCompany(Objects.nonNull(negotiationDTO.getDestinationCompanyId()) ?
                        companyService.findById(negotiationDTO.getDestinationCompanyId())
                                .orElseThrow(() -> new CalculatorException(
                                        HttpStatus.NOT_FOUND, messages.getMessage("validation.error.company.not.found")
                                )) : null)
                .destination(Objects.nonNull(negotiationDTO.getDestinationId()) ?
                        destinationService.findById(negotiationDTO.getDestinationId())
                                .orElseThrow(() -> new CalculatorException(
                                        HttpStatus.NOT_FOUND, messages.getMessage("validation.error.destination.not.found")
                                )) : null)
                .shipLoads(shipLoads)
                .build();
    }

    public NegotiationResource from(Negotiation negotiation){
        return NegotiationResource.builder()
                .negotiationId(negotiation.getId())
                .name(negotiation.getName())
                .description(negotiation.getDescription())
                .destination(destinationFactory.from(negotiation.getDestination()))
                .originCompany(companyFactory.from(negotiation.getOriginCompany()))
                .destinationCompany(companyFactory.from(negotiation.getDestinationCompany()))
                .shipLoads(
                        negotiation.getShipLoads().stream()
                                .map(shipLoadFactory::from)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
