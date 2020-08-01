package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.usecase.interactors.InteractorFactory;
import com.iucse.passnet.recruitment.usecase.interactors.commands.ActionCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterController {

    private final InteractorFactory interactorFactory;

    @Autowired
    public RecruiterController(InteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
    }

    public List<Job> getAllPostedJobs(){
        return null;
    }
    public void postJob(String teacherId, Job newJob){
        ActionCommand command = interactorFactory.getTeacherPostJobCommand(teacherId, newJob);
        command.execute();
    }

    public void acceptApplicants(String studentId){

    }
}
