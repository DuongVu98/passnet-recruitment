package com.iucse.passnet.recruitment.domain.aggregate.job.vos;

public interface ValueObject<T> {
	boolean equal(T t);
}
