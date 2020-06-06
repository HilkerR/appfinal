package com.desarrolloweb.spring.app.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.web.bind.annotation.*;
import com.desarrolloweb.spring.app.entities.Umedida;
import com.desarrolloweb.spring.app.dao.UmedidaRepository;
import com.desarrolloweb.spring.app.util.PageRender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;




@Controller
@RequestMapping("umedida")
public class UmedidaController {
	
	@Autowired
	private UmedidaRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idUniad")));

		Page<Umedida> umedidas = repository.findAll(pageRequest);

		PageRender<Umedida> pageRender = new PageRender<Umedida>("/umedida/listar", umedidas);
		
		model.addAttribute("titulo", "Listado de Unidades de Medida");
		model.addAttribute("umedidas", umedidas);
		model.addAttribute("page", pageRender);
		return "list-umedida";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nueva Unidad de Medida");
		model.addAttribute("umedida", new Umedida());
		return "form-umedida";
	}
	
	@GetMapping(value = "/editar/{idUniad}")
	public String editar(@PathVariable(value="idUniad") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/umedida/listar";
		}
		model.addAttribute("title", "Editar Unidad de Medida");
		model.addAttribute("umedida", repository.findById(id).get());
		return "form-umedida";
	}
	
	@GetMapping(value = "/eliminar/{idUniad}")
	public String eliminar(@PathVariable(value="idUniad") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/umedida/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Umedida umedida)
	{
		repository.save(umedida);  

		return "redirect:/umedida/listar";
	}
}
