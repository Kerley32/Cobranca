package br.com.kerley.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kerley.pokemon.model.Habilidade;

public interface Habilidades extends JpaRepository<Habilidade, Long> {
	public List<Habilidade> findByNomeContaining(String nome);

	Habilidade findByNome(String nome);

}
