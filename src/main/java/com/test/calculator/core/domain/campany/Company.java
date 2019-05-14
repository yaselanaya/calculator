package com.test.calculator.core.domain.campany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.test.calculator.core.common.validation.Common;
import com.test.calculator.core.common.validation.OnUpdate;
import com.test.calculator.core.domain.negotiation.Negotiation;
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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = OnUpdate.class, message = "validation.error.entity.notnull.id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany
    @JoinColumn(name = "origin_company_id")
    @JsonManagedReference("originCompany")
    private Collection<Negotiation> originNegotiations;

    @OneToMany
    @JoinColumn(name = "destination_company_id")
    @JsonManagedReference("destinationCompany")
    private Collection<Negotiation> destinationNegotiations;
}
