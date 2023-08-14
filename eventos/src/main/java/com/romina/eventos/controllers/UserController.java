package com.romina.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.romina.eventos.models.EventModel;
import com.romina.eventos.models.LogReg;
import com.romina.eventos.models.Provincias;
//import com.romina.eventos.models.Provincias;
import com.romina.eventos.models.User;
import com.romina.eventos.services.EventService;
import com.romina.eventos.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	//INYECCION DE DEPENDENCIAS
	private final UserService userServ;
	private final EventService eventService;
	public UserController(UserService uSer, EventService eServ) {
		this.userServ = uSer;
		this.eventService = eServ;
	}
	
	@GetMapping("/")
	public String raiz(Model viewModel) {
		viewModel.addAttribute("user", new User());
		viewModel.addAttribute("login", new LogReg());
		viewModel.addAttribute("provincias", Provincias.provincias);
		return "loginreg.jsp";
	}
	
	
	@PostMapping("/registration")
	public String registro(@Valid @ModelAttribute("user") User usuario,
			BindingResult resultado, Model viewModel ) {
		if(resultado.hasErrors()) {
			viewModel.addAttribute("login", new LogReg());
			viewModel.addAttribute("provincias", Provincias.provincias);
			return "loginreg.jsp";
		}
		User usuarioRegistrado = userServ.registroUsuario(usuario, resultado);
		viewModel.addAttribute("login", new LogReg());
		if(usuarioRegistrado != null) {
			viewModel.addAttribute("registro", "Gracias por registrarte, ahora login por favor");
		}
		return "loginreg.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login") LogReg loginuser,
			BindingResult resultado, Model viewModel, HttpSession sesion) {
		if (resultado.hasErrors()) {
			viewModel.addAttribute("user", new User());
			return "loginreg.jsp";
		}
		
		if(userServ.authenthicateUser(
				loginuser.getEmail(), 
				loginuser.getPassword(), 
				resultado )) {
			User usuarioLog = userServ.encontrarPorEmail(loginuser.getEmail());
			sesion.setAttribute("userID",usuarioLog.getId());
			return "redirect:/events";
		}else {
			viewModel.addAttribute("errorLog", "Por favor intenta de nuevo");
			viewModel.addAttribute("user", new User());
			return "loginreg.jsp";
		}
		
	}
	
	@GetMapping("/events")
	public String bienvenida(@ModelAttribute("evento") EventModel evento,
			BindingResult resultado,
			HttpSession sesion, Model viewModel) {
		Long userId =  (Long) sesion.getAttribute("userID");
		if(userId == null ) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("provincias", Provincias.provincias);
		viewModel.addAttribute("eventosPronvinciaUser", eventService.eventoProvinciaUsuario(usuario.getProvincia()) );
		viewModel.addAttribute("eventosNoPronvinciaUser", eventService.eventoNoProvinciaUsuario(usuario.getProvincia()) );
		return "dashboard.jsp";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.setAttribute("userID", null);
		return "redirect:/";
	}

}