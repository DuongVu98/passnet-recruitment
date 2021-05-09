package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;

public interface CompensatingHandler {
	void reverse(CompensatingCommand compensatingCommand);
}
