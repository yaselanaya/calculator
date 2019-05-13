package com.test.calculator.core.web.shiplaod.dto;

import com.test.calculator.core.domain.shiplaod.ShipLoad;
import com.test.calculator.core.domain.shiplaod.ShipLoadFactory;
import com.test.calculator.core.web.shiplaod.ShipLoadController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ShipLoadResourceAssembler extends ResourceAssemblerSupport<ShipLoad, ShipLoadResource> {

    private final ShipLoadFactory shipLoadFactory;

    public ShipLoadResourceAssembler(ShipLoadFactory shipLoadFactory) {
        super(ShipLoadController.class, ShipLoadResource.class);
        this.shipLoadFactory = shipLoadFactory;
    }

    @Override
    public ShipLoadResource toResource(ShipLoad shipLoad) {
        ShipLoadResource resource = shipLoadFactory.from(shipLoad);
        Link selfLink = linkTo(ControllerLinkBuilder.methodOn(ShipLoadController.class)
                .findById(shipLoad.getId())).withSelfRel();
        resource.add(selfLink);
        return resource;
    }

    public Collection<ShipLoadResource> toResources(Collection<ShipLoad> destinations) {
        return destinations.stream().map(this::toResource).collect(Collectors.toList());
    }
}
