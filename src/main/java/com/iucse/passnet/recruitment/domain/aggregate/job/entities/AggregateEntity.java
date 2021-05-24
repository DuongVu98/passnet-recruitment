package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

public interface AggregateEntity<ID> {
	boolean equal(ID id);
}
