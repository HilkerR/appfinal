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
@RequestMapping("mesa")

public class MesaController {
	@Autowired
	private MesaRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idMesa")));

		Page<Mesa> mesas = repository.findAll(pageRequest);
	
		PageRender<Mesa> pageRender = new PageRender<Mesa>("/mesas/listar", mesas);
				
		model.addAttribute("titulo", "Listado de Bebidas");
		model.addAttribute("mesas", mesas);
		model.addAttribute("page", pageRender);
		return "list-mesa";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nueva mesa");
		model.addAttribute("mesa", new Mesa());
		return "form-mesa";
	}
	
	@GetMapping(value = "/editar/{idMesa}")
	public String editar(@PathVariable(value="idMesa") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/mesa/listar";
		}
		model.addAttribute("title", "Editar mesa");
		model.addAttribute("mesa", repository.findById(id).get());
		return "form-mesa";
	}
	
	@GetMapping(value = "/eliminar/{idMesa}")
	public String eliminar(@PathVariable(value="idMesa") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/mesa/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Mesa mesa)
	{
		repository.save(mesa);

		return "redirect:/mesa/listar";
	}

	
	
}
