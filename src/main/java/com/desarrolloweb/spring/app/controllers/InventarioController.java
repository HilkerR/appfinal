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


import com.desarrolloweb.spring.app.dao.InventarioRepository;

import com.desarrolloweb.spring.app.entities.Inventario;
import com.desarrolloweb.spring.app.util.PageRender;

@Controller
@RequestMapping("inventario")
public class InventarioController {
	@Autowired
	private InventarioRepository repository;

	@GetMapping(value="/listar")
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idInventario")));

		Page<Inventario> inventario = repository.findAll(pageRequest);

		PageRender<Inventario> pageRender = new PageRender<Inventario>("/inventario/listar", inventario);
		
		model.addAttribute("titulo", "Listado de Inventario");
		model.addAttribute("listadoInventario", inventario);
		model.addAttribute("page", pageRender);
		return "list-inventario";
	}

	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nuevo inventario");
		model.addAttribute("inventario", new Inventario());
		return "form-inventario";
	}
	
	@GetMapping(value = "/editar/{idInventario}")
	public String editar(@PathVariable(value="idInventario") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/inventario/listar";
		}
		model.addAttribute("title", "Editar inventario");
		model.addAttribute("inventario", repository.findById(id).get());
		return "form-inventario";
	}
	
	@GetMapping(value = "/eliminar/{idInventario}")
	public String eliminar(@PathVariable(value="idInventario") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/inventario/listar";
	}

	@PostMapping(value = "/nuevo")
	public String guardar(Inventario inventario)
	{
		repository.save(inventario);

		return "redirect:/inventario/listar";
	}
}
