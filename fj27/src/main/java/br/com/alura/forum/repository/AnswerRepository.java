package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import br.com.alura.forum.model.Answer;

public interface AnswerRepository extends Repository<Answer, Long>, JpaSpecificationExecutor<Answer>{

	public Answer save(Answer answer);
}
