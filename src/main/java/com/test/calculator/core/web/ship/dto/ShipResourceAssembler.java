package com.test.calculator.core.web.ship.dto;

import com.test.calculator.core.domain.ship.Ship;
import com.test.calculator.core.domain.ship.ShipFactory;
import com.test.calculator.core.web.ship.ShipController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ShipResourceAssembler extends ResourceAssemblerSupport<Ship, ShipResource> {
    
    private final ShipFactory shipFactory;

    public ShipResourceAssembler(ShipFactory shipFactory) {
        super(ShipController.class, ShipResource.class);
        this.shipFactory = shipFactory;
    }

    @Override
    public ShipResource toResource(Ship ship) {
        ShipResource resource = shipFactory.from(ship);
        Link selfLink = linkTo(ControllerLinkBuilder.methodOn(ShipController.class)
                .findById(ship.getId())).withSelfRel();
        resource.add(selfLink);
        return resource;
    }

    public Collection<ShipResource> toResources(Collection<Ship> destinations) {
        return destinations.stream().map(this::toResource).collect(Collectors.toList());
    }
}
