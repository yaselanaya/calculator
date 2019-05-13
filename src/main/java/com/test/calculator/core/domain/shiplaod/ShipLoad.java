package com.test.calculator.core.domain.shiplaod;

import com.test.calculator.core.common.validation.Common;
import com.test.calculator.core.common.validation.OnUpdate;
import com.test.calculator.core.domain.ship.Ship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class ShipLoad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = OnUpdate.class, message = "validation.error.entity.notnull.id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Ship ship;

    @Column(name = "load", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Float load;
}
