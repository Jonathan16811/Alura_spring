package br.com.alura.forum.security.controller;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.alura.forum.model.topic_domain.Topic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicBriefDto {

	private String categoryName;
	private List<String> subcategories;
	
	public static Page<TopicBriefDto> listFromTopics(Page<Topic> topics){
		return topics.map(TopicBriefDto::new);
	}
	
	public TopicBriefDto(Topic topic) {
		this.categoryName = topic.getCourse().getCategoryName();
		this.subcategories = topic.getCourse().getSubcategory().getCategory().get().getSubcategoryNames();
	}
}
