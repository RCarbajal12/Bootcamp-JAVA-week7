package com.romina.productosycategorias.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.romina.productosycategorias.models.CategoryModel;
import com.romina.productosycategorias.models.ProductModel;

public interface CategoryRepo extends CrudRepository<CategoryModel, Long>{

	List<CategoryModel> findByProductsNotContains(ProductModel producto);
	
	List<CategoryModel> findAll();

}
