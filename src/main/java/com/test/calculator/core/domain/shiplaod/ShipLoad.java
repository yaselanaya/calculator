package com.test.calculator.core.domain.shiplaod;

import com.test.calculator.core.common.validation.Common;
import com.test.calculator.core.common.validation.OnUpdate;
import com.test.calculator.core.domain.negotiation.Negotiation;
import com.test.calculator.core.domain.ship.Ship;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ShipLoad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = OnUpdate.class, message = "validation.error.entity.notnull.id")
    private Long id;

    @Column(name = "load", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Float load;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "negotiation_id")
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Negotiation negotiation;
}
