package com.romina.countries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.romina.countries.models.Countries;

@Repository
public interface CountriesRepo extends CrudRepository<Countries, Long>{
	
	//PREGUNTA 1: CONSEGUIR PAISES QUE HABLAN SLOVENO
	@Query(value="SELECT c.name, l.language, l.percentage FROM countries c "
			+ "JOIN languages l ON c.code = l.country_code "
			+ "WHERE l.language = 'Slovene' "
			+ "ORDER BY l.percentage DESC; ", nativeQuery=true)
	List<Object[]> hablanSloveno();
	
	
	//PREGUNTA 2: OBTENER NUMERO TOTAL DE CIUDADES POR PAIS
	@Query(value="SELECT c.name, COUNT(ci.id) AS numciudades "
			+"FROM countries c "
			+"LEFT JOIN cities ci ON c.code = ci.country_code "
			+"GROUP BY c.name "
			+"ORDER BY numciudades DESC", nativeQuery=true)
	List<Object[]> ciudadesPorPais();
	
	
	//PREGUNTA 4: OBTENER PORCENTAJE 
	@Query(value="SELECT c.name AS countryName, l.percentage "
			+"FROM countries c JOIN languages l ON c.code = l.country_code "
			+"WHERE l.percentage > 89 "
			+"ORDER BY c.name DESC", nativeQuery=true)
	List<Object[]> porcentajePais();
	
	
	//PREGUNTA 5: OBTENER PAISES POR SUPERFICIFIE Y POBLACION
	@Query(value="SELECT c.name, c.surface_area, c.population "
			+"FROM countries c "
			+"WHERE c.surface_area < 501 AND c.population > 100000 "
			+"ORDER BY c.name DESC", nativeQuery=true)
	List<Object[]> superficiePaisesPoblación();
	
	
	//PREGUNTA 6: OBTENER PAISES CON MONARQUIA,SUPERFICIE MAYOR A 200 Y EXP.VIDA MAYOR A 75
	@Query(value="SELECT c.name, c.government_form, c.surface_area, c.life_expectancy "
			+"FROM countries c "
			+"WHERE c.government_form = 'Constitucional Monarchy' "
			+"AND c.surface_area > 200 AND c.life_expectancy > 75", nativeQuery=true)
	List<Object[]> paisesPorMonarquiaSuperficieVida();
	
	//PREGUNTA 8: SUMAR EL NUMERO DE PAISES EN CADA REGION
	@Query(value="SELECT c.region, COUNT(c.name) "
			+"FROM countries c "
			+"GROUP BY c.region "
			+"ORDER BY COUNT(c.name) DESC ", nativeQuery=true)
	List<Object[]> sumPaisesRegion();
	
}
