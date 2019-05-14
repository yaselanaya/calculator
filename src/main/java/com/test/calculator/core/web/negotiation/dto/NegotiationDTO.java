package com.test.calculator.core.web.negotiation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NegotiationDTO {
    private Long id;
    private String name;
    private String description;
    private Long originCompanyId;
    private Long destinationCompanyId;
    private Long destinationId;
    private List<Long> shipLoadIds;
}
