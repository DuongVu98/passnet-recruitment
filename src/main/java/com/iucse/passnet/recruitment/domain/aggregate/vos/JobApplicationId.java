package com.iucse.passnet.recruitment.domain.aggregate.vos;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JobApplicationId implements Serializable {
	private String value;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		JobApplicationId that = (JobApplicationId) o;

		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return 1015828324;
	}
}
