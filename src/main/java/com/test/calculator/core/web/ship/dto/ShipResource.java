package com.test.calculator.core.web.ship.dto;

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
@XmlRootElement(name = "ship")
public class ShipResource extends ResourceSupport {
    private Long shipId;
    private String name;
    private String description;
    private Float capacity;
}
