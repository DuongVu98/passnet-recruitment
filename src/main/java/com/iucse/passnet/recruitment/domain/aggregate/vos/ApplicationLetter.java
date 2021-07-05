package com.iucse.passnet.recruitment.domain.aggregate.vos;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ApplicationLetter {
	private String value;
}
