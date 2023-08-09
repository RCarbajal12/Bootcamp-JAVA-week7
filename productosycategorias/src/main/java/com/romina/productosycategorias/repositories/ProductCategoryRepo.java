package com.romina.productosycategorias.repositories;

import org.springframework.data.repository.CrudRepository;

import com.romina.productosycategorias.models.ProductCategoryModel;

public interface ProductCategoryRepo extends CrudRepository<ProductCategoryModel,Long>{

}
