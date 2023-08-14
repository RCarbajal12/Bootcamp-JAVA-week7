package com.romina.eventos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romina.eventos.models.EventModel;
import com.romina.eventos.models.MensajeModel;
import com.romina.eventos.models.User;
import com.romina.eventos.repositories.EventRepo;
import com.romina.eventos.repositories.MensajeRepo;

@Service
public class EventService {
	
	//INYECCION DE DEPENDENCIA
	private final EventRepo eventRepo;
	private final MensajeRepo mensajeRepo;
	
	//CONSTRUCTOR
	public EventService(EventRepo eRe, MensajeRepo mRe) {
		this.eventRepo = eRe;
		this.mensajeRepo = mRe;
	}
	
	//CREAR EVENTO
	public EventModel crearEvento(EventModel evento) {
		return eventRepo.save(evento);
	}
	
	//EDITAR EVENTO
	public EventModel actualizarEvento(EventModel evento) {
		return eventRepo.save(evento);
	}
	
	//ELIMINAR EVENTO
	public void borrarEvento(Long id) {
		eventRepo.deleteById(id);
	}
	
	//UNIRSE O CANCELAR ASISTENCIA A EVENTO
	public void unirseCancelarEvento(EventModel evento, User usuario, boolean asistencia) {
		if(asistencia) {
			evento.getAsistentes().add(usuario);
		}else {
			evento.getAsistentes().remove(usuario);
		}
		eventRepo.save(evento);
	}
	
	
	public List<EventModel> eventoProvinciaUsuario(String provincia){
		return eventRepo.findByProvincia(provincia);
	}
	
	public List<EventModel> eventoNoProvinciaUsuario(String provincia){
		return eventRepo.findByProvinciaIsNot(provincia);
	}
	
	public EventModel unEvento(Long id) {
		return eventRepo.findById(id).orElse(null);
	}
	
	public MensajeModel mostrarMensaje(Long id) {
		return mensajeRepo.findById(id).orElse(null);
	}
	
	//CREAR UN MENSAJE
	public void agregarComentario(User usuario, EventModel evento, String comentario) {
		MensajeModel mensaje = new MensajeModel(usuario, evento, comentario);
		mensajeRepo.save(mensaje);
	}

}