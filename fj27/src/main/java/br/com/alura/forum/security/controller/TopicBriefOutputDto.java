package br.com.alura.forum.security.controller;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.model.topic_domain.TopicStatus;

public class TopicBriefOutputDto {

	private Long id;
	private String shortDescription;
	private long secondsSinceLastUpdate;
	private String owerName;
	private long creationInstant;
	private String courseName;
	private String subcategory;
	private String categoryName;
	private int numberOfResponses;
	private boolean solved;
	
	public static Page<TopicBriefOutputDto> listFromTopics(Page<Topic> topics){
		return topics.map(TopicBriefOutputDto::new);		
	}

	public TopicBriefOutputDto(Topic topic) {
		this.id = topic.getId();
		this.shortDescription = topic.getShortDescription();
		this.secondsSinceLastUpdate = getSecondsSinceLastUpdate(topic.getLastUpdate());
		this.creationInstant = getCreationInstant(topic.getCreationInstant());
		this.owerName = topic.getOwner().getName();
		this.courseName = topic.getCourse().getName();
		this.subcategory = topic.getCourse().getSubcategory().getName();
		this.categoryName = topic.getCourse().getCategoryName();
		this.numberOfResponses = topic.getNumberOfAnswers();
		this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
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
		return secondsSinceLastUpdate;
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

	public long getSecondsSinceLastUpdate(Instant instant) {
		return Duration.between(instant, Instant.now()).get(ChronoUnit.SECONDS);
	}

	public void setSecondsSinceLastUpdate(long secondsSinceLastUpdate) {
		this.secondsSinceLastUpdate = secondsSinceLastUpdate;
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

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

}
