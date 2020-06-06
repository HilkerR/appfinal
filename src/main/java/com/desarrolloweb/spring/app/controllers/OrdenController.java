package com.desarrolloweb.spring.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.desarrolloweb.spring.app.dao.*;
import com.desarrolloweb.spring.app.entities.*;
import com.desarrolloweb.spring.app.util.PageRender;

@Controller
@RequestMapping("orden")
public class OrdenController {
	@Autowired
	private OrdenRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idOrden")));

		Page<Orden> ordenes = repository.findAll(pageRequest);

		PageRender<Orden> pageRender = new PageRender<Orden>("/orden/listar", ordenes);
		
		model.addAttribute("titulo", "Listado de ordenes");
		model.addAttribute("ordenes", ordenes);
		model.addAttribute("page", pageRender);
		return "list-orden";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nueva orden");
		model.addAttribute("orden", new Orden());
		return "form-orden";
	}
	
	@GetMapping(value = "/editar/{idOrden}")
	public String editar(@PathVariable(value="idOrden") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/orden/listar";
		}
		model.addAttribute("title", "Editar orden");
		model.addAttribute("orden", repository.findById(id).get());
		return "form-orden";
	}
	
	@GetMapping(value = "/eliminar/{idOrden}")
	public String eliminar(@PathVariable(value="idOrden") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/orden/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Orden orden)
	{
		repository.save(orden);

		return "redirect:/orden/listar";
	}
}