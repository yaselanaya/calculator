package com.test.calculator.core.domain.negotiation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.test.calculator.core.common.validation.Common;
import com.test.calculator.core.common.validation.OnUpdate;
import com.test.calculator.core.domain.campany.Company;
import com.test.calculator.core.domain.destination.Destination;
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
public class Negotiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = OnUpdate.class, message = "validation.error.entity.notnull.id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "origin_company_id")
    @JsonBackReference("originCompany")
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Company originCompany;

    @ManyToOne
    @JoinColumn(name = "destination_company_id")
    @JsonBackReference("destinationCompany")
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Company destinationCompany;

    @OneToMany(mappedBy = "negotiation")
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Collection<ShipLoad> shipLoads;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Destination destination;
}
