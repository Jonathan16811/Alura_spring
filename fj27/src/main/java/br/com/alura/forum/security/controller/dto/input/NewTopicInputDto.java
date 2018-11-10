package br.com.alura.forum.security.controller.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.repository.CourseRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewTopicInputDto {
	
	@NotBlank
	@Size(min= 10)
	private String shortDescription;
	@NotBlank
	@Size(min= 10)
	private String content;
	@NotEmpty
	private String courseName;

	public Topic build(User owner, CourseRepository courseRepository) {
		Course course = courseRepository.findByName(this.courseName);
		return new Topic(this.shortDescription, this.content, owner, course);
	}
	
}
