package com.iucse.passnet.recruitment.usecase.mapper;

import com.iucse.passnet.recruitment.domain.aggregate.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.views.JobApplicationLiteView;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JobApplicationMapper {
    @NonNull
    JobApplication instance;

    public JobApplicationView toApplicationView(){
        return JobApplicationView.builder()
           .letter(instance.getLetter().getValue())
           .content(instance.getContent().getValue())
           .state(instance.getApplicationState().name())
           .studentId(instance.getApplicationOwner().getValue())
           .build();
    }

    public JobApplicationLiteView toLiteView(){
        return JobApplicationLiteView.builder()
           .studentId(instance.getApplicationOwner().getValue())
           .applicationState(instance.getApplicationState().name())
           .build();
    }
}
