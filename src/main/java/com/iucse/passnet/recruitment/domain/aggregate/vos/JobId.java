package com.iucse.passnet.recruitment.domain.aggregate.vos;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JobId implements Serializable {
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobId jobId = (JobId) o;

        return Objects.equals(value, jobId.value);
    }

    @Override
    public int hashCode() {
        return 2030246044;
    }
}
