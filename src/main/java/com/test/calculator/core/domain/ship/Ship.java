package com.test.calculator.core.domain.ship;

import com.test.calculator.core.common.validation.Common;
import com.test.calculator.core.common.validation.OnUpdate;
import com.test.calculator.core.domain.shiplaod.ShipLoad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = OnUpdate.class, message = "validation.error.entity.notnull.id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Float capacity;

    @OneToMany(mappedBy = "ship")
    private Collection<ShipLoad> shipLoads;
}
