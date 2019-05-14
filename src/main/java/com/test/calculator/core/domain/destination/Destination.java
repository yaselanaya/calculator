package com.test.calculator.core.domain.destination;

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
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = OnUpdate.class, message = "validation.error.entity.notnull.id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "surcharge", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.entity.notnull")
    private Integer surcharge;

    @OneToMany(mappedBy = "destination")
    private Collection<Negotiation> negotiations;
}
