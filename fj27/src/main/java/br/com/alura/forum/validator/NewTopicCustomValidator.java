package br.com.alura.forum.validator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.alura.forum.model.PossibleSpam;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.security.controller.dto.input.NewTopicInputDto;

public class NewTopicCustomValidator implements Validator {

	private static final String LIMITE_EXCEDIDO = "O limite individual de novos topicos foi excedido";
	private final TopicRepository topicRepository;
	private User loggedUser;

	public NewTopicCustomValidator(TopicRepository topicRepository, User loggedUser) {
		this.topicRepository = topicRepository;
		this.loggedUser = loggedUser;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NewTopicInputDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Instant oneHourAgo = Instant.now().minus(1, ChronoUnit.HOURS);
		List<Topic> topics = topicRepository.findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(loggedUser,
				oneHourAgo);
		
		PossibleSpam possibleSpam = new PossibleSpam(topics);
		
		if(possibleSpam.hasTopicLimitExceeded()) {
			long minutesToNextTopic = possibleSpam.minutesToNextTopic(oneHourAgo);
			errors.reject("newTopicinputDto.limit.exceed", new Object[] {minutesToNextTopic}, LIMITE_EXCEDIDO);
		}
	}
}