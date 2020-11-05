package com.iucse.passnet.recruitment.domain.aggregate.job.vos;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JobId implements Serializable {
    private String value;
}
