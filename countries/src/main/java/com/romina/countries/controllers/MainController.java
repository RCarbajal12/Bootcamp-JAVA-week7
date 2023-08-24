package com.romina.countries.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.romina.countries.services.ApiService;

@Controller
public class MainController {
	
	private final ApiService apiService;
	public MainController(ApiService cSer) {
		this.apiService = cSer;
	}
	
	@GetMapping("/")
    public String uno(Model viewModel) {
        List<Object[]> table = apiService.primerPregunta();
        viewModel.addAttribute("primeraTabla", table);
        return "uno.jsp";
    }
	
	@GetMapping("/dos")
    public String dos(Model viewModel) {
        List<Object[]> table = apiService.segundaPregunta();
        viewModel.addAttribute("segundaTabla", table);
        return "dos.jsp";
    }
	
	@GetMapping("/tres")
    public String tres(Model viewModel) {
        List<Object[]> table = apiService.tercerPregunta();
        viewModel.addAttribute("tercerTabla", table);
        return "tres.jsp";
    }
	
	@GetMapping("/cuatro")
    public String cuatro(Model viewModel) {
        List<Object[]> table = apiService.cuartaPregunta();
        viewModel.addAttribute("cuartaTabla", table);
        return "cuatro.jsp";
    }
	
	@GetMapping("/cinco")
    public String cinco(Model viewModel) {
        List<Object[]> table = apiService.quintaPregunta();
        viewModel.addAttribute("quintaTabla", table);
        return "cinco.jsp";
    }
	
	@GetMapping("/seis")
    public String seis(Model viewModel) {
        List<Object[]> table = apiService.sextaPregunta();
        viewModel.addAttribute("sextaTabla", table);
        return "seis.jsp";
    }
	
	@GetMapping("/siete")
    public String siete(Model viewModel) {
        List<Object[]> table = apiService.septimaPregunta();
        viewModel.addAttribute("septimaTabla", table);
        return "siete.jsp";
    }
	
	@GetMapping("/ocho")
    public String ocho(Model viewModel) {
        List<Object[]> table = apiService.octavaPregunta();
        viewModel.addAttribute("octavaTabla", table);
        return "ocho.jsp";
    }
	
	
	

}
