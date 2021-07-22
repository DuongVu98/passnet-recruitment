package com.iucse.passnet.recruitment.domain.aggregate.entities;

import com.iucse.passnet.recruitment.domain.aggregate.vos.*;
import javax.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_applications")
@Slf4j(topic = "[JobApplication]")
public class JobApplication extends BaseEntity {

	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private JobApplicationId id;

	@Setter
	@ToString.Exclude
	@JoinColumn(name = "job_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Job job;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "application_content"))
	private Content content;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "application_letter"))
	private ApplicationLetter letter;

	@Enumerated(EnumType.STRING)
	@Column(name = "application_state")
	private ApplicationStates applicationState;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "application_owner"))
	private ProfileId applicationOwner;

	public void applyJob(Job job) {
		this.job = job;
	}

	public void accepted() {
		if (this.applicationState.equals(ApplicationStates.PENDING)) {
			log.info("change state!");
			this.applicationState = ApplicationStates.ACCEPTED;
		}
	}

	public void removed() {
		if (this.applicationState.equals(ApplicationStates.PENDING)) {
			log.info("change state!");
			this.applicationState = ApplicationStates.PENDING;
		}
	}
}
