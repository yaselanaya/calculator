package com.test.calculator.core.domain.destination;

import com.test.calculator.core.web.destination.dto.DestinationDTO;
import com.test.calculator.core.web.destination.dto.DestinationResource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DestinationFactory {

    public Destination from(DestinationDTO destinationDTO){
        return Destination.builder()
                .id(Objects.nonNull(destinationDTO.getId()) ? destinationDTO.getId() : null)
                .name(Objects.nonNull(destinationDTO.getName()) ? destinationDTO.getName() : null)
                .description(Objects.nonNull(destinationDTO.getDescription()) ? destinationDTO.getDescription() : null)
                .surcharge(Objects.nonNull(destinationDTO.getSurcharge()) ? destinationDTO.getSurcharge() : null)
                .build();
    }

    public DestinationResource from(Destination destination){
        return DestinationResource.builder()
                .destinationId(Objects.nonNull(destination.getId()) ? destination.getId() : null)
                .name(Objects.nonNull(destination.getName()) ? destination.getName() : null)
                .description(Objects.nonNull(destination.getDescription()) ? destination.getDescription() : null)
                .surcharge(Objects.nonNull(destination.getSurcharge()) ? destination.getSurcharge() : null)
                .build();
    }
}
