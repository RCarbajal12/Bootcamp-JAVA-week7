package com.romina.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.romina.dojooverflow.models.QuestionTagModel;

@Repository
public interface QuestionTagRepo extends CrudRepository<QuestionTagModel, Long> {

}
