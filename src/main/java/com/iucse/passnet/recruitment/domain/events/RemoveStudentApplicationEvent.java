package com.iucse.passnet.recruitment.domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveStudentApplicationEvent {
    private String eventId;
    private String jobId;
    private String taId;
}
