package com.romina.empleadosygerentes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.romina.empleadosygerentes.models.Empleado;
import com.romina.empleadosygerentes.repositories.GerenteRepo;

@Controller
public class MainController {
	
	//INYECCION DE DEPENDENCIAS
	private final GerenteRepo gerenteRepo;
	public MainController(GerenteRepo gRe) {
		this.gerenteRepo = gRe;
	}
	
	@GetMapping("/search/{managerId}")
	public String mostrarEmpleados(@PathVariable("managerId") Long managerId,
			Model viewModel) {
		List<Empleado> mostrarEmpleado = gerenteRepo.findByManagerId(managerId);
		viewModel.addAttribute("empleado", mostrarEmpleado);
		return "";
	}
	
	@GetMapping("/mostrar/{empleadoId}")
	public String mostrarGerente(@PathVariable("empleadoId") Long empleadoId,
			Model viewModel) {
		Optional<Empleado> mostrarUnEmpleado = gerenteRepo.findById(empleadoId);
		if(mostrarUnEmpleado != null) {
			Empleado suGerente = mostrarUnEmpleado.get();
			viewModel.addAttribute("mostrarGerente", suGerente);
		}
		return "";
	}

}
