package com.test.calculator.core.web.negotiation.dto;

import com.test.calculator.core.web.campany.dto.CompanyResource;
import com.test.calculator.core.web.destination.dto.DestinationResource;
import com.test.calculator.core.web.shiplaod.dto.ShipLoadResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "negotiation")
public class NegotiationResource extends ResourceSupport {
    private Long negotiationId;
    private String name;
    private String description;
    private CompanyResource originCompany;
    private CompanyResource destinationCompany;
    private DestinationResource destination;
    private List<ShipLoadResource> shipLoads;
}
