package com.test.calculator.core.web.destination.dto;

import com.test.calculator.core.domain.destination.Destination;
import com.test.calculator.core.domain.destination.DestinationFactory;
import com.test.calculator.core.web.destination.DestinationController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class DestinationResourceAssembler extends ResourceAssemblerSupport<Destination, DestinationResource> {

    private final DestinationFactory destinationFactory;

    public DestinationResourceAssembler(DestinationFactory destinationFactory) {
        super(DestinationController.class, DestinationResource.class);
        this.destinationFactory = destinationFactory;
    }

    @Override
    public DestinationResource toResource(Destination destination) {
        DestinationResource resource = destinationFactory.from(destination);
        Link selfLink = linkTo(ControllerLinkBuilder.methodOn(DestinationController.class)
                .findById(destination.getId())).withSelfRel();
        resource.add(selfLink);
        return resource;
    }

    public Collection<DestinationResource> toResources(Collection<Destination> destinations) {
        return destinations.stream().map(this::toResource).collect(Collectors.toList());
    }
}
