package br.com.kerley.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kerley.pokemon.model.Treinador;

public interface Treinadores extends JpaRepository<Treinador, Long> {
	public List<Treinador> findByNomeContaining(String nome);

}
