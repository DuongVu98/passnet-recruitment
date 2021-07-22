package com.iucse.passnet.recruitment.domain.aggregate.vos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProfileId {
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileId)) return false;
        ProfileId profileId = (ProfileId) o;
        return getValue().equals(profileId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
