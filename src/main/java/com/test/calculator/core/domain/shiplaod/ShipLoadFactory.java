package com.test.calculator.core.domain.shiplaod;

import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.message.Messages;
import com.test.calculator.core.domain.ship.IShipService;
import com.test.calculator.core.domain.ship.ShipFactory;
import com.test.calculator.core.web.shiplaod.dto.ShipLoadDTO;
import com.test.calculator.core.web.shiplaod.dto.ShipLoadResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ShipLoadFactory {

    private final IShipService shipService;

    private final ShipFactory shipFactory;

    private final Messages messages;

    @Autowired
    public ShipLoadFactory(IShipService shipService, ShipFactory shipFactory, Messages messages) {
        this.shipService = shipService;
        this.shipFactory = shipFactory;
        this.messages = messages;
    }

    public ShipLoad from(ShipLoadDTO shipLoadDTO) throws CalculatorException{
        return ShipLoad.builder()
                .id(Objects.nonNull(shipLoadDTO.getId()) ? shipLoadDTO.getId() : null)
                .ship(Objects.nonNull(shipLoadDTO.getShipId())  ?
                        shipService.findById(shipLoadDTO.getShipId())
                                .orElseThrow(() -> new CalculatorException(
                                        HttpStatus.NOT_FOUND, messages.getMessage("validation.error.ship.not.found")
                                )) : null
                )
                .load(Objects.nonNull(shipLoadDTO.getLoad()) ? shipLoadDTO.getLoad() : null)
                .build();
    }

    public ShipLoadResource from(ShipLoad shipLoad){
        return ShipLoadResource.builder()
                .shipLoadId(Objects.nonNull(shipLoad.getId()) ? shipLoad.getId() : null)
                .ship(Objects.nonNull(shipLoad.getShip()) ? shipFactory.from(shipLoad.getShip()) : null)
                .load(Objects.nonNull(shipLoad.getLoad()) ? shipLoad.getLoad() : null)
                .build();
    }
}
