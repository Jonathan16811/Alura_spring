package br.com.alura.forum.security.controller.dto.input;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.domain.Page;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.model.topic_domain.TopicStatus;

public class FindOutputDto {

	private Long id;
	private String shortDescription;
	private String content;
	private TopicStatus status;
	private long creationInstant;
	private long lastUpdate;
	private String courseName;
	private String subcategory;
	private String categoryName;
	private String owerName;
	private int numberOfResponses;
	@OneToMany(mappedBy = "topic")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Answer> answers = new ArrayList<>();
	
	public static Page<FindOutputDto> listFromTopics(Page<Topic> topics){
		return topics.map(FindOutputDto::new);		
	}

	public FindOutputDto(Topic topic) {
		this.id = topic.getId();
		this.shortDescription = topic.getShortDescription();
		this.content = topic.getContent();
		this.status = topic.getStatus();
		this.creationInstant = getCreationInstant(topic.getCreationInstant());
		this.lastUpdate = getLastUpdate(topic.getLastUpdate());
		this.courseName = topic.getCourse().getName();
		this.subcategory = topic.getCourse().getSubcategory().getName();
		this.categoryName = topic.getCourse().getCategoryName();
		this.owerName = topic.getOwner().getName();
		this.numberOfResponses = topic.getNumberOfAnswers();
		this.answers = topic.getAnswers();
	}

	public long getCreationInstant(Instant instant) {
		return creationInstant;
	}

	public void setCreationInstant(long creationInstant) {
		this.creationInstant = creationInstant;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public long getSecondsSinceLastUpdate() {
		return lastUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public long getLastUpdate(Instant instant) {
		return Duration.between(instant, Instant.now()).get(ChronoUnit.SECONDS);
	}

	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getOwerName() {
		return owerName;
	}

	public void setOwerName(String owerName) {
		this.owerName = owerName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubcatetoryName() {
		return subcategory;
	}

	public void setSubcatetoryName(String subcatetoryName) {
		this.subcategory = subcatetoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getNumberOfResponses() {
		return numberOfResponses;
	}

	public void setNumberOfResponses(int numberOfResponses) {
		this.numberOfResponses = numberOfResponses;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public long getCreationInstant() {
		return creationInstant;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
