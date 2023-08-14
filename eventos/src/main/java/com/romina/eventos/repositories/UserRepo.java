package com.romina.eventos.repositories;

import org.springframework.data.repository.CrudRepository;

import com.romina.eventos.models.User;

public interface UserRepo extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	

}
