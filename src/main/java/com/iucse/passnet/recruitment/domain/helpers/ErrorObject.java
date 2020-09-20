package com.iucse.passnet.recruitment.domain.helpers;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {
    private String errorCode;
    private String errorDescription;
}
