package com.romina.productosycategorias.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.romina.productosycategorias.models.CategoryModel;
import com.romina.productosycategorias.models.ProductModel;

public interface ProductRepo extends CrudRepository<ProductModel, Long>{
	
	List<ProductModel> findAll();
	
	List<ProductModel> findByCategoriesNotContains(CategoryModel categoria);



}
