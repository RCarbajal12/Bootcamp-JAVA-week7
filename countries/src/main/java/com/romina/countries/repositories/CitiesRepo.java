package com.romina.countries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.romina.countries.models.Cities;

@Repository
public interface CitiesRepo extends CrudRepository<Cities, Long>{
	
	//PREGUNTA 3: OBTENER TODAS LAS CIUDADES DE MEXICO CON UNA POBLACION  MAYOR A 500.000
	@Query(value="SELECT ci.name AS cities_name, ci.population "
			+"FROM cities ci "
			+"WHERE country_code = 'MEX' "
			+"AND ci.population > 500000 "
			+"ORDER BY ci.population DESC ", nativeQuery=true)
	List<Object[]> ciudadesMexicoPoblacion();
	
	//PREGUNTA 7: OBTENER TODAS LAS CIUDADES DE ARGENTINA DENTRO DEL DISTRITO DE BUENOS AIRES y que tengan una población mayor a 500.000
	@Query(value="SELECT countries.name, cities.name, cities.district, cities.population "
			+"FROM cities "
			+"JOIN countries "
			+"WHERE countries.name = 'Argentina' "
			+"AND cities.district = 'Buenos Aires' "
			+"AND cities.population > 500000 ", nativeQuery=true)
	List<Object[]> ciudadesArgentinaBsAs();
	
}

