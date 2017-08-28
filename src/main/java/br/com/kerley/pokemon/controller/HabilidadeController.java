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

import br.com.kerley.pokemon.model.Habilidade;
import br.com.kerley.pokemon.repository.filter.HabilidadeFilter;
import br.com.kerley.pokemon.service.CadastroHabilidadeService;

@Controller
@RequestMapping("/habilidades")
public class HabilidadeController {

	@Autowired
	private CadastroHabilidadeService cadastroHabilidadeService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Habilidade>> pesquisar(@ModelAttribute("filtro") HabilidadeFilter filtro) {
		List<Habilidade> todosHabilidades = cadastroHabilidadeService.filtrar(filtro);

		return ResponseEntity.status(HttpStatus.OK).body(todosHabilidades);
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> salvar(@Valid @RequestBody Habilidade habilidade) {
//
//		// for (PokemonHabilidade pks : habilidade.getPokemons()) {
//		// System.out.println("Objeto que chegaram : " + pks.toString());
//		// }
//		habilidade = cadastroHabilidadeService.salvar(habilidade);
//
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(habilidade.getId()).toUri();
//
//		return ResponseEntity.created(uri).build();
//	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody List<Habilidade> listHabilidades) {
		URI uri = null;

		for (Habilidade habilidade : listHabilidades) {

			if (!cadastroHabilidadeService.porNome(habilidade.getNome())) {
				habilidade = cadastroHabilidadeService.salvar(habilidade);
				uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(habilidade.getId())
						.toUri();
			}
		}

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {

		cadastroHabilidadeService.excluir(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Habilidade habilidade, @PathVariable("id") Long id) {

		habilidade.setId(id);

		cadastroHabilidadeService.atualizar(habilidade);

		return ResponseEntity.noContent().build();
	}

}
