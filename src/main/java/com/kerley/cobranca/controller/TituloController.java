package com.kerley.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kerley.cobranca.model.StatusTitulo;
import com.kerley.cobranca.model.Titulo;
import com.kerley.cobranca.repository.filter.TituloFilter;
import com.kerley.cobranca.service.CadastroTituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "CadastroTitulo";

	@Autowired
	private CadastroTituloService cadastroTituloService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo()); // Instacia um novo Titulo sempre que
									// carregar
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	// RedirectAttributes attributes: serve para adicionar atributos em um
	// redirecionamento
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {// Se encontrar erro na view
			return CADASTRO_VIEW; // Retorna o nome da View sem a extensçao
		}
		try {

			cadastroTituloService.salvar(titulo);
			// attributes.addFlashAttribute: Atualiza variavel mensagem na View
			// quando for redirecionamento
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");

			return "redirect:/titulos/novo";// redirect : redireciona para uma
											// nova
											// url
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {

		cadastroTituloService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Título excluido com sucesso!");

		return "redirect:/titulos";
	}

	@RequestMapping // O padrao é o metodo GET
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
		List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro);

		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos); // Atualiza lista de titulos da
												// View
		return mv;
	}

	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		return cadastroTituloService.receber(codigo);
	}

	@RequestMapping("{codigo}") // recebe a variavel codigo
	// conversao utiliza o paramento para entidade titulo(po)
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		// por baixo dos panos o spring por causa do jpa repository, realiza um
		// findone em titulos
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}

	// Carrega o atributo para utilizacao
	// na View
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
