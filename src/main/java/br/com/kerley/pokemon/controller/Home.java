package br.com.kerley.pokemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

	private static final String CADASTRO_VIEW = "Index";

	@RequestMapping("/")
	public String irParaHome() {

		return CADASTRO_VIEW;
	}

	// @RequestMapping("treinador") // O padrao é o metodo GET
	// public String treinadores() {
	//
	// return "CadastroTreinador";
	// }
}
