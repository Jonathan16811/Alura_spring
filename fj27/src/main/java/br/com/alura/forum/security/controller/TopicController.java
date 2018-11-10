package br.com.alura.forum.security.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.security.controller.dto.input.NewTopicInputDto;
import br.com.alura.forum.security.controller.dto.output.TopicOutputDto;
import br.com.alura.forum.validator.NewTopicCustomValidator;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<TopicBriefOutputDto> listTopics(TopicSearchInputDto topicSearch,
			@PageableDefault(sort = "creationInstant", direction = Sort.Direction.DESC) Pageable pageRequest) {

		Specification<Topic> topicSearchSpecification = topicSearch.build();
		Page<Topic> topics = this.topicRepository.findAll(topicSearchSpecification, pageRequest);
		return TopicBriefOutputDto.listFromTopics(topics);

	}

	@GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<TopicBriefDto> listTestes(TopicSearchInputDto topicSearch,
			@PageableDefault(sort = "creationInstant", direction = Sort.Direction.DESC) Pageable pageRequest) {

		Specification<Topic> topicSearchSpecification = topicSearch.build();
		Page<Topic> topics = this.topicRepository.findAll(topicSearchSpecification, pageRequest);
		return TopicBriefDto.listFromTopics(topics);

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicOutputDto> createTopic(@Valid @RequestBody NewTopicInputDto newTopicInputDto,
			@AuthenticationPrincipal User loggerdUser, UriComponentsBuilder uriBuilder) {

		Topic topic = newTopicInputDto.build(loggerdUser, this.courseRepository);
		this.topicRepository.save(topic);

		URI path = uriBuilder.path("/api/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(path).body(new TopicOutputDto(topic));
	}

	@InitBinder("newTopicInputDto")
	public void initBinder(WebDataBinder binder, @AuthenticationPrincipal User loggedUser) {
		binder.addValidators(new NewTopicCustomValidator(this.topicRepository, loggedUser));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TopicBriefOutputDto findTopic(@PathVariable(value = "id") Long id) {

		return this.topicRepository.findById(id);
		
	}

}
