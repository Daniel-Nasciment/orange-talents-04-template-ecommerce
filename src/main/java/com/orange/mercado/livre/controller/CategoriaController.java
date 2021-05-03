package com.orange.mercado.livre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.mercado.livre.form.CategoriaForm;
import com.orange.mercado.livre.model.Categoria;
import com.orange.mercado.livre.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public String postMethodName(@RequestBody @Valid CategoriaForm categoriaForm) {
		
	 Categoria categoria = categoriaForm.converterCategoria(categoriaRepository);
	 
	 categoriaRepository.save(categoria);
		
		return categoria.toString();
	}

	
}
