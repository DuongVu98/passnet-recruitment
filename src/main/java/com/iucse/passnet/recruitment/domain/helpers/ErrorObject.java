package com.iucse.passnet.recruitment.domain.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {
	private String errorCode;
	private String errorDescription;
}
