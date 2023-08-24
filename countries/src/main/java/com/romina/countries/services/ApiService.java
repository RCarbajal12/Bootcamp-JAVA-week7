package com.romina.countries.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romina.countries.repositories.CitiesRepo;
import com.romina.countries.repositories.CountriesRepo;

@Service
public class ApiService {
	
	private final CountriesRepo countriesRepo;
	private final CitiesRepo citiesRepo;
	public ApiService(CountriesRepo coRe, CitiesRepo ciRe) {
		this.countriesRepo = coRe;
		this.citiesRepo = ciRe;
	}

	public List<Object[]> primerPregunta(){
		return countriesRepo.hablanSloveno();
	}
	
	public List<Object[]> segundaPregunta(){
		return countriesRepo.ciudadesPorPais();
	}
	
	public List<Object[]> tercerPregunta(){
		return citiesRepo.ciudadesMexicoPoblacion();
	}
	
	public List<Object[]> cuartaPregunta(){
		return countriesRepo.porcentajePais();
	}
	
	public List<Object[]> quintaPregunta(){
		return countriesRepo.superficiePaisesPoblación();
	}
	
	public List<Object[]> sextaPregunta(){
		return countriesRepo.paisesPorMonarquiaSuperficieVida();
	}
	
	public List<Object[]> septimaPregunta(){
		return citiesRepo.ciudadesArgentinaBsAs();
	}
	
	public List<Object[]> octavaPregunta(){
		return countriesRepo.sumPaisesRegion();
	}
	
	

}
