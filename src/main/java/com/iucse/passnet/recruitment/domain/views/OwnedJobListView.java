package com.iucse.passnet.recruitment.domain.views;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "owned_job_list_view", timeToLive = 10)
public class OwnedJobListView extends CacheableView {
	@Setter
	private List<LiteJobView> litePostedJobs;

	@Setter
	private String teacherId;

	@Builder
	public OwnedJobListView(String id, List<LiteJobView> litePostedJobs, String teacherId) {
		super(id);
		this.litePostedJobs = litePostedJobs;
		this.teacherId = teacherId;
	}
}
