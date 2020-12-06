package com.iucse.passnet.recruitment.domain.aggregate.job.vos;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JobId implements Serializable, ValueObject<JobApplicationId> {
	private String value;

	@Override
	public boolean equal(JobApplicationId jobApplicationId) {
		return false;
	}
}
