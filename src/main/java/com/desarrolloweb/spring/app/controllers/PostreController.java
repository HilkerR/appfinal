package com.desarrolloweb.spring.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.desarrolloweb.spring.app.dao.*;
import com.desarrolloweb.spring.app.entities.*;
import com.desarrolloweb.spring.app.util.PageRender;

@Controller
@RequestMapping("postre")

public class PostreController {
	@Autowired
	private PostreRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idPostre")));

		Page<Postre> postres = repository.findAll(pageRequest);

		PageRender<Postre> pageRender = new PageRender<Postre>("/bebida/listar", postres);
				
		model.addAttribute("titulo", "Listado de Bebidas");
		model.addAttribute("postres", postres);
		model.addAttribute("page", pageRender);
		return "list-postre";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nueva postre");
		model.addAttribute("postre", new Postre());
		return "form-postre";
	}
	
	@GetMapping(value = "/editar/{idPostre}")
	public String editar(@PathVariable(value="idPostre") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/postre/listar";
		}
		model.addAttribute("title", "Editar postre");
		model.addAttribute("postre", repository.findById(id).get());
		return "form-postre";
	}
	
	@GetMapping(value = "/eliminar/{idPostre}")
	public String eliminar(@PathVariable(value="idPostre") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/postre/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Postre postre)
	{
		repository.save(postre);

		return "redirect:/postre/listar";
	}

	
	
}
