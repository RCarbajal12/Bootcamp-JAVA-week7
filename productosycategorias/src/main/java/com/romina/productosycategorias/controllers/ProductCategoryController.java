package com.romina.productosycategorias.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.romina.productosycategorias.models.ProductCategoryModel;
import com.romina.productosycategorias.services.MainService;

@Controller
public class ProductCategoryController {
	
	//INYECTAR DEPENDECIAS
	private final MainService mainServ;
	public ProductCategoryController(MainService mainS) {
		this.mainServ = mainS;
	}
	

	@PostMapping("/aso/{idProducto}")
	public String asociarProductoCategoria(@PathVariable("idProducto") Long idProducto,
			@ModelAttribute("asociacion") ProductCategoryModel productosCategorias,
			Model viewModel, BindingResult resultado) {
		
		if(resultado.hasErrors()) {
			return "showproduct.jsp";
		}
		mainServ.crearVinculo(productosCategorias);
		return "redirect:/product/"+idProducto;
		
	}
//	@PostMapping("/aso/categories/{idCategoria}")
//	public String asociarProductoCategorias(@PathVariable("idCategoria") Long idCategoria,
//			@ModelAttribute("asociacion") ProductCategoryModel productosCategorias,
//			Model viewModel, BindingResult resultado) {
//		
//		if(resultado.hasErrors()) {
//			return "showcategory.jsp";
//		}
//		mainServ.crearVinculo(productosCategorias);
//		return "redirect:/categories/"+idCategoria;
//		
//	}
	@PostMapping("/ase/{idCategoria}")
	public String asociarProductoCategorias(@PathVariable("idCategoria") Long idCategoria,
			@ModelAttribute("asociaciones") ProductCategoryModel productosCategorias,
			Model viewModel, BindingResult resultado) {
		
		if(resultado.hasErrors()) {
			return "showcategory.jsp";
		}
		mainServ.crearVinculo(productosCategorias);
		return "redirect:/categories/"+idCategoria;
		
	}
	
	
	
}