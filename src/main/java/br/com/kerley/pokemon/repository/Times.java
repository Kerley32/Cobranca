package br.com.kerley.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kerley.pokemon.model.Time;

public interface Times extends JpaRepository<Time, Long> {
	public List<Time> findByNomeContaining(String nome);

}
