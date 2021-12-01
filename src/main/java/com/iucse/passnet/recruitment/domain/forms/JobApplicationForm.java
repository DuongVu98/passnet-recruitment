package com.iucse.passnet.recruitment.domain.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationForm {
    @NotBlank(message = "letter should not be empty")
    @Size(min = 10, message = "letter should not be less than 10")
    private String letter;

    @NotBlank(message = "letter should not be empty")
    @Size(min = 20, message = "content should not less than 20")
    private String content;
}
