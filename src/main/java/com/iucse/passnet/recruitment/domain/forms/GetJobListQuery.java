package com.iucse.passnet.recruitment.domain.forms;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetJobListQuery {
    @NonNull
    List<String> jobIds;
}
