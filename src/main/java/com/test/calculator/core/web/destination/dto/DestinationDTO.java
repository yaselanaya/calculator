package com.test.calculator.core.web.destination.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DestinationDTO {
    private Long id;
    private String name;
    private String description;
    private Integer surcharge;
}
