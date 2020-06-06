package com.desarrolloweb.spring.app.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import com.desarrolloweb.spring.app.dao.ClienteRepository;
import com.desarrolloweb.spring.app.util.PageRender;
import com.desarrolloweb.spring.app.entities.Cliente;


@Controller
@RequestMapping("cliente")

public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping(value="/listar")
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idCliente")));

		Page<Cliente> cliente = repository.findAll(pageRequest);

		PageRender<Cliente> pageRender = new PageRender<Cliente>("/cliente/listar", cliente);
		
		model.addAttribute("titulo", "Listado de cliente");
		model.addAttribute("listadoCliente", cliente);
		model.addAttribute("page", pageRender);
		return "list-cliente";
	}

	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nuevo cliente");
		model.addAttribute("cliente", new Cliente());
		return "form-cliente";
	}
	
	@GetMapping(value = "/editar/{idCliente}")
	public String editar(@PathVariable(value="idCliente") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/cliente/listar";
		}
		model.addAttribute("title", "Editar cliente");
		model.addAttribute("cliente", repository.findById(id).get());
		return "form-cliente";
	}
	
	@GetMapping(value = "/eliminar/{idCliente}")
	public String eliminar(@PathVariable(value="idCliente") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/cliente/listar";
	}

	@PostMapping(value = "/nuevo")
	public String guardar(Cliente cliente)
	{
		repository.save(cliente);

		return "redirect:/cliente/listar";
	}
}

