package br.com.kerley.pokemon.controller;

import java.net.URI;
import java.net.URISyntaxException;
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

import br.com.kerley.pokemon.model.Pokemon;
import br.com.kerley.pokemon.repository.filter.PokemonFilter;
import br.com.kerley.pokemon.service.CadastroPokemonService;

@Controller
@RequestMapping("/pokemons")
public class PokemonController {

	@Autowired
	private CadastroPokemonService cadastroPokemonService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Pokemon>> pesquisar(@ModelAttribute("filtro") PokemonFilter filtro) {
		List<Pokemon> todosPokemons = cadastroPokemonService.filtrar(filtro);

		return ResponseEntity.status(HttpStatus.OK).body(todosPokemons);
	}

	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<Void> salvar(@Valid @RequestBody Pokemon pokemon) {
	//
	// // for (PokemonHabilidade pks : habilidade.getPokemons()) {
	// // System.out.println("Objeto que chegaram : " + pks.toString());
	// // }
	// pokemon = cadastroPokemonService.salvar(pokemon);
	//
	// URI uri =
	// ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pokemon.getId())
	// .toUri();
	//
	// return ResponseEntity.created(uri).build();
	// }

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody List<Pokemon> listPokemons) {
		URI uri = null;
		try {
			uri = new URI("/");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Pokemon pokemon : listPokemons) {

			if (!cadastroPokemonService.existente(pokemon.getNome())) {
				pokemon = cadastroPokemonService.salvar(pokemon);
				uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pokemon.getId())
						.toUri();
			}
		}

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {

		cadastroPokemonService.excluir(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Pokemon pokemon, @PathVariable("id") Long id) {

		pokemon.setId(id);

		cadastroPokemonService.atualizar(pokemon);

		return ResponseEntity.noContent().build();
	}

}
