package com.iucse.passnet.recruitment.domain.aggregate.job.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ApplicationState {
    private ApplicationStates value;
}
