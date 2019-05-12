package com.test.calculator.core.web.campany.dto;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "company")
public class CompanyResource  extends ResourceSupport {
    private Long companyId;
    private String name;
    private String description;
}
