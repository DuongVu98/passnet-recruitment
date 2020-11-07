package com.iucse.passnet.recruitment.domain.aggregate.job.vos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CourseName {
    private String value;
}
