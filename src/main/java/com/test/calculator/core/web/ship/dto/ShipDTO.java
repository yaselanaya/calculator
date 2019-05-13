package com.test.calculator.core.web.ship.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShipDTO {
    private Long id;
    private String name;
    private String description;
    private Float capacity;
}
