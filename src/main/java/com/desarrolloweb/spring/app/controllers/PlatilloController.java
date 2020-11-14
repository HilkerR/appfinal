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

import com.desarrolloweb.spring.app.dao.PlatilloRepository;
import com.desarrolloweb.spring.app.entities.Platillo;
import com.desarrolloweb.spring.app.util.PageRender;

@Controller
@RequestMapping("platillo")

public class PlatilloController {
	@Autowired
	private PlatilloRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idPlato")));

		Page<Platillo> platillos = repository.findAll(pageRequest);

		PageRender<Platillo> pageRender = new PageRender<Platillo>("/patillo/listar", platillos);
				
		model.addAttribute("titulo", "Listado de Platillos");
		model.addAttribute("platillos", platillos);
		model.addAttribute("page", pageRender);
		return "list-platillo";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nueva Platillo");
		model.addAttribute("platillo", new Platillo());
		return "form-platillo";
	}
	
	@GetMapping(value = "/editar/{idPlato}")
	public String editar(@PathVariable(value="idPlato") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/platillo/listar";
		}
		model.addAttribute("title", "Editar platillo");
		model.addAttribute("platillo", repository.findById(id).get());
		return "form-platillo";
	}
	
	@GetMapping(value = "/eliminar/{idPlato}")
	public String eliminar(@PathVariable(value="idPlato") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/Platillo/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Platillo platillo)
	{
		repository.save(platillo);

		return "redirect:/platillo/listar";
	}

}

