package com.iucse.passnet.recruitment.domain.events;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateClassEvent {
	private String eventId;
	private String teacherId;
	private List<String> taIdList;
}
