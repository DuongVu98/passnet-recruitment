package com.iucse.passnet.recruitment.domain.views;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("posted_jobs_view")
public class PostedJobsView extends CacheableView {
	private List<LiteJobView> litePostedJobs;

	@Builder
	public PostedJobsView(String id, List<LiteJobView> litePostedJobs) {
		super(id);
		this.litePostedJobs = litePostedJobs;
	}
}
