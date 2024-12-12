package com.senac.receptor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.receptor.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	

}
