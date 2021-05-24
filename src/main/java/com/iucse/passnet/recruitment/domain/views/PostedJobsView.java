package com.iucse.passnet.recruitment.domain.views;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "posted_jobs_view", timeToLive = 10)
public class PostedJobsView extends CacheableView {
	@Setter
	private List<LiteJobView> litePostedJobs;

	@Builder
	public PostedJobsView(String id, List<LiteJobView> litePostedJobs) {
		super(id);
		this.litePostedJobs = litePostedJobs;
	}
}
