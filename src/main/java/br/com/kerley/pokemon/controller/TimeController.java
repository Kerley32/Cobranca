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

import br.com.kerley.pokemon.model.Time;
import br.com.kerley.pokemon.repository.filter.TimeFilter;
import br.com.kerley.pokemon.service.CadastroTimeService;

@Controller
@RequestMapping("/times")
public class TimeController {

	@Autowired
	private CadastroTimeService cadastroTimeService;

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Time>> pesquisar(@ModelAttribute("filtro") TimeFilter filtro) {
		List<Time> todosTimes = cadastroTimeService.filtrar(filtro);

		return ResponseEntity.status(HttpStatus.OK).body(todosTimes);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Time time) {

		time = cadastroTimeService.salvar(time);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(time.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {

		cadastroTimeService.excluir(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Time time, @PathVariable("id") Long id) {

		time.setId(id);

		cadastroTimeService.atualizar(time);

		return ResponseEntity.noContent().build();
	}

}
