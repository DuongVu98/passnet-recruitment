package com.iucse.passnet.recruitment.domain.aggregate.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationId implements Serializable {
    private String value;
}
