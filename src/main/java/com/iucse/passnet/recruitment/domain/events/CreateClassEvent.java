package com.iucse.passnet.recruitment.domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassEvent {
    private String eventId;
    private String teacherId;
    private List<String> taIdList;
}
