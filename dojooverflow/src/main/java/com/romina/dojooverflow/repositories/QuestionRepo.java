package com.romina.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;

import com.romina.dojooverflow.models.QuestionsModel;

public interface QuestionRepo extends CrudRepository<QuestionsModel, Long> {

}
