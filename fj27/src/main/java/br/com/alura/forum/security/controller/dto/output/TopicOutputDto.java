package br.com.alura.forum.security.controller.dto.output;

import java.time.Instant;

import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.model.topic_domain.TopicStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicOutputDto {

	private Long id;
	private String shortDescription;
	private String content;
	private TopicStatus status;
	private int numberOfResponses;
	private Instant lastUpDate;
	private String ownerName;
	private String courseName;
	private String subCategoryName;
	private String categoryName;
	
	public TopicOutputDto(Topic topic) {
		this.id = topic.getId();
		this.shortDescription = topic.getShortDescription();
		this.content = topic.getContent();
		this.status = topic.getStatus();
		this.numberOfResponses = topic.getNumberOfAnswers();
		this.lastUpDate = topic.getLastUpdate();
		this.ownerName = topic.getOwner().getName();
		this.courseName = topic.getCourse().getName();
		this.subCategoryName = topic.getCourse().getSubcategoryName();
		this.categoryName = topic.getCourse().getCategoryName();
	}
	
		
}
