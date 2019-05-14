package com.test.calculator.core.web.negotiation.dto;

import com.test.calculator.core.domain.destination.Destination;
import com.test.calculator.core.domain.negotiation.Negotiation;
import com.test.calculator.core.domain.negotiation.NegotiationFactory;
import com.test.calculator.core.web.destination.dto.DestinationResource;
import com.test.calculator.core.web.negotiation.NegotiationController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class NegotiationResourceAssembler extends ResourceAssemblerSupport<Negotiation, NegotiationResource> {

    private final NegotiationFactory negotiationFactory;

    public NegotiationResourceAssembler(NegotiationFactory negotiationFactory) {
        super(NegotiationController.class, NegotiationResource.class);
        this.negotiationFactory = negotiationFactory;
    }

    @Override
    public NegotiationResource toResource(Negotiation negotiation) {
        NegotiationResource resource = negotiationFactory.from(negotiation);
        Link selfLink = linkTo(ControllerLinkBuilder.methodOn(NegotiationController.class)
                .findById(negotiation.getId())).withSelfRel();
        resource.add(selfLink);
        return resource;
    }

    public Collection<NegotiationResource> toResources(Collection<Negotiation> negotiations) {
        return negotiations.stream().map(this::toResource).collect(Collectors.toList());
    }

}
