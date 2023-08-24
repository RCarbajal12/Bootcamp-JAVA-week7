package com.romina.empleadosygerentes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.romina.empleadosygerentes.models.Empleado;

@Repository
public interface GerenteRepo extends CrudRepository<Empleado, Long>{
	
	//ENCONTRAR MANAGER POR ID
	List<Empleado> findByManagerId(Long managerId);
	
	//MOSTRAR EL ID DEL EMPLEADO PARA BUSCAR SU GERENTE
	Optional<Empleado> findById(Long EmpleadoId);
	
	

}
