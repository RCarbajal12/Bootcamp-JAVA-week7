package com.romina.dojooverflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romina.dojooverflow.models.QuestionsModel;
import com.romina.dojooverflow.repositories.QuestionRepo;


@Service
public class QuestionService {
	
	//INYECCION DE DEPENDENCIAS
	private final QuestionRepo questionRepo;
	
	public QuestionService(QuestionRepo qRepo) {
		this.questionRepo = qRepo;
	}
	
	//CREAR UNA PREGUNTA
	public QuestionsModel crearPregunta(QuestionsModel question) {
		return questionRepo.save(question);
	}
	
	//MOSTRAR TODAS LAS PREGUNTAS
	public List<QuestionsModel> allPreguntas() {
		return (List<QuestionsModel>) questionRepo.findAll();
	}
	
	//MOSTRAR PREGUNTA POR ID
	public QuestionsModel encontrarQuestionPorId(Long id) {
		return questionRepo.findById(id).orElse(null);
	}
	
}