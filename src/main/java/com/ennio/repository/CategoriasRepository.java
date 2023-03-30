package com.ennio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ennio.model.Categoria;
//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> { 
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
}
