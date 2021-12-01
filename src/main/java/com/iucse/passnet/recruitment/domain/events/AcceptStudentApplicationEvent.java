package com.iucse.passnet.recruitment.domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptStudentApplicationEvent {
    private String eventId;
    private String jobId;
    private String taId;
}
