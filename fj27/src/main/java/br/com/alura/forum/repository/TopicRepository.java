package br.com.alura.forum.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.security.controller.TopicBriefOutputDto;

public interface TopicRepository extends Repository<Topic, Long>, JpaSpecificationExecutor<Topic>{
	
	@Query("select t from Topic t")
	List<Topic> list();

	void save(Topic topic);

	List<Topic> findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(User loggedUser, Instant oneHourAgo);

	TopicBriefOutputDto findById(Long id);

	
	
}
