package com.romina.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.romina.eventos.models.EventModel;
import com.romina.eventos.models.Provincias;
import com.romina.eventos.models.User;
import com.romina.eventos.services.EventService;
import com.romina.eventos.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class EventController {
	
	//INYECCION DE DEPENDENCIAS
	private final EventService eventService;
	private final UserService userServ;
	public EventController(EventService eSer,  UserService uSer) {
		this.eventService = eSer;
		this.userServ = uSer;
	}
	
	//NUEVO EVENTO 
	@PostMapping("/nuevo/evento")
	public String crearEvento(@Valid @ModelAttribute("evento") EventModel evento, 
			BindingResult resultado, HttpSession sesion, Model viewModel) {
		//VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId =  (Long) sesion.getAttribute("userID");
		if(userId == null ) {
			return "redirect:/";
		}
		if (resultado.hasErrors()) {
			User usuario = userServ.encontrarUserPorId(userId);
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("provincias", Provincias.provincias);
			return "dashboard.jsp";
		}
		eventService.crearEvento(evento);
		return "redirect:/events";
	}
	
	//EDITAR EVENTO 
	@GetMapping("events/{idEvento}/edit")
	public String formEdicionEvento(@PathVariable("idEvento") Long idEvento, 
			@ModelAttribute("evento") EventModel evento, HttpSession sesion,
			Model viewModel){
		//VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId =  (Long) sesion.getAttribute("userID");
		if(userId == null ) {
			return "redirect:/";
		}
		EventModel unEvento = eventService.unEvento(idEvento);
		if(unEvento == null) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("provincias", Provincias.provincias);
		viewModel.addAttribute("evento", unEvento);
		
		return "edit.jsp";
	}

	@PutMapping("/events/{id}/edit")
	public String editarEvento(@Valid @ModelAttribute("evento") EventModel evento,
			BindingResult resultado, @PathVariable("id") Long idEvento, HttpSession sesion,
			Model viewModel) {
		//VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
				Long userId =  (Long) sesion.getAttribute("userID");
				if(userId == null ) {
					return "redirect:/";
				}
				User usuario = userServ.encontrarUserPorId(userId);
				if(resultado.hasErrors()) {
					viewModel.addAttribute("usuario", usuario);
					viewModel.addAttribute("provincias", Provincias.provincias);
					
					return "edit.jsp";
				}
				eventService.actualizarEvento(evento);
				return "redirect:/events";
	}
	
	//BORRAR EVENTO 
	@DeleteMapping("/events/{id}/delete")
	public String borrarEvento(@PathVariable("id")Long idEvento){
		eventService.borrarEvento(idEvento);
		return "redirect:/events";
	}
	
	//UNIRME/CANCELAR AL EVENTO
	@GetMapping("/event/{idEvento}/{idUsuario}/{opcion}")
	public String asistirCancelarEvento(@PathVariable("idEvento")Long idEvento,
			@PathVariable("idUsuario") Long idUsuario, @PathVariable("opcion") String opcion,
			HttpSession sesion) {
		//VALIDAR SI LA SESION DEL USUARIO ESTA ACTIVA
		Long userId =  (Long) sesion.getAttribute("userID");
		if(userId == null ) {
			return "redirect:/";
		}
		EventModel unEvento = eventService.unEvento(idEvento);
		boolean unirseCancelar = (opcion.equals("unirse"));
		User unUsuario = userServ.encontrarUserPorId(userId);
		
		eventService.unirseCancelarEvento(unEvento, unUsuario, unirseCancelar);
		
		return "redirect:/events";
	}
	
	//MOSTRAR INFORMACIÓN DEL EVENTO 
	@GetMapping("/events/{idEvento}")
	public String mostrarEvento(Model viewModel, @PathVariable("idEvento") Long idEvento, HttpSession sesion) {
		//VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		viewModel.addAttribute("evento", eventService.unEvento(idEvento));
		viewModel.addAttribute("mensajes", eventService.mostrarMensaje(idEvento));
		return "show.jsp";
	}
	
	//ENVIAR COMENTARIO
	@PostMapping("/events/{idEvento}/comentario")
	public String agregarComentario(@PathVariable("idEvento") Long idEvento, @RequestParam("comment") String comentario,
			HttpSession sesion, RedirectAttributes errores) {
		//VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		if(comentario.equals("")) {
			errores.addFlashAttribute("error", "Por favor no envias mensajes vacios");
			return "redirect:/events";
		}
		EventModel unEvento = eventService.unEvento(idEvento);
		User usuario = userServ.encontrarUserPorId(userId);
		
		eventService.agregarComentario(usuario, unEvento, comentario);

		return "redirect:/events/"+idEvento;
	}
	

}