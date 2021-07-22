package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;

public interface CommandExecutor {
    Job execute(BaseCommand command);
}
