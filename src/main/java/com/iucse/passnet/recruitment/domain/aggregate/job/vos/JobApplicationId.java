package com.iucse.passnet.recruitment.domain.aggregate.job.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JobApplicationId implements Serializable, ValueObject<JobApplicationId> {
    private String value;

    @Override
    public boolean equal(JobApplicationId applicationId) {
        return this.value.equals(applicationId.value);
    }
}
