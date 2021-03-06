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

import com.desarrolloweb.spring.app.dao.BebidaRepository;
import com.desarrolloweb.spring.app.entities.Bebida;
import com.desarrolloweb.spring.app.util.PageRender;

@Controller
@RequestMapping("bebida")

public class BebidaController {
	@Autowired
	private BebidaRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idBebida")));

		Page<Bebida> bebidas = repository.findAll(pageRequest);

		PageRender<Bebida> pageRender = new PageRender<Bebida>("/bebida/listar", bebidas);
				
		model.addAttribute("titulo", "Listado de Bebidas");
		model.addAttribute("bebidas", bebidas);
		model.addAttribute("page", pageRender);
		return "list-bebida";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nueva bebida");
		model.addAttribute("bebida", new Bebida());
		return "form-bebida";
	}
	
	@GetMapping(value = "/editar/{idBebida}")
	public String editar(@PathVariable(value="idBebida") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/bebida/listar";
		}
		model.addAttribute("title", "Editar bebida");
		model.addAttribute("bebida", repository.findById(id).get());
		return "form-bebida";
	}
	
	@GetMapping(value = "/eliminar/{idBebida}")
	public String eliminar(@PathVariable(value="idBebida") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/bebida/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Bebida bebida)
	{
		repository.save(bebida);

		return "redirect:/bebida/listar";
	}

	
	
}
