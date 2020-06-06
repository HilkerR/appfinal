package com.desarrolloweb.spring.app.controllers;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.desarrolloweb.spring.app.dao.RoleRepository;
import com.desarrolloweb.spring.app.util.PageRender;


import com.desarrolloweb.spring.app.entities.Role;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("rol")
public class RoleController {

	@Autowired
	private RoleRepository repository;

	@GetMapping(value="/listar")
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idRol")));

		Page<Role> rol = repository.findAll(pageRequest);

		PageRender<Role> pageRender = new PageRender<Role>("/rol/listar", rol);
		
		model.addAttribute("titulo", "Listado de rol");
		model.addAttribute("listadoRol", rol);
		model.addAttribute("page", pageRender);
		return "list-rol";
	}

	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nuevo rol");
		model.addAttribute("rol", new Role());
		return "form-rol";
	}
	
	@GetMapping(value = "/editar/{idRol}")
	public String editar(@PathVariable(value="idRol") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/rol/listar";
		}
		model.addAttribute("title", "Editar rol");
		model.addAttribute("rol", repository.findById(id).get());
		return "form-rol";
	}
	
	@GetMapping(value = "/eliminar/{idRol}")
	public String eliminar(@PathVariable(value="idRol") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/rol/listar";
	}

	@PostMapping(value = "/nuevo")
	public String guardar(Role rol)
	{
		repository.save(rol);

		return "redirect:/rol/listar";
	}
}

