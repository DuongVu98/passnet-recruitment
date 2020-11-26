package com.iucse.passnet.recruitment.domain.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@AllArgsConstructor
public class CacheableView {

    @Id
    protected String id;

    @TimeToLive(unit = TimeUnit.SECONDS)
    protected Long timeToLive;
}
