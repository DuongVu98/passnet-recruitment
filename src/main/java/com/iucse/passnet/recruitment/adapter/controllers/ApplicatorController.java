package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.dto.Teacher;
import com.iucse.passnet.recruitment.usecase.interactors.InteractorFactory;
import com.iucse.passnet.recruitment.usecase.interactors.commands.ActionCommand;
import com.iucse.passnet.recruitment.usecase.interactors.queries.ActionQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[ApplicatorController]")
public class ApplicatorController {

    private final InteractorFactory interactorFactory;

    @Autowired
    public ApplicatorController(InteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
    }

    public void studentApplyJob(String studentId, String jobId){
        ActionCommand command = this.interactorFactory.getStudentApplyJobCommand(studentId, jobId);
        command.execute();
    }

    public Teacher viewTeacherProfile(String teacherId){
        ActionQuery<Teacher> query = this.interactorFactory.getViewTeacherProfile(teacherId);
        return query.execute();
    }

    public Job viewPostedJob(String jobId){
        ActionQuery<Job> query = this.interactorFactory.getViewPostedJob(jobId);
        return query.execute();
    }
}
