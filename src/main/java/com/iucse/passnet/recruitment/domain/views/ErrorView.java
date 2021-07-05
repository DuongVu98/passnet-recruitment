package com.iucse.passnet.recruitment.domain.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorView {

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;
}
