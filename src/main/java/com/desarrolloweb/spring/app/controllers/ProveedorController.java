package com.desarrolloweb.spring.app.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
import org.springframework.web.bind.annotation.*;
import com.desarrolloweb.spring.app.entities.Proveedor;
import com.desarrolloweb.spring.app.dao.ProveedorRepository;
import com.desarrolloweb.spring.app.util.PageRender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;




@Controller
@RequestMapping("proveedor")
public class ProveedorController {
	@Autowired
	private ProveedorRepository repository;
	
	@GetMapping(value="/listar")
	public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
	{
		Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idProveedor")));

		Page<Proveedor> proveedores = repository.findAll(pageRequest);

		PageRender<Proveedor> pageRender = new PageRender<Proveedor>("/proveedor/listar", proveedores);
		
		model.addAttribute("titulo", "Listado de proveedores");
		model.addAttribute("proveedores", proveedores);
		model.addAttribute("page", pageRender);
		return "list-proveedor";
	}
	
	@GetMapping(value="/nuevo")
	public String nuevo(Model model)
	{
		model.addAttribute("title", "Nuevo proveedor");
		model.addAttribute("proveedor", new Proveedor());
		return "form-proveedor";
	}
	
	@GetMapping(value = "/editar/{idProveedor}")
	public String editar(@PathVariable(value="idProveedor") Long id, Model model)
	{
		if (id <= 0)
		{
			return "redirect:/proveedor/listar";
		}
		model.addAttribute("title", "Editar proveedor");
		model.addAttribute("proveedor", repository.findById(id).get());
		return "form-proveedor";
	}
	
	@GetMapping(value = "/eliminar/{idProveedor}")
	public String eliminar(@PathVariable(value="idProveedor") Long id, Model model)
	{
		if (id > 0)
		{
			repository.delete(repository.findById(id).get());
		}
		return "redirect:/proveedor/listar";
	}
	
	@PostMapping(value = "/nuevo")
	public String guardar(Proveedor proveedor)
	{
		repository.save(proveedor);

		return "redirect:/proveedor/listar";
	}
}
