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
@RequestMapping("factura")
public class FacturaController {
	@Autowired
	private FacturaRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idFactura")));

		Page<Factura> facturas = repository.findAll(pageRequest);

		PageRender<Factura> pageRender = new PageRender<Factura>("/factura/listar", facturas);
		
		model.addAttribute("titulo", "Listado de inventario");
		model.addAttribute("facturas", facturas);
		model.addAttribute("page", pageRender);
		return "list-factura";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nueva factura");
		model.addAttribute("factura", new Factura());
		return "form-factura";
	}
	
	@GetMapping(value = "/editar/{idFactura}")
	public String editar(@PathVariable(value="idFactura") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/factura/listar";
		}
		model.addAttribute("title", "Editar factura");
		model.addAttribute("factura", repository.findById(id).get());
		return "form-factura";
	}
	
	@GetMapping(value = "/eliminar/{idFactura}")
	public String eliminar(@PathVariable(value="idFactura") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/factura/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Factura factura)
	{
		repository.save(factura);

		return "redirect:/factura/listar";
	}
}
