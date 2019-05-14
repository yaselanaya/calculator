package com.test.calculator.core.web.negotiation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FleteResource {
    private Long shipLoadId;
    private String shipName;
    private Float flete;
}
