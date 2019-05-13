package com.test.calculator.core.domain.ship;

import com.test.calculator.core.web.ship.dto.ShipDTO;
import com.test.calculator.core.web.ship.dto.ShipResource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ShipFactory {

    public Ship from(ShipDTO shipDTO){
        return Ship.builder()
                .id(Objects.nonNull(shipDTO.getId()) ? shipDTO.getId() : null)
                .name(Objects.nonNull(shipDTO.getName()) ? shipDTO.getName() : null)
                .description(Objects.nonNull(shipDTO.getDescription()) ? shipDTO.getDescription() : null)
                .capacity(Objects.nonNull(shipDTO.getCapacity()) ? shipDTO.getCapacity() : null)
                .build();
    }

    public ShipResource from(Ship ship){
        return ShipResource.builder()
                .shipId(Objects.nonNull(ship.getId()) ? ship.getId() : null)
                .name(Objects.nonNull(ship.getName()) ? ship.getName() : null)
                .description(Objects.nonNull(ship.getDescription()) ? ship.getDescription() : null)
                .capacity(Objects.nonNull(ship.getCapacity()) ? ship.getCapacity() : null)
                .build();
    }
}
