package com.test.calculator.core.domain.campany;

import com.test.calculator.core.common.Common;
import com.test.calculator.core.common.OnUpdate;
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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = OnUpdate.class, message = "validation.error.company.notnull.id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(groups = Common.class, message = "validation.error.company.notnull.name")
    private String name;

    @Column(name = "description")
    private String description;
}
