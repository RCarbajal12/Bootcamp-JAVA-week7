package com.romina.eventos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.romina.eventos.models.MensajeModel;


public interface MensajeRepo extends CrudRepository<MensajeModel, Long>{
	
	List<MensajeModel> findAll();

}
