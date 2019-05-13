package com.test.calculator.core.web.shiplaod.dto;

import com.test.calculator.core.web.ship.dto.ShipResource;
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
@XmlRootElement(name = "shipLoad")
public class ShipLoadResource extends ResourceSupport {
    private Long shipLoadId;
    private ShipResource ship;
    private Float load;
}
