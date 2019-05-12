package com.test.calculator.core.web.destination.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "destination")
public class DestinationResource extends ResourceSupport {
    private Long destinationId;
    private String name;
    private String description;
    private Integer surcharge;
}
