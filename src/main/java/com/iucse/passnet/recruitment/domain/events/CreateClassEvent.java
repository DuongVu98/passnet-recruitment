package com.iucse.passnet.recruitment.domain.events;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CreateClassEvent {
    private String eventId;
    private String teacherId;
    private List<String> taIdList;
}
