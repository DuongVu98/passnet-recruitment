package com.iucse.passnet.recruitment.usecase.services;

import com.iucse.passnet.recruitment.domain.commands.AcceptJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.commands.RemoveJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.compensating.AcceptJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.domain.compensating.RemoveJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.exceptions.WrongCommandTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensatingCommandProvider {
	private final UUIDGeneratorService uuidGeneratorService;

	@Autowired
	public CompensatingCommandProvider(UUIDGeneratorService uuidGeneratorService) {
		this.uuidGeneratorService = uuidGeneratorService;
	}

	public CompensatingCommand build(BaseCommand baseCommand) {
		if (baseCommand instanceof AcceptJobApplicationCommand) {
			return this.build((AcceptJobApplicationCommand) baseCommand);
		} else if (baseCommand instanceof RemoveJobApplicationCommand) {
			return this.build((RemoveJobApplicationCommand) baseCommand);
		} else {
			throw new WrongCommandTypeException("transaction command is not included.");
		}
	}

	private AcceptJobApplicationCompensating build(AcceptJobApplicationCommand baseCommand) {
		return new AcceptJobApplicationCompensating(
			this.uuidGeneratorService.generate().toString(),
			baseCommand.getJobId(),
			baseCommand.getJobApplicationId()
		);
	}

	private RemoveJobApplicationCompensating build(RemoveJobApplicationCommand baseCommand) {
		return new RemoveJobApplicationCompensating(
			this.uuidGeneratorService.generate().toString(),
			baseCommand.getJobId(),
			baseCommand.getJobApplicationId()
		);
	}
}
