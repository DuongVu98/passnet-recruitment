package com.iucse.passnet.recruitment.usecase.interactors.queries;

import com.iucse.passnet.recruitment.domain.dto.Teacher;
import com.iucse.passnet.recruitment.domain.mappers.ModelMapper;
import com.iucse.passnet.recruitment.domain.models.UserModel;
import com.iucse.passnet.recruitment.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ViewTeacherProfileQuery implements ActionQuery<Teacher>{

    private final String teacherId;
    private final UserRepository userRepository;
    private final ModelMapper<UserModel, Teacher, String> teacherModelMapper;

    private Teacher viewTeacherProfile(){
        UserModel userModel = userRepository.findFirstById(teacherId);
        return teacherModelMapper.mapToDto(userModel);
    }

    @Override
    public Teacher execute() {
        return viewTeacherProfile();
    }
}
