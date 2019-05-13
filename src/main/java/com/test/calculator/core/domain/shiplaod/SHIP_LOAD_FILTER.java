package com.test.calculator.core.domain.shiplaod;

import lombok.Getter;

@Getter
public enum SHIP_LOAD_FILTER {

    SHIP("ship");

    private String name;

    SHIP_LOAD_FILTER(String name) {
        this.name = name;
    }
}
