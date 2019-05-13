package com.test.calculator.core.web.shiplaod.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShipLoadDTO {
    private Long id;
    private Long shipId;
    private Float load;
}
