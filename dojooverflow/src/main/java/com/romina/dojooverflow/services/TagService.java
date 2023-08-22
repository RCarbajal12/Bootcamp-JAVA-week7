package com.romina.dojooverflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romina.dojooverflow.models.TagsModel;
import com.romina.dojooverflow.repositories.TagRepo;

@Service
public class TagService {
	
	//INYECCION DE DEPENDENCIAS
	private final TagRepo tagRepo;
	public TagService(TagRepo tRepo) {
		this.tagRepo = tRepo;
	}
	
	//CREAR UN TAG 
	public TagsModel crearTag(TagsModel tag) {
		return tagRepo.save(tag);
	}
	
	
	//MOSTRAR PREGUNTA POR ID
	public TagsModel encontrarTagPorId(Long id) {
		return tagRepo.findById(id).orElse(null);
	}

	//BUSCAR TAGS EXISTENTES
	public TagsModel existeTag(String nombreTag) {
        return tagRepo.findBySubject(nombreTag);
    }
	
    public List<TagsModel> obtenerTagsRelacionadosAPregunta(Long idPregunta) {
        return tagRepo.findTagsByQuestionsId(idPregunta);
    }
}

