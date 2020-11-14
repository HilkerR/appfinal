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


import com.desarrolloweb.spring.app.dao.ReservacioneRepository;
import com.desarrolloweb.spring.app.entities.Reservacione;
import com.desarrolloweb.spring.app.util.PageRender;

@Controller
@RequestMapping("reservacione")

public class ReservacionesController {
	@Controller
	@RequestMapping("reservacione")

	public class ReservacioneController {
		@Autowired
		private ReservacioneRepository repository;
		
		@GetMapping(value="/listar")
		public String Listar(@RequestParam(name="page", defaultValue="0") int page, Model model)
		{
			Pageable pageRequest = PageRequest.of(page, 4, Sort.by(Order.asc("idReservaciones")));

			Page<Reservacione> reservaciones = repository.findAll(pageRequest);

			PageRender<Reservacione> pageRender = new PageRender<Reservacione>("/reservacione/listar", reservaciones);
					
			model.addAttribute("titulo", "Listado de Reservaciones");
			model.addAttribute("reservaciones", reservaciones);
			model.addAttribute("page", pageRender);
			return "list-reservacione";
		}
		
		@GetMapping(value="/nuevo")
		public String nuevo(Model model)
		{
			model.addAttribute("title", "Nueva reservacion");
			model.addAttribute("reservaciones", new Reservacione());
			return "form-reservacione";
		}
		
		@GetMapping(value = "/editar/{idReservaciones}")
		public String editar(@PathVariable(value="idReservaciones") Long id, Model model)
		{
			if (id <= 0)
			{
				return "redirect:/reservacione/listar";
			}
			model.addAttribute("title", "Editar Reservacion");
			model.addAttribute("reservacione", repository.findById(id).get());
			return "form-reservacione";
		}
		
		@GetMapping(value = "/eliminar/{idReservaciones}")
		public String eliminar(@PathVariable(value="idReservaciones") Long id, Model model)
		{
			if (id > 0)
			{
				repository.delete(repository.findById(id).get());
			}
			return "redirect:/reservacione/listar";
		}
		
		@PostMapping(value = "/nuevo")
		public String guardar(Reservacione reservacione)
		{
			repository.save(reservacione);

			return "redirect:/reservacione/listar";
		}


	}
	
}
