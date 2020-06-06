package com.desarrolloweb.spring.app.controllers;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.desarrolloweb.spring.app.dao.UsuarioRepository;
import com.desarrolloweb.spring.app.util.PageRender;


import com.desarrolloweb.spring.app.entities.Usuario;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping(value="/listar")
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idUsuario")));

		Page<Usuario> usuario = repository.findAll(pageRequest);

		PageRender<Usuario> pageRender = new PageRender<Usuario>("/usuario/listar", usuario);
		
		model.addAttribute("titulo", "Listado de usuario");
		model.addAttribute("listadoUsuario", usuario);
		model.addAttribute("page", pageRender);
		return "list-usuario";
	}

	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nuevo usuario");
		model.addAttribute("usuario", new Usuario());
		return "form-usuario";
	}
	
	@GetMapping(value = "/editar/{idUsuario}")
	public String editar(@PathVariable(value="idUsuario") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/usuario/listar";
		}
		model.addAttribute("title", "Editar usuario");
		model.addAttribute("usuario", repository.findById(id).get());
		return "form-usuario";
	}
	
	@GetMapping(value = "/eliminar/{idUsuario}")
	public String eliminar(@PathVariable(value="idUsuario") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/usuario/listar";
	}

	@PostMapping(value = "/nuevo")
	public String guardar(Usuario usuario)
	{
		repository.save(usuario);

		return "redirect:/usuario/listar";
	}
}

