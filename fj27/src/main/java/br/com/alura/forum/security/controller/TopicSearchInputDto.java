package br.com.alura.forum.security.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.forum.model.topic_domain.Topic;
import br.com.alura.forum.model.topic_domain.TopicStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicSearchInputDto {

	private TopicStatus status;
	private String categoryName;

	public Specification<Topic> build() {
		return (root, criteriaQuery, criteriaBuilder) -> {
			ArrayList<Predicate> predicates = new ArrayList<>();

			validateIsPresent(root, criteriaBuilder, predicates);

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};

	}

	private void validateIsPresent(Root<Topic> root, CriteriaBuilder criteriaBuilder, ArrayList<Predicate> predicates) {

		Optional<TopicStatus> statusFilter = Optional.ofNullable(status);
		Optional<String> categoria = Optional.ofNullable(categoryName);

		statusFilter.ifPresent(status -> predicates.add(criteriaBuilder.equal(root.get("status"), status)));

		categoria.ifPresent(categoryName -> {
			Path<String> categoryNamePath = root.get("course").get("subcategory").get("category").get("name");
			predicates.add(criteriaBuilder.equal(categoryNamePath, categoryName));
		});
	}

}
