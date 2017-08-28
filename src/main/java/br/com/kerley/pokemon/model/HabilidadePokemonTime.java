package br.com.kerley.pokemon.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "habilidade_pokemon_time")
public class HabilidadePokemonTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "time_id", nullable = false)
	private Time time;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pokemon_time_id", nullable = false)
	private PokemonTime pokemonTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "habilidade_id", nullable = false)
	private Habilidade habilidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Habilidade getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(Habilidade habilidade) {
		this.habilidade = habilidade;
	}

	public PokemonTime getPokemonTime() {
		return pokemonTime;
	}

	public void setPokemonTime(PokemonTime pokemonTime) {
		this.pokemonTime = pokemonTime;
	}

}
