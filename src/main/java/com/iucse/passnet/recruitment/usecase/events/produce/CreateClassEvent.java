package com.iucse.passnet.recruitment.usecase.events.produce;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CreateClassEvent {
    private String teacherId;
    private List<String> taIdList;
}
