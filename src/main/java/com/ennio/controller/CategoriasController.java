package com.ennio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ennio.model.Categoria;
import com.ennio.model.Vacante;
import com.ennio.service.ICategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	//@Qualifier("categoriasServiceJpa")//dice que Servici implementar con la interfaz
	private ICategoriasService ServiceCategoria;
	
	// @GetMapping("/index")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = ServiceCategoria.buscarTodas();
		model.addAttribute("categorias",lista);
		return "categorias/listCategorias";
	}
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page/*@RequestParam("page") Integer numberPage*/) {
		//Pageable page = PageRequest.of(numberPage, 5);
		Page<Categoria> lista = ServiceCategoria.buscarTodas(page);
		model.addAttribute("categorias", lista);
		return "categorias/listCategorias";
	}
	
	// @GetMapping("/create")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Categoria categoria) {
	return "categorias/formCategoria";
	}
	
	// @PostMapping("/save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "vacantes/categoria";
		}
		ServiceCategoria.guardar(categoria);
		attributes.addFlashAttribute("msg","Registro guardado");
		System.out.println("Categoria: " + categoria);
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attribute) {
		System.out.println("Borrar categoria con id: " + idCategoria);
		ServiceCategoria.eliminar(idCategoria);
		attribute.addFlashAttribute("msg", "La Cateoria fue eliminada");
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idCategoria, Model model) {
		Categoria categoria = ServiceCategoria.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria";
	}
	
}
