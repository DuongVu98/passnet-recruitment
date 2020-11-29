package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

import com.iucse.passnet.recruitment.domain.aggregate.job.vos.ValueObject;

public interface AggregateEntity<ID> {
	boolean equal(ID id);
}
