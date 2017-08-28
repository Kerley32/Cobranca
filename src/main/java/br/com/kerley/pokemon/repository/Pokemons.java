package br.com.kerley.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kerley.pokemon.model.Pokemon;

public interface Pokemons extends JpaRepository<Pokemon, Long> {
	public List<Pokemon> findByNomeContaining(String nome);

	Pokemon findByNome(String nome);

}
