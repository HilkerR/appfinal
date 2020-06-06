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

import com.desarrolloweb.spring.app.dao.TipoRepository;
import com.desarrolloweb.spring.app.entities.Tipo;

import com.desarrolloweb.spring.app.util.PageRender;

@Controller
@RequestMapping("tipo")
public class TipoController {
	
	@Autowired
	private TipoRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idTipo")));

		Page<Tipo> tipo = repository.findAll(pageRequest);

		PageRender<Tipo> pageRender = new PageRender<Tipo>("/tipo/listar", tipo);
		
		model.addAttribute("titulo", "Listado de Tipos");
		model.addAttribute("listadoTipo", tipo);
		model.addAttribute("page", pageRender);
		return "list-tipo";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nuevo Tipo");
		model.addAttribute("tipo", new Tipo());
		return "form-tipo";
	}
	
	@GetMapping(value = "/editar/{idTipo}")
	public String editar(@PathVariable(value="idTipo") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/tipo/listar";
		}
		model.addAttribute("title", "Editar tipo");
		model.addAttribute("tipo", repository.findById(id).get());
		return "form-tipo";
	}
	
	@GetMapping(value = "/eliminar/{idTipo}")
	public String eliminar(@PathVariable(value="idTipo") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/tipo/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Tipo tipo)
	{
		repository.save(tipo);

		return "redirect:/tipo/listar";
	}
}
