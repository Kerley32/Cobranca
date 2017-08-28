package br.com.kerley.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kerley.pokemon.model.Pokemon;
import br.com.kerley.pokemon.repository.Pokemons;
import br.com.kerley.pokemon.repository.filter.PokemonFilter;
import br.com.kerley.pokemon.service.exception.NaoEncontradoException;

@Service
public class CadastroPokemonService {

	@Autowired
	private Pokemons pokemons;

	public Pokemon salvar(Pokemon treinador) {

			return pokemons.save(treinador);
		
	}

	public void excluir(Long codigo) {
		pokemons.delete(codigo);
	}

	public boolean existente(String nome) {
		Pokemon pokemon = pokemons.findByNome(nome);
		if (pokemon == null) {
			return false;
		}
		return true;
	}

	public void atualizar(Pokemon treinador) {
		verificarExistencia(treinador);
		pokemons.save(treinador);
	}

	private void verificarExistencia(Pokemon pokemon) {
		buscar(pokemon.getId());
	}

	public Pokemon buscar(Long id) {
		Pokemon pokemon = pokemons.findOne(id);
		if (pokemon == null) {
			throw new NaoEncontradoException("Pokemon nao encontrado.");
		}
		return pokemon;
	}

	public List<Pokemon> filtrar(PokemonFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();

		return pokemons.findByNomeContaining(nome);

	}
}
