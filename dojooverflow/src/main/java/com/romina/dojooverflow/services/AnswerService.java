package com.romina.dojooverflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romina.dojooverflow.models.AnswerModel;
import com.romina.dojooverflow.repositories.AnswerRepo;

@Service
public class AnswerService {
	
	//INYECCION DE DEPENDENCIAS
	private AnswerRepo answerRepo;
	public AnswerService(AnswerRepo aRepo) {
		this.answerRepo = aRepo;
	}
	
	//CREAR UNA RESPUESTA
	public AnswerModel crearRespuesta(AnswerModel answer) {
		return answerRepo.save(answer);
	}
	
	//VINCULAR RESPUESTA A PREGUNTA
	public List<AnswerModel> respuestaAPregunta(Long questionId){
		return answerRepo.findAllByQuestionId(questionId);
	}
}
