package com.romina.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.romina.dojooverflow.models.AnswerModel;

@Repository
public interface AnswerRepo extends CrudRepository<AnswerModel, Long>{
	
	List<AnswerModel> findAllByQuestionId(Long questionId);
	
	

}
