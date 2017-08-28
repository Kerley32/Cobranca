package br.com.kerley.pokemon.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.kerley.pokemon.model.Treinador;
import br.com.kerley.pokemon.repository.filter.TreinadorFilter;
import br.com.kerley.pokemon.service.CadastroTreinadorService;

@Controller
@RequestMapping("/treinadores")
public class TreinadorController {

	@Autowired
	private CadastroTreinadorService cadastroTreinadorService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Treinador>> pesquisar(@ModelAttribute("filtro") TreinadorFilter filtro) {
		List<Treinador> todosTreinadores = cadastroTreinadorService.filtrar(filtro);

		return ResponseEntity.status(HttpStatus.OK).body(todosTreinadores);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Treinador treinador) {

		treinador = cadastroTreinadorService.salvar(treinador);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(treinador.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {

		cadastroTreinadorService.excluir(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Treinador treinador, @PathVariable("id") Long id) {

		treinador.setId(id);

		cadastroTreinadorService.atualizar(treinador);

		return ResponseEntity.noContent().build();
	}

}
